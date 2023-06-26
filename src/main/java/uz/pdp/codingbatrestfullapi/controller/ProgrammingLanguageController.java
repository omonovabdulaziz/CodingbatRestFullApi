package uz.pdp.codingbatrestfullapi.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.codingbatrestfullapi.entity.ProgrammingLanguage;
import uz.pdp.codingbatrestfullapi.payload.Result;
import uz.pdp.codingbatrestfullapi.service.ProgrammingLanguageService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/programmingLanguage")
public class ProgrammingLanguageController {
    @Autowired
    ProgrammingLanguageService programmingLanguageService;


    //CREATE
    @PostMapping
    public ResponseEntity<Result> addProgrammingLanguages(@Valid @RequestBody ProgrammingLanguage programmingLanguage) {
        Result result = programmingLanguageService.addProgrammingLanguages(programmingLanguage);
        return ResponseEntity.status(result.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(result);
    }


    //READ
    @GetMapping
    public List<ProgrammingLanguage> getProgrammingLanguages() {
        return programmingLanguageService.getProgrammingLanguages();
    }

    //READBYID
    @GetMapping("/byId/{id}")
    public ProgrammingLanguage getProgrammaingLangueage(@PathVariable Integer id) {
        return programmingLanguageService.getProgrammaingLangueage(id);
    }

    //UPDATE
    @PutMapping("/byId/{id}")
    public ResponseEntity<Result> updateProgrammingLanguage(@PathVariable Integer id, @RequestBody ProgrammingLanguage programmingLanguage) {
        Result result = programmingLanguageService.updateProgrammingLanguage(id, programmingLanguage);
        return ResponseEntity.status(result.isSuccess()? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(result);
    }


    //DELETE
    @DeleteMapping("/byId/{id}")
    public ResponseEntity<Result> deletedProgrammingLanguage(@PathVariable Integer id){
        Result result = programmingLanguageService.deleteProgrammingLanguae(id);
        return ResponseEntity.status(result.isSuccess()? HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(result);
    }



    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }


}
