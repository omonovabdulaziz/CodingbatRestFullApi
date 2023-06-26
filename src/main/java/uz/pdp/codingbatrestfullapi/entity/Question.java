package uz.pdp.codingbatrestfullapi.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private boolean completed;
    @Column(nullable = false)
    private String realQuestion;
    @Column(nullable = false)
    private String example;
    @ManyToOne
    private Unit unit;
    @Column(nullable = false)
    private String showSolution;
}
