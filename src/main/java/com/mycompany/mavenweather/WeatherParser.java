/*
 * Author: George Othen
 * Title: Weather Parser
 * Date: 6/03/2016
 */
package com.mycompany.mavenweather;

import java.io.IOException;
import java.net.MalformedURLException;
import org.xml.sax.helpers.DefaultHandler;
import java.net.URL;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author Gurge
 */
public class WeatherParser {

    WeatherHandler handler;
    SAXParserFactory factory;
    SAXParser saxParser;
    InputSource source;
    URL weather;

    /**
     * Constructor creates weatherHandler & SAXParser
     * @throws MalformedURLException
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException 
     */
    public WeatherParser() throws MalformedURLException, ParserConfigurationException, SAXException, IOException {
        handler = new WeatherHandler();
        factory = SAXParserFactory.newInstance();
        saxParser = factory.newSAXParser();
    }

    /**
     * Method that allows weather information retrieval based on user input url
     * @param url a string that contains the url to the bbc weather rss feed
     * @return a string that contains weather information obtained from rss feed
     * @throws IOException
     * @throws SAXException 
     */
    public String getWeatherString(String url) throws IOException, SAXException {
        weather = new URL(url);
        saxParser.parse(new InputSource(weather.openStream()), handler);
        return handler.getWeatherString();
    }
    
    /**
     * Method that allows custom location weather information retrieval
     * @param id a string with the geonameId of the location retrieving weather information for
     * @return a string that contains weather information obtained from rss feed
     * @throws MalformedURLException
     * @throws SAXException
     * @throws IOException 
     */
    public String getWeatherStringCon(String id) throws MalformedURLException, SAXException, IOException{
        weather = new URL("http://open.live.bbc.co.uk/weather/feeds/en/" + id + "/observations.rss");
        saxParser.parse(new InputSource(weather.openStream()), handler);
        return handler.getWeatherString();
    }
    
    /**
     * Method that returns WeatherDescription object
     * @return WeatherDescription object with weather attributes: temperature, wind speed, humidity, etc
     */
    public WeatherDescription getWeatherInformation(){
        return handler.getWeatherDescription();
    }

}
