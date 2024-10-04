package be.abis.exercise.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class EnrolmentDTO {

    private String firstName;
    private String lastName;
    private String enrolleeCompanyName;
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate startDate;
    private String locationCompany;
    private String locationTown;
    private String coursetitle;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEnrolleeCompanyName() {
        return enrolleeCompanyName;
    }

    public void setEnrolleeCompanyName(String enrolleeCompanyName) {
        this.enrolleeCompanyName = enrolleeCompanyName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
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

    public String getCoursetitle() {
        return coursetitle;
    }

    public void setCoursetitle(String coursetitle) {
        this.coursetitle = coursetitle;
    }
}
