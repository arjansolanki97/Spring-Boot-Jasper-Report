package com.anjaneya.jasper.report.controller;

import com.anjaneya.jasper.report.entity.Employee;
import com.anjaneya.jasper.report.entity.Response;
import com.anjaneya.jasper.report.service.EmployeeService;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/")
    public Response getAllEmployee(){
        return employeeService.getEmployee();
    }
    @GetMapping("")
    public Response getAllEmployees(){
        return employeeService.getEmployee();
    }

    @GetMapping("/create")
    public Response createEmployee( Employee employee){
        return employeeService.saveEmployee(employee);
    }

    @GetMapping("/print/pdf")
    public Response printEmployeePdf(HttpServletResponse respo) throws JRException, IOException {

        return employeeService.printEmployeePdf(respo);
    }
}
