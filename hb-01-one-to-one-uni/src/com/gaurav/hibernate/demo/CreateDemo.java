package com.gaurav.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.gaurav.hibernate.demo.entity.Instructor;
import com.gaurav.hibernate.demo.entity.InstructorDetail;
import com.gaurav.hibernate.demo.entity.Student;

public class CreateDemo {

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
			
			//create the objects
			Instructor tempInstructor1 = 
					new Instructor("Gaurav", "Sahal", "gaurav.sahal10@gmail.com");
			
			InstructorDetail tempInstructorDetail1 = 
					new InstructorDetail(
							"http://www.youtube.com/beginnersbaggage",
							"Luv 2 code!!!");

			
			Instructor tempInstructor2 = 
					new Instructor("Chad", "Darby", "darby@luv2code.com");
			
			InstructorDetail tempInstructorDetail2 = 
					new InstructorDetail(
							"http://www.luv2code.com/youtube",
							"Luv coding!!!");
			
			//associate the objects
			tempInstructor1.setInstructorDetail(tempInstructorDetail1);
			tempInstructor2.setInstructorDetail(tempInstructorDetail2);
			
			//start a transaction
			session.beginTransaction();
		
			//save the instructor
			//
			//Note: this will also save the details object
			//because of CascadeType.ALL
			//
			System.out.println("Saving Instructor: "+ tempInstructor1);
			session.save(tempInstructor1);
			
			System.out.println("Saving Instructor: "+ tempInstructor2);
			session.save(tempInstructor2);
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}

}
