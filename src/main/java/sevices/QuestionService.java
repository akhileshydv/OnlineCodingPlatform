package sevices;

import entities.Level;
import entities.Question;

import java.util.List;

public interface QuestionService {
    public Question createQuestion(String name, String question, Level difficultyLevel);
    public List<Question> listQuestion(Level difficultyLevel);

}
