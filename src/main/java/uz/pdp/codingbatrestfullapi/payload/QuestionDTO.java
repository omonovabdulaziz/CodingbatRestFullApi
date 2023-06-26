package uz.pdp.codingbatrestfullapi.payload;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuestionDTO {
    @NotNull(message = "bosh bolmasin")
    private String name;
    private boolean completed = false;
    @NotNull(message = "bosh bolmasin")
    private String realQuestion;
    @NotNull(message = "bosh bolmasin")
    private String example;
    @NotNull(message = "bosh bolmasin")
    private Integer unitId;
    @NotNull(message = "bosh bolmasin")
    private String showSolution;
}
