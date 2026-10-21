
package gt.edu.url.tallerformularios.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filtros(HttpSecurity http) throws Exception {

        http
            .authorizeHttpRequests(reglas -> reglas

                // Páginas públicas
                .requestMatchers(
                    "/login",
                    "/estilos.css",
                    "/error",
                    "/acceso-denegado"
                ).permitAll()

                // Solo ADMIN puede registrar jugadores
                .requestMatchers(HttpMethod.GET, "/jugadores/nuevo")
                .hasRole("ADMIN")

                // Solo ADMIN puede guardar jugadores
                .requestMatchers(HttpMethod.POST, "/jugadores")
                .hasRole("ADMIN")

                // Solo ADMIN puede editar jugadores
                .requestMatchers(HttpMethod.GET, "/jugadores/*/editar")
                .hasRole("ADMIN")

                // Solo ADMIN puede eliminar jugadores
                .requestMatchers(HttpMethod.POST, "/jugadores/*/eliminar")
                .hasRole("ADMIN")

                // ADMIN y ENTRENADOR pueden consultar jugadores
                .requestMatchers(HttpMethod.GET, "/jugadores", "/jugadores/*")
                .hasAnyRole("ADMIN", "ENTRENADOR")

                // Otras rutas requieren iniciar sesión
                .anyRequest().authenticated()
            )

            .formLogin(login -> login
                .loginPage("/login")
                .defaultSuccessUrl("/jugadores", true)
                .permitAll()
            )

            .logout(logout -> logout
                .logoutSuccessUrl("/login?salio")
                .permitAll()
            )

            .exceptionHandling(excepcion -> excepcion
                .accessDeniedPage("/acceso-denegado")
            );

        return http.build();
    }
}