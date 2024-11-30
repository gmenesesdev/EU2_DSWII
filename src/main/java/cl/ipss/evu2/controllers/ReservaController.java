package cl.ipss.evu2.controllers;

import cl.ipss.evu2.models.Reserva;
import cl.ipss.evu2.services.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public String listarReservas(Model model) {
        List<Reserva> reservas = reservaService.listarReservas();
        model.addAttribute("reservas", reservas);
        return "admin/reservas/listar";
    }

    // @PostMapping("/crear")
    // public String crearReserva(@RequestParam Long mesaId, @RequestParam Long clienteId, @RequestParam Date fecha) {
    //     reservaService.crearReserva(mesaId, clienteId, fecha);
    //     return "redirect:/reservas";
    // }

    // @PostMapping("/cancelar")
    // public String cancelarReserva(@RequestParam Long reservaId) {
    //     reservaService.cancelarReserva(reservaId);
    //     return "redirect:/reservas";
    // }

    // @GetMapping("/cliente/{clienteId}")
    // public String listarReservasCliente(@PathVariable Long clienteId, Model model) {
    //     List<Reserva> reservasCliente = reservaService.listarReservasCliente(clienteId);
    //     model.addAttribute("reservasCliente", reservasCliente);
    //     return "cliente/reservas/listar";
    // }
}
