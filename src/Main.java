import java.util.ArrayList;

public class Main {
    //TODO: User attribute input
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
        } else {
            throw new IllegalArgumentException("Wrong input parameters.");
        }

        Attributes.insertVectorsIntoArrays(trainingPath, trainingAttributes, 1);
        Attributes.insertVectorsIntoArrays(testingPath, testingAttributes, 0);
        System.out.println("Training set location: " + trainingPath + "\nTesting set location: " + testingPath
                + "\nNumber of neighbours: " + numberOfNeighbours + "\nFound Decision attributes:" + Attributes.decisionAttributes);

        Comparator.regression(testingAttributes);
    }
}