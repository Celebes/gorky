package www.wcy.wat.gorky.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import www.wcy.wat.edu.gorky.rest.model.Student;


@Controller
@RequestMapping(value="/")
public class JacksonController {
	
	@RequestMapping(method=RequestMethod.GET, value="/wynik/{id}", produces={"application/json; charset=UTF-8"})
	public @ResponseBody Student findById(@PathVariable int id) {
		System.out.println("Mam " + id);
		Student student = new Student();
		student.setId(id);
		student.setName("Ram");
		student.setCollegeName("UP College");
		student.setAge(25);
		System.out.println("Tu bylem!");
	    return student;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/wynik/{id}")
	public @ResponseBody Student findById2(@PathVariable int id) {
		System.out.println("Mam " + id);
		Student student = new Student();
		student.setId(id);
		student.setName("Ram");
		student.setCollegeName("UP College");
		student.setAge(25);
		System.out.println("Tu bylem!");
	    return student;
	}

}
