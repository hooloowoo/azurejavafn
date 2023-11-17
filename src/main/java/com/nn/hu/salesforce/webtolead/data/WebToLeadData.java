package com.nn.hu.salesforce.webtolead.data;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class WebToLeadData {


    private String firstName;
    private String lastName;
    private String emailAddress;
    private String telephoneNumber;
    private String zipCode;
    private String streetName;
    private String cityName;
    private Boolean marketingConsent;

    public WebToLeadData() { 
        System.out.println("WebToLead data initialized.");
        this.firstName = "Dummy";
        this.lastName = "Dummy";
        this.emailAddress = "example@example.com";
    }

    public WebToLeadData firstName(String firstName) {
        setFirstName(firstName);
        return this;
    }

    public WebToLeadData lastName(String lastName) {
        setLastName(lastName);
        return this;
    }

    public WebToLeadData emailAddress(String emailAddress) {
        setEmailAddress(emailAddress);
        return this;
    }

    public WebToLeadData telephoneNumber(String telephoneNumber) {
        setTelephoneNumber(telephoneNumber);
        return this;
    }

    public WebToLeadData zipCode(String zipCode) {
        setZipCode(zipCode);
        return this;
    }

    public WebToLeadData streetName(String streetName) {
        setStreetName(streetName);
        return this;
    }

    public WebToLeadData cityName(String cityName) {
        setCityName(cityName);
        return this;
    }

    public WebToLeadData marketingConsent(Boolean marketingConsent) {
        setMarketingConsent(marketingConsent);
        return this;
    }

}
