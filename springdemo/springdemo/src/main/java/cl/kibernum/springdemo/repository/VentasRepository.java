package cl.kibernum.springdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import cl.kibernum.springdemo.model.Ventas;

@Repository
public interface VentasRepository extends JpaRepository<Ventas, Long>{
}
