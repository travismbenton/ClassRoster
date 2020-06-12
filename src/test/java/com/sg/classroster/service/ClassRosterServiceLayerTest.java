/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classroster.service;

import com.sg.classroster.dto.Student;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.annotation.Order;

/**
 *
 * @author travi
 */
public class ClassRosterServiceLayerTest {
    
    ClassRosterServiceLayer service;
    
    public ClassRosterServiceLayerTest() {
        
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        service = ctx.getBean("serviceLayer", ClassRosterServiceLayer.class);
//        ClassRosterDao dao = new ClassRosterDaoStubImpl();
//        ClassRosterAuditDao auditDao = new ClassRosterAuditDaoStubImpl();
        
//        service = new ClassRosterServiceLayerImpl(dao, auditDao);
    }
    
    @BeforeAll
    public void setUpClass() throws Exception {
        List<Student>studentList = service.getAllStudents();
        for (Student student : studentList){
            service.removeStudent(student.getStudentId());
        }
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() throws Exception {
        Student student = new Student("0001");
        student.setFirstName("Sally");
        student.setLastName("Smith");
        student.setCohort("Java-May-2020");
        service.createStudent(student); 
        
        student = new Student("0002");
        student.setFirstName("Sally");
        student.setLastName("Smith");
        student.setCohort("Java-May-2020");
        service.createStudent(student);  
    }
    
    @AfterEach
    public void tearDown() {
    }
    
    /**
     * Test of createStudent method, of class ClassRosterServiceLayer.
     */
    @Test // -- "Business Rules" CreateStudent 1 of 3 Test --    
    public void testCreateStudent() throws Exception {
        Student student = new Student("0021");
        student.setFirstName("Sally");
        student.setLastName("Smith");
        student.setCohort("Java-May-2020");
        service.createStudent(student);        
    }
    
    @Test // -- "Business Rules" CreateStudent 2 of 3 Test --    
    public void testCreateStudentDuplicateId() throws Exception{
        Student student = new Student("0002");
        student.setFirstName("Sally");
        student.setLastName("Smith");
        student.setCohort("Java-May-2020");
        
        try {
            service.createStudent(student);
            fail("Expected ClassRosterDuplicateIdException was not thrown.");    
        } catch (ClassRosterDuplicateIdException e) {
            return;                
        }
    }
    
    @Test // -- "Business Rules" CreateStudent 3 of 3 Test --    
    public void testCreateStudentInvalidData() throws Exception{
        Student student = new Student("0031");
        student.setFirstName("");
        student.setLastName("Smith");
        student.setCohort("Java-May-2020");
        
        try {
            service.createStudent(student);
            fail("Expected ClassRosterDataValidationException was not thrown.");    
        } catch (ClassRosterDataValidationException e) {
            return;                
        }
    }    

    /**
     * Test of getStudent method, of class ClassRosterServiceLayer.
     */
    @Test    
    public void testGetStudent() throws Exception {
        Student student = service.getStudent("0002");
        assertNotNull(student);
        
        student = service.getStudent("9900");
        assertNull(student);       
    }

    /**
     * Test of removeStudent method, of class ClassRosterServiceLayer.
     */
    @Test    
    public void testRemoveStudent() throws Exception {
        Student student = service.removeStudent("0001");
        assertNull(student);        
    }
    
    /**
     * Test of getAllStudents method, of class ClassRosterServiceLayer.
     */
    @Test    
    public void testGetAllStudents() throws Exception {
        assertEquals(2, service.getAllStudents().size());
    }
   
    
}
