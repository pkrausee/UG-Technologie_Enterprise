package pkrause.proj2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pkrause.proj2.domain.Address;
import pkrause.proj2.repository.AddressRepository;
import pkrause.proj2.result.MultipleResult;
import pkrause.proj2.result.SingleResult;

import java.util.List;
import java.util.UUID;

@Service
public class AddressService implements IService<Address, UUID> {
    private AddressRepository repository;

    @Autowired
    public AddressService(AddressRepository repository) {
        this.repository = repository;
    }

    public SingleResult<Address> save(Address address) {
        if (!address.isValid()) {
            return new SingleResult<>(address, "Not valid.", false);
        }

        this.repository.save(address);

        return new SingleResult<>(address, "Address saved.", true);
    }

    public SingleResult<Address> read(UUID id) {
        Address findResult = this.repository.findById(id).orElse(null);

        if (findResult == null) {
            return new SingleResult<>(null, "Address not found.", false);
        }

        return new SingleResult<>(findResult, "Success.", true);
    }

    public MultipleResult<Address> read() {
        List<Address> findResult = this.repository.findAll();

        if (findResult.size() == 0) {
            return new MultipleResult<>(findResult, "No addresses in database", false);
        }

        return new MultipleResult<>(findResult, "Success", true);
    }

    public SingleResult<Address> update(UUID id, Address address) {
        Address findResult = this.repository.findById(id).orElse(null);

        if (findResult == null) {
            return new SingleResult<>(address, "Address not found.", false);
        }

        if (!address.isValid()) {
            return new SingleResult<>(address, "Not valid.", false);
        }

        address.setId(id);
        this.repository.save(address);

        return new SingleResult<>(address, "Address updated.", true);
    }

    public SingleResult<Address> delete(UUID id) {
        Address findResult = this.repository.findById(id).orElse(null);

        if (findResult == null) {
            return new SingleResult<>(null, "Address not found.", false);
        }

        try {
            this.repository.deleteById(id);
        } catch (RuntimeException ex) {
            return new SingleResult<>(findResult, "Address cannot be deleted", false);
        }

        return new SingleResult<>(findResult, "Address deleted.", true);
    }
}
