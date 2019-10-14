package com.systig.systigmaster.clientes.repositorios.interfaces;

import com.systig.systigmaster.clientes.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioDao extends JpaRepository<Usuario, Long> {
    Usuario getByUsernameEquals(String username);
}
