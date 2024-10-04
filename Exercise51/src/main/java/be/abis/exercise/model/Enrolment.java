package be.abis.exercise.model;

import be.abis.exercise.converter.CancelConverter;
import jakarta.persistence.*;

@Entity
@Table(name = "enrolments")
@IdClass(EnrolmentId.class)
@NamedNativeQuery(name="Enrolment.findByEnrolleeNQ",
        query = "select pfname, plname, enr.coname as enrolcomp, sdate, loc.coname as loccomp, loc.cotown as loctown, cltitle " +
                "from persons p inner join companies enr on pa_cono=enr.cono " +
                "               inner join enrolments on e_pno=PNO " +
                "               inner join sessions on e_sno=SNO " +
                "               inner join companies loc on sloc_cono = loc.cono " +
                "               inner join courses on s_cid=cid " +
                "where pno = :pno and scancel is null and ecancel is null")
public class Enrolment {

    @Id
    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "e_sno")
    private Session session;
    @Id
    @Column(name="eno")
    private int enrolmentInSession;

    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "e_pno")
    private Person enrollee;

    @Column(name="epay")
    private double pricePerDayPayed;

    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "e_cono")
    private Company enrolmentCompany;

    @Column(name="ecancel")
    @Convert(converter= CancelConverter.class)
    private boolean cancelled;

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public int getEnrolmentInSession() {
        return enrolmentInSession;
    }

    public void setEnrolmentInSession(int enrolmentInSession) {
        this.enrolmentInSession = enrolmentInSession;
    }

    public Person getEnrollee() {
        return enrollee;
    }

    public void setEnrollee(Person enrollee) {
        this.enrollee = enrollee;
    }

    public double getPricePerDayPayed() {
        return pricePerDayPayed;
    }

    public void setPricePerDayPayed(double pricePerDayPayed) {
        this.pricePerDayPayed = pricePerDayPayed;
    }

    public Company getEnrolmentCompany() {
        return enrolmentCompany;
    }

    public void setEnrolmentCompany(Company enrolmentCompany) {
        this.enrolmentCompany = enrolmentCompany;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
