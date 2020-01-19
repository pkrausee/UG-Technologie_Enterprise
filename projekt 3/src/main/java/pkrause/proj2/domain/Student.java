package pkrause.proj2.domain;

import pkrause.proj2.common.StringHelper;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
    private String secondName;
    private String surname;

    @ManyToOne(fetch = FetchType.EAGER)
    private Group group;

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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
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

    public boolean isValid() {
        return !StringHelper.isNullOrEmpty(this.name) &&
                !StringHelper.isNullOrEmpty(this.surname);
    }
}
