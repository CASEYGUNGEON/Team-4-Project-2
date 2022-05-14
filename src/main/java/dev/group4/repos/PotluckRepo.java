package dev.group4.repos;

import dev.group4.entities.Potluck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository
public interface PotluckRepo extends JpaRepository<Potluck,String> {
    List<Potluck> findPotluckByVisibility(boolean visibility);
    List<Potluck> findPotluckByCreatorId(String creatorId);
}
