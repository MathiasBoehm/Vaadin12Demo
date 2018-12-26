package de.struktuhr.orderapp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Customer {

    private final static String DEFAULT_IMAGE_URL = "https://randomuser.me/api/portraits/women/60.jpg";

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;

    private String lastName;

    private boolean manager;

    private LocalDate birthday;

    private String salutation;

    private BigDecimal salary;

    private String imageUrl;

    protected Customer() {
    }

    public Customer(String firstName, String lastName, boolean manager, LocalDate birthday, String salutation, BigDecimal salary, String imageUrl) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.manager = manager;
        this.birthday = birthday;
        this.salutation = salutation;
        this.salary = salary;
        this.imageUrl = imageUrl;
    }

    public Customer(String firstName, String lastName, boolean manager, LocalDate birthday, String salutation, BigDecimal salary) {
        this(firstName, lastName, manager, birthday, salutation, salary, DEFAULT_IMAGE_URL);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isManager() {
        return manager;
    }

    public void setManager(boolean manager) {
        this.manager = manager;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}