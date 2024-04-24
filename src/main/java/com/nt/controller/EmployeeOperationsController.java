package com.nt.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nt.entity.Employee;
import com.nt.service.IEmployeeMgmtService;

@Controller
@RequestMapping("/employee")  // global path
public class EmployeeOperationsController {
	
	@Autowired
	private IEmployeeMgmtService empService;
	
	@GetMapping("/")
	public String showHomePage() {
		// return LVN
		return "welcome";
	}
	@GetMapping("/report")
	public String showReport(Map<String,Object> map) {
		// invoke the service class method
		List<Employee> list = empService.fetchAllEmployees();
		// Keep the Result in model attribute
		map.put("empList", list);
		// Return LVN
		return "show_report";
	}
	
	@GetMapping("/emp_add")
	public String showFormForSaveEmployee(@ModelAttribute("emp") Employee emp) {
		return "register_employee";
	}
	
	
	
	@PostMapping("/emp_add")
	public String saveEmployee(@ModelAttribute("emp") Employee emp,RedirectAttributes attrs) {
		System.out.println("EmployeeOperationsController.saveEmployee()");
		// Use service
		String msg = empService.registerEmployee(emp);
//		Iterable<Employee> itEmps = empService.fetchAllEmployees();
		// keep the result as flashAttribute
		attrs.addFlashAttribute("resultMsg",msg);

//		map.put("resltMsg", msg);
//		map.put("empsList", itEmps);
		
		// return LVN
		return "redirect:emp_report";
	}
	
	@GetMapping("/emp_report")
	public String showEmployeeReport(Map<String,Object> map) {
		System.out.println("EmployeeOperationsController.showEmployeeReport()");
		// use Service
		Iterable<Employee> itEmps=empService.fetchAllEmployees();
		// put result in model attribute
		map.put("empList", itEmps);
		//return LVN
		return "show_report";
	}
	@GetMapping("/emp_edit")
	public String showEditFormPage(@ModelAttribute("emp") Employee emp,@RequestParam("no") int no) {
		// use the service
		Employee emp1 = empService.getEmployeeByNo(no);
		// copy recived emp1 object data to Model class object
		BeanUtils.copyProperties(emp1, emp); // copy emp1 object data to emp object
		// return LVN
		return "edit_employee_form";
		
	}
	
	@PostMapping("/emp_edit") // for edit form submission
	public String editEmployee(RedirectAttributes attrs,@ModelAttribute("emp") Employee emp) {
		System.out.println("EmployeeOperationsController.editEmployee()");
		// use the service
		String result = empService.modifyEmployee(emp);
		// keep the result in RedirectAttributes Shared Memory as the FlashAttributes
		attrs.addFlashAttribute("resultMsg",result);
		return "redirect:report";
	}
	
	@GetMapping("/emp_delete")
	public String removeEmployeeById(@RequestParam("no") int no,RedirectAttributes attrs) {
		// use service
		String result = empService.deleteEmployee(no);
		// keep the results Redirect Attributes shared Memory
		attrs.addFlashAttribute("resultMsg",result);
		// return LVN
		
		return "redirect:report";
	}
	
}
