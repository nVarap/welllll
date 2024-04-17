package com.nighthawk.spring_portfolio.mvc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import com.nighthawk.spring_portfolio.mvc.linkr.Internship;
import com.nighthawk.spring_portfolio.mvc.linkr.InternshipRepository;
import com.nighthawk.spring_portfolio.mvc.linkr.InternshipService;
import com.nighthawk.spring_portfolio.mvc.linkr.Student;
import com.nighthawk.spring_portfolio.mvc.linkr.StudentController;
import com.nighthawk.spring_portfolio.mvc.linkr.StudentRepository;
import com.nighthawk.spring_portfolio.mvc.linkr.StudentService;
import com.nighthawk.spring_portfolio.mvc.linkrAuthentication.LinkrPAT;
import com.nighthawk.spring_portfolio.mvc.linkrAuthentication.PatJpaRepository;
import com.nighthawk.spring_portfolio.mvc.note.Note;
import com.nighthawk.spring_portfolio.mvc.person.Person;
import com.nighthawk.spring_portfolio.mvc.person.PersonDetailsService;

import groovyjarjarantlr4.v4.codegen.model.ExceptionClause;

import java.util.List;

@Component
@Configuration // Scans Application for ModelInit Bean, this detects CommandLineRunner
public class ModelInit {  
    @Autowired PersonDetailsService personService;
    @Autowired PatJpaRepository patRepo;
    @Autowired StudentRepository studentRepository;
    @Autowired InternshipRepository internshipRepository;


    @Bean
    CommandLineRunner run() {  // The run() method will be executed after the application starts
        return args -> {

            System.out.println("Started Init)");

            Internship[] clist = Internship.internshipInit();
            for (Internship c : clist){
                if(c.getId() == null){
                    c.setId(internshipRepository.getMaxId() + 1);
                }
                List<Internship> found = internshipRepository.findInternshipByNameIgnoreCase(c.getName());
                if (found.size() == 0){
                    internshipRepository.save(c);
                }
            }

            Student[] elist = Student.StudentInit();
            for (Student e : elist){
                if(e.getId() == null){
                    e.setId(studentRepository.getMaxId() + 1);
                }
                List<Student> foundEmails = studentRepository.findAllByEmail(e.getEmail());
                if(foundEmails.size() == 0){
                    studentRepository.save(e);
                }
            }

            // Person database is populated with test data
            Person[] personArray = Person.init();
            for (Person person : personArray) {
                //findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase
                List<Person> personFound = personService.list(person.getName(), person.getEmail());  // lookup
                if (personFound.size() == 0) {
                    try {
                        personService.save(person);  // save
                    }
                    catch(Exception e){
                        System.out.println(e);
                    }
                }
            }

            System.out.println("Exited person");
            
            LinkrPAT[] list = LinkrPAT.init();
            for(LinkrPAT l : list){
                List<LinkrPAT> found = patRepo.findAllByUser(l.getUser());
                if(found.size() == 0){
                    patRepo.save(l);
                }
            }

            System.out.println("ended init");
        };
    }
}