package pkrause.proj2.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pkrause.proj2.domain.Address;
import pkrause.proj2.result.MultipleResult;
import pkrause.proj2.result.SingleResult;
import pkrause.proj2.service.AddressService;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
public class AddressController {
    private AddressService service;

    @Autowired
    public AddressController(AddressService service) {
        this.service = service;
    }

    @PostMapping("/api/address")
    public ResponseEntity<Address> saveAddress(RequestEntity<Address> address) {
        if (address.getBody() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        SingleResult<Address> saveResult = this.service.save(address.getBody());

        if (saveResult.success()) {
            return new ResponseEntity<>(address.getBody(), HttpStatus.OK);
        }

        return new ResponseEntity<>(address.getBody(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/api/address")
    public ResponseEntity<List<Address>> getAddress() {
        MultipleResult<Address> readResult = this.service.read();

        if (readResult.success()) {
            return new ResponseEntity<>(readResult.getResult(), HttpStatus.OK);
        }

        return new ResponseEntity<>(Collections.emptyList(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/api/address/{id}")
    public ResponseEntity<Address> getAddress(@PathVariable String id) {
        try {
            UUID uuid = UUID.fromString(id);

            System.out.println(uuid);

            SingleResult<Address> readResult = this.service.read(uuid);

            if (readResult.success()) {
                return new ResponseEntity<>(readResult.getResult(), HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/api/address/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable String id, RequestEntity<Address> address) {
        if (address.getBody() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            UUID uuid = UUID.fromString(id);

            SingleResult<Address> updateResult = this.service.update(uuid, address.getBody());

            if (updateResult.success()) {
                return new ResponseEntity<>(updateResult.getResult(), HttpStatus.OK);
            }

            return new ResponseEntity<>(address.getBody(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(address.getBody(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/api/address/{id}")
    public ResponseEntity<Address> deleteAddress(@PathVariable String id) {
        try {
            UUID uuid = UUID.fromString(id);

            SingleResult<Address> deleteResult = this.service.delete(uuid);

            if (deleteResult.success()) {
                return new ResponseEntity<>(deleteResult.getResult(), HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
