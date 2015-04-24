package il.eyalgo.goeuro.model;

public class Endpoint {
    private static final String COMMA = ",";
    private final String _type;
    private final String _id;
    private final String name;
    private final String type;
    private final GeoPosition geoPosition;

    public Endpoint(String _type, String _id, String name, String type, GeoPosition geoPosition) {
        this._type = _type;
        this._id = _id;
        this.name = name;
        this.type = type;
        this.geoPosition = geoPosition;
    }

    public String asCsv() {
        //@formatter:off
        return new StringBuilder(_type).append(COMMA)
            .append(_id).append(COMMA)
            .append(name).append(COMMA)
            .append(type).append(COMMA)
            .append(geoPosition.getLatitude()).append(COMMA)
            .append(geoPosition.getLongitude())
            .toString();
        //@formatter:on
    }
}
