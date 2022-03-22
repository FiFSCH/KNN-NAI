public class Comparator implements java.util.Comparator<Attributes> {

    @Override
    public int compare(Attributes a1, Attributes a2) {
        return Float.compare(a1.distanceFromAnotherAttribute, a2.distanceFromAnotherAttribute);
    }
}
