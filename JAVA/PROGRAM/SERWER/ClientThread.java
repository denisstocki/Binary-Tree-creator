/**
 * IMPORT BIBLIOTEK
 */
import java.io.*;
import java.io.Console;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * KLASA CLEINTTHREAD
 */
public class ClientThread extends Thread {

    /**
     * DEKLARACJA ZMIENNYCH
     */
    private Socket socket;
    int client_number;

    /**
     * KONSTRUKTOR KLASY CLIENTTHREAD
     * @param socket
     */
    public ClientThread(Socket socket, int client_number) {
        this.socket = socket;
        this.client_number = client_number;
    }

    /**
     * FUNKCJA STARTUJACA WATEK
     */
    public void run() {

        /**
         * PROBUJEMY KOMUNIKOWAC SIE Z KLIENTEM
         */
        try {

            /**
             * DEKLRACJA ZMIENNYCH
             */
            String tree_type = new String();
            boolean tree_picked = false;
            boolean close = false;
            int to_int;
            double to_double;

            /**
             * DRZEWA BINARNE POSZCZEGOLNYCH TYPOW
             */
            BinaryTree<Integer> integerBinaryTree = new BinaryTree<>();
            BinaryTree<Double> doubleBinaryTree = new BinaryTree<>();
            BinaryTree<String> stringBinaryTree = new BinaryTree<>();

            /**
             * ODBIERANIE Z SOCKETA
             */
            InputStream input = socket.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(input));

            /**
             * WYSYLANIE DO SOCKETA
             */
            OutputStream output = socket.getOutputStream();
            PrintWriter out = new PrintWriter(output, true);

            /**
             * PETLA DZIALAJACA DOPOKI UZYTKOWNIK NIE WYBIERZE RODZAJU DRZEWA
             */
            while (!tree_picked){

                /**
                 * SWITCH WYBIERAJACY RODZAJ DRZEWA
                 */
                switch (in.readLine()){

                    case "int":
                        out.println("You picked INTEGER type for your tree !");
                        out.println("do_null");
                        tree_picked = true;
                        tree_type = "int";
                        break;

                    case "double":
                        out.println("You picked DOUBLE type for your tree !");
                        out.println("do_null");
                        tree_picked = true;
                        tree_type = "double";
                        break;

                    case "string":
                        out.println("You picked STRING type for your tree !");
                        out.println("do_null");
                        tree_picked = true;
                        tree_type = "string";
                        break;

                    case "exit":
                        close = true;
                        out.println("I end my work, see you !");
                        out.println("do_nothing");
                        break;

                    default:
                        out.println("Please use only the types that were mentioned earlier !");
                        out.println("do_nothing");
                }
            }

            /**
             * PETLA DZIALAJACA DOPOKI UZYTKOWNIK TEGO CHCE
             */
            while(!close){

                /**
                 * SWITCH OBSLUGUJACY TEKST WPISYWANY PRZEZ UZYTKOWNIKA
                 */
                switch (in.readLine()){

                    case "add":
                        out.println("Type a variable of "+tree_type+" type now !");
                        out.println("do_nothing");
                        if(tree_type.equals("int")){
                            try{
                                to_int = Integer.parseInt(in.readLine());
                                integerBinaryTree.add(to_int);
                                out.println("You added a value !");
                                out.println(integerBinaryTree.display());
                            }
                            catch (NumberFormatException ex){
                                out.println("Please use correct commands !");
                                out.println("do_nothing");
                            }
                        }
                        else if(tree_type.equals("double")){
                            try{
                                to_double = Double.parseDouble(in.readLine());
                                doubleBinaryTree.add(to_double);
                                out.println("You added a value !");
                                out.println(doubleBinaryTree.display());
                            }
                            catch (NumberFormatException ex){
                                out.println("Please use correct commands !");
                                out.println("do_nothing");
                            }
                        }
                        else if(tree_type.equals("string")){
                            stringBinaryTree.add(in.readLine());
                            out.println("You added a value !");
                            out.println(stringBinaryTree.display());
                        }
                        break;

                    case "delete":
                        out.println("Type a variable of "+tree_type+" type now !");
                        out.println("do_nothing");
                        if(tree_type.equals("int")){
                            try{
                                to_int = Integer.parseInt(in.readLine());
                                integerBinaryTree.delete(to_int);
                                out.println("You deleted a value !");
                                out.println(integerBinaryTree.display());
                            }
                            catch (NumberFormatException ex){
                                out.println("Please use correct commands !");
                                out.println("do_nothing");
                            }
                        }
                        else if(tree_type.equals("double")){
                            try{
                                to_double = Double.parseDouble(in.readLine());
                                doubleBinaryTree.delete(to_double);
                                out.println("You deleted a value !");
                                out.println(doubleBinaryTree.display());
                            }
                            catch (NumberFormatException ex){
                                out.println("Please use correct commands !");
                                out.println("do_nothing");
                            }
                        }
                        else if(tree_type.equals("string")){
                            stringBinaryTree.delete(in.readLine());
                            out.println("You deleted a value !");
                            out.println(stringBinaryTree.display());
                        }
                        break;

                    case "search":
                        out.println("Type a variable of "+tree_type+" type now !");
                        out.println("do_nothing");
                        if(tree_type.equals("int")){
                            out.println("It is "+integerBinaryTree.search(Integer.valueOf(in.readLine()))+" there is an element like that !");
                            out.println("do_nothing");
                        }
                        else if(tree_type.equals("double")){
                            out.println("It is "+doubleBinaryTree.search(Double.valueOf(in.readLine()))+" there is an element like that !");
                            out.println("do_nothing");
                        }
                        else if(tree_type.equals("string")){
                            out.println("It is "+stringBinaryTree.search(in.readLine())+" there is an element like that !");
                            out.println("do_nothing");
                        }
                        break;

                    case "exit":
                        close = true;
                        out.println("I end my work, see you !");
                        out.println("do_nothing");
                        break;

                    default:
                        out.println("Please use correct commands !");
                        out.println("do_nothing");
                }
            }

            /**
             * ZAMKNIECIE SOCKETA
             */
            socket.close();

        }

        /**
         * LAPANIE WYJATKOW
         */
        catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
