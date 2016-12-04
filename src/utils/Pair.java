package utils;

public class Pair<F, S> {
    private F first;    // the first member of pair
    private S second;   // the second member of pair

    public Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    public F getFirst() {
        return first;
    }

    public S getSecond() {
        return second;
    }
}