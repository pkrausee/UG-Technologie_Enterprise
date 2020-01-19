package pkrause.proj2.service;

import pkrause.proj2.result.MultipleResult;
import pkrause.proj2.result.SingleResult;

public interface IService<TEntity, TId> {
    // Create
    SingleResult<TEntity> save(TEntity entity);

    // Read
    SingleResult<TEntity> read(TId id);

    MultipleResult<TEntity> read();

    // Update
    SingleResult<TEntity> update(TId id, TEntity entity);

    // Delete
    SingleResult<TEntity> delete(TId id);
}
