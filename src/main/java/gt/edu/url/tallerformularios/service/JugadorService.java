package gt.edu.url.tallerformularios.service;

import gt.edu.url.tallerformularios.model.Jugador;
import gt.edu.url.tallerformularios.repository.JugadorRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JugadorService {

    private final JugadorRepository jugadorRepository;

    public JugadorService(JugadorRepository jugadorRepository) {
        this.jugadorRepository = jugadorRepository;
    }

    public void guardar(Jugador jugador) {
        jugadorRepository.save(jugador);
    }

    public List<Jugador> listar() {
        return jugadorRepository.findAll();
    }

    public Jugador buscarPorId(Long id) {
        return jugadorRepository.findById(id)
                .orElseThrow();
    }

    public void eliminarPorId(Long id) {
        jugadorRepository.deleteById(id);
    }
}