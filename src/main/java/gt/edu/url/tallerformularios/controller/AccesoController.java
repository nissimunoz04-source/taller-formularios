package gt.edu.url.tallerformularios.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccesoController {

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }

    @GetMapping("/acceso-denegado")
    public String mostrarAccesoDenegado() {
        return "acceso-denegado";
    }
}