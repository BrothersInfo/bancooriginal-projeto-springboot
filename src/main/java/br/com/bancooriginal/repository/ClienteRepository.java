package br.com.bancooriginal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.bancooriginal.model.Cliente;

/**
 * 
 * @author Cosme Ribeiro da Silva
 *
 */
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
