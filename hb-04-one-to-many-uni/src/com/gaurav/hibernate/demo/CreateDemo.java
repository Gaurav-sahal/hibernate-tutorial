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
/*			Instructor tempInstructor = 
					new Instructor("Gaurav", "Sahal", "gaurav.sahal10@gmail.com");
			
			InstructorDetail tempInstructorDetail = 
					new InstructorDetail(
							"http://www.youtube.com/beginnersbaggage",
							"Luv 2 code!!!");
*/
			
			Instructor tempInstructor = 
					new Instructor("Chad", "Darby", "darby@luv2code.com");
			
			InstructorDetail tempInstructorDetail = 
					new InstructorDetail(
							"http://www.luv2code.com/youtube",
							"Luv coding!!!");
			
			//associate the objects
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			//start a transaction
			session.beginTransaction();
		
			//save the instructor
			//
			//Note: this will also save the details object
			//because of CascadeType.ALL
			//
			System.out.println("Saving Instructor: "+ tempInstructor);
			session.save(tempInstructor);
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}

}
