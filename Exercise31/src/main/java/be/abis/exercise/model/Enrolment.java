package be.abis.exercise.model;

public class Enrolment {

    private Session session;
    private int enrolmentInSession;
    private Person enrollee;
    private double pricePerDayPayed;
    private Company enrolmentCompany;
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
