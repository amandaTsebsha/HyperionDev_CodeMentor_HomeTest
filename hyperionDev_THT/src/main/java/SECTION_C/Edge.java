package SECTION_C;


public class Edge implements Comparable<Edge> {
    private final int to;
    private final int weight;

    public Edge(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }

    public int getTo() {
        return to;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Edge other) {
        return Integer.compare(weight, other.weight);
    }

}
