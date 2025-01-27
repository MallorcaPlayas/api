package org.example.apirest.utils;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        RouteTestHandler routeTestHandler = new RouteTestHandler();

        saxParser.parse("Maraton-Costitx-Pina-Algaida-Montuiri-Comuna_de_Lloret-Costitx-Llubi.gpx", routeTestHandler);

        RouteTest result = routeTestHandler.getRoute();

        List<LocationTest> points = result.getSegment();

        System.out.println(points);
    }
}
