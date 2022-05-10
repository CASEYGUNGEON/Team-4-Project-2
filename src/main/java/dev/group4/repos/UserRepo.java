package dev.group4.repos;


import dev.group4.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface UserRepo  extends JpaRepository<User,String> {
    //TODO add custom method signatures
}
