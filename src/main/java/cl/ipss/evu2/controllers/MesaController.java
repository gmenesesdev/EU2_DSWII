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
        model.addAttribute("titulo", "Nueva Mesa");
        model.addAttribute("mesa", new Mesa());
        return "mesas-crear";
    }

    @PostMapping("/guardar")
    public String guardarMesa(Mesa mesa, RedirectAttributes redirectAttributes) {
        // Verificar si la mesa ya existe (es decir, si tiene un ID)
        if (mesa.getId() != null) {
            // Si la mesa tiene ID, es una actualización
            mesaService.guardarMesa(mesa); // Actualiza la mesa
            redirectAttributes.addFlashAttribute("msg", "Mesa actualizada con éxito");
        } else {
            // Si la mesa no tiene ID, es una nueva mesa
            mesaService.guardarMesa(mesa); // Crea una nueva mesa
            redirectAttributes.addFlashAttribute("msg", "Mesa creada con éxito");
        }
        return "redirect:/mesas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarMesa(@PathVariable long id) {
        mesaService.eliminarMesa(id);
        return "redirect:/mesas";
    }

    @GetMapping("/editar/{id}")
    public String editarMesa(@PathVariable long id, Model model) {
        model.addAttribute("mesa", mesaService.obtenerMesa(id));
        return "mesas-crear";
    }
}
