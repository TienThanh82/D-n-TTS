package vn.titv.spring.demo.service;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.titv.spring.demo.dao.EmployeeRepository;
import vn.titv.spring.demo.entity.Employee;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{
    private EmployeeRepository employeeRepository;

    @Autowired
    public StudentServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllStudents() {
        return employeeRepository.findAll();
    }

    @Override

    public Employee getStudentById(int id) {
        return employeeRepository.getById(id);
    }

    @Override
    @Transactional
    public Employee addStudent(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public Employee updateStudent(Employee employee) {
        return employeeRepository.saveAndFlush(employee);
    }

    @Override
    @Transactional
    public void deleteStudentById(int id) {
        employeeRepository.deleteById(id);
    }
}
