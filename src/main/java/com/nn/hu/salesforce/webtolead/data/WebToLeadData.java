package com.nn.hu.salesforce.webtolead.data;
    
public class WebToLeadData {

    private final static int OBJ_HASH = 3461;
    
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String telephoneNumber;
    private String zipCode;
    private String streetName;
    private String cityName;
    private Boolean marketingConsent;
    
    //private static final long serialVersionUID = -5285244303L;

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

    public String getFirstName() {
        return this.firstName;
    }

    private void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    private void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    private void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getTelephoneNumber() {
        return this.telephoneNumber;
    }

    private void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getZipCode() {
        return this.zipCode;
    }

    private void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreetName() {
        return this.streetName;
    }

    private void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCityName() {
        return this.cityName;
    }

    private void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Boolean isMarketingConsent() {
        return this.marketingConsent;
    }

    public Boolean getMarketingConsent() {
        return this.marketingConsent;
    }

    private void setMarketingConsent(Boolean marketingConsent) {
        this.marketingConsent = marketingConsent;
    }

    @Override
    public int hashCode() {
        int hash = WebToLeadData.OBJ_HASH;

        hash = 89 * hash + (this.firstName == null ? 0 : this.firstName.hashCode());
        hash = 89 * hash + (this.lastName == null ? 0 : this.lastName.hashCode());
        hash = 89 * hash + (this.emailAddress == null ? 0 : this.emailAddress.hashCode());
        hash = 89 * hash + (this.zipCode == null ? 0 : this.zipCode.hashCode());

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        // Shallow comparison
        if(obj == null) {
            return false;
        }

        if(obj.getClass() != this.getClass()) {
            return false;
        }

        if(obj.hashCode() != this.hashCode()) {
            return false;
        }

        // Deep comparison
        final WebToLeadData other = (WebToLeadData) obj;
        if((this.firstName == null) ? (other.firstName != null) : !this.firstName.equals(other.firstName)) {
            return false;
        }

        if((this.lastName == null) ? (other.lastName != null) : !this.lastName.equals(other.lastName)) {
            return false;
        }

        if((this.emailAddress == null) ? (other.emailAddress != null) : !this.emailAddress.equals(other.emailAddress)) {
            return false;
        }

        if((this.zipCode == null) ? (other.zipCode != null) : !this.zipCode.equals(other.zipCode)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "WebToLead data[" + this.hashCode() + "]";
    }
}