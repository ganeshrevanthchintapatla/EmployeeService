//package com.gfg.employeaap.service;
// 
//import com.gfg.employeaap.entity.Employee;
//import com.gfg.employeaap.repository.EmployeeRepo;
//import com.gfg.employeaap.response.EmployeeResponse;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
// 
//@Service
//public class EmployeeService {
// 
//    @Autowired
//    private EmployeeRepo employeeRepo;
// 
//    @Autowired
//    private ModelMapper mapper;
// 
//    public EmployeeResponse getEmployeeById(int id) {
//        Optional<Employee> employee = employeeRepo.findById(id);
//        EmployeeResponse employeeResponse = mapper.map(employee, EmployeeResponse.class);
//        return employeeResponse;
//    }
// 
//}
package com.gfg.employeaap.service;

import com.gfg.employeaap.entity.Employee;
import com.gfg.employeaap.repository.EmployeeRepo;
import com.gfg.employeaap.response.AddressResponse;
import com.gfg.employeaap.response.EmployeeResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private ModelMapper mapper;
    
    @Autowired
    private RestTemplate restTemplate;

    public EmployeeResponse getEmployeeById(int id) {
        Optional<Employee> optionalEmployee = employeeRepo.findById(id);
        
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            EmployeeResponse employeeResponse =mapper.map(employee, EmployeeResponse.class);
             AddressResponse addressResponse = restTemplate.getForObject("http://localhost:8081/address-service/address/{id}", AddressResponse.class, id);
             employeeResponse.setAddressResponse(addressResponse);
      
             return employeeResponse;
        } else {
            // Handle case where employee with given id is not found
            return null;
        }
    }
}
