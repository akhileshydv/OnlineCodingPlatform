package repositories;

import entities.Question;

import java.util.*;

public class QuestionDao implements BaseDao<Question, Long>{
    private static Map<Long, Question> questionDb = new HashMap<>();
    private static Long  currentId = 0L;
    @Override
    public void create(Question entity) {
        currentId++;
        entity.setId(currentId);
        questionDb.putIfAbsent(currentId, entity);
    }

    @Override
    public void update(Question entity) {
        validateUser(entity.getId());
        questionDb.put(entity.getId(), entity);
    }

    @Override
    public Question findById(Long id) {
        validateUser(id);
        return questionDb.get(id);
    }

    @Override
    public List<Question> findAll() {
        List<Question> questions = new ArrayList<>();
        for(Map.Entry element: questionDb.entrySet()){
            questions.add((Question) element.getValue());
        }
        return questions;
    }

    @Override
    public void delete(Long id) {
        validateUser(id);
        questionDb.remove(id);
    }

    private void validateUser(Long id){
        Optional.ofNullable(questionDb.get(id)).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
