package org.example.apirest.utils;

import org.example.apirest.dto.location.CreateLocationDto;
import org.example.apirest.dto.route.CreateRouteDto;
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
        RouteHandler gpxHandler = new RouteHandler();

        saxParser.parse("Maraton-Costitx-Pina-Algaida-Montuiri-Comuna_de_Lloret-Costitx-Llubi.gpx", gpxHandler);

        CreateRouteDto result = gpxHandler.getRoute();

        System.out.println(result);
    }
}
