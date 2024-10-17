package be.abis.exercise.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("c")
public class CompanySession extends Session {

    public CompanySession() {
    }

    public CompanySession(LocalDate startDate, Person instructor, Company location, Course course) {
        super(startDate, instructor, location, course);
    }





}
