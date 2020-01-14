package pkrause.proj2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pkrause.proj2.domain.Lecture;
import pkrause.proj2.repository.LectureRepository;
import pkrause.proj2.result.MultipleResult;
import pkrause.proj2.result.SingleResult;

import java.util.List;

@Service
public class LectureService implements IService<Lecture, Long> {
    private LectureRepository repository;

    @Autowired
    public LectureService(LectureRepository repository) {
        this.repository = repository;
    }

    public SingleResult<Lecture> save(Lecture lecture) {
        if (!lecture.isValid()) {
            return new SingleResult<>(lecture, "Not valid.", false);
        }

        this.repository.save(lecture);

        return new SingleResult<>(lecture, "Lecture saved.", true);
    }

    public SingleResult<Lecture> read(Long id) {
        Lecture findResult = this.repository.findById(id).orElse(null);

        if (findResult == null) {
            return new SingleResult<>(null, "Lecture not found.", false);
        }

        return new SingleResult<>(findResult, "Success.", true);
    }

    public MultipleResult<Lecture> read() {
        List<Lecture> findResult = this.repository.findAll();

        if (findResult.size() == 0) {
            return new MultipleResult<>(findResult, "No lectures in database", false);
        }

        return new MultipleResult<>(findResult, "Success", true);
    }

    public SingleResult<Lecture> update(Long id, Lecture lecture) {
        Lecture findResult = this.repository.findById(id).orElse(null);

        if (findResult == null) {
            return new SingleResult<>(null, "Lecture not found.", false);
        }

        if (!lecture.isValid()) {
            return new SingleResult<>(lecture, "Not valid.", false);
        }

        lecture.setId(id);
        this.repository.save(lecture);

        return new SingleResult<>(lecture, "Lecture updated.", true);
    }

    public SingleResult<Lecture> delete(Long id) {
        Lecture findResult = this.repository.findById(id).orElse(null);

        if (findResult == null) {
            return new SingleResult<>(null, "Lecture not found.", false);
        }

        try {
            this.repository.deleteById(id);
        } catch (RuntimeException ex) {
            return new SingleResult<>(findResult, "Lecture cannot be deleted", false);
        }

        return new SingleResult<>(findResult, "Lecture deleted.", true);
    }
}
