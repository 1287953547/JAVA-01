package com.example.springstarter.domain;

import com.example.springstarter.domain.Student;
import lombok.Data;

import java.util.List;


public class Klass { 
    
    private List<Student> students;

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void dong(){
        System.out.println(this.getStudents());
    }
    
}
