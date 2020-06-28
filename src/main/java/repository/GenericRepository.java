package repository;

import java.util.List;

public interface GenericRepository<T, ID> {
    T create(T t);

    T read(ID id);

    T update(ID id, T t);

    void delete(ID id);

    List<T> getAll();
}
