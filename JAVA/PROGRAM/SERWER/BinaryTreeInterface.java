/**
 * INTERFEJS DLA KLASY BINARYTREE
 * @param <T>
 */
public interface BinaryTreeInterface<T> {

    /**
     * DEKLARACJA METOD KLASY BINARYTREE
     */
    public void add(T value);
    public Node<T> addRecursive(Node<T> current, T value);
    public String display();
    public void displayRecursive();
    public void delete(T value);
    public Node<T> deleteRecursive(Node<T> node, T value);
    public boolean search(T value);
    public boolean searchRecursive(Node<T> current, T value);
    public T findMinimum(Node<T> node);
}
