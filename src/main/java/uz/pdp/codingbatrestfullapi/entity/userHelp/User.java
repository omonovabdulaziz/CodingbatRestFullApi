package uz.pdp.codingbatrestfullapi.entity.userHelp;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "userlar")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    @NotNull(message = "bosh bolmasin")
    private String email;
    @Column(nullable = false)
    @NotNull(message = "bosh bolmasin")

    private String password;
}
