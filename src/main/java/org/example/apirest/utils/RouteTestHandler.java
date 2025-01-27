package org.example.apirest.utils;

import lombok.Data;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
public class RouteTestHandler extends DefaultHandler {
    private static final String SEGMENTS = "trkseg";
    private static final String POINT = "trkpt";
    private static final String ELEVATION = "ele";
    private static final String TIME = "time";

    private RouteTest route;
    private LocationTest currentLocation;
    private StringBuilder elementValue;

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (elementValue == null) {
            elementValue = new StringBuilder();
        } else {
            elementValue.append(ch, start, length);
        }
    }

    @Override
    public void startDocument() throws SAXException {
        this.route = new RouteTest();
        this.route.setSegment(new ArrayList<>());
    }

    @Override
    public void startElement(String uri, String lName, String qName, Attributes attr) throws SAXException {
        switch (qName) {
            case POINT:
                this.currentLocation = new LocationTest();
                this.currentLocation.setLat(Double.parseDouble(attr.getValue("lat")));
                this.currentLocation.setLon(Double.parseDouble(attr.getValue("lon")));
                break;
            case ELEVATION , TIME:
                this.elementValue = new StringBuilder();
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case POINT:
                this.route.getSegment().add(this.currentLocation);
                break;
            case ELEVATION:
                currentLocation.setEle(Double.parseDouble(elementValue.toString()));
                break;
            case TIME:
                if (this.currentLocation == null) {
                    return;
                }
                String datetime = elementValue.toString().replace("Z", "");
                currentLocation.setDateTime(LocalDateTime.parse(datetime));
                break;
        }
    }
}
