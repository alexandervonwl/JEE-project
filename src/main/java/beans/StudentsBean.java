package beans;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class StudentsBean implements java.io.Serializable {
    @JsonProperty
    private static Vector<StudentBean> students = null;
    public StudentsBean(){ students = new Vector<StudentBean>();}

    public void addStudent(StudentBean studentBean){
        students.add(studentBean);
    }

    public void removeStudent(StudentBean studentBean) {students.remove(studentBean);}

    public Vector<StudentBean> getStudents(){
        return students;
    }

    public void setStudents(Vector<StudentBean> otherStudents) {students = otherStudents; }

    public String toString(){
        String students="";
        for(StudentBean student:getStudents()){
            students += student.getNume()+", ";
        }
        return students;
    }
}
