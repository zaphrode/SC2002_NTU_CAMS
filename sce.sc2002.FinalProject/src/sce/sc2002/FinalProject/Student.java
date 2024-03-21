package sce.sc2002.FinalProject;

import java.util.*;
import java.util.ArrayList;

public class Student{

    private ArrayList<Enquiry> enquiries;
    //Not sure if needed
    private String id;
    private String faculty;
    
    public Student(String id, String faculty){
        this.id      = id;
        this.faculty = faculty;
        enquiries = new ArrayList<>();
    }

    public String getID()       {return id;}
    public String getFaculty()  {return faculty;}

    public void addEnquiry(Enquiry enquiry) {
    }




    
}
