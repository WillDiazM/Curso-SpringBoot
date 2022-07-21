package cl.kibernum.springdemo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import cl.kibernum.springdemo.model.Empleados;

@Repository
public interface EmpleadosRepository extends JpaRepository<Empleados, Long>{
}
