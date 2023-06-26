package uz.pdp.codingbatrestfullapi.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.codingbatrestfullapi.entity.userHelp.User;
import uz.pdp.codingbatrestfullapi.payload.Result;
import uz.pdp.codingbatrestfullapi.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    //sign up

    public Result addUser(User user) {
        boolean existUser = userRepository.existsByEmail(user.getEmail());
        if (existUser)
            return new Result("bunday emailli user mavjud", false);

        userRepository.save(user);
        return new Result("saved", true);
    }


    //sign in
    public Result getUsers(User user){
        boolean exist = userRepository.existsByEmailAndPassword(user.getEmail(), user.getPassword());
        if (exist)
            return new Result("cabinetga hush kelibsiz" , true);

        return new Result("parol yoki email notog'ri " , false);

    }


}
