
package com.student.model;

public class Student {

    private int id;
    private String name;
    private int age;
    private String email;
    private String course;

    public Student(int id, String name, int age,
                   String email, String course) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.course = course;
    }

    public Student(String name, int age,
                   String email, String course) {
        this(0, name, age, email, course);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getCourse() {
        return course;
    }
}
