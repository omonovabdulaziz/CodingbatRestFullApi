package uz.pdp.codingbatrestfullapi.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.codingbatrestfullapi.entity.ProgrammingLanguage;
import uz.pdp.codingbatrestfullapi.entity.Unit;
import uz.pdp.codingbatrestfullapi.payload.Result;
import uz.pdp.codingbatrestfullapi.payload.UnitDTO;
import uz.pdp.codingbatrestfullapi.repository.ProgramingLanguageRepository;
import uz.pdp.codingbatrestfullapi.repository.UnitRepositiory;

import java.util.List;
import java.util.Optional;

@Service
public class UnitService {
    @Autowired
    UnitRepositiory unitRepositiory;
    @Autowired
    ProgramingLanguageRepository programingLanguageRepository;


    //create
    public Result addUnit(UnitDTO unitDTO) {
        Optional<ProgrammingLanguage> optionalProgrammingLanguage = programingLanguageRepository.findById(unitDTO.getProgrammingLanguageId());
        if (!optionalProgrammingLanguage.isPresent())
            return new Result("bunday id li programma tili topilmadi", false);

        Integer integer = unitRepositiory.existsByProgrammingLanguage(unitDTO.getMainTitle(), unitDTO.getProgrammingLanguageId());
        if (integer > 0)
            return new Result("bunday unit ushbu dasturlash tilida mavjud", false);
        Unit unit = new Unit();
        unit.setCompleted(unitDTO.isCompleted());
        unit.setInfo(unitDTO.getInfo());
        unit.setGrade(unitDTO.getGrade());
        unit.setMainTitle(unitDTO.getMainTitle());
        unit.setProgrammingLanguage(optionalProgrammingLanguage.get());
        unitRepositiory.save(unit);
        return new Result("saved", true);
    }

    //read
    public List<Unit> getAllUnit() {
        return unitRepositiory.findAll();
    }


    //read2
    public Unit getUnit(Integer id){
        Optional<Unit> optionalUnit = unitRepositiory.findById(id);
        if (!optionalUnit.isPresent())
            return new Unit();

        return optionalUnit.get();
    }


    //update
    public Result updateUnit(Integer id  ,  UnitDTO unitDTO ){
        Optional<ProgrammingLanguage> optionalProgrammingLanguage = programingLanguageRepository.findById(unitDTO.getProgrammingLanguageId());
        Integer integer = unitRepositiory.existsByProgrammingLanguage(unitDTO.getMainTitle(), unitDTO.getProgrammingLanguageId());
        if (integer > 0 )
            return new Result("bunday unit ushbu programming languageda mavjud" , false);
        Unit unit = new Unit();
        unit.setId(id);
        unit.setCompleted(unitDTO.isCompleted());
        unit.setInfo(unitDTO.getInfo());
        unit.setGrade(unitDTO.getGrade());
        unit.setMainTitle(unitDTO.getMainTitle());
        unit.setProgrammingLanguage(optionalProgrammingLanguage.get());
        unitRepositiory.save(unit);
        return new Result("updated"  ,true);
    }



    //delete
    public Result deleteUnit(Integer id){
        try {
            unitRepositiory.deleteById(id);
            return new Result("deleted" , true);
        }catch (Exception e){
            return new Result("errror!!" , false);
        }
    }

}
