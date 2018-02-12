/*
 * Author: George Othen
 * Title: Weather Handler
 * Date: 6/03/2016
 */
package com.mycompany.mavenweather;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Gurge
 */
public class WeatherHandler extends DefaultHandler {

    private boolean weather, item, description, nextItem;
    private int count = 1;
    private WeatherDescription descrip = new WeatherDescription();
    private StringBuffer buffer, buffer2;
    
    /**
     * 
     * @param uri The Namespace URI, or the empty string if the element has no Namespace URI or if Namespace processing is not being performed.
     * @param localName The local name (without prefix), or the empty string if Namespace processing is not being performed.
     * @param qName The qualified name (with prefix), or the empty string if qualified names are not available.
     * @param attributes The attributes attached to the element. If there are no attributes, it shall be an empty Attributes object.
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equals("item")) {
            item = true;
        }
        if (qName.equals("title") && item) {
            weather = true;
            buffer = new StringBuffer();
        }
        if (qName.equals("description")) {
            description = true;
            buffer2 = new StringBuffer();
        }
    }

    /**
     * 
     * @param uri The Namespace URI, or the empty string if the element has no Namespace URI or if Namespace processing is not being performed.
     * @param localName The local name (without prefix), or the empty string if Namespace processing is not being performed.
     * @param qName The qualified name (with prefix), or the empty string if qualified names are not available.
     * @throws SAXException 
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("item")) {
            item = false;
        }
        if (qName.equals("title")) {
            weather = false;
        }
        if (qName.equals("description")) {
            description = false;
        }
    }

    /**
     * 
     * @param ch The characters.
     * @param start The start position in the character array.
     * @param length  The number of characters to use from the character array.
     */
    @Override
    public void characters(char[] ch, int start, int length) {
        if (weather) {
            for (int i = start; i < (start + length); i++) {
                buffer.append(ch[i]);
            }
        }
        if (description && item) {
            for (int i = start; i < (start + length); i++) {
                buffer2.append(ch[i]);
            }
            String temperature = "Temperature: ";
            String windDir = "Wind Direction: ";
            String windSpd = "Wind Speed: ";
            String humidity = "Humidity: ";
            String pressure = "Pressure: ";
            String visibility = "Visibility: ";
            String information = buffer2.toString();
            descrip.setTemperature(information.substring(information.indexOf(temperature)
                    + temperature.length(), information.indexOf(windDir) - 2));
            descrip.setWindDir(information.substring(information.indexOf(windDir)
                    + windDir.length(), information.indexOf(windSpd) - 2));
            descrip.setWindSpd(information.substring(information.indexOf(windSpd)
                    + windSpd.length(), information.indexOf(humidity) - 2));
            descrip.setHumidity(information.substring(information.indexOf(humidity)
                    + humidity.length(), information.indexOf(pressure) - 2));
            descrip.setPressure(information.substring(information.indexOf(pressure)
                    + pressure.length(), information.indexOf(visibility) - 2));
            descrip.setVisibility(information.substring(information.indexOf(visibility)
                    + visibility.length(), information.length()));
        }
    }

    /**
     * Method that returns string with weather information
     * @return a string with weather information
     */
    public String getWeatherString() {
        return buffer.toString();
    }

    /**
     * Method that returns a WeatherDescription object
     * @return a WeatherDescriptionObject
     */
    public WeatherDescription getWeatherDescription() {
        return descrip;
    }
}
