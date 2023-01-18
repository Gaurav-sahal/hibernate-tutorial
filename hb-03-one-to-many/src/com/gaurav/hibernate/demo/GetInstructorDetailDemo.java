package com.gaurav.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.gaurav.hibernate.demo.entity.Instructor;
import com.gaurav.hibernate.demo.entity.InstructorDetail;
import com.gaurav.hibernate.demo.entity.Student;

public class GetInstructorDetailDemo {

	public static void main(String[] args) {

		//create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
				
			//start a transaction
			session.beginTransaction();
		
			// get the instructor detail object
			int theId = 29;
			
			InstructorDetail tempInstructorDetail = 
					session.get(InstructorDetail.class, theId);
			
			//print the instructor detail
			System.out.println("tempInstructorDetail: "+ tempInstructorDetail);
			
			//print the associated instructor
			System.out.println("the associated Instructor: "+ tempInstructorDetail.getInstructor());
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		catch(Exception exe) {
			exe.printStackTrace();
		}
		finally {
			//handle connection leak issue
			session.close();
			
			factory.close();
		}
	}

}
