package il.eyalgo.goeuro.writer;

import il.eyalgo.goeuro.model.Endpoint;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.google.common.collect.Lists;

public class CsvWriter {

    public CsvWriter() {
    }

    public void createCsv(String fileName, List<Endpoint> endpoints) throws IOException {
        Path path = Files.createFile(Paths.get(fileName));
        Iterable<? extends CharSequence> lines = endpointsAsLines(endpoints);
        Files.write(path, lines, Charset.defaultCharset());
    }
    
    private List<String> endpointsAsLines(List<Endpoint> endpoints) {
        List<String> lines = Lists.newArrayList();
        for (Endpoint endpoint : endpoints) {
            lines.add(endpoint.asCsv());
        }
        return lines;
    }
}
