package cl.kibernum.springdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import cl.kibernum.springdemo.model.DetalleVentas;

@Repository
public interface DetalleVentasRepository extends JpaRepository<DetalleVentas, Long>{
}
