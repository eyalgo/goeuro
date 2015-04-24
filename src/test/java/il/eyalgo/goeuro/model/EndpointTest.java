package il.eyalgo.goeuro.model;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

public class EndpointTest {
    @Test
    public void endpoint_creates_correct_csv_line() {
        Endpoint endpoint = new Endpoint("the_type", "the_id", "a---name", "a---type", new GeoPosition(52.39886, 13.06566));
        String csvFormat = endpoint.asCsv();

        String ezpectedCsvFormat = "the_type,the_id,a---name,a---type,52.39886,13.06566";
        assertThat("CSV format is wrong", csvFormat, equalTo(ezpectedCsvFormat));
    }
}
