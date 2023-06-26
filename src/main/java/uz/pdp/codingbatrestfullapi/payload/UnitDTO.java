package uz.pdp.codingbatrestfullapi.payload;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UnitDTO {
    @NotNull(message = "bosh bolmasin")
    private String mainTitle;
    @NotNull(message = "bosh bolmasin")
    private Integer grade;
    @NotNull(message = "bosh bolmasin")
    private String info;
    private boolean  completed = false;
    @NotNull(message = "bosh bolmasin")
    private Integer programmingLanguageId;


}
