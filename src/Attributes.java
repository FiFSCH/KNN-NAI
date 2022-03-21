import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Attributes {
    HashMap<ArrayList<Double>, String> attributes;

    public Attributes(HashMap<ArrayList<Double>, String> attributes) {
        this.attributes = attributes;
    }

    public static void insertVectorsIntoArrays(String path, ArrayList<Attributes> whichVectorsList) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();

            while ( line != null ) {
                String[] whichDataSet = line.split(",");
                ArrayList<Double> tmp = new ArrayList<>();
                HashMap<ArrayList<Double>, String> tmpMap = new HashMap<>();

                for (int i = 0; i < whichDataSet.length - 1; i++) {
                    tmp.add(Double.parseDouble(whichDataSet[i]));
                }

                tmpMap.put(tmp, whichDataSet[whichDataSet.length - 1]);
                whichVectorsList.add(new Attributes(tmpMap));
                line = reader.readLine();
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
