package com.programcreek.helloworld.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.programcreek.helloworld.dao.EmployeeDAO;

public class EmployeeController implements Controller {
	private EmployeeDAO employeeManager;

	public EmployeeDAO getEmployeeManager() {
		return employeeManager;
	}

	public void setEmployeeManager(EmployeeDAO employeeManager) {
		this.employeeManager = employeeManager;
	}

	public ModelAndView handleRequest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		System.out.println("in EmployeeController");

		ModelAndView mv = new ModelAndView("employeeList");
		mv.addObject("employeeList", this.employeeManager.getEmployeeList());

		return mv;
	}
}
