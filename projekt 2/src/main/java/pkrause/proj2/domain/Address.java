package pkrause.proj2.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import pkrause.proj2.common.StringHelper;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity(name = "address")
public class Address implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String address;
    private String postCode;
    private String country;

    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Student student;

    //region GET SET
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    //endregion

    @Override
    public String toString() {
        return "Address{" +
                ", address='" + address + '\'' +
                ", postCode='" + postCode + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    public boolean isValid() {
        return !StringHelper.isNullOrEmpty(this.address) &&
                !StringHelper.isNullOrEmpty(this.postCode) &&
                !StringHelper.isNullOrEmpty(this.country);
    }
}
