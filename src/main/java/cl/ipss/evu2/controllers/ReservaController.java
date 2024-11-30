package cl.ipss.evu2.controllers;

import cl.ipss.evu2.models.Reserva;
import cl.ipss.evu2.services.ReservaService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public String listarReservas(Model model) {
        model.addAttribute("titulo", "Listado de Reservas");
        model.addAttribute("reservas", reservaService.listarReservas());
        return "reservas-lista";
    }

    @GetMapping("/cliente")
    public String buscarReservasPorCliente(@RequestParam(value = "clienteId", required = false) Long clienteId, Model model) {
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
