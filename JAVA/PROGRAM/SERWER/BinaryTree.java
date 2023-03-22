/**
 * IMPORT BIBLIOTEK
 */
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * KLASA BINARYTREE
 */
public class BinaryTree<T extends Comparable<T>> implements BinaryTreeInterface<T> {

    /**
     * DEKLARACJA ZMIENNYCH
     */
    private Node<T> root = null;
    private String displayedTree;
    private String string;

    /**
     * FUNKCJA DODAJACA ELEMENT DO DRZEWA
     * @param value
     */
    @Override
    public void add(T value) {
        root = addRecursive(root, value);
    }

    /**
     * FUNKCJA DODAJACA ELEMENT REKURSYWNIE
     * @param current
     * @param value
     */
    @Override
    public Node<T> addRecursive(Node<T> current, T value) {

        /**
         * PRZYPADEK GDY CURRENT NODE JEST NULL
         */
        if(current == null) return new Node<>(value);

        /**
         * PRZYPADEK GDY VALUE JEST MNIEJSZE OD WARTOSCI OJCA
         */
        if(value.compareTo(current.value)<0) {
            current.left_child = addRecursive(current.left_child, value);
        }

        /**
         * PRZYPADEK GDY VALUE JEST WIEKSZE LUB ROWNE OD OJCA
         */
        else if (value.compareTo(current.value)>=0) {
            current.right_child = addRecursive(current.right_child, value);
        }

        return current;
    }

    /**
     * FUNKCJA WYSWIETLA DRZEWO
     */
    @Override
    public String display() {

        /**
         * ZAPISANIE POPRZEDNIEGO STRUMIENIA WYJSCIA
         */
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;

        /**
         * USTAWIENIE NA NOWY STRUMIEN WEJSCIA
         */
        System.setOut(ps);
        displayRecursive();
        System.out.flush();

        /**
         * PRZYWROCENIE POPRZEDNIEGO STRUMIENIA
         */
        System.setOut(old);
        return baos.toString().replaceAll(System.lineSeparator(), " ");
    }

    /**
     * FUNKCJA WYSWIETLA DRZEWO REKURSYWNIE
     */
    @Override
    public void displayRecursive() {

        /**
         * JESLI KORZEN JEST PUSTY
         */
        if (root == null) {
            return;
        }

        /**
         * JESLI KORZEN NIE JEST PUSTY
         */
        else{

            /**
             * POCZATKOWA WARTOSC
             */
            System.out.print(String.valueOf(root.value)+" ");

            /**
             * DEKLARACJA ZMIENNYCH
             */
            int count;
            ArrayList<Node<T>> nodes = new ArrayList<Node<T>>();
            nodes.add(root);
            boolean flag=true;

            /**
             * PETLA DZIALA DOPOKI NIE SKONCZA SIE NIEPUSTE WIERSZE DRZEWA
             */
            while (flag) {
                count = 0;

                /**
                 * SPRAWDZENIE CZY NASTEONY KORZEN NIE JEST PUSTY
                 */
                for (int i = 0; i < nodes.size(); i++) {
                    if (nodes.get(i).left_child != null || nodes.get(i).right_child != null) count++;
                }

                /**
                 * JESLI JEST TO PETLA SIE ZAKONCZY
                 */
                if (count == 0) flag = false;

                /**
                 * JESLI NIE TO PRZELICZY NASTEPNE WIERSZE
                 */
                else {
                    ArrayList<Node<T>> nodesnew = new ArrayList<Node<T>>();
                    for (int i = 0; i < nodes.size(); i++) {

                        /**
                         * SPRAWDZENIE CZY LEWE DZIECKO DANEGO ELEMENTU NIE JEST NULL
                         */
                        if (nodes.get(i).left_child == null) {
                            System.out.print("null ");
                            nodesnew.add(new Node("null"));
                        }

                        /**
                         * JESLI NIE JEST TO DRUKUJEMY JEGO WARTOSC
                         */
                        else {
                            System.out.print(String.valueOf(nodes.get(i).left_child.value) + " ");
                            nodesnew.add(nodes.get(i).left_child);
                        }

                        /**
                         * SPRAWDZENIE CZY PRAWE DZIECKO DANEGO ELEMENTU NIE JEST NULL
                         */
                        if (nodes.get(i).right_child == null) {
                            System.out.print("null ");
                            nodesnew.add(new Node("null"));
                        }

                        /**
                         * JESLI NIE JEST TO DRUKUJEMY JEGO WARTOSC
                         */
                        else {
                            System.out.print(String.valueOf(nodes.get(i).right_child.value) + " ");
                            nodesnew.add(nodes.get(i).right_child);
                        }
                    }

                    /**
                     * ZAMIANA LIST
                     */
                    nodes = nodesnew;
                }
            }
        }
    }

    /**
     * FUNKCJA USUWA DANY ELEMENT
     */
    @Override
    public void delete(T value) {
        root = deleteRecursive(root, value);
    }

    /**
     * FUNKCJA USUWA DANY ELEMENT REKURSYWNI
     * @param current
     * @param value
     * @return
     */
    @Override
    public Node<T> deleteRecursive(Node<T> current, T value) {
        if(current == null) return current;
        if(value.compareTo(current.value)<0) current.left_child = deleteRecursive(current.left_child, value);
        else if(current.value.compareTo(value)<0) current.right_child = deleteRecursive(current.right_child, value);
        else{
            if(current.left_child == null) return current.right_child;
            else if(current.right_child == null) return  current.left_child;
            current.value = findMinimum(current.right_child);
            current.right_child = deleteRecursive(current.right_child, current.value);
        }
        return current;
    }

    /**
     * FUNKCJA ZNAJDUJE MINIMUM
     */
    public T findMinimum(Node<T> current){
        T minimum = current.value;
        while (current.left_child != null){
            minimum = current.left_child.value;
            current = current.left_child;
        }
        return minimum;
    }

    /**
     * FUNKCJA SZUKA DANEGO ELEMENTU
     * @param value
     * @return
     */
    @Override
    public boolean search(T value) {
        return searchRecursive(root, value);
    }

    /**
     * FUNKCJA SZUKA DANEGO ELEMENTU REKURSYWNIE
     * @param current
     * @param value
     * @return
     */
    @Override
    public boolean searchRecursive(Node<T> current, T value) {
        if(current == null) return false;
        if(value.equals(current.value)) return true;
        return value.compareTo(current.value)<0 ? searchRecursive(current.left_child, value) : searchRecursive(current.right_child, value);
    }
}