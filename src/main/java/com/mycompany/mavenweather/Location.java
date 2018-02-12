/*
 * Author: George Othen
 * Title: Location
 * Date: 8/03/2016
 */
package com.mycompany.mavenweather;

/**
 *
 * @author George
 */
public class Location {

    private String placeName;
    private String geonameId;
    private String country;

    public Location() {
    }

    /**
     * 
     * @param placeName name of the place being searched
     */
    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    /**
     * 
     * @param geonameId the geonameId of the place being searched
     */
    public void setGeonameId(String geonameId) {
        this.geonameId = geonameId;
    }

    /**
     * 
     * @param country the country the place is located in
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * 
     * @return name of the place being searched
     */
    public String getPlaceName() {
        return placeName;
    }

    /**
     * 
     * @return the geonameId of the place being searched
     */
    public String getGeonameId() {
        return geonameId;
    }

    /**
     * 
     * @return the country the place is located in
     */
    public String getCountry() {
        return country;
    }

}
