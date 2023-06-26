package uz.pdp.codingbatrestfullapi.payload;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result {
private String message;
private boolean success;
}
