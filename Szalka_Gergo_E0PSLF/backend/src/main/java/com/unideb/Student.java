package com.unideb;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "Konyvek")
public class Student {

    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private String university;
    private String faculty;
    private boolean isForeignStudent;

    // avoid this "No default constructor for entity"
    public Student() {
    }

    public Student(Long id, String lastName, String firstName, int age, String university, String faculty, boolean isForeignStudent) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.university = university;
        this.faculty = faculty;
        this.isForeignStudent = isForeignStudent;
    }

    public Student(String lastName, String firstName, int age, String university, String faculty, boolean isForeignStudent) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.university = university;
        this.faculty = faculty;
        this.isForeignStudent = isForeignStudent;
    }

    /*public Student(String name, String author, BigDecimal price) {
        this.name = name;
        this.author = author;
        this.price = price;
    }*/

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUniversity () {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public boolean getForeignStudent() {
        return isForeignStudent;
    }

    public void setForeignStudent(boolean isForeignStudent) {
        this.isForeignStudent = isForeignStudent;
    }

    @Override
    public String toString() {
        return "Tanulo {" +
                "id=" + id +
                ", keresztnev='" + firstName + '\'' +
                ", vezeteknev='" + lastName + '\'' +
                ", eletkor='" + age + '\'' +
                ", egyetem='" + university + '\'' +
                ", kar='" + faculty + '\'' +
                ", kulfoldi='" + isForeignStudent + '\'' +
                '}';
    }

}

