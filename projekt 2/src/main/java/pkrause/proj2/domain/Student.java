package pkrause.proj2.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import pkrause.proj2.common.StringHelper;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
    private String secondName;
    private String surname;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "student")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Address address;

    @ManyToOne(fetch = FetchType.EAGER)
    private Group group;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Lecture> lectures;

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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Set<Lecture> getLectures() {
        return lectures;
    }

    public void setLectures(Set<Lecture> lectures) {
        this.lectures = lectures;
    }
    //endregion

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", surname='" + surname + '\'' +
                ", address=" + address +
                ", group=" + group +
                ", lectures=" + lectures +
                '}';
    }

    public boolean isValid() {
        return !StringHelper.isNullOrEmpty(this.name) &&
                !StringHelper.isNullOrEmpty(this.surname);
    }
}
