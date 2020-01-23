package pkrause.proj3.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Document(collection = "groups")
public class MongoGroup {
    @Id
    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Specialization is mandatory")
    private String specialization;

    @NotNull
    @Min(value = 1, message = "Minimum 1 student is required")
    private Integer maxStudents;

    @JsonIgnore
    private List<MongoStudent> students;

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

    public List<MongoStudent> getStudents() {
        return students;
    }

    public void setStudents(List<MongoStudent> students) {
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
}
