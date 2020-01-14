package pkrause.proj2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pkrause.proj2.domain.Lecture;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long> {
}
