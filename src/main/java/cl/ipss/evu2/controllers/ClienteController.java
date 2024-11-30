package cl.ipss.evu2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cl.ipss.evu2.models.Cliente;
import cl.ipss.evu2.services.ClienteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public String listarClientes(Model model) {
        model.addAttribute("titulo", "Listado de Clientes");
        model.addAttribute("clientes", clienteService.listarClientes());
        return "cliente-lista";
    }

    @GetMapping("/nuevo")
    public String nuevoCliente(Model model) {
        model.addAttribute("titulo", "Nuevo Cliente");
        model.addAttribute("cliente", new Cliente());
        return "cliente-crear";
    }
    
    @PostMapping("/guardar")
    public String guardarCliente(Cliente cliente, RedirectAttributes redirectAttributes) {
        clienteService.crearCliente(cliente);
        redirectAttributes.addFlashAttribute("msg", "Cliente creado con éxito");
        return "redirect:/";
    }
    
}
