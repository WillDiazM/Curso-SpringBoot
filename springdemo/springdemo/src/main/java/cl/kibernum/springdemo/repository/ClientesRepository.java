package cl.kibernum.springdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.kibernum.springdemo.model.Clientes;

@Repository
public interface ClientesRepository extends JpaRepository<Clientes, Long>{
}
