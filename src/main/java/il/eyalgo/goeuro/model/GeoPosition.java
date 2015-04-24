package il.eyalgo.goeuro.model;

public class GeoPosition {

    private final double latitude;
    private final double longitude;

    public GeoPosition(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
