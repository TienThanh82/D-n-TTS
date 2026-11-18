package vn.titv.spring.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.titv.spring.demo.entity.Employee;
import vn.titv.spring.demo.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private StudentService studentService;

    @Autowired
    public EmployeeController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Employee> getAllStudents(){
        return this.studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getStudentById(@PathVariable int id){
        Employee employee = studentService.getStudentById(id);
        if (employee !=null){
            return ResponseEntity.ok(employee);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Employee> addStudent(@RequestBody Employee employee){
        employee.setId(0); // bat buoc them moi va phat sinh ra id
        employee = studentService.addStudent(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateStudent(@PathVariable int id, @RequestBody Employee employee){
        Employee existingEmployee = studentService.getStudentById(id);
        if (existingEmployee !=null){
            existingEmployee.setFirstName(employee.getFirstName());
            existingEmployee.setLastName(employee.getLastName());
            existingEmployee.setEmail(employee.getEmail());
            studentService.updateStudent(existingEmployee);
            return ResponseEntity.ok(existingEmployee);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> deleteStudent(@PathVariable int id){
        Employee existingEmployee = studentService.getStudentById(id);
        if (existingEmployee !=null){
          studentService.deleteStudentById(id);
          return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
