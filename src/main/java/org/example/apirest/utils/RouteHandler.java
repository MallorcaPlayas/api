package org.example.apirest.utils;

import lombok.Data;
import org.example.apirest.dto.location.CreateLocationDto;
import org.example.apirest.dto.route.CreateRouteDto;
import org.example.apirest.model.Route;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class RouteHandler extends DefaultHandler {
    private static final String NAME = "name";
    private static final String DISTANCE_METERS = "gpxtrkx:Distance";
    private static final String DURATION_SECONDS = "gpxtrkx:TotalElapsedTime";
    private static final String ASCENDANT = "gpxtrkx:Ascent";
    private static final String DESCENDANT = "gpxtrkx:Descent";
    private static final String POINT = "trkpt";
    private static final String ELEVATION = "ele";
    private static final String TIME = "time";

    private CreateRouteDto route;
    private List<CreateLocationDto> locations;
    private CreateLocationDto currentLocation;
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
        this.route = new CreateRouteDto();
        this.route.setLocations(new ArrayList<>());
    }

    @Override
    public void startElement(String uri, String lName, String qName, Attributes attr) throws SAXException {
        switch (qName) {
            case POINT:
                this.currentLocation = new CreateLocationDto();
                this.currentLocation.setLatitude(Double.parseDouble(attr.getValue("lat")));
                this.currentLocation.setLongitude(Double.parseDouble(attr.getValue("lon")));
                break;
            case ELEVATION, TIME, NAME, DISTANCE_METERS, DURATION_SECONDS, ASCENDANT, DESCENDANT:
                this.elementValue = new StringBuilder();
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case NAME:
                this.route.setName(this.elementValue.toString());
            case POINT:
                this.locations.add(this.currentLocation);
                break;
            case ELEVATION:
                currentLocation.setElevation(Double.parseDouble(elementValue.toString()));
                break;
            case TIME:
                if (this.currentLocation == null) {
                    return;
                }
                String datetime = elementValue.toString().replace("Z", "");
                currentLocation.setTime(LocalDateTime.parse(datetime));
                break;
            case DISTANCE_METERS:
                this.route.setDistance(Double.parseDouble(elementValue.toString()));
                break;
            case DURATION_SECONDS:
                this.route.setDuration(Double.parseDouble(elementValue.toString()));
                break;
            case ASCENDANT:
                this.route.set
                break;
            case DESCENDANT:

                break;
        }
    }
}
