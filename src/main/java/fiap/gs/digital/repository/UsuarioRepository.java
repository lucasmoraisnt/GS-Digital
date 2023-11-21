package fiap.gs.digital.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fiap.gs.digital.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String username);

}