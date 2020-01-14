package pl.edu.ug.tent.springmvcdemo.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.ug.tent.springmvcdemo.domain.PotatoSack;
import pl.edu.ug.tent.springmvcdemo.result.MultiResult;
import pl.edu.ug.tent.springmvcdemo.result.SingleResult;
import pl.edu.ug.tent.springmvcdemo.service.PotatoSackManager;

import java.util.Map;
import java.util.UUID;

@RestController
public class PotatoSackController {

    private PotatoSackManager potatoSackManager;

    @Autowired
    public PotatoSackController(PotatoSackManager potatoSackManager) {
        this.potatoSackManager = potatoSackManager;
    }

    @GetMapping("/api/potatoSack")
    public ResponseEntity<MultiResult<PotatoSack>> getPotatoSacks() {
        MultiResult<PotatoSack> getResult = this.potatoSackManager.getAllPotatoSacks();

        return new ResponseEntity<>(getResult, HttpStatus.OK);
    }

    @GetMapping("/api/potatoSack/kind")
    public ResponseEntity<SingleResult<Map<String, Integer>>> getPotatoSacksByKind() {
        SingleResult<Map<String, Integer>> getResult = this.potatoSackManager.getAllPotatoSacksByKind();

        return new ResponseEntity<>(getResult, HttpStatus.OK);
    }

    @GetMapping("/api/potatoSack/kind/{kind}")
    public ResponseEntity<MultiResult<PotatoSack>> getPotatoSacksByKind(@PathVariable String kind) {
        MultiResult<PotatoSack> getResult = this.potatoSackManager.findByKind(kind);

        return new ResponseEntity<>(getResult, HttpStatus.OK);
    }

    @GetMapping("/api/potatoSack/magazine")
    public ResponseEntity<SingleResult<Map<String, Integer>>> getPotatoSacksByMagazine() {
        SingleResult<Map<String, Integer>> getResult = this.potatoSackManager.getAllPotatoSacksByMagazine();

        return new ResponseEntity<>(getResult, HttpStatus.OK);
    }

    @GetMapping("/api/potatoSack/magazine/{magazine}")
    public ResponseEntity<MultiResult<PotatoSack>> getPotatoSacksByMagazine(@PathVariable String magazine) {
        MultiResult<PotatoSack> getResult = this.potatoSackManager.findByMagazine(magazine);

        return new ResponseEntity<>(getResult, HttpStatus.OK);
    }

    @PostMapping("/api/potatoSack")
    public ResponseEntity<SingleResult<PotatoSack>> addPotatoSack(@RequestBody PotatoSack potatoSack) {
        SingleResult<PotatoSack> addResult = this.potatoSackManager.addPotatoSack(potatoSack);

        if (addResult.getSuccess()) {
            return new ResponseEntity<>(addResult, HttpStatus.CREATED);
        }

        return new ResponseEntity<>(addResult, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/api/potatoSack/{id}")
    public ResponseEntity<SingleResult<PotatoSack>> updatePotatoSack(@PathVariable String id, @RequestBody PotatoSack potatoSack) {
        try {
            UUID uuid = UUID.fromString(id);

            SingleResult<PotatoSack> updateResult = this.potatoSackManager.update(uuid, potatoSack);

            if (updateResult.getSuccess()) {
                return new ResponseEntity<>(updateResult, HttpStatus.OK);
            }

            return new ResponseEntity<>(updateResult, HttpStatus.BAD_REQUEST);
        } catch (IllegalArgumentException exception) {
            return new ResponseEntity<>(
                    new SingleResult<>("Cannot parse given UUID", false, null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/api/potatoSack/{id}")
    public ResponseEntity<SingleResult<PotatoSack>> deletePotatoSack(@PathVariable String id) {
        try {
            UUID uuid = UUID.fromString(id);

            SingleResult<PotatoSack> deleteResult = this.potatoSackManager.remove(uuid);

            if (deleteResult.getSuccess()) {
                return new ResponseEntity<>(deleteResult, HttpStatus.OK);
            }

            return new ResponseEntity<>(deleteResult, HttpStatus.BAD_REQUEST);
        } catch (IllegalArgumentException exception) {
            return new ResponseEntity<>(
                    new SingleResult<>("Cannot parse given UUID", false, null),
                    HttpStatus.BAD_REQUEST);
        }
    }
}
