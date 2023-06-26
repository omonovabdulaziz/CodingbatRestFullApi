package uz.pdp.codingbatrestfullapi.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.codingbatrestfullapi.entity.Unit;
import uz.pdp.codingbatrestfullapi.payload.Result;
import uz.pdp.codingbatrestfullapi.payload.UnitDTO;
import uz.pdp.codingbatrestfullapi.service.UnitService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/unit")
public class UnitController {
    @Autowired
    UnitService unitService;

    //CREATE
    @PostMapping
    public ResponseEntity<Result> addUnit(@Valid @RequestBody UnitDTO unitDTO) {
        Result result = unitService.addUnit(unitDTO);
        return ResponseEntity.status(result.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(result);
    }


    //READ
    @GetMapping
    public List<Unit> getAllUnit() {
        return unitService.getAllUnit();
    }

    //READ2
    @GetMapping("/byId/{id}")
    public Unit getUnit(@PathVariable Integer id) {
        return unitService.getUnit(id);
    }


    //UPDATE
    @PutMapping("/byId/{id}")
    public ResponseEntity<Result> updateUnit(@PathVariable Integer id,@Valid @RequestBody UnitDTO unitDTO) {
        Result result = unitService.updateUnit(id, unitDTO);
        return ResponseEntity.status(result.isSuccess()? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(result);
    }


    //DELETE
    @DeleteMapping("/byId/{id}")
    public ResponseEntity<Result> deleteUnit(@PathVariable Integer id){
        Result result = unitService.deleteUnit(id);
        return ResponseEntity.status(result.isSuccess()? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(result);
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
