package dev.group4.repos;

import dev.group4.entities.Potluck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface PotluckRepo extends JpaRepository<Potluck,String> {
}
