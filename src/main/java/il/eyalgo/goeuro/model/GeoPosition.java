package il.eyalgo.goeuro.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

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

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("latitude", latitude)
                .add("longitude", longitude)
                .toString();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(latitude, longitude);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof GeoPosition) {
            GeoPosition that = (GeoPosition) object;
            return Objects.equal(this.latitude, that.latitude)
                    && Objects.equal(this.longitude, that.longitude);
        }
        return false;
    }
}
