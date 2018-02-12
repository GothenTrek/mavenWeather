/*
 * Author: George Othen
 * Title: Weather Description
 * Date: 9/03/2016
 */
package com.mycompany.mavenweather;

/**
 * Object to hold the weather description
 * @author Gurge
 */
public class WeatherDescription {
    String temperature, windDir, windSpd, humidity, pressure, visibility;

    /**
     * 
     * @param temperature the temperature
     */
    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    /**
     * 
     * @param windDir the direction the wind is travelling in
     */
    public void setWindDir(String windDir) {
        this.windDir = windDir;
    }

    /**
     * 
     * @param windSpd the speed of the wind
     */
    public void setWindSpd(String windSpd) {
        this.windSpd = windSpd;
    }

    /**
     * 
     * @param humidity the humidity
     */
    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    /**
     * 
     * @param pressure the pressure
     */
    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    /**
     * 
     * @param visibility description of the visibility
     */
    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    /**
     * 
     * @return the temperature
     */
    public String getTemperature() {
        return temperature;
    }

    /**
     * 
     * @return the direction the wind is travelling in
     */
    public String getWindDir() {
        return windDir;
    }

    /**
     * 
     * @return the speed of the wind
     */
    public String getWindSpd() {
        return windSpd;
    }

    /**
     * 
     * @return the humidity
     */
    public String getHumidity() {
        return humidity;
    }

    /**
     * 
     * @return the pressure
     */
    public String getPressure() {
        return pressure;
    }

    /**
     * 
     * @return description of the visibility
     */
    public String getVisibility() {
        return visibility;
    }
    
    @Override
    public String toString(){
        return temperature + windDir + humidity + pressure + visibility;
    }
}
