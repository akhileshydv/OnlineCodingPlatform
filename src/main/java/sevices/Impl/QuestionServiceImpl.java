package sevices.Impl;

import entities.Level;
import entities.Question;
import repositories.QuestionDao;
import sevices.QuestionService;

import java.util.List;
import java.util.stream.Collectors;

public class QuestionServiceImpl implements QuestionService {
    QuestionDao questionDao = new QuestionDao();

    @Override
    public Question createQuestion(String name, String _question, Level difficultyLevel) {
        Question question = new Question(name, _question, difficultyLevel);
        questionDao.create(question);
        return question;
    }

    @Override
    public List<Question> listQuestion(Level difficultyLevel) {
        return questionDao.findAll()
                .stream()
                .filter(question -> difficultyLevel == null || (question.getLevel().equals(difficultyLevel))).collect(Collectors.toList());
    }
}
