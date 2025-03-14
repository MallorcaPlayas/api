package org.example.apirest.utils;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.GeoPoint;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import org.example.apirest.dto.location.CreateLocationDto;
import org.example.apirest.dto.route.CreateRouteDto;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class RouteHandler extends DefaultHandler {

    // name of the tags in the xml file
    private static final String NAME = "name";
    private static final String DISTANCE_METERS = "gpxtrkx:Distance";
    private static final String DURATION_SECONDS = "gpxtrkx:TotalElapsedTime";
    private static final String ASCENDANT_METERS = "gpxtrkx:Ascent";
    private static final String DESCENDANT_METERS = "gpxtrkx:Descent";
    private static final String POINT = "trkpt";
    private static final String WAY_POINT = "wpt";
    private static final String ELEVATION = "ele";
    private static final String TIME = "time";

    // Variables for save extracted data from xml file
    private CreateRouteDto route;
    private List<CreateLocationDto> trackedLocations;
    private List<CreateLocationDto> wayLocations;

    // Auxiliary variables for the read xml file
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
        this.trackedLocations = new ArrayList<>();
        this.wayLocations = new ArrayList<>();
    }

    @Override
    public void startElement(String uri, String lName, String qName, Attributes attr) throws SAXException {
        switch (qName) {
            case POINT:
                this.currentLocation = new CreateLocationDto();
                this.currentLocation.setPoint(
                        new GeoPoint(
                                Double.parseDouble(attr.getValue("lat")),
                                Double.parseDouble(attr.getValue("lon"))
                        ));
                break;
            case WAY_POINT:
                this.currentLocation = new CreateLocationDto();
                this.currentLocation.setPoint(
                        new GeoPoint(
                                Double.parseDouble(attr.getValue("lat")),
                                Double.parseDouble(attr.getValue("lon"))
                        ));
                break;
            case ELEVATION, TIME, NAME, DISTANCE_METERS, DURATION_SECONDS, ASCENDANT_METERS, DESCENDANT_METERS:
                this.elementValue = new StringBuilder();
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case NAME:
                this.route.setName(this.elementValue.toString());
                break;
            case POINT:
                this.trackedLocations.add(this.currentLocation);
                break;
            case WAY_POINT:
                this.wayLocations.add(this.currentLocation);
                break;
            case ELEVATION:
                if (this.currentLocation == null) {
                    return;
                }
                currentLocation.setElevation(Double.parseDouble(elementValue.toString()));
                break;
            case TIME:
                if (this.currentLocation == null) {
                    return;
                }
                String datetime = elementValue.toString().replace("Z", "");
                LocalDateTime localDateTime = LocalDateTime.parse(datetime);
                currentLocation.setTime(Timestamp.of(Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant())));
                break;
            case DISTANCE_METERS:
                this.route.setDistance(Double.parseDouble(elementValue.toString()));
                break;
            case DURATION_SECONDS:
                this.route.setDuration(Double.parseDouble(elementValue.toString()));
                break;
            case ASCENDANT_METERS:
                this.route.setElevationAsc(Double.parseDouble(elementValue.toString()));
                break;
            case DESCENDANT_METERS:
                this.route.setElevationDesc(Double.parseDouble(elementValue.toString()));
                break;
        }
    }
}
