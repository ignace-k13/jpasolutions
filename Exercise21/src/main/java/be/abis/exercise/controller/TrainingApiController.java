package be.abis.exercise.controller;

import be.abis.exercise.model.Enrolment;
import be.abis.exercise.model.Person;
import be.abis.exercise.model.Session;
import be.abis.exercise.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TrainingApiController {

    @Autowired
    TrainingService trainingService;

    @GetMapping("sessions/query")
    List<Session> findSessionsForCourse(@RequestParam("title") String courseTitle){
        return null;
    }

    @GetMapping("persons/{id}/enrolments")
    public List<Enrolment> findEnrolments(@PathVariable("id") int personId)
    {
        return null;
    }

    @PostMapping("sessions/{id}/enrolments")
    public void enrollForSession(@RequestBody Person person, @PathVariable("id") int sessionId){

    }

}
