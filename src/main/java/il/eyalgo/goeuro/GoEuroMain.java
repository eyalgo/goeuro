package il.eyalgo.goeuro;

import il.eyalgo.goeuro.model.Endpoint;
import il.eyalgo.goeuro.request.GoEuroRequest;
import il.eyalgo.goeuro.response.JsonResponseConverter;
import il.eyalgo.goeuro.writer.CsvWriter;

import java.net.URISyntaxException;
import java.nio.file.FileAlreadyExistsException;
import java.util.List;

public class GoEuroMain {
    private final static String CSV_SUFFIX = ".csv";

    public GoEuroMain() {
    }

    public static void main(String[] args) throws URISyntaxException {
        if (args.length < 1) {
            help();
            System.exit(1);
        }
        String querySuffix = args[0];
        String filePath = querySuffix + CSV_SUFFIX;
        if (args.length > 1) {
            filePath = args[1];
        }
        executeQuery(querySuffix, filePath);
    }

    private static void executeQuery(String querySuffix, String filePath) {
        GoEuroRequest request = new GoEuroRequest();
        JsonResponseConverter converter = new JsonResponseConverter();
        CsvWriter writer = new CsvWriter();

        try {
            String jsonArray = request.callApi(querySuffix);
            List<Endpoint> endpoints = converter.jsonArrayToEndpoints(jsonArray);
            writer.createCsv(filePath, endpoints);
        } catch (FileAlreadyExistsException e) {
            System.err.println(String.format("File: '%s' already exists. exiting...", filePath));
            System.exit(2);
        } catch (Exception e) {
            System.err.println("Unknown error. See stack trace");
            e.printStackTrace();
        }
    }

    private static void help() {
        System.err.println("Please provide correct input");
        System.err.println();
        System.err.println("Example: java ­jar GoEuroTest.jar \"STRING\"");
        System.err.println("You can set the ouput file as well.");
        System.err.println("Example: java ­jar GoEuroTest.jar \"STRING\" \"File_name\"");
        System.err.println(String.format("If output file not provided, then input string + %s will be created",
                CSV_SUFFIX));
    }
}
