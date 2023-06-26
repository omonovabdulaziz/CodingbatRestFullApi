package uz.pdp.codingbatrestfullapi.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.codingbatrestfullapi.entity.userHelp.User;
import uz.pdp.codingbatrestfullapi.payload.Result;
import uz.pdp.codingbatrestfullapi.service.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    //sign in
    @PostMapping
    public ResponseEntity<Result> addUser( @Valid @RequestBody User user){
        Result result = userService.addUser(user);
        return  ResponseEntity.status(result.isSuccess()? HttpStatus.CREATED: HttpStatus.CONFLICT).body(result);
    }



    //sign up
    @GetMapping
    public ResponseEntity<Result> getUsers(@Valid @RequestBody User user){
        Result result = userService.getUsers(user);
        return ResponseEntity.status(result.isSuccess()? HttpStatus.ACCEPTED: HttpStatus.CONFLICT).body(result);
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
