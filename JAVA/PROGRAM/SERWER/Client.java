/**
 * IMPORT BIBLIOTEK
 */
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * KLASA CLIENT
 */
public class Client {

    /**
     * FUNKCJA GLOWNA MAIN
     * @param args
     */
    public static void main(String[] args) {

        /**
         * PROGRAM PROBUJE WSPOLPRACOWAC Z SERWEREM
         */
        try  {

            /**
             * LACZENIE Z SERWEREM
             */
            Socket socket = new Socket("localhost", 4444);

            /**
             * WYSYLANIE DO SERWERA
             */
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            /**
             * ODBIERANIE Z SERWERA
             */
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            /**
             * DEKLARACJA ZMIENNYCH
             */
            Scanner scanner = new Scanner(System.in);
            String text;
            boolean close = false;

            /**
             * ZCZYTANIE INFORMACJI POCZATKOWYCH Z SERWERA
             */
            for(int i=1; i<=5; i++){
                System.out.println("SERVER ---> "+in.readLine());
            }
            System.out.println("");

            /**
             * WYSLANIE INFORMACJI DO SERWERA
             */
            out.println(scanner.next());

            /**
             * ZCZYTANIE INFORMACJI POCZATKOWYCH Z SERWERA
             */
            System.out.println("");
            System.out.println("SERVER ---> "+in.readLine());
            System.out.println("");
            for(int i=1; i<=6; i++){
                System.out.println("SERVER ---> "+in.readLine());
            }
            System.out.println("");

            /**
             * PETLA OBSLUGUJACA PRACE SERWERA
             */
            while (!close){
                text = scanner.next();
                out.println(text);
                if(text.equals("exit")){
                    close = true;
                }
                System.out.println("SERVER ---> "+in.readLine());
            }

            /**
             * WYLACZNIE SOCKETA
             */
            socket.close();

        }

        /**
         * LAPANIE WYJATKOW
         */
        catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());

        }

        /**
         * LAPANIE WYJATKOW
         */
        catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}