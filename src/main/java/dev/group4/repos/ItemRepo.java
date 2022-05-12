package dev.group4.repos;

import dev.group4.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface ItemRepo  extends JpaRepository<Item,String> {
}
