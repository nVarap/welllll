package com.nighthawk.spring_portfolio.controllers.Linkr;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nighthawk.spring_portfolio.mvc.linkrAuthentication.LinkrPAT;
import com.nighthawk.spring_portfolio.mvc.linkrAuthentication.PatJpaRepository;


@Controller
public class LinkrSignin {
    @Autowired PatJpaRepository patRepo;

    @GetMapping("/linkrPATs/{user}")
    // @RequestParam handles variables binding to frontend, defaults, etc
    public String linkrdisplay(String user, Model model) {
        List<LinkrPAT> found = patRepo.findAllByUser(user);
        model.addAttribute("pat", found);
        
        return "linkrPATs";
    }


    




}
