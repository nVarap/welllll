package com.nighthawk.spring_portfolio.mvc.linkr;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter; 

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String position;
    private String email;
    private String password;
    private int followers;

    // change to hashmap
    private int ideas;
    private int joined; 

    private int investments; 


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Internship internship;


    public Student(String name, String position, String email, String password){
        this.name = name;
        this.position = position;
        this.email = email;
        this.password = password;
        this.ideas = 0;
        this.joined = 0;
        this.investments = 0;
    }

    public static Student[] StudentInit(){
        Student e1 = new Student("Tanay", "CEO", "tpatel@gmail.com", "123Tanay!");
        Student e2 = new Student("Varaprasad", "CTO", "vnibhanupudi@gmail.com", "123Vlu!");
        Student e3 = new Student("Paaras", "CFO", "ppurohit@gmail.com", "123Paras!");
        Student e4 = new Student("Tobias", "Student", "toby@gmail.com", "123Toby");
        Student e5 = new Student("Hubert", "Student", "hop@gmail.com", "123hop");
        Student[] elist =  {e1, e2, e3, e4, e5};
        return elist;
    }
}