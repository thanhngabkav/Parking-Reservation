package com.atp.webservice.parking_reservation_10.config;

import java.util.ArrayList;

public class GenerateClass {
    public static class Location
    {
        private double lat;

        public double getLat() { return this.lat; }

        public void setLat(double lat) { this.lat = lat; }

        private double lng;

        public double getLng() { return this.lng; }

        public void setLng(double lng) { this.lng = lng; }
    }

    public static class Northeast
    {
        private double lat;

        public double getLat() { return this.lat; }

        public void setLat(double lat) { this.lat = lat; }

        private double lng;

        public double getLng() { return this.lng; }

        public void setLng(double lng) { this.lng = lng; }
    }

    public static class Southwest
    {
        private double lat;

        public double getLat() { return this.lat; }

        public void setLat(double lat) { this.lat = lat; }

        private double lng;

        public double getLng() { return this.lng; }

        public void setLng(double lng) { this.lng = lng; }
    }

    public static class Viewport
    {
        private Northeast northeast;

        public Northeast getNortheast() { return this.northeast; }

        public void setNortheast(Northeast northeast) { this.northeast = northeast; }

        private Southwest southwest;

        public Southwest getSouthwest() { return this.southwest; }

        public void setSouthwest(Southwest southwest) { this.southwest = southwest; }
    }

    public static class Geometry
    {
        private Location location;

        public Location getLocation() { return this.location; }

        public void setLocation(Location location) { this.location = location; }

        private Viewport viewport;

        public Viewport getViewport() { return this.viewport; }

        public void setViewport(Viewport viewport) { this.viewport = viewport; }
    }


    public static class Opening_hours
    {
        private boolean open_now;

        public boolean getOpen_now() { return this.open_now; }

        public void setOpen_now(boolean open_now) { this.open_now = open_now; }

        private ArrayList<String> weekday_text;

        public ArrayList<String> getWeekday_text() { return this.weekday_text; }

        public void setWeekday_text(ArrayList<String> weekday_text) { this.weekday_text = weekday_text; }
    }

    public static class Photo
    {
        private int height;

        public int getHeight() { return this.height; }

        public void setHeight(int height) { this.height = height; }

        private ArrayList<String> html_attributions;

        public ArrayList<String> getHtml_attributions() { return this.html_attributions; }

        public void setHtml_attributions(ArrayList<String> html_attributions) { this.html_attributions = html_attributions; }

        private String photo_reference;

        public String getPhoto_reference() { return this.photo_reference; }

        public void setPhoto_reference(String photo_reference) { this.photo_reference = photo_reference; }

        private int width;

        public int getWidth() { return this.width; }

        public void setWidth(int width) { this.width = width; }
    }

    public static class RootObject
    {
        private Geometry geometry;

        public Geometry getGeometry() { return this.geometry; }

        public void setGeometry(Geometry geometry) { this.geometry = geometry; }

        private String icon;

        public String getIcon() { return this.icon; }

        public void setIcon(String icon) { this.icon = icon; }

        private String id;

        public String getId() { return this.id; }

        public void setId(String id) { this.id = id; }

        private String name;

        public String getName() { return this.name; }

        public void setName(String name) { this.name = name; }


        private Opening_hours opening_hours;

        public Opening_hours getOpening_hours() { return this.opening_hours; }

        public void setOpening_hours(Opening_hours opening_hours) { this.opening_hours = opening_hours; }

        private ArrayList<Photo> photos;

        public ArrayList<Photo> getPhotos() { return this.photos; }

        public void setPhotos(ArrayList<Photo> photos) { this.photos = photos; }

        private String place_id;

        public String getPlace_id() { return this.place_id; }

        public void setPlace_id(String place_id) { this.place_id = place_id; }

        private double rating;

        public double getRating() { return this.rating; }

        public void setRating(double rating) { this.rating = rating; }

        private String reference;

        public String getReference() { return this.reference; }

        public void setReference(String reference) { this.reference = reference; }

        private String scope;

        public String getScope() { return this.scope; }

        public void setScope(String scope) { this.scope = scope; }

        private ArrayList<String> types;

        public ArrayList<String> getTypes() { return this.types; }

        public void setTypes(ArrayList<String> types) { this.types = types; }

        private String vicinity;

        public String getVicinity() { return this.vicinity; }

        public void setVicinity(String vicinity) { this.vicinity = vicinity; }
    }
}
