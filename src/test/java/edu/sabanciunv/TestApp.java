package edu.sabanciunv;

import edu.sabanciuniv.model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TestApp {
    public static void main(String [] args){

        saveTestData();

    }

    private static void saveTestData() {

        List<Student> studentList = new ArrayList<>();
        List<Course> courseList = new ArrayList<>();
        List<Instructors> instructorsList = new ArrayList<>();
        Date d1 = new Date(2005, Calendar.FEBRUARY,25);
        Date d2 = new Date(1998,Calendar.APRIL,14);
        Student s1 = new Student("ArdaGuler","male","Kadıköy",d1);
        Student s2 = new Student("AltayBayindir","male","Ankara",d2);
        studentList.add(s1);
        studentList.add(s2);
        Course c1 = new Course("Physical Chemistry",202,3);
        Course c2 = new Course("Modern Physics Theories",401,3);
        Instructors i1 = new PermanentInstructor("Davut","Konya",7428800,"fixed salary");
        Instructors i2 = new VistingResearcher("Saadet","Sivas",7428822,"hourly");
        courseList.add(c1);
        courseList.add(c2);
        instructorsList.add(i1);
        instructorsList.add(i2);

        s1.setStudentCourse(c1);
        s2.setStudentCourse(c2);

        c1.setCourseInstructors(i1);
        c2.setCourseInstructors(i2);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqlPU");
        EntityManager entityManager = emf.createEntityManager();

        for(Student stdn :studentList ){
            entityManager.getTransaction().begin();
            entityManager.persist(stdn);
            entityManager.getTransaction().commit();


        }

        for (Course crs : courseList){

            entityManager.getTransaction().begin();
            entityManager.persist(crs);
            entityManager.getTransaction().commit();
        }

        for (Instructors instr : instructorsList){

            entityManager.getTransaction().begin();
            entityManager.persist(instr);
            entityManager.getTransaction().commit();

        }

        entityManager.close();


    }

}
