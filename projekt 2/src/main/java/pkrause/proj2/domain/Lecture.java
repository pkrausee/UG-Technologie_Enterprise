package pkrause.proj2.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pkrause.proj2.common.StringHelper;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "lecture")
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String range;
    private Integer maxStudents;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "lectures")
    private Set<Student> students;

    //region GET SET
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public Integer getMaxStudents() {
        return maxStudents;
    }

    public void setMaxStudents(Integer maxStudents) {
        this.maxStudents = maxStudents;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
    //endregion

    @Override
    public String toString() {
        return "Lecture{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", range='" + range + '\'' +
                ", maxStudents=" + maxStudents +
                ", students=" + (students == null ? 0 : students.size()) +
                '}';
    }

    public boolean isValid() {
        return !StringHelper.isNullOrEmpty(this.name) &&
                !StringHelper.isNullOrEmpty(this.range) &&
                this.maxStudents != null && this.maxStudents > 0;
    }
}
