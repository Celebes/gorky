package www.wcy.wat.edu.gorky.rest.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Controller;

import www.wcy.wat.edu.gorky.rest.model.Student;
@Path("/restwb") 
@Controller
public class StudentService {
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Student getStudentDetails(@PathParam("id") String id){
		Student student = new Student();
		student.setId(Integer.parseInt(id));
		student.setName("Ram");
		student.setCollegeName("UP College");
		student.setAge(25);
		System.out.println("Tu bylem!!");
		return student;
	}
} 