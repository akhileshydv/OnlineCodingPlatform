package repositories;

import java.util.List;

public interface BaseDao <T, V>{
    void create(T entity);
    void update(T entity);
    T findById(V id);
    List<T> findAll();
    void delete(V id);
}
