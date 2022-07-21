package cl.kibernum.springdemo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import cl.kibernum.springdemo.model.Sucursal;

@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, Long>{
}
