package uz.pdp.codingbatrestfullapi.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.codingbatrestfullapi.entity.Question;
import uz.pdp.codingbatrestfullapi.payload.QuestionDTO;
import uz.pdp.codingbatrestfullapi.payload.Result;
import uz.pdp.codingbatrestfullapi.service.QuestionService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    QuestionService questionService;


    //CREATE
    @PostMapping
    public ResponseEntity<Result> addQuestion(@Valid @RequestBody QuestionDTO questionDTO) {
        Result result = questionService.addQuestion(questionDTO);
        return ResponseEntity.status(result.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(result);
    }


    //READ
    @GetMapping
    public List<Question> getQuestions() {
        return questionService.getQuestions();
    }

    //read2
    @GetMapping("/byId/{id}")
    public Question getQuestion(@PathVariable Integer id) {
        return questionService.getQuestion(id);
    }


    //UPDATE
    @PutMapping("/byId/{id}")
    public ResponseEntity<Result> updateQuestion(@PathVariable Integer id, @Valid @RequestBody QuestionDTO questionDTO) {
        Result result = questionService.updateQuestion(id, questionDTO);
        return ResponseEntity.status(result.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(result);
    }


    //DELETE
    @DeleteMapping("/byId/{id}")
    public ResponseEntity<Result> deleteQuestion(@PathVariable  Integer id){
        Result result = questionService.deleteQuestion(id);
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
