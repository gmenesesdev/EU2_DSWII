package cl.ipss.evu2.controllers;

import cl.ipss.evu2.models.Mesa;
import cl.ipss.evu2.services.MesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/mesas")
public class MesaController {

    @Autowired
    private MesaService mesaService;

    @GetMapping
    public String listarMesas(Model model) {
        List<Mesa> mesas = mesaService.listarMesas();
        model.addAttribute("mesas", mesas);
        return "admin/mesas/listar";
    }

    // @PostMapping("/actualizar")
    // public String actualizarMesa(@RequestParam Long id, @RequestParam int capacidad) {
    //     mesaService.actualizarMesa(id, capacidad);
    //     return "redirect:/admin/mesas";
    // }

    // @GetMapping("/disponibles")
    // public String buscarMesasDisponibles(@RequestParam Date fecha, Model model) {
    //     List<Mesa> mesasDisponibles = mesaService.findMesasDisponibles(fecha);
    //     model.addAttribute("mesasDisponibles", mesasDisponibles);
    //     model.addAttribute("fecha", fecha);
    //     return "admin/mesas/disponibles";
    // }
}
