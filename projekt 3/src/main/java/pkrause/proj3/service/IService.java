package pkrause.proj3.service;

import java.util.List;

public interface IService<TEntity, TId> {
    // Create
    TEntity save(TEntity entity);

    // Read
    TEntity read(TId id);

    List<TEntity> read();

    // Update
    TEntity update(TId id, TEntity entity);

    // Delete
    void delete(TId id);
}
