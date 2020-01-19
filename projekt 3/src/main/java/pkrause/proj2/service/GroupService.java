package pkrause.proj2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pkrause.proj2.domain.Group;
import pkrause.proj2.repository.GroupRepository;
import pkrause.proj2.result.MultipleResult;
import pkrause.proj2.result.SingleResult;

import java.util.List;

@Service
public class GroupService implements IService<Group, Long> {
    private GroupRepository repository;

    @Autowired
    public GroupService(GroupRepository repository) {
        this.repository = repository;
    }

    public SingleResult<Group> save(Group group) {
        if (!group.isValid()) {
            return new SingleResult<>(group, "Not valid.", false);
        }

        this.repository.save(group);

        return new SingleResult<>(group, "Group saved.", true);
    }

    public SingleResult<Group> read(Long id) {
        Group findResult = this.repository.findById(id).orElse(null);

        if (findResult == null) {
            return new SingleResult<>(null, "Group not found.", false);
        }

        return new SingleResult<>(findResult, "Success.", true);
    }

    public MultipleResult<Group> read() {
        List<Group> findResult = this.repository.findAll();

        if (findResult.size() == 0) {
            return new MultipleResult<>(findResult, "No groups in database", false);
        }

        return new MultipleResult<>(findResult, "Success", true);
    }

    public SingleResult<Group> update(Long id, Group group) {
        Group findResult = this.repository.findById(id).orElse(null);

        if (findResult == null) {
            return new SingleResult<>(group, "Group not found.", false);
        }

        if (!group.isValid()) {
            return new SingleResult<>(group, "Not valid.", false);
        }

        group.setId(id);
        this.repository.save(group);

        return new SingleResult<>(group, "Group updated.", true);
    }

    public SingleResult<Group> delete(Long id) {
        Group findResult = this.repository.findById(id).orElse(null);

        if (findResult == null) {
            return new SingleResult<>(null, "Group not found.", false);
        }

        try {
            this.repository.deleteById(id);
        } catch (RuntimeException ex) {
            return new SingleResult<>(findResult, "Group cannot be deleted", false);
        }

        return new SingleResult<>(findResult, "Group deleted.", true);
    }
}
