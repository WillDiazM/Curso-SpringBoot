package cl.kibernum.springdemo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import cl.kibernum.springdemo.model.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long>{
}
