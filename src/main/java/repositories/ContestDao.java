package repositories;

import entities.Contest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ContestDao implements BaseDao<Contest, Long>{
    private static Map<Long, Contest> contestDb = new HashMap<>();
    private static Long  currentId = 0L;
    @Override
    public void create(Contest entity) {
        currentId++;
        entity.setId(currentId);
        contestDb.putIfAbsent(currentId, entity);
    }

    @Override
    public void update(Contest entity) {
        validateUser(entity.getId());
        contestDb.put(entity.getId(), entity);
    }

    @Override
    public Contest findById(Long id) {
        validateUser(id);
        return contestDb.get(id);
    }

    @Override
    public List<Contest> findAll() {
        return null;
    }

    @Override
    public void delete(Long id) {
        validateUser(id);
        contestDb.remove(id);
    }

    private void validateUser(Long id){
        Optional.ofNullable(contestDb.get(id)).orElseThrow(() -> new RuntimeException("Contest not found"));
    }
}
