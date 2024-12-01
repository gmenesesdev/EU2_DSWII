package cl.ipss.evu2.controllers;

import cl.ipss.evu2.models.Cliente;
import cl.ipss.evu2.models.Mesa;
import cl.ipss.evu2.models.Reserva;
import cl.ipss.evu2.services.ClienteService;
import cl.ipss.evu2.services.MesaService;
import cl.ipss.evu2.services.ReservaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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
        List<Reserva> reservas = reservaService.listarReservasPorClienteId(clienteId);

        if (reservas.isEmpty()) {
            model.addAttribute("mensaje", "No se encontraron reservas para el cliente con ID: " + clienteId);
        }

        model.addAttribute("reservas", reservas);
        return "reservas-cliente";
    }

    @GetMapping("/crear")
    public String crearReserva(Model model) {
        List<Mesa> mesasDisponibles = mesaService.findMesasDisponibles();

        if (mesasDisponibles.isEmpty()) {
            model.addAttribute("mensaje", "No hay mesas disponibles.");
            model.addAttribute("reserva", new Reserva()); // Incluso en este caso
            return "reservas-crear";
        }

        model.addAttribute("mesasDisponibles", mesasDisponibles);
        model.addAttribute("reserva", new Reserva());
        return "reservas-crear";
    }

    @PostMapping("/confirmar")
    public String confirmarReserva(@ModelAttribute Reserva reserva, Model model) {
        Long clienteId = reserva.getCliente().getId();
        Long mesaId = reserva.getMesa().getId();

        // Obtener las entidades Cliente y Mesa
        Cliente cliente = clienteService.buscarClientePorId(clienteId).orElse(null);
        Mesa mesa = mesaService.obtenerMesa(mesaId);

        if (cliente == null || mesa == null) {
            model.addAttribute("error", "Cliente o Mesa no encontrados.");
            return "error";
        }

        // Validar que la mesa esté disponible
        List<Mesa> mesasDisponibles = mesaService.findMesasDisponibles();
        if (!mesasDisponibles.contains(mesa)) {
            model.addAttribute("error", "La mesa seleccionada no está disponible.");
            return "error";
        }

        // Crear y guardar la reserva
        reserva.setCliente(cliente);
        reserva.setMesa(mesa);
        reservaService.crearReserva(reserva);

        model.addAttribute("mensaje", "Reserva confirmada con éxito.");
        return "reserva-confirmada";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarReserva(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        reservaService.cancelarReserva(id);;
        redirectAttributes.addFlashAttribute("msg", "Reserva cancelada con éxito");
        return "redirect:/reservas/cliente";
    }

}
