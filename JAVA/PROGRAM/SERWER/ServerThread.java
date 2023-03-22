/**
 * IMPORT BIBLIOTEK
 */
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * KLASA SERVERTHREAD
 */
public class ServerThread {

    /**
     * FUNKCJA GL0WNA MAIN
     * @param args
     */
    public static void main(String[] args) {

        /**
         * PROBUJEMY UTWORZY SOCKETA
         */
        try (ServerSocket serverSocket = new ServerSocket(4444)) {

            /**
             * INFORMACJA SYSTEMOWA
             */
            System.out.println("Server is working on port 4444 ...");

            /**
             * DEKLARACJA ZMIENNYCH
             */
            int i=1;

            /**
             * PETLA DOLACZAJACA NOWYCH UZYTKOWNIKOW
             */
            while (i>0) {

                /**
                 * AKCEPTACJA POLACZENIA
                 */
                Socket socket = serverSocket.accept();

                /**
                 * INFORMACJA SYSTEMOWA
                 */
                System.out.println("New client connected ... Client number: "+i);
                i++;

                /**
                 * START WATKU DLA DANEGO KLIENTA
                 */
                new ClientThread(socket, i).start();
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
