/*
 * Author: George Othen
 * Title: Search Handler
 * Date: 8/03/2016
 */
package com.mycompany.mavenweather;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author George
 */
public class SearchHandler extends DefaultHandler {

    boolean getGeoID;
    StringBuffer buffer;
    Location location;
    public static ArrayList<Location> list;
    SearchParser parser;

    /**
     * 
     * @param parser 
     */
    public SearchHandler(SearchParser parser) {
        this.parser = parser;
        location = new Location();
        list = new ArrayList();
    }

    /**
     * 
     * @param uri The Namespace URI, or the empty string if the element has no Namespace URI or if Namespace processing is not being performed.
     * @param localName  The local name (without prefix), or the empty string if Namespace processing is not being performed.
     * @param qName The qualified name (with prefix), or the empty string if qualified names are not available.
     * @param attributes The attributes attached to the element. If there are no attributes, it shall be an empty Attributes object.
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        buffer = new StringBuffer();
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
        if (qName.equalsIgnoreCase("geoname")) {
            location = new Location();
        }
        switch (qName) {
            case "name":
                location.setCountry(buffer.toString());
                break;
            case "geonameId":
                location.setGeonameId(buffer.toString());
                break;
            case "countryName":
                location.setPlaceName(buffer.toString());
                break;
        }
        try {
            if (buffer.toString().equalsIgnoreCase(location.getGeonameId())) {
                if (location.getGeonameId() != null && parser.checkValidity(location.getGeonameId())) {
                    list.add(location);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(SearchHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * 
     * @param ch The characters
     * @param start the start position in the character array
     * @param length the number of characters to use from the character array
     */
    @Override
    public void characters(char[] ch, int start, int length) {
        for (int i = start; i < start + length; i++) {
            buffer.append(ch[i]);
        }
    }

    /**
     * Method that returns validated location data
     * @return the ArrayList with the validated location data
     */
    public ArrayList getList() {
        return list;
    }
}
