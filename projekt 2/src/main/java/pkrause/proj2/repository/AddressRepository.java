package pkrause.proj2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pkrause.proj2.domain.Address;

import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {
}
