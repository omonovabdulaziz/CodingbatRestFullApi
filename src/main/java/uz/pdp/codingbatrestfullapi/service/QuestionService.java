package uz.pdp.codingbatrestfullapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.codingbatrestfullapi.entity.Question;
import uz.pdp.codingbatrestfullapi.entity.Unit;
import uz.pdp.codingbatrestfullapi.payload.QuestionDTO;
import uz.pdp.codingbatrestfullapi.payload.Result;
import uz.pdp.codingbatrestfullapi.repository.QuestionRepository;
import uz.pdp.codingbatrestfullapi.repository.UnitRepositiory;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    UnitRepositiory unitRepositiory;

    //create
    public Result addQuestion(QuestionDTO questionDTO) {
        Optional<Unit> optionalUnit = unitRepositiory.findById(questionDTO.getUnitId());
        if (!optionalUnit.isPresent())
            return new Result("bunday idli unit topilmadi", false);

        Question question = new Question();
        question.setRealQuestion(questionDTO.getRealQuestion());
        question.setName(questionDTO.getName());
        question.setCompleted(questionDTO.isCompleted());
        question.setUnit(optionalUnit.get());
        question.setShowSolution(questionDTO.getShowSolution());
        question.setExample(questionDTO.getExample());
        questionRepository.save(question);
        return new Result("saved", true);
    }

    //read
    public List<Question> getQuestions() {
        return questionRepository.findAll();
    }


    //read2
    public Question getQuestion(Integer id) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);
        if (!optionalQuestion.isPresent())
            return new Question();

        return optionalQuestion.get();
    }

    //update
    public Result updateQuestion(Integer id, QuestionDTO questionDTO) {
        Optional<Unit> optionalUnit = unitRepositiory.findById(questionDTO.getUnitId());
        Question question = new Question();
        question.setRealQuestion(questionDTO.getRealQuestion());
        question.setName(questionDTO.getName());
        question.setId(id);
        question.setUnit(optionalUnit.get());
        question.setCompleted(questionDTO.isCompleted());
        question.setShowSolution(questionDTO.getShowSolution());
        question.setExample(questionDTO.getExample());
        questionRepository.save(question);
        return new Result("updated", true);
    }


    //delete
    public Result deleteQuestion(Integer id) {
        try {
            Optional<Question> optionalQuestion = questionRepository.findById(id);
            if (!optionalQuestion.isPresent())
                return new Result("bunday id li question topilmadi", false);
            questionRepository.deleteById(id);
            return new Result("deleted", true);
        } catch (Exception e) {
            return new Result("error!!", false);
        }
    }
}
