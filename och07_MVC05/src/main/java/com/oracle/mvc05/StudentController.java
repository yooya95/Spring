package com.oracle.mvc05;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oracle.mvc05.dto.Student;

@Controller
public class StudentController {
	private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
	
	@RequestMapping("/studentForm")
	public String studentForm() {
		logger.info("studentForm start...");
		return "studentForm";
	}
	@RequestMapping("/student/create")
	public String studentCreate(Student student,BindingResult result, Model model ) {
		String page = "studentDonePage";
		logger.info("/student/creat start...");
        StudentValidator validator = new StudentValidator();
		validator.validate(student, result);
		logger.info("result Message->{}", result.toString());
		System.out.println("result Message getFieldError->"+ result.getFieldErrors("name"));
		String name ="";
		String id ="";
		if(result.hasErrors())	{
        	if(result.hasFieldErrors("name")) {
				System.out.println("result.hasErrors1->"+result.getFieldError("name"));
				FieldError fieldError1 =result.getFieldError("name");
				name = fieldError1.getCode();
				System.out.println("fieldError1.getCode name->"+ name);
				
				model.addAttribute("nameErr", name);
        	}
           	if(result.hasFieldErrors("strId")) {
    				System.out.println("result.hasErrors2->"+result.getFieldError("strId"));
    				FieldError fieldError2 =result.getFieldError("strId");
    				id = fieldError2.getCode();
    				System.out.println("fieldError1.getCode id->"+ id);
   				
    				model.addAttribute("idErr", id);
            }
           	page = "studentForm";
        // 정상일 경우     	
	    } else {
	    //	model.addAttribute("student", student);    
	    }		
		
		System.out.println("result Message->End");
		logger.info("result page->{}", page);
		logger.info("result name->{}", name);
		
		return page;
	}
	
}
