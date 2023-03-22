/**
 * KLASA NODE
 */
public class Node<T> {

    /**
     * DEKLARACJA ZMIENNYCH
     */
    T value;
    Node<T> left_child, right_child;

    /**
     * KONSTRUKTOR KLASY NODE
     */
    public Node(T value){
        this.value = value;
        left_child = null;
        right_child = null;
    }
}