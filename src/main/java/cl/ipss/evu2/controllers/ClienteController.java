package cl.ipss.evu2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cl.ipss.evu2.models.Cliente;
import cl.ipss.evu2.services.ClienteService;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/nuevo")
    public String guardarCliente(Cliente cliente) {
        clienteService.crearCliente(cliente);
        return "redirect:/clientes";
    }
    
}
