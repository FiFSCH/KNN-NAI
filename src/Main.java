import java.util.ArrayList;

public class Main {
    protected static String trainingPath;
    protected static String testingPath;
    protected static int numberOfNeighbours;

    protected static ArrayList<Attributes> trainingAttributes = new ArrayList<>();
    protected static ArrayList<Attributes> testingAttributes = new ArrayList<>();

    public static void main(String[] args) {
        if (args.length == 3) {
            trainingPath = args[0];
            testingPath = args[1];
            numberOfNeighbours = Integer.parseInt(args[2]);
        }

        Attributes.insertVectorsIntoArrays(trainingPath, trainingAttributes);
        Attributes.insertVectorsIntoArrays(testingPath, testingAttributes);

        for (Attributes v : trainingAttributes) {
            System.out.println(v.attributes.entrySet());
        }
        System.out.println("TRAINING");
        for (Attributes v : testingAttributes) {
            System.out.println(v.attributes.entrySet());
        }
        System.out.println("Testing");
    }

}
