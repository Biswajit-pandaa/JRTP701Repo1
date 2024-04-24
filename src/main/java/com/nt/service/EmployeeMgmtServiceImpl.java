package com.nt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.entity.Employee;
import com.nt.repository.IEmployeeRepository;
@Service("empService")
public class EmployeeMgmtServiceImpl implements IEmployeeMgmtService {
	@Autowired
	private IEmployeeRepository empRepo;
	
	@Override
	public List<Employee> fetchAllEmployees() {
		
//		return empRepo.findAll().sort((emp1,emp2)->(emp1.getEmpno()-emp2.getEmpno()));
		List<Employee> list = empRepo.findAll();
		list.sort((emp1,emp2)->(emp1.getEmpno()-emp2.getEmpno()));
		return list;
	}

	@Override
	public String registerEmployee(Employee emp) {
		
		return "employee is saved with id value : "+empRepo.save(emp).getEmpno();
	}

//	@Override
//	public Employee searchEmployee(Employee emp) {
//		
////		Optional<Employee> opt = empRepo.findById(no);
//		
//		return empRepo.findById(no).orElseThrow(()->new IllegalArgumentException("Employee is not found"));
//	}
	@Override
	public Employee getEmployeeByNo(int no) {
		Employee emp = empRepo.findById(no).orElseThrow(()->new IllegalArgumentException("Employee is not found"));
		return emp;
		
	}

	@Override
	public String modifyEmployee(Employee emp) {
		Optional<Employee> opt = empRepo.findById(emp.getEmpno());
		if(opt.isPresent()) {
			// update the object
			empRepo.save(emp);
			return emp.getEmpno()+"--> Employee details are updated";
		}
		return emp.getEmpno()+"--> Employee is not found";
	}

	@Override
	public String deleteEmployee(int no) {
		Optional<Employee> opt = empRepo.findById(no);
		if(opt.isPresent()) {
			empRepo.deleteById(no);
			return no+" id Employee is deleted.";
		}else {
			
			return no+" id is not found for Deletion";
		}
	}

	@Override
	public Employee searchEmployee(Employee emp) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
}
