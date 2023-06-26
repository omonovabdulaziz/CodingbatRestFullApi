package uz.pdp.codingbatrestfullapi.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.codingbatrestfullapi.entity.ProgrammingLanguage;
import uz.pdp.codingbatrestfullapi.payload.Result;
import uz.pdp.codingbatrestfullapi.repository.ProgramingLanguageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProgrammingLanguageService {
    @Autowired
    ProgramingLanguageRepository programingLanguageRepository;


    //create
    public Result addProgrammingLanguages(ProgrammingLanguage programmingLanguage ){

        boolean exist = programingLanguageRepository.existsByName(programmingLanguage.getName());
        if (exist)
            return new Result("bunday nomli til qoshildi" , false);

        programingLanguageRepository.save(programmingLanguage);
        return new Result("saqlandi" , true);
    }
    //read
    public List<ProgrammingLanguage> getProgrammingLanguages(){
        return programingLanguageRepository.findAll();
    }
    //readbyId
    public ProgrammingLanguage getProgrammaingLangueage( Integer id){
        Optional<ProgrammingLanguage> optionalProgrammingLanguage = programingLanguageRepository.findById(id);
        return optionalProgrammingLanguage.orElseGet(ProgrammingLanguage::new);


    }
    //update

    public Result updateProgrammingLanguage( Integer id,  ProgrammingLanguage programmingLanguage) {
        boolean exist = programingLanguageRepository.existsByName(programmingLanguage.getName());
        if (exist)
            return new Result("bunday nomli language bor" , false);

        ProgrammingLanguage programmingLanguage1 = new ProgrammingLanguage(id , programmingLanguage.getName());
        programingLanguageRepository.save(programmingLanguage1);
        return new Result("updated " , true);
    }
    //delete

    public Result deleteProgrammingLanguae(Integer id){
         try {
             Optional<ProgrammingLanguage> optionalProgrammingLanguage = programingLanguageRepository.findById(id);
             if (!optionalProgrammingLanguage.isPresent())
                 return new Result("topilmadi" , false);

             programingLanguageRepository.deleteById(id);
             return new Result("deleted" , true);
         }catch (Exception e){
             e.printStackTrace();
             return new Result("error!!" , false);
         }
    }
}
