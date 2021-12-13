package br.com.spring.dao;

import br.com.spring.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioDAO extends JpaRepository<Usuario, Long> {

    @Query("SELECT u FROM Usuario u WHERE u.email = :email")
    public Usuario findByEmail(String email);

    @Query("SELECT u FROM Usuario u WHERE u.username = :usuario and u.senha = :senha")
    public Usuario login(String usuario, String senha);
}
