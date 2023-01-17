package com.anjaneya.jasper.report.service;

import com.anjaneya.jasper.report.entity.Employee;
import com.anjaneya.jasper.report.entity.Response;
import com.anjaneya.jasper.report.repository.EmployeeRepository;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    Response response;

    public Response getEmployee(){
        List<Employee> list = employeeRepository.findAll();

        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Employee list");
        response.setData(list);

        return response;
    }

    public Response findEmployee(int id){
        Optional<Employee> employee = employeeRepository.findById(id);

        if(employee.isPresent()){
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setMessage("Employee Not Found");
            response.setData(employee.get());
        }
        else {
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("Employee Found");
            response.setData(employee.get());
        }

        return response;
    }

    public Response saveEmployee(Employee employee){

        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Employee created");
        response.setData(employeeRepository.save(employee));

        return response;
    }

    public Response printEmployeePdf(HttpServletResponse respo) throws IOException, JRException {

        List<Employee> employeeList = employeeRepository.findAll();
        File file = ResourceUtils.getFile("classpath:employee.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employeeList);

        Map<String, Object> map = new HashMap<>();
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,map,dataSource);

        JasperExportManager.exportReportToPdfStream(jasperPrint, respo.getOutputStream());
        respo.setContentType("application/pdf");
        respo.addHeader("Content-Disposition", "attachment; filename=jasper.pdf");

        response.setStatus(HttpStatus.OK.value());
        response.setMessage("PDF generated");
        response.setData("PDF generated");

        return response;
    }
}
