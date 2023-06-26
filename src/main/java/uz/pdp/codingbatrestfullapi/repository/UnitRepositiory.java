package uz.pdp.codingbatrestfullapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.codingbatrestfullapi.entity.ProgrammingLanguage;
import uz.pdp.codingbatrestfullapi.entity.Unit;

public interface UnitRepositiory extends JpaRepository<Unit, Integer> {
    @Query(nativeQuery = true, value = "select count(*) from unit where main_title=:variable and programming_language_id =:secondvariable")
    Integer existsByProgrammingLanguage(@Param("variable")String variable , @Param("secondvariable") Integer secondvariable);

}
