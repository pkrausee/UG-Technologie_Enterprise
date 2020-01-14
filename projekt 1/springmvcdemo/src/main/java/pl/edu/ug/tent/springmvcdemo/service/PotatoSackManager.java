package pl.edu.ug.tent.springmvcdemo.service;

import pl.edu.ug.tent.springmvcdemo.domain.PotatoSack;
import pl.edu.ug.tent.springmvcdemo.result.MultiResult;
import pl.edu.ug.tent.springmvcdemo.result.SingleResult;

import java.util.Map;
import java.util.UUID;

public interface PotatoSackManager {

    SingleResult<PotatoSack> addPotatoSack(PotatoSack potatoSack);

    SingleResult<PotatoSack> update(UUID id, PotatoSack potatoSack);

    SingleResult<PotatoSack> remove(UUID id);

    SingleResult<PotatoSack> findById(UUID id);

    MultiResult<PotatoSack> getAllPotatoSacks();

    SingleResult<Map<String, Integer>> getAllPotatoSacksByMagazine();

    SingleResult<Map<String, Integer>> getAllPotatoSacksByKind();

    MultiResult<PotatoSack> findByKind(String kind);

    MultiResult<PotatoSack> findByMagazine(String kind);

}
