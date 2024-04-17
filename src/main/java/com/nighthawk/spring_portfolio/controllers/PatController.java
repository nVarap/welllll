package com.nighthawk.spring_portfolio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.nighthawk.spring_portfolio.mvc.linkr.Student;
import com.nighthawk.spring_portfolio.mvc.linkr.StudentRepository;
import com.nighthawk.spring_portfolio.mvc.linkrAuthentication.LinkrPAT;
import com.nighthawk.spring_portfolio.mvc.linkrAuthentication.PatJpaRepository;

public class PatController {
    @Autowired
    PatJpaRepository PATRepository;

    @GetMapping("/api/pats")
    public String UsersAPI(Model model){
        List<LinkrPAT> allStudents = PATRepository.findAll();
        model.addAttribute("PATS", allStudents);
        
        return "linkrPATs";
    }

    @GetMapping("/api/pats")
    public LinkrPAT getAPIByUser(String name){
        List<LinkrPAT> allStudents = PATRepository.findAll();
        for (LinkrPAT l : allStudents){
            if(l.getUser().equals(name)){
                return l;
            }
        }
        return new LinkrPAT();
    }
}
