package uz.pdp.codingbatrestfullapi.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String mainTitle;
    @Column(nullable = false)
    private Integer grade;
    @Column(nullable = false)
    private String info;
    private boolean completed = false;
    @ManyToOne
    private ProgrammingLanguage programmingLanguage;
}
