import java.util.ArrayList;
import java.util.Iterator;

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
        } else {
            throw new IllegalArgumentException("Wrong input parameters.");
        }

        Attributes.insertVectorsIntoArrays(trainingPath, trainingAttributes, 1);
        Attributes.insertVectorsIntoArrays(testingPath, testingAttributes, 0);

        Iterator<Attributes> it = trainingAttributes.iterator();
        Iterator<Attributes> it2 = testingAttributes.iterator();
        int i = 0;
        while ( it.hasNext() && it2.hasNext() ) {
            trainingAttributes.get(i).distanceFromAnotherAttribute = Attributes.euclideanDistance(trainingAttributes.get(i), testingAttributes.get(i));
            i++;
            it.next();
            it2.next();
        }
        trainingAttributes.removeIf(a -> a.distanceFromAnotherAttribute == 0.0f);
        trainingAttributes.sort(new Comparator());


        //TODO: all below is just for debugging purposes
        for (Attributes v : trainingAttributes) {
            System.out.println(v.attributes.entrySet());
        }
        System.out.println("TRAINING");
        for (Attributes v : testingAttributes) {
            System.out.println(v.attributes.entrySet());
        }
        System.out.println("Testing");
        for (int k = 0; k < testingAttributes.size(); k++) {
            Attributes.euclideanDistance(trainingAttributes.get(k), testingAttributes.get(k));
        }
        System.out.println(Attributes.decisionAttributes);
    }
}