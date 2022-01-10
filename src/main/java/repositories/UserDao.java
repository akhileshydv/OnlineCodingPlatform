package repositories;

import entities.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserDao implements BaseDao<User, String>{
    private static Map<String, User> userDb = new HashMap<>();

    @Override
    public void create(User entity) {
        userDb.putIfAbsent(entity.getName(), entity);
    }

    @Override
    public void update(User entity) {
        validateUser(entity.getName());
        userDb.put(entity.getName(), entity);
    }

    @Override
    public User findById(String name) {
        validateUser(name);
        return userDb.get(name);
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void delete(String name) {
        validateUser(name);
        userDb.remove(name);
    }

    private void validateUser(String name){
        Optional.ofNullable(userDb.get(name)).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
