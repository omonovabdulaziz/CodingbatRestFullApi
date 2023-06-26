package uz.pdp.codingbatrestfullapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.codingbatrestfullapi.entity.Question;

public interface QuestionRepository extends JpaRepository<Question , Integer> {
}
