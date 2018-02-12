/*
 * Author: George Othen
 * Title: Weather Manager
 * Date: 10/03/2016
 */
package com.mycompany.mavenweather;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import org.xml.sax.SAXException;

/**
 *
 * @author Gurge
 */
public class WeatherManager {
    WeatherReader reader;
    WeatherWriter writer;
    SearchParser parser;
    File file = null;
    String search;
    
    public WeatherManager(){        
    }
    
    /**
     * 
     * @param search the search term/location being searched
     * @throws IOException
     * @throws MalformedURLException
     * @throws XMLStreamException
     * @throws ParserConfigurationException
     * @throws SAXException 
     */
    public void manageWeather(String search) throws IOException, MalformedURLException, XMLStreamException, ParserConfigurationException, SAXException{
        parser = new SearchParser();        
        file = File.createTempFile("Search", ".xml");
        reader = new WeatherReader();
        writer = new WeatherWriter(file);
        
        //retrieve validated locations
        String[] geonames = reader.read(search);
        
        //writes all validated locations to xml file
        for(int i = 0; i < geonames.length; i++){
            writer.write(search, parser.checkValidity(geonames[i]), geonames[i], i == geonames.length-1);
        }
    }
}
