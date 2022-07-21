package cl.kibernum.springdemo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import cl.kibernum.springdemo.model.Comuna;

@Repository
public interface ComunaRepository extends JpaRepository<Comuna, Long>{
}
