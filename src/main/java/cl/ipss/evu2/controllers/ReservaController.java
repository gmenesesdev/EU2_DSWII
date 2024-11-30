package cl.ipss.evu2.controllers;

import cl.ipss.evu2.models.Cliente;
import cl.ipss.evu2.models.Mesa;
import cl.ipss.evu2.models.Reserva;
import cl.ipss.evu2.services.ClienteService;
import cl.ipss.evu2.services.MesaService;
import cl.ipss.evu2.services.ReservaService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private MesaService mesaService;

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public String listarReservas(Model model) {
        model.addAttribute("titulo", "Listado de Reservas");
        model.addAttribute("reservas", reservaService.listarReservas());
        return "reservas-lista";
    }

    @GetMapping("/cliente")
    public String buscarReservasPorCliente(@RequestParam(value = "clienteId", required = false) Long clienteId,
            Model model) {
        model.addAttribute("titulo", "Buscar Reservas por Cliente");
        List<Reserva> reservas = new ArrayList<>(); // Inicializamos como lista vacía
        if (clienteId != null) {
            // Buscar reservas por ID de cliente
            reservas = reservaService.listarReservasPorClienteId(clienteId);
            // Verificamos si no se encontraron reservas
            if (reservas.isEmpty()) {
                model.addAttribute("mensaje", "No se encontraron reservas para el cliente con ID: " + clienteId);
            }
        }
        // Agregar reservas al modelo, aunque sean nulas
        model.addAttribute("reservas", reservas);
        return "reservas-cliente";
    }

    @GetMapping("/crear")
    public String crearReserva(@RequestParam(value = "fecha", required = true) String fechaStr, Model model) {
        // Validar que la fecha no esté vacía o nula
        if (fechaStr == null || fechaStr.isEmpty()) {
            model.addAttribute("mensaje", "La fecha es obligatoria.");
            return "reservas-crear"; // Retorna la vista con el mensaje de error
        }

        // Parsear la fecha a tipo LocalDate
        LocalDate fecha = LocalDate.parse(fechaStr);

        // Buscar mesas disponibles para esa fecha
        List<Mesa> mesasDisponibles = mesaService.findMesasDisponibles(fecha);

        // Si no hay mesas disponibles, mostrar mensaje
        if (mesasDisponibles.isEmpty()) {
            model.addAttribute("mensaje", "No hay mesas disponibles para la fecha seleccionada.");
            return "reservas-crear";
        }

        model.addAttribute("mesasDisponibles", mesasDisponibles);
        model.addAttribute("fecha", fechaStr); // Se pasa como String
        return "reservas-crear";
    }

    // Procesar la confirmación de la reserva
    // Método para confirmar la reserva
    @PostMapping("/confirmarReserva")
    public String confirmarReserva(@RequestParam("clienteId") Long clienteId,
            @RequestParam("mesaId") Long mesaId,
            @RequestParam("fecha") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fecha,
            Model model) {

        // Obtener las entidades Cliente y Mesa
        Cliente cliente = clienteService.buscarClientePorId(clienteId).orElse(null); // Debes tener este método en tu
        Mesa mesa = mesaService.obtenerMesa(0);

        if (cliente == null || mesa == null) {
            model.addAttribute("error", "Cliente o Mesa no encontrados.");
            return "error";
        }

        // Validamos que la mesa esté disponible
        List<Mesa> mesasDisponibles = mesaService.findMesasDisponibles(fecha);
        if (!mesasDisponibles.contains(mesa)) {
            model.addAttribute("error", "La mesa seleccionada no está disponible para esa fecha.");
            return "error";
        }

        // Crear la reserva con las entidades completas
        Reserva reserva = new Reserva();
        reserva.setCliente(cliente); // Establecemos el cliente completo
        reserva.setMesa(mesa); // Establecemos la mesa completa
        reserva.setFecha(fecha); // Establecemos la fecha

        // Guardar la reserva
        reservaService.crearReserva(reserva);

        model.addAttribute("mensaje", "Reserva confirmada con éxito.");
        return "reserva-confirmada";
    }

    // @GetMapping("/cliente/{clienteId}")
    // public String listarReservasCliente(@PathVariable Long clienteId, Model
    // model) {
    // List<Reserva> reservasCliente =
    // reservaService.listarReservasCliente(clienteId);
    // model.addAttribute("reservasCliente", reservasCliente);
    // return "cliente/reservas/listar";
    // }

    // @PostMapping("/crear")
    // public String crearReserva(@RequestParam Long mesaId, @RequestParam Long
    // clienteId, @RequestParam Date fecha) {
    // reservaService.crearReserva(mesaId, clienteId, fecha);
    // return "redirect:/reservas";
    // }

    // @PostMapping("/cancelar")
    // public String cancelarReserva(@RequestParam Long reservaId) {
    // reservaService.cancelarReserva(reservaId);
    // return "redirect:/reservas";
    // }

}
