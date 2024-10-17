package be.abis.exercise.model;

import be.abis.exercise.converter.CancelConverter;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "sessions")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn (name="skind", discriminatorType=DiscriminatorType.STRING)
public class Session implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="sno")
    private int sessionId;
    @Column(name="sdate")
    private LocalDate startDate;
    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "sins_pno")
    private Person instructor;
    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "sloc_cono")
    private Company location;

    //@Column(name="skind", insertable = false, updatable = false)
    //private String kind;

    @Column(name="sincomes")
    private double income;
    @Column(name="scancel")
    @Convert(converter= CancelConverter.class)
    private boolean cancelled;
    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "s_cid")
    private Course course;

    public Session(){}

    public Session(LocalDate startDate,Person instructor, Company location,Course course){
        this.startDate=startDate;
        this.instructor=instructor;
        this.location=location;
        this.course=course;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Company getLocation() {
        return location;
    }

    public void setLocation(Company location) {
        this.location = location;
    }

    public Person getInstructor() {
        return instructor;
    }

    public void setInstructor(Person instructor) {
        this.instructor = instructor;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

   /* public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }*/

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

   public String getKind(){
       return this.getClass().getAnnotation(DiscriminatorValue.class).value();
    }

    public String toString(){
        return "Session " + sessionId + " about " + this.course.getShortTitle().trim() + " is given by " + instructor.getFirstName() + ". This is a " + getKind() + " session.";

    }
}
