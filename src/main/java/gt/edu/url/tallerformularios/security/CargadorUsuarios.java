package gt.edu.url.tallerformularios.security;

import gt.edu.url.tallerformularios.model.Usuario;
import gt.edu.url.tallerformularios.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CargadorUsuarios implements CommandLineRunner {

    private final UsuarioRepository repositorio;
    private final PasswordEncoder codificador;

    @Value("${app.admin.username}")
    private String usuarioAdmin;

    @Value("${app.admin.password}")
    private String claveAdmin;

    @Value("${app.entrenador.username}")
    private String usuarioEntrenador;

    @Value("${app.entrenador.password}")
    private String claveEntrenador;

    public CargadorUsuarios(
            UsuarioRepository repositorio,
            PasswordEncoder codificador) {

        this.repositorio = repositorio;
        this.codificador = codificador;
    }

    @Override
    public void run(String... args) {

        if (claveAdmin.isBlank() || claveEntrenador.isBlank()) {
            throw new IllegalStateException(
                "Debes configurar ADMIN_PASSWORD y ENTRENADOR_PASSWORD"
            );
        }

        if (repositorio.findByUsername(usuarioAdmin).isEmpty()) {
            Usuario admin = new Usuario(
                usuarioAdmin,
                codificador.encode(claveAdmin),
                "ADMIN"
            );

            repositorio.save(admin);
            System.out.println("Administrador creado correctamente.");
        }

        if (repositorio.findByUsername(usuarioEntrenador).isEmpty()) {
            Usuario entrenador = new Usuario(
                usuarioEntrenador,
                codificador.encode(claveEntrenador),
                "ENTRENADOR"
            );

            repositorio.save(entrenador);
            System.out.println("Entrenador creado correctamente.");
        }
    }
}