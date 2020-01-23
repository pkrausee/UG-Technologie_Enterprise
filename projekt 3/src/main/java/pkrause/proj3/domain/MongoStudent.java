package pkrause.proj3.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Document(collection = "students")
public class MongoStudent {
    @Id
    private UUID id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    private String secondName;

    @NotBlank(message = "Surname is mandatory")
    private String surname;

    private MongoGroup group;

    //region GET SET
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public MongoGroup getGroup() {
        return group;
    }

    public void setGroup(MongoGroup group) {
        this.group = group;
    }
    //endregion

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", surname='" + surname + '\'' +
                ", group=" + group +
                '}';
    }
}
