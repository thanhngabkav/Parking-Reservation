package com.atp.webservice.parking_reservation_10.services.algorithms;

import java.io.Serializable;

public class MapHelper implements Serializable {

    public double distance(double lat1, double lon1, double lat2, double lon2, char unit) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515; // Meter
        if (unit == 'K') {//Kilometer
            dist = dist * 1.609344;
        } else if (unit == 'N') { //Nautical Miles
            dist = dist * 0.8684;
        }
        return (dist);
    }

    /**
     *  Converts decimal degrees to radians
     * @param deg
     * @return
     */
    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    /**
     *  Converts radians to decimal degrees
     * @param rad
     * @return
     */
    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

}
