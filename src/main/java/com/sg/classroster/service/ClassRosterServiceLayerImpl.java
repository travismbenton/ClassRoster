/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classroster.service;

import com.sg.classroster.dao.ClassRosterAuditDao;
import com.sg.classroster.dao.ClassRosterDao;
import com.sg.classroster.dao.ClassRosterPersistenceException;
import com.sg.classroster.dto.Student;
import java.util.List;

/**
 *
 * @author travi
 */
public class ClassRosterServiceLayerImpl implements ClassRosterServiceLayer {
    
    ClassRosterDao dao;
    private ClassRosterAuditDao auditDao;
    
    // -- Constructor --
    public ClassRosterServiceLayerImpl(ClassRosterDao dao, ClassRosterAuditDao auditDao) {
    this.dao = dao;
    this.auditDao = auditDao;
    }
    // -- "END" Constructor --

    @Override // INCLUDE "AUDIT LOG"
    public void createStudent(Student student) throws 
            ClassRosterDuplicateIdException, 
            ClassRosterDataValidationException, 
            ClassRosterPersistenceException {
    
        if (dao.getStudent(student.getStudentId()) != null) {
        throw new ClassRosterDuplicateIdException(
                "ERROR: Could not create student.  Student Id "
                + student.getStudentId()
                + " already exists");
    }
        validateStudentData(student);
        dao.addStudent(student.getStudentId(), student);
        
        // -- AUDIT LOG --
        //auditDao.writeAuditEntry( 
            //"Student " + student.getStudentId() + " CREATED.");
    }
    

    @Override // Pass Through Method
    public List<Student> getAllStudents() throws 
            ClassRosterPersistenceException {
        return dao.getAllStudents();    
    }

    @Override  // Pass Through Method
    public Student getStudent(String studentId) throws 
            ClassRosterPersistenceException {
        return dao.getStudent(studentId);    
    }

    @Override // INCLUDE "AUDIT LOG" 
    public Student removeStudent(String studentId) throws 
            ClassRosterPersistenceException {
        Student removedStudent = dao.removeStudent(studentId);
        
        // -- AUDIT LOG --
        //auditDao.writeAuditEntry("Student " + studentId + " REMOVED.");
        return removedStudent;   
    }    
    
    
    private void validateStudentData(Student student) throws
        ClassRosterDataValidationException {

        if (student.getFirstName() == null
            || student.getFirstName().trim().length() == 0
            || student.getLastName() == null
            || student.getLastName().trim().length() == 0
            || student.getCohort() == null
            || student.getCohort().trim().length() == 0) {

        throw new ClassRosterDataValidationException(
                "ERROR: All fields [First Name, Last Name, Cohort] are required.");
        }
    }
    
    
} // -- "END" ClassRosterServiceLayerImpl --
