package be.abis.exercise.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("p")
public class PublicSession extends Session {

    public PublicSession() {
    }

    public PublicSession(LocalDate startDate, Person instructor, Company location, Course course) {
        super(startDate, instructor, location, course);
    }



}
