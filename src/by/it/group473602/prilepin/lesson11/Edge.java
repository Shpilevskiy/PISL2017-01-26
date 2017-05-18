package by.it.group473602.prilepin.lesson11;

public class Edge {
    int from;
    int to;
    int weight;

    public Edge(int u, int v, int w) {
        this.from = u;
        this.to = v;
        this.weight = w;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

}
