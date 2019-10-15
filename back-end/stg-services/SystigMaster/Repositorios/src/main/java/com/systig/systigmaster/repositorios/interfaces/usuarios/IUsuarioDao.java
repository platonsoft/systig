package com.systig.systigmaster.repositorios.interfaces.usuarios;

import com.systig.systigmaster.repositorios.modelos.usuarios.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioDao extends JpaRepository<Usuario, Long> {
    Usuario getByUsernameEquals(String username);
}
