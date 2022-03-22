import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class CLI {
    private static boolean finish = true;
    protected static int userInputFlag = 0;
    protected static ArrayList<Attributes> userAttributes = new ArrayList<>();

    protected static void Menu() {
        DecimalFormatSymbols symbol = new DecimalFormatSymbols();
        symbol.setDecimalSeparator('.');
        DecimalFormat format = new DecimalFormat("0.#");
        format.setDecimalFormatSymbols(symbol);
        Scanner scan = new Scanner(System.in);

        while ( finish ) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("\nTraining set location: " + Main.trainingPath + "\nTesting set location: " + Main.testingPath
                    + "\nNumber of neighbours: " + Main.numberOfNeighbours + "\nVector Dimensionality: " + Main.vectorDimensionality
                    + "\nFound Decision attributes: " + Attributes.decisionAttributes + "\n");
            System.out.println("""
                    What would you like to do?
                    1. Test program using testing set.
                    2. Provide your own vector.
                    3. exit.
                    Enter number from 1-3:\s""");
            int input = scan.nextInt();
            switch (input) {
                case 1 -> {
                    userInputFlag = 0;
                    Comparator.regression(Main.testingAttributes);
                }
                case 2 -> {
                    userInputFlag = 1;
                    ArrayList<Float> tmp = new ArrayList<>();
                    for (int i = 0; i < Main.vectorDimensionality; i++) {
                        System.out.println("Enter a float number: ");
                        try {
                            tmp.add(format.parse(scan.next()).floatValue());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    HashMap<ArrayList<Float>, String> tmpMap = new HashMap<>();
                    tmpMap.put(tmp, null);
                    Attributes userAttribute = new Attributes(tmpMap, null);
                    userAttributes.add(userAttribute);
                    Comparator.regression(userAttributes);
                    System.out.println("Total number of cases: " + userAttributes.size());
                }
                case 3 -> {
                    scan.close();
                    finish = false;
                    System.exit(1);
                }
                default -> throw new IllegalArgumentException("Wrong input!");
            }
        }
    }
}