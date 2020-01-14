package pl.edu.ug.tent.springmvcdemo.service;

import org.springframework.stereotype.Service;
import pl.edu.ug.tent.springmvcdemo.domain.PotatoSack;
import pl.edu.ug.tent.springmvcdemo.result.MultiResult;
import pl.edu.ug.tent.springmvcdemo.result.SingleResult;
import pl.edu.ug.tent.springmvcdemo.validator.PotatoSackValidator;

import java.util.*;

@Service
public class PotatoSackInMemoryService implements PotatoSackManager {
    private static PotatoSackValidator validator = new PotatoSackValidator();
    private static List<PotatoSack> potatoSacks = new ArrayList<>();

    public SingleResult<PotatoSack> addPotatoSack(PotatoSack potatoSack) {
        SingleResult<PotatoSack> validationResult = validator.validate(potatoSack);
        if (!validationResult.getSuccess()) {
            return new SingleResult<>(
                    "Cannot validate given Sack: " + validationResult.getMessage(),
                    false,
                    potatoSack
            );
        }

        if (!(potatoSack.getId() == null) && potatoSacks.contains(potatoSack)) {
            return new SingleResult<>(
                    "Given id is already used.",
                    false,
                    potatoSack
            );
        } else {
            potatoSack.setId(UUID.randomUUID());
        }
        potatoSacks.add(potatoSack);

        return new SingleResult<>(
                "Potato sack added.",
                true,
                potatoSack
        );
    }

    public SingleResult<PotatoSack> update(UUID id, PotatoSack potatoSack) {
        SingleResult<PotatoSack> findResult = findById(id);

        if (findResult.getSuccess()) {
            SingleResult<PotatoSack> validationResult = validator.validate(potatoSack);
            if (!validationResult.getSuccess()) {
                return new SingleResult<>(
                        "Cannot validate given sack: " + validationResult.getMessage(),
                        false,
                        potatoSack
                );
            }

            if(potatoSack.getId() == null) {
                potatoSack.setId(id);
            }

            int index = findIndex(potatoSack);

            if(index >= 0) {
                potatoSacks.set(index, potatoSack);
            } else {
                return new SingleResult<>(
                        "Cannot find given sack.",
                        false,
                        potatoSack
                );
            }

            return new SingleResult<>(
                    "Potato sack updated.",
                    true,
                    potatoSack
            );
        }

        return findResult;
    }

    public SingleResult<PotatoSack> remove(UUID id) {
        SingleResult<PotatoSack> findResult = findById(id);

        if (findResult.getSuccess()) {
            potatoSacks.remove(findResult.getResult());

            return new SingleResult<>(
                    "Potato sack deleted.",
                    true,
                    findResult.getResult()
            );
        }

        return findResult;
    }

    public SingleResult<PotatoSack> findById(UUID id) {
        PotatoSack result = null;

        for (PotatoSack potatoSack : potatoSacks) {
            if (potatoSack.getId().equals(id)) {
                result = potatoSack;
            }
        }

        if (result == null) {
            return new SingleResult<>(
                    "Sack with given id does not exist.",
                    false,
                    null
            );
        }

        return new SingleResult<>(
                "Potato sack found.",
                true,
                result
        );
    }

    public MultiResult<PotatoSack> getAllPotatoSacks() {
        if (potatoSacks.isEmpty()) {
            return new MultiResult<>(
                    "There are no potato sacks.",
                    false,
                    potatoSacks
            );
        }

        return new MultiResult<>(
                "Success.",
                true,
                potatoSacks
        );
    }

    public SingleResult<Map<String, Integer>> getAllPotatoSacksByMagazine() {
        Map<String, Integer> results = new HashMap<>();

        for(PotatoSack ps : potatoSacks) {
            if(results.containsKey(ps.getMagazine())) {
                results.put(ps.getMagazine(), results.get(ps.getMagazine()) + 1);
            } else {
                results.put(ps.getMagazine(), 1);
            }
        }

        if (potatoSacks.isEmpty()) {
            return new SingleResult<>(
                    "There are no potato sacks.",
                    false,
                    results
            );
        }

        return new SingleResult<>(
                "Success.",
                true,
                results
        );
    }

    public SingleResult<Map<String, Integer>> getAllPotatoSacksByKind() {
        Map<String, Integer> results = new HashMap<>();

        for(PotatoSack ps : potatoSacks) {
            if(results.containsKey(ps.getKind())) {
                results.put(ps.getKind(), results.get(ps.getKind()) + 1);
            } else {
                results.put(ps.getKind(), 1);
            }
        }

        if (potatoSacks.isEmpty()) {
            return new SingleResult<>(
                    "There are no potato sacks.",
                    false,
                    results
            );
        }

        return new SingleResult<>(
                "Success.",
                true,
                results
        );
    }

    public MultiResult<PotatoSack> findByKind(String kind) {
        List<PotatoSack> results = new ArrayList<>();
        for (PotatoSack potatoSack : potatoSacks) {
            if (potatoSack.getKind().equals(kind)) {
                results.add(potatoSack);
            }
        }

        if (results.isEmpty()) {
            return new MultiResult<>(
                    "There are no sacks with given kind.",
                    false,
                    null
            );
        }

        return new MultiResult<>(
                "Potato sacks found.",
                true,
                results
        );
    }

    public MultiResult<PotatoSack> findByMagazine(String magazine) {
        List<PotatoSack> results = new ArrayList<>();
        for (PotatoSack potatoSack : potatoSacks) {
            if (potatoSack.getMagazine().equals(magazine)) {
                results.add(potatoSack);
            }
        }

        if (results.isEmpty()) {
            return new MultiResult<>(
                    "There are no sacks in given magazine.",
                    false,
                    null
            );
        }

        return new MultiResult<>(
                "Potato sacks found.",
                true,
                results
        );
    }

    private int findIndex (PotatoSack potatoSack) {
        for(int i = 0; i < potatoSacks.size(); i++) {
            if(potatoSacks.get(i).getId().equals(potatoSack.getId())) {
                return i;
            }
        }

        return -1;
    }
}
