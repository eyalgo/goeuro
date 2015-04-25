package il.eyalgo.goeuro.model;

import com.google.common.base.MoreObjects;
import com.google.gson.annotations.SerializedName;
import com.google.common.base.Objects;

public class Endpoint {
    private static final String COMMA = ",";
    private final String _type;
    private final String _id;
    private final String name;
    private final String type;
    @SerializedName("geo_position")
    private final GeoPosition geoPosition;

    public Endpoint(String _type, String _id, String name, String type, GeoPosition geoPosition) {
        this._type = _type;
        this._id = _id;
        this.name = name;
        this.type = type;
        this.geoPosition = geoPosition;
    }

    public String asCsv() {
        return new StringBuilder()
                .append(_type).append(COMMA)
                .append(_id).append(COMMA)
                .append(name).append(COMMA)
                .append(type).append(COMMA)
                .append(geoPosition.getLatitude()).append(COMMA)
                .append(geoPosition.getLongitude())
                .toString();
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("_type", _type)
                .add("_id", _id)
                .add("name", name)
                .add("type", type)
                .add("geoPosition", geoPosition)
                .toString();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(_type, _id, name, type, geoPosition);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Endpoint) {
            Endpoint that = (Endpoint) object;
            return Objects.equal(this._type, that._type)
                    && Objects.equal(this._id, that._id)
                    && Objects.equal(this.name, that.name)
                    && Objects.equal(this.type, that.type)
                    && Objects.equal(this.geoPosition, that.geoPosition);
        }
        return false;
    }
}
