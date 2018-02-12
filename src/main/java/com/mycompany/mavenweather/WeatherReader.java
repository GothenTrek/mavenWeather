/*
 * Author: George Othen
 * Title: Weather Reader 
 * Date: 10/03/2016
 */
package com.mycompany.mavenweather;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import org.xml.sax.InputSource;

/**
 *
 * @author Gurge
 */
public class WeatherReader {
    
    /**
     * 
     * @param search the search term/location being searched
     * @return a string array with the geonameId's
     * @throws MalformedURLException
     * @throws IOException
     * @throws XMLStreamException 
     */
    public String[] read(String search) throws MalformedURLException, IOException, XMLStreamException{
        //if element tag has been found
        boolean bGeo = false;
        //count
        int i = 0;
        String[] geoNames = new String[6];
        String uri = "http://api.geonames.org/search?q=" + search + "&maxRows=5&lang=en&username=eeu24e";
        URL url = new URL(uri);
        InputStream input = url.openStream();
        XMLInputFactory xmlInput = XMLInputFactory.newInstance();
        XMLStreamReader xmlStream = xmlInput.createXMLStreamReader(uri, input);
        int event = xmlStream.getEventType();
        while(true){
            event = xmlStream.next();
            switch(event){
                
                case XMLStreamConstants.START_ELEMENT:
                    if(xmlStream.getLocalName().equals("geonameId")){
                        bGeo = true;
                    }
                    break;
                case XMLStreamConstants.CHARACTERS:
                    if(bGeo){
                        geoNames[i] = xmlStream.getText();
                        bGeo = false;
                        i++;
                    }
            }
            if(!xmlStream.hasNext())break;
        }
        return geoNames;
    }
}
