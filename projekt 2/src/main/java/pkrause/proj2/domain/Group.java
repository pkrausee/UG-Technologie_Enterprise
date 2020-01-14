package pkrause.proj2.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pkrause.proj2.common.StringHelper;

import javax.persistence.*;
import java.util.List;

@Entity(name = "lecture_group")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String specialization;
    private Integer maxStudents;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "group")
    private List<Student> students;

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

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public Integer getMaxStudents() {
        return maxStudents;
    }

    public void setMaxStudents(Integer maxStudents) {
        this.maxStudents = maxStudents;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
    //endregion

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", specialization='" + specialization + '\'' +
                ", maxStudents=" + maxStudents +
                ", students=" + (students == null ? 0 : students.size()) +
                '}';
    }

    public boolean isValid() {
        return !StringHelper.isNullOrEmpty(this.name) &&
                !StringHelper.isNullOrEmpty(this.specialization) &&
                this.maxStudents != null && this.maxStudents > 0;
    }
}
