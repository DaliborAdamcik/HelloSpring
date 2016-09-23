package com.programcreek.helloworld.dao;

import java.util.List;

import com.programcreek.helloworld.model.*;

public interface EmployeeDAO 
{
	public List<Employee> getEmployeeList();
	public Employee addEmployee(Employee e);
}




