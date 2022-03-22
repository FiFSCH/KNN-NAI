import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Attributes {
    protected static ArrayList<String> decisionAttributes = new ArrayList<>();
    HashMap<ArrayList<Float>, String> attributes;
    protected String name;
    protected float distanceFromAnotherAttribute;


    public Attributes(HashMap<ArrayList<Float>, String> attributes, String name) {
        this.attributes = attributes;
        this.name = name;
    }

    protected static void insertVectorsIntoArrays(String path, ArrayList<Attributes> whichVectorsList, int flag) {
        try {

            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();

            while ( line != null ) {
                String[] whichDataSet = line.split(",");
                ArrayList<Float> tmp = new ArrayList<>();
                HashMap<ArrayList<Float>, String> tmpMap = new HashMap<>();

                for (int i = 0; i < whichDataSet.length - 1; i++) {
                    tmp.add(Float.parseFloat(whichDataSet[i]));
                }
                tmpMap.put(tmp, whichDataSet[whichDataSet.length - 1]);
                if (flag == 1) {
                    decisionAttributes.add(whichDataSet[whichDataSet.length - 1]);
                    decisionAttributes = decisionAttributes.stream().distinct().collect(Collectors.toCollection(ArrayList<String>::new));
                }
                whichVectorsList.add(new Attributes(tmpMap, whichDataSet[whichDataSet.length - 1]));
                line = reader.readLine();
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //I assume that dimensions are the same that's why in the loop im using size of one of them
    protected static float euclideanDistance(Attributes attribute1, Attributes attribute2) {
        float distance = 0.0f;
        ArrayList<Float> tmp1 = convertSetIntoArray(attribute1);
        ArrayList<Float> tmp2 = convertSetIntoArray(attribute2);

        for (int i = 0; i < tmp1.size(); i++) {
            distance += (float) Math.pow((tmp1.get(i) - tmp2.get(i)), 2);
        }
        return (float) Math.sqrt(distance);
    }

    private static ArrayList<Float> convertSetIntoArray(Attributes attribute) {
        ArrayList<Float> converter = new ArrayList<>();
        for (ArrayList<Float> floats : attribute.attributes.keySet()) {
            converter = floats;
        }
        return converter;
    }
}