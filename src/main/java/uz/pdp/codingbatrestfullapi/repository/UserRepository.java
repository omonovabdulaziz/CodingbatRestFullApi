package uz.pdp.codingbatrestfullapi.repository;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.codingbatrestfullapi.entity.userHelp.User;

public interface UserRepository extends JpaRepository<User , Integer> {
    boolean existsByEmail(String email);
    boolean existsByEmailAndPassword(String email,String password);
}
