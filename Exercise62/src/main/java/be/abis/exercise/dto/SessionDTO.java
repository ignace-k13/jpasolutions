package be.abis.exercise.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class SessionDTO {

    private int sessionNumber;
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate startDate;
    private String instructorFirstName;
    private String instructorLastName;
    private String locationCompany;
    private String locationTown;
    private String kind;
    private boolean cancelled;
    private String courseTitle;

    public SessionDTO(){}
    public SessionDTO(int sessionNumber, LocalDate startDate, String instructorFirstName, String instructorLastName, String locationCompany, String locationTown, String kind, boolean cancelled, String courseTitle) {
        this.sessionNumber = sessionNumber;
        this.startDate = startDate;
        this.instructorFirstName = instructorFirstName;
        this.instructorLastName = instructorLastName;
        this.locationCompany = locationCompany;
        this.locationTown = locationTown;
        this.kind = kind;
        this.cancelled = cancelled;
        this.courseTitle = courseTitle;
    }

    public int getSessionNumber() {
        return sessionNumber;
    }

    public void setSessionNumber(int sessionNumber) {
        this.sessionNumber = sessionNumber;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getInstructorFirstName() {
        return instructorFirstName;
    }

    public void setInstructorFirstName(String instructorFirstName) {
        this.instructorFirstName = instructorFirstName;
    }

    public String getInstructorLastName() {
        return instructorLastName;
    }

    public void setInstructorLastName(String instructorLastName) {
        this.instructorLastName = instructorLastName;
    }

    public String getLocationCompany() {
        return locationCompany;
    }

    public void setLocationCompany(String locationCompany) {
        this.locationCompany = locationCompany;
    }

    public String getLocationTown() {
        return locationTown;
    }

    public void setLocationTown(String locationTown) {
        this.locationTown = locationTown;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }
}
