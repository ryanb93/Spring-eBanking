package core.domain;

/**
 * A class that stores all the necessary data for an address.
 */
public class PostalAddress {

    private String houseNumber; //House number of an address.
    private String street;      //Street of an address.
    private String city;        //The city of an address.
    private String county;      //The county of an address.
    private String country;     //The country of an address.
    private String postalCode;  //The postal code of an address.

    /**
     * Empty default constructor needed by Spring to create a PostalAddress
     * object from the JSON request body. 
     * 
     * It creates an empty object and then goes through all the setters and
     * sets the values based on the JSON key/value pairs.
     */
    public PostalAddress() {
        super();
    }

    /**
     *
     * @param houseNumber - House number of an address.
     * @param street - Street of an address.
     * @param city - City of an address.
     * @param county - County of an address.
     * @param country - Country of an address.
     * @param postalCode - Postal code of an address.
     */
    public PostalAddress(String houseNumber, String street, String city, String county, String country, String postalCode) {
        super();
        this.setHouseNumber(houseNumber);
        this.setStreet(street);
        this.setCity(city);
        this.setCounty(county);
        this.setCountry(country);
        this.setPostalCode(postalCode);
    }

    /**
     * Sets the house number.
     *
     * @param houseNumber - the house number
     */
    public final void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    /**
     * Sets the street.
     *
     * @param street - the street
     */
    public final void setStreet(String street) {
        this.street = street;
    }

    /**
     * Sets the city.
     *
     * @param city - the city
     */
    public final void setCity(String city) {
        this.city = city;
    }

    /**
     * Sets the county.
     *
     * @param county - the county
     */
    public final void setCounty(String county) {
        this.county = county;
    }

    /**
     * Sets the country.
     *
     * @param country - the country
     */
    public final void setCountry(String country) {
        this.country = country;
    }

    /**
     * Sets the postal code.
     *
     * @param postalCode - the postal code
     */
    public final void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
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
    public String getPostalCode() {
        return this.postalCode;
    }

    @Override
    public String toString() {
        return (houseNumber + ", " + street + ", " + city + ", " + county + ", " + country + ", " + postalCode);
    }

}
