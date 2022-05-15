package dev.group4.repos;

import dev.group4.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository
public interface ItemRepo  extends JpaRepository<Item,String> {
    Item findItemByDescription(String description);
    List<Item> findItemByPotluckId(String id);
}
