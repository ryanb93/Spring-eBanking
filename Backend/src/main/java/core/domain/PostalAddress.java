package core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.validation.constraints.NotNull;
import org.springframework.web.util.HtmlUtils;

/**
 * A class that stores all the necessary data for an address.
 */
public class PostalAddress {

    /** House number of an address. */
    @NotNull
    private String houseNumber;
    
    /** Street of an address. */
    @NotNull
    private String street;      
    
    /** City of an address. */
    @NotNull
    private String city;   

    /** Country of an address. */
    @NotNull
    private String county;      
    
    /** Country of an address. */
    @NotNull
    private String country; 
    
    /** Post code of an address. */
    @NotNull
    private String postCode;

    /**
     * Empty default constructor needed by Spring to create a PostalAddress
     * object from the JSON request body. 
     * 
     * It creates an empty object and then goes through all the setters and
     * sets the values based on the JSON key/value pairs.
     */
    public PostalAddress() {
    }

    /**
     *
     * @param houseNumber - House number of an address.
     * @param street - Street of an address.
     * @param city - City of an address.
     * @param county - County of an address.
     * @param country - Country of an address.
     * @param postCode - Postal code of an address.
     */
    public PostalAddress(String houseNumber, String street, String city, String county, String country, String postCode) {
        super();
        this.setHouseNumber(houseNumber);
        this.setStreet(street);
        this.setCity(city);
        this.setCounty(county);
        this.setCountry(country);
        this.setPostCode(postCode);
    }

    /**
     * Sets the house number.
     *
     * @param houseNumber - the house number
     */
    public final void setHouseNumber(String houseNumber) {
        this.houseNumber = HtmlUtils.htmlEscape(houseNumber);
    }

    /**
     * Sets the street.
     *
     * @param street - the street
     */
    public final void setStreet(String street) {
        this.street = HtmlUtils.htmlEscape(street);
    }

    /**
     * Sets the city.
     *
     * @param city - the city
     */
    public final void setCity(String city) {
        this.city = HtmlUtils.htmlEscape(city);
    }

    /**
     * Sets the county.
     *
     * @param county - the county
     */
    public final void setCounty(String county) {
        this.county = HtmlUtils.htmlEscape(county);
    }

    /**
     * Sets the country.
     *
     * @param country - the country
     */
    public final void setCountry(String country) {
        this.country = HtmlUtils.htmlEscape(country);
    }

    /**
     * Sets the post code.
     *
     * @param postCode - the postal code
     */
    public final void setPostCode(String postCode) {
        this.postCode = HtmlUtils.htmlEscape(postCode);
    }

    /**
     * Returns the house number.
     *
     * @return the house number
     */
    public String getHouseNumber() {
        return this.houseNumber;
    }

    /**
     * Returns the street.
     *
     * @return the street
     */
    public String getStreet() {
        return this.street;
    }

    /**
     * Returns the city.
     *
     * @return the city
     */
    public String getCity() {
        return this.city;
    }

    /**
     * Returns the county.
     *
     * @return the county
     */
    public String getCounty() {
        return this.county;
    }

    /**
     * Returns the country.
     *
     * @return the country
     */
    public String getCountry() {
        return this.country;
    }

    /**
     * Returns the postal code.
     *
     * @return the postal code
     */
    public String getPostCode() {
        return this.postCode;
    }

    @Override
    public String toString() {
        return (houseNumber + ", " + street + ", " + city + ", " + county + ", " + country + ", " + postCode);
    }
    
    /**
     * This method checks to see that all the required data held on a Postal Address 
     * is present.
     * 
     * @return boolean true or false dependent on whether all expected fields are present and correct.
     */
    @JsonIgnore
    public boolean isValid() {
        boolean valid = true;
        
        if (this.getHouseNumber() == null || this.getHouseNumber().equals("")) {
            valid = false;
        } else if (this.getStreet() == null || this.getStreet().equals("")) {
            valid = false;
        } else if (this.getCity() == null || this.getCity().equals("")) {
            valid = false;
        } else if (this.getCounty() == null || this.getCounty().equals("")) {
            valid = false;
        } else if (this.getCountry() == null || this.getCountry().equals("")) {
            valid = false;
        } else if (this.getPostCode() == null || this.getPostCode().equals("")) {
            valid = false;
        }
        
        return valid;
    }

}
