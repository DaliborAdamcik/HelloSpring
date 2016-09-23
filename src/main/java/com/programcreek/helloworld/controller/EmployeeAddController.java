package com.programcreek.helloworld.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.programcreek.helloworld.dao.EmployeeDAO;
import com.programcreek.helloworld.model.Employee;


@Controller
public class EmployeeAddController {
	@Autowired
	private EmployeeDAO employeeManager;
	
//	public void setEmployeeManager(EmployeeDAO employeeManager) {
//		this.employeeManager = employeeManager;
//	}

	public EmployeeDAO getEmployeeManager() {
		return employeeManager;
	}


	@RequestMapping("/showEmployeeForm")
	public ModelAndView getEmployeeForm(){
		ModelAndView mv = new ModelAndView("employeeAdd");
		mv.addObject("employeeEntity", new Employee());
		return mv;
	}
	
	@RequestMapping("/addEmployee")
	public ModelAndView addEmployee(@ModelAttribute Employee e){
		ModelAndView mv = new ModelAndView("employeeList");
		
		
		getEmployeeManager().addEmployee(e);
		mv.addObject("employeeList", getEmployeeManager().getEmployeeList());

		return mv;
	}
}
