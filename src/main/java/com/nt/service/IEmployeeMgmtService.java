package com.nt.service;

import java.util.List;

import com.nt.entity.Employee;

public interface IEmployeeMgmtService {
	public List<Employee> fetchAllEmployees();
	public String registerEmployee(Employee emp);
	public Employee searchEmployee(Employee emp);
	public String modifyEmployee(Employee emp);
	public Employee getEmployeeByNo(int no);
	public String deleteEmployee(int no);
}
