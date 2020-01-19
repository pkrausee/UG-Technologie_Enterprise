package pkrause.proj3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pkrause.proj3.domain.Group;
import pkrause.proj3.repository.GroupRepository;

import javax.validation.Valid;
import java.util.List;

@Service
public class GroupService implements IService<Group, Long> {
    private GroupRepository repository;

    @Autowired
    public GroupService(GroupRepository repository) {
        this.repository = repository;
    }

    public Group save(@Valid Group group) {
        return this.repository.save(group);
    }

    public Group read(Long id) {
        return this.repository.findById(id).orElse(null);
    }

    public List<Group> read() {
        return this.repository.findAll();
    }

    public Group update(Long id, @Valid Group group) {
        group.setId(id);
        return this.repository.save(group);
    }

    public void delete(Long id) {
        this.repository.deleteById(id);
    }
}
