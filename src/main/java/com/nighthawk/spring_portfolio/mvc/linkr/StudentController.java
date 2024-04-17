package com.nighthawk.spring_portfolio.mvc.linkr;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.nighthawk.spring_portfolio.mvc.person.Person;
import com.nighthawk.spring_portfolio.mvc.person.PersonDetailsService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// Using lombok to automatically generate a logger
@Slf4j
@RestController
@RequestMapping("/api/student") // Base URL for all endpoints in this controller
public class StudentController {

    private final StudentService StudentService; // Service for student-related operations
    private final ModelMapper modelMapper; // For entity-to-DTO mapping
    private final PersonDetailsService personDetailsService; // Service for managing person details

    @Autowired
    public StudentController(StudentService StudentService, ModelMapper modelMapper, PersonDetailsService personDetailsService) {
        this.StudentService = StudentService;
        this.modelMapper = modelMapper;
        this.personDetailsService = personDetailsService;
    }

    // Endpoint to get all Student
    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudent() {
        // Retrieving all Student and mapping them to DTOs
        List<Student> Student = StudentService.getAllStudent();
        List<StudentDTO> studentDTOs = Student.stream()
                .map(student -> modelMapper.map(student, StudentDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(studentDTOs, HttpStatus.OK); // Returning DTO list with OK status
    }

    // Endpoint to get an student by their ID
    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long studentId) {
        log.info("Attempting to retrieve student with ID: {}", studentId); // Logging the attempt
        Optional<Student> student = StudentService.getStudentById(studentId); // Retrieving student by ID
        if (student.isPresent()) { // If student is found
            log.info("Found student with ID: {}", studentId); // Logging successful retrieval
            return ResponseEntity.ok().body(student.get()); // Returning student with OK status
        } else { // If student is not found
            log.warn("Student with ID {} not found", studentId); // Logging warning for not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Returning NOT_FOUND status
        }
    }

    // Endpoint to add a new student
    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        log.info("Attempting to add student: {}", student); // Logging the attempt
        Student addedStudent = StudentService.createStudent(student); // Creating the student
        log.info("Student added successfully: {}", addedStudent); // Logging successful addition
        
        // Creating a Person object and saving person details
        Person p6 = new Person();
        p6.setName("No Name");
        p6.setEmail(student.getEmail());
        p6.setPassword(student.getPassword());
        try {
            Date d = new SimpleDateFormat("MM-dd-yyyy").parse("05-15-2007");
            p6.setDob(d);
        } catch (Exception e) {
        }
        personDetailsService.save(p6); // Saving person details
        
        System.out.println("Hello"); // Printing a message
        
        return new ResponseEntity<>(addedStudent, HttpStatus.CREATED); // Returning the added student with CREATED status
    }

    // Endpoint to delete an student by their ID
    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long studentId) {
        log.info("Attempting to delete student with ID: {}", studentId); // Logging the attempt
        StudentService.deleteStudent(studentId); // Deleting the student
        log.info("Student with ID {} deleted successfully", studentId); // Logging successful deletion
        return ResponseEntity.noContent().build(); // Returning NO_CONTENT status
    }
}