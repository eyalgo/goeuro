package il.eyalgo.goeuro;

public class GoEuroMain {
    private final static String DEFAULT_CSV_FILE = "goeuro.csv";

    public GoEuroMain() {
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            help();
            System.exit(1);
        }
    }

    private static void help() {
        System.out.println("Please provide correct input");
        System.out.println();
        System.out.println("Example: java ­jar GoEuroTest.jar \"STRING\"");
        System.out.println("You can set the ouput file as well.");
        System.out.println("Example: java ­jar GoEuroTest.jar \"STRING\" \"File_name\"");
        System.out.println(String.format("If output file not provided, then %s will be created", DEFAULT_CSV_FILE));
    }
}
