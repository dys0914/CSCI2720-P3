package project3;

public class NodeType<T extends Comparable<T>> {
    public T info;
    public NodeType<T> left;
    public NodeType<T> right;
    public NodeType(T key) {
        info = key;
    }
}
