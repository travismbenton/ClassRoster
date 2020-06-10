/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classroster.ui;

import com.sg.classroster.dao.ClassRosterPersistenceException;
import com.sg.classroster.dto.Student;
import java.util.List;

/**
 *
 * @author travi
 */
public class ClassRosterView {    
    
    private UserIO io;
    
    // -- Constructor --
    public ClassRosterView(UserIO io){
        this.io = io;
    }
    // -- Constructor --
    
    
    public int printMenuAndGetSelections() throws ClassRosterPersistenceException{
        
        io.print("Main Menu");
        io.print("1. List Student ID's");
        io.print("2. Create New Student");
        io.print("3. View a Student");
        io.print("4. Remove a Student");
        io.print("5. Exit");        
            
        return io.readInt("Please select from the above choices.", 1, 5);
        
    }        
   //io.print(studentId+" Currently in use! Please try again.");
    
    public Student getNewStudentInfo(){ // gather student information from the user and create a new Student object
        String studentId = io.readString("Please enter Student ID. ");        
        String firstName = io.readString("Please enter First Name. ");
        String lastName = io.readString("Please enter Last Name. ");
        String cohort = io.readString("Please enter Cohort. ");        
        Student currentStudent = new Student(studentId);        
        currentStudent.setFirstName(firstName);
        currentStudent.setLastName(lastName);
        currentStudent.setCohort(cohort);        
        return currentStudent;
    }
    
    
     public void displayCreateStudentBanner(){
        io.print("=== Create Student ===");
    }
     
    
    public void displayCreateSucessBanner(){
        io.print("Student successfully created.");
    }
    
    
    public void displayStudentList(List<Student> studentList){
        for (Student currentStudent : studentList){
            io.print(currentStudent.getStudentId()+ ": "
                    + currentStudent.getFirstName()+" "
                    + currentStudent.getLastName()+" "
                    + currentStudent.getCohort());
        }
        io.readString("Please hit enter to continue.");
    }
    
    
    public void displayDisplayAllBanner(){
        io.print("=== Display All Students ===");
    }
    
    
    public void displayDisplayStudentBanner(){
        io.print("=== Display Student ===");
    }
    
    
    public String getStudentIdChoice(){
        return io.readString("Please enter the Student ID. ");
    }
    
    
    public void displayStudent(Student student){
        if (student != null){
            io.print(student.getStudentId());
            io.print(student.getFirstName()+ " "+student.getLastName());
            io.print(student.getCohort());
            io.print("");            
        } else {
            io.print("No such person");
        }
        io.readString("Please hit enter to continue.");
    }
    
    
    public void displayRemoveStudentBanner () {
	io.print("=== Remove Student ===");
	}

    public void displayRemoveSuccessBanner () {
	io.readString("Student successfully removed. Please hit enter to continue.");
	}
    
    
    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }
    
    
    public void displayErrorMessage(String errorMsg){
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
    
    
    
} // -- "END" class ClassRosterView --
