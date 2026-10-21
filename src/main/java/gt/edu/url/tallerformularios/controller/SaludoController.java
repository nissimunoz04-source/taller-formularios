package gt.edu.url.tallerformularios.controller;

import gt.edu.url.tallerformularios.model.Jugador;
import gt.edu.url.tallerformularios.service.JugadorService;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SaludoController {

    private final JugadorService jugadorService;

    public SaludoController(JugadorService jugadorService) {
        this.jugadorService = jugadorService;
    }

    @GetMapping("/saludo")
    public String saludo(Model model) {
        model.addAttribute("institucion", "Escuela de Fútbol Los Cerritos");
        return "saludo";
    }

    // Mostrar formulario para registrar un jugador nuevo
    @GetMapping("/jugadores/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("jugador", new Jugador());
        return "formulario";
    }

    // Guardar jugador nuevo o actualizar uno existente
    @PostMapping("/jugadores")
    public String registrarJugador(
            @Valid @ModelAttribute("jugador") Jugador jugador,
            BindingResult resultado) {

        if (resultado.hasErrors()) {
            return "formulario";
        }

        jugadorService.guardar(jugador);
        return "redirect:/jugadores";
    }

    // Mostrar lista de jugadores
    @GetMapping("/jugadores")
    public String listarJugadores(Model model) {
        model.addAttribute("jugadores", jugadorService.listar());
        return "lista";
    }

    // Mostrar detalle de un jugador
    @GetMapping("/jugadores/{id}")
    public String mostrarDetalle(@PathVariable Long id, Model model) {

        Jugador jugador = jugadorService.buscarPorId(id);

        model.addAttribute("jugador", jugador);
        return "detalle";
    }

    // Abrir formulario para editar un jugador
    @GetMapping("/jugadores/{id}/editar")
    public String editarJugador(@PathVariable Long id, Model model) {

        Jugador jugador = jugadorService.buscarPorId(id);

        model.addAttribute("jugador", jugador);
        return "formulario";
    }

    // Eliminar un jugador
    @PostMapping("/jugadores/{id}/eliminar")
    public String eliminarJugador(@PathVariable Long id) {

        jugadorService.eliminarPorId(id);

        return "redirect:/jugadores";
    }
}