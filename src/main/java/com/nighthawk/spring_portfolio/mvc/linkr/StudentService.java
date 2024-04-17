package com.nighthawk.spring_portfolio.mvc.linkr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Method to retrieve all students
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    // Method to retrieve an student by their ID
    public Optional<Student> getStudentById(Long studentId) {
        return studentRepository.findById(studentId);
    }

    // Method to create a new student
    public Student createStudent(Student student) {
        // If the student ID is not provided, generate a new ID
        if (student.getId() == null) {
            student.setId(generateNextId());
        }
        return studentRepository.save(student); // Save the student and return it
    }

    // Method to delete an student by their ID
    public void deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    // Method to load user by username (email) for authentication purposes
    public Student loadUserByUsername(String email) throws UsernameNotFoundException {
        Student student = studentRepository.findByEmail(email); // Retrieve user by email from the database
        if (student == null) { // If user is not found
            throw new UsernameNotFoundException("User not found with username: " + email); // Throw exception
        }
        // Assuming Student implements UserDetails
        return student;
    }

    // Method to generate the next ID for a new student
    private Long generateNextId() {
        // Generate the next ID by retrieving the maximum ID and incrementing it
        return studentRepository.getMaxId() + 1;
    }
}
