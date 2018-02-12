/*
 * Author: George Othen
 * Title: Weather Writer
 * Date: 10/03/2016
 */
package com.mycompany.mavenweather;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 *
 * @author Gurge
 */
public class WeatherWriter {
    File file = null;
    String geoname;
    XMLStreamWriter writer;
    
    /**
     * Constructor that creates all objects and starts xml file title
     * @param file the temporary file to be written to
     * @throws FileNotFoundException
     * @throws XMLStreamException 
     */
    public WeatherWriter(File file) throws FileNotFoundException, XMLStreamException{
        this.file = file;
        FileOutputStream stream = new FileOutputStream(file);
        XMLOutputFactory outputFactory = XMLOutputFactory.newFactory();
        writer = outputFactory.createXMLStreamWriter(stream, "UTF-8");
        writer.writeStartDocument("utf-8", "1.0");
        writer.writeStartElement("weatherSearches");
    }
    
    /**
     * 
     * @param search the search term/location being searched
     * @param found boolean with value reflecting whether location is bbc validated
     * @param geoname the geonameId of the location
     * @param done boolean with value reflecting whether all locations have been written
     * @throws FileNotFoundException
     * @throws XMLStreamException
     * @throws IOException 
     */
    public void write(String search, Boolean found, String geoname, Boolean done) throws FileNotFoundException, XMLStreamException, IOException{ 
        
        this.geoname = "";
        
        writer.writeStartElement("search");
        writer.writeAttribute("date", new Date().toString());
        
        writer.writeStartElement("term");
        writer.writeCharacters(search);
        writer.writeEndElement();
        
        writer.writeStartElement("found");
        writer.writeCharacters(Boolean.toString(found));
        writer.writeEndElement();
        
        writer.writeStartElement("geoNameID");
        if(!found){
            geoname = "";
        }
        else{
            this.geoname = geoname;
        }
        writer.writeCharacters(this.geoname);
        writer.writeEndElement();
        writer.writeEndElement();
        
        if(done){
            writer.writeEndElement();
            writer.close();
            System.out.println(file.getAbsolutePath());
        }
    } 
}
