package il.eyalgo.goeuro.writer;
import static org.hamcrest.Matchers.emptyCollectionOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import il.eyalgo.goeuro.model.Endpoint;
import il.eyalgo.goeuro.model.GeoPosition;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.google.common.collect.Lists;


public class CsvWriterTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    private final String csvFileName = "output.csv";
    private CsvWriter writer;
    
    @Before
    public void setup() throws IOException {
        writer = new CsvWriter();
        folder.create();
    }
    
    @After
    public void tearDown() {
        folder.delete();
    }

    @Test
    public void when_empty_list_of_endpoints_then_file_is_empty() throws IOException {
        String fileName = folder.getRoot() + File.separator +  csvFileName;
        List<Endpoint> endpoints = Collections.emptyList();
        writer.createCsv(fileName, endpoints);

        Path path = Paths.get(fileName);
        assertThat(Files.exists(path), is(true));
        List<String> allLines = Files.readAllLines(path, Charset.defaultCharset());
        assertThat(allLines, emptyCollectionOf(String.class));
    }
    
    @Test
    public void writing_positions_as_csv() throws IOException {
        String fileName = folder.getRoot() + File.separator +  csvFileName;
        GeoPosition position1 = new GeoPosition(1.2, 2.3);
        Endpoint endpoint1 = new Endpoint("1_type", "1_id", "n1", "t1", position1);
        
        GeoPosition position2 = new GeoPosition(4.5, 5.6);
        Endpoint endpoint2 = new Endpoint("2_type", "2_id", "n2", "t2", position2);
        
        List<Endpoint> positions = Lists.newArrayList(endpoint1, endpoint2);
        writer.createCsv(fileName, positions);

        Path path = Paths.get(fileName);
        List<String> allLines = Files.readAllLines(path, Charset.defaultCharset());
        assertThat(allLines.size(), equalTo(2));
        assertThat(allLines.get(0), equalTo("1_type,1_id,n1,t1,1.2,2.3"));
        assertThat(allLines.get(1), equalTo("2_type,2_id,n2,t2,4.5,5.6"));
    }
}
