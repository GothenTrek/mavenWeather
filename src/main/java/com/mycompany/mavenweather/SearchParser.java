/*
 * Author: George Othen
 * Title: Search Parser
 * Date: 8/03/2016
 */
package com.mycompany.mavenweather;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author George
 */
public class SearchParser {

    SearchHandler handler2;
    SAXParserFactory factory2;
    SAXParser saxParser2;
    URL search;

    /**
     * 
     * @throws MalformedURLException
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException 
     */
    public SearchParser() throws MalformedURLException, ParserConfigurationException, SAXException, IOException {
        handler2 = new SearchHandler(this);
        factory2 = SAXParserFactory.newInstance();
        saxParser2 = factory2.newSAXParser();
    }

    /**
     * 
     * @param placeName the name of the place being searched
     * @return ArrayList of bbc validated locations
     * @throws IOException
     * @throws SAXException 
     */
    public ArrayList getLocationNames(String placeName) throws IOException, SAXException {
        search = new URL("http://api.geonames.org/search?q=" + placeName + "&maxRows=3&lang=en&username=eeu24e");
        saxParser2.parse(new InputSource(search.openStream()), handler2);
        return handler2.getList();
    }

    /**
     * 
     * @param id the geonameId being validated
     * @return boolean that determines existence of a page
     * @throws MalformedURLException
     * @throws IOException 
     */
    public boolean checkValidity(String id) throws MalformedURLException, IOException {
        //http://stackoverflow.com/questions/1378199/how-to-check-if-a-url-exists-or-returns-404-with-java
        URL u = new URL("http://open.live.bbc.co.uk/weather/feeds/en/" + id + "/observations.rss");
        HttpURLConnection huc = (HttpURLConnection) u.openConnection();
        huc.setRequestMethod("HEAD");
        huc.connect();
        int code = huc.getResponseCode();
        if (code == 404) {
            return false;
        } else {
            return true;
        }
    }

}
