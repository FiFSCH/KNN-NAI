import java.util.ArrayList;
import java.util.Iterator;

public class Comparator implements java.util.Comparator<Attributes> {

    @Override
    public int compare(Attributes a1, Attributes a2) {
        return Float.compare(a1.distanceFromAnotherAttribute, a2.distanceFromAnotherAttribute);
    }

    protected static void regression(ArrayList<Attributes> set2) {

        if (Main.numberOfNeighbours >= Main.trainingAttributes.size() || Main.numberOfNeighbours <= 0)
            throw new IllegalArgumentException("Provide different number of neighbours!");

        float correct = 0.0f, totalNumberOfCases = 0.0f;

        for (Attributes test : set2) {
            ArrayList<String> mostCommonDecisionAttribute = new ArrayList<>();

            Iterator<Attributes> it = Main.trainingAttributes.iterator();

            while ( it.hasNext() ) {
                Attributes attributes = it.next();
                attributes.distanceFromAnotherAttribute = Attributes.euclideanDistance(attributes, test);
            }

            Main.trainingAttributes.sort(new Comparator());
            Main.trainingAttributes.removeIf(a -> a.distanceFromAnotherAttribute == 0.0f);

            for (int j = 0; j < Main.numberOfNeighbours; j++) {
                mostCommonDecisionAttribute.add(Main.trainingAttributes.get(j).name);
            }

            String decision = mostFrequent(mostCommonDecisionAttribute);
            if (CLI.userInputFlag == 0) {
                if (test.name.equalsIgnoreCase(decision))
                    correct++;
                totalNumberOfCases++;
            } else if (CLI.userInputFlag == 1) {
                System.out.println("Your vector have been classified as " + decision);
            }
        }
        if (CLI.userInputFlag == 0) {
            float accuracy = (correct / totalNumberOfCases) * 100;
            String accuracyString = String.format("%.2f", accuracy);
            System.out.println("Total number of cases: " + totalNumberOfCases + "\nAccuracy: " + accuracyString + "%");
        }

    }

    static String mostFrequent(ArrayList<String> names) {
        String result = names.get(0);
        int max = 1, curr = 1;

        for (int i = 1; i < names.size(); i++) {
            if (names.get(i).equalsIgnoreCase(names.get(i - 1)))
                curr++;
            else {
                if (curr > max) {
                    max = curr;
                    result = names.get(i - 1);
                }
                curr = 1;
            }
        }
        if (curr > max) {
            result = names.get(names.size() - 1);
        }
        return result;
    }
}