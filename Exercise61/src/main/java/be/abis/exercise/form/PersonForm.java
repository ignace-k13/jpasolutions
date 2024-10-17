package be.abis.exercise.form;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class PersonForm {

    private String firstName;
    private String lastName;
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate birthDate;
    private String emailAddress;
    private String password;
    private String language;
    private String companyName;
    private String telephoneNumber;
    private String vatNr;
    private String street;
    private String nr;
    private String zipcode;
    private String town;
    private String countryCode;

    public PersonForm() {}

    public PersonForm(String firstName, String lastName, LocalDate birthDate, String emailAddress, String password, String language, String companyName, String telephoneNumber, String vatNr, String street, String nr, String zipcode, String town, String countryCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.emailAddress = emailAddress;
        this.password = password;
        this.language = language;
        this.companyName = companyName;
        this.telephoneNumber = telephoneNumber;
        this.vatNr = vatNr;
        this.street = street;
        this.nr = nr;
        this.zipcode = zipcode;
        this.town = town;
        this.countryCode = countryCode;
    }

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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getVatNr() {
        return vatNr;
    }

    public void setVatNr(String vatNr) {
        this.vatNr = vatNr;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
