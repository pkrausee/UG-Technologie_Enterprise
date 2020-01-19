package pkrause.proj3.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IReactiveService<TEntity, TId> {
    // Create
    Mono<TEntity> save(TEntity entity);

    // Read
    Mono<TEntity> read(TId id);

    Flux<TEntity> read();

    // Update
    Mono<TEntity> update(TId id, TEntity entity);

    // Delete
    Mono<Void> delete(TId id);
}
