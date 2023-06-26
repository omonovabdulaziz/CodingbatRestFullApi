package uz.pdp.codingbatrestfullapi.repository;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.codingbatrestfullapi.entity.ProgrammingLanguage;

public interface ProgramingLanguageRepository extends JpaRepository<ProgrammingLanguage , Integer> {
    boolean existsByName( String name);
}
