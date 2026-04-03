package com.example.tarefa4;

import java.util.List;
import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class StudentResponse {
    @SerializedName("students")
    @Expose
    private List<Student> students;

    public List<Student> getStudents() {
        return students;
    }

}