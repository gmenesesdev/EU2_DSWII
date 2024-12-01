package cl.ipss.evu2.controllers;

import cl.ipss.evu2.models.Mesa;
import cl.ipss.evu2.services.MesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/mesas")
public class MesaController {

    @Autowired
    private MesaService mesaService;

    @GetMapping
    public String listarMesas(Model model) {
        model.addAttribute("titulo", "Listado de Mesas");
        model.addAttribute("mesas", mesaService.listarMesas());
        return "mesas-lista";
    }

    @GetMapping("/nueva")
    public String nuevaMesa(Model model) {
        model.addAttribute("titulo", "Crear Nueva Mesa");
        model.addAttribute("mesa", new Mesa());
        return "mesas-crear";
    }

    @PostMapping("/guardar")
    public String guardarMesa(@ModelAttribute Mesa mesa, RedirectAttributes redirectAttributes) {
        String mensaje = (mesa.getId() == null) ? "Mesa creada con éxito" : "Mesa actualizada con éxito";
        mesaService.guardarMesa(mesa);
        redirectAttributes.addFlashAttribute("msg", mensaje);
        return "redirect:/mesas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarMesa(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        mesaService.eliminarMesa(id);
        redirectAttributes.addFlashAttribute("msg", "Mesa eliminada con éxito");
        return "redirect:/mesas";
    }

    @GetMapping("/editar/{id}")
    public String editarMesa(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Mesa mesa = mesaService.obtenerMesa(id);
        if (mesa == null) {
            redirectAttributes.addFlashAttribute("error", "Mesa no encontrada");
            return "redirect:/mesas";
        }
        model.addAttribute("titulo", "Editar Mesa");
        model.addAttribute("mesa", mesa);
        return "mesas-crear";
    }
}
