package org.example.apirest.utils;

import org.example.apirest.dto.location.LocationDto;
import org.example.apirest.model.Location;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        LocationHandler gpxHandler = new LocationHandler();

        saxParser.parse("Maraton-Costitx-Pina-Algaida-Montuiri-Comuna_de_Lloret-Costitx-Llubi.gpx", gpxHandler);

        List<LocationDto> result = gpxHandler.getLocations();

        System.out.println(result);
    }
}
