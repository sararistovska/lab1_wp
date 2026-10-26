package mk.ukim.finki.wp.lab.bootstrap;


import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Registration;
import mk.ukim.finki.wp.lab.model.Workshop;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder
{
    public static List<Workshop> workshops = null;
    public static List<Registration> registrations = null;


    @PostConstruct
    public void init()
    {
        workshops = new ArrayList<>();
        workshops.add(new Workshop("Java Basics", "Programming", 1500, 20));
        workshops.add(new Workshop("Spring Boot Intro", "Programming", 2500, 15));
        workshops.add(new Workshop("Web Design 101", "Design", 1800, 12));
        workshops.add(new Workshop("UX Research", "Design", 2200, 10));
        workshops.add(new Workshop("Data Analysis with Python", "Data", 3000, 8));
        workshops.add(new Workshop("SQL Fundamentals", "Data", 1200, 25));
        workshops.add(new Workshop("Docker for Beginners", "DevOps", 2000, 18));
        workshops.add(new Workshop("Git and GitHub", "DevOps", 800, 30));
        workshops.add(new Workshop("Public Speaking", "Soft Skills", 1000, 5));
        workshops.add(new Workshop("Advanced Algorithms", "Programming", 3500, 0));

        registrations = new ArrayList<>();
    }
}
