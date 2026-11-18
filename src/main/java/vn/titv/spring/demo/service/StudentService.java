package vn.titv.spring.demo.service;

import vn.titv.spring.demo.entity.Employee;

import java.util.List;

public interface StudentService {
    public List<Employee> getAllStudents();

    public Employee getStudentById(int id);

    public Employee addStudent(Employee employee);

    public Employee updateStudent(Employee employee);

    public void deleteStudentById(int id);

}
