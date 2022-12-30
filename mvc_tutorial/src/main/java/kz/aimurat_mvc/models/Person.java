package kz.aimurat_mvc.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.*;

public class Person {
    private int id;

    @NotEmpty(message = "Required field")
    @Size(min = 2, max = 30, message = "Wrong size")
    private String name;

    @NotEmpty(message = "Required field")
    @Size(min = 2, max = 30, message = "Wrong size")
    private String surname;

    @NotEmpty(message = "Required field")
    @Email(message = "Not correct email")
    private String email;

    @Min(value = 0, message = "Wrong age")
    private int age;

    @Pattern(regexp = "[A-Z][a-z]*,[\\s]+[A-Z][a-z]*", message = "Address is not in correct format: 'Country, City'")
    private String address;

    public Person() {
    }

    public Person(int id, String name, String surname, String email, int age, String address) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.age = age;
        this.address = address;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return this.id;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
