/**
 * IMPORT BIBLIOTEK
 */
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.shape.Line;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * KLASA CLIENT
 */
public class Client extends Application {

    /**
     * ZMIENNE FX
     */
    Stage finalStage;
    Scene scene;
    BorderPane borderPane;
    StackPane topStackPane, bottomStackPane;
    Pane centerPane;
    TextField bottomTextField;
    Label topLabel, centerLabel;
    Text topText;
    Line line;
    PrintWriter out;
    BufferedReader in;
    Socket socket;
    ScrollPane centerScrollPane;
    String tree_type, string;
    ArrayList<String> newstrings;
    NodeValue rootNode;
    Double out_of_space;
    double screen_width, screen_height;
    boolean show_instruction, first_gone;

    /**
     * FUNKCJA GLOWNA MAIN
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * FUNKCJA STARTUJACA APLIKACJE
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {

        /**
         * INICJACJA ZMIENNYCH
         */
        stage = new Stage();
        borderPane = new BorderPane();
        topText = new Text("Welcome to BST creator !");
        topLabel = new Label("");
        topStackPane = new StackPane(topLabel, topText);
        centerPane = new Pane();
        centerScrollPane = new ScrollPane();
        bottomTextField = new TextField("Write your commands right here ...");
        bottomStackPane = new StackPane(bottomTextField);
        screen_width = Screen.getPrimary().getBounds().getWidth();
        screen_height = Screen.getPrimary().getBounds().getHeight();
        scene = new Scene(borderPane, screen_width/1.5, screen_height/1.5);

        /**
         * DZIALANIA NA STAGE
         */
        stage.setTitle("Binary Tree");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        /**
         * DZIALANIA NA TOPLABEL
         */
        topLabel.setStyle("-fx-background-color: black;");
        topLabel.setPrefSize(screen_width/1.5, 40);

        /**
         * DZIALANIA NA TOPTEXT
         */
        topText.setStyle("-fx-font: 30px Tahoma;" +
                        "-fx-fill: linear-gradient(from 0% 0% to 100% 200%, repeat, aqua 0%, red 50%);" +
                        "-fx-stroke: black;" +
                        "-fx-stroke-width: 1;");

        /**
         * DZIALANIA NA CENTERLABEL
         */
        centerLabel = new Label("You can use following commands in this turn:\n" +
                "1: [int] - sets your binary tree to INT type\n" +
                "2: [double] - sets your binary tree to DOUBLE type\n" +
                "3: [string] - sets your binary tree to STRING type\n" +
                "4: [exit] - closes the programm\n");
        centerLabel.setStyle("-fx-font: 20px Tahoma;");
        centerLabel.setBackground(Background.fill(Color.GREY));
        centerLabel.setAlignment(Pos.CENTER);
        centerLabel.setPrefSize(screen_width/1.5,screen_height/1.5-90);

        /**
         * DZIALANIA NA CENTERSCROLLPANE
         */
        centerScrollPane.setStyle("-fx-background: grey;");

        /**
         * DZIALANIA NA BOTTOMTEXTFIELD
         */
        bottomTextField.setPrefSize(screen_width/1.5, 50);
        bottomTextField.setAlignment(Pos.CENTER);
        bottomTextField.setStyle("-fx-background-color: black; -fx-text-inner-color: white; -fx-font: 20px Tahoma;");

        /**
         * DZIALANIA NA BORDERPANE
         */
        borderPane.setTop(topStackPane);
        borderPane.setBottom(bottomStackPane);
        borderPane.setCenter(centerLabel);

        /**
         * DZIALANIA NA TREE_TYPE
         */
        tree_type = "string";

        /**
         * DZIALANIA NA SHOW_INSTRUCTION
         */
        show_instruction = true;
        first_gone = false;

        try {
            /**
             * LACZENIE Z SERWEREM
             */
            socket = new Socket("localhost", 4444);

            /**
             * WYSYLANIE DO SERWERA
             */
            out = new PrintWriter(socket.getOutputStream(), true);

            /**
             * ODBIERANIE Z SERWERA
             */
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }

        /**
         * LAPANIE WYJATKOW
         */
        catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
            exitStage(stage);
        }

        /**
         * LAPANIE WYJATKOW
         */
        catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
            exitStage(stage);
        }

        /**
         * DZIALANIA NA FINALSTAGE
         */
        finalStage = stage;

        /**
         * REAKCJA NA DZIALANIA UZYTKOWNIKA NA KLAWIATURZE NA BOTTOMTEXTFIELD
         */
        bottomTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {

            /**
             * FUNKCJA WYWOLANA PO DZIALANIU UZYTKOWNIKA
             * @param keyEvent
             */
            @Override
            public void handle(KeyEvent keyEvent) {

                /**
                 * JESLI UZYTKOWNIK NACISNIE ENTER
                 */
                if(keyEvent.getCode().equals(KeyCode.ENTER)) {

                    if(!first_gone){
                        string = bottomTextField.getText();
                        bottomTextField.clear();
                        out.println(string);
                        try {
                            setTopText(finalStage);
                            string = in.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                            exitStage(finalStage);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            exitStage(finalStage);
                        }
                        if(string.equals("do_null")){
                            first_gone = true;
                            centerLabel.setText("Later on you can use following commands:\n" +
                                    "1: (FIRST VALUE) [add] - sets your tree on adding mode\n" +
                                    "   (SECOND VALUE IN NEW LINE) ['value'] - adds value to your tree\n" +
                                    "2: (FIRST VALUE) [delete] - sets your tree on deleting mode\n" +
                                    "   (SECOND VALUE IN NEW LINE) ['value'] - deletes value from your tree\n" +
                                    "3: (FIRST VALUE) [search] - sets your tree on searching mode\n" +
                                    "   (SECOND VALUE IN NEW LINE) ['value'] - searches for a value in your tree\n" +
                                    "4: [exit] - closes the programm\n");
                        }
                    }

                    else {

                        /**
                         * ZCZYTANIE KOMENDY OD UZYTKOWNIKA, WYSLANIE DO SERWERA
                         */
                        string = bottomTextField.getText();
                        out.println(string);
                        bottomTextField.clear();

                        try {
                            /**
                             * USTAWIENIE CENTERBORDERPANE
                             */
                            borderPane.setCenter(centerScrollPane);
                            centerScrollPane.setContent(centerPane);

                            /**
                             * WYSWIETLENIE WIADOMOSCI OD SERWERA
                             */
                            setTopText(finalStage);
                            string = in.readLine();

                            /**
                             * JESLI SERWER ODSYLA NIESTATYCZNA KOMENDE
                             */
                            if (!string.equals("do_null") && !string.equals("do_nothing")) {

                                /**
                                 * PRZYPISANIE KODU DRZEWA BINARNEGO
                                 */
                                newstrings = returnStringArray(string);

                                /**
                                 * UTWORZENIE CENTERPANE
                                 */
                                centerPane = new Pane();

                                /**
                                 * PRZYPISANIE KORZENIA
                                 */
                                rootNode = new NodeValue(newstrings.get(0));

                                /**
                                 * UTWORZENIE DRZEWA U KLIENTA
                                 */
                                for (int i = 1; i < newstrings.size(); i++) {
                                    if (!newstrings.get(i).equals("null")) {
                                        rootNode = addRecursive(rootNode, newstrings.get(i));
                                    }
                                }

                                /**
                                 * USTAWIENIE OUT_OF_SPACE NA WARTOSC POCZATKOWA
                                 */
                                out_of_space = 0.0;

                                /**
                                 * WYSWIETLENIE DRZEWA W STAGE
                                 */
                                drawNodeRecursive((int) screen_width / 3, 0, (int) screen_width / 3, 50, rootNode, (newstrings.size() + 1) / 4, 0, 0);

                                /**
                                 * JESLI DRZEWO WYBIEGA POZA EKRAN
                                 */
                                if (out_of_space < 0) {
                                    centerPane = new Pane();
                                    drawNodeRecursive((int) (screen_width / 3 + Math.abs(out_of_space)), 0, (int) (screen_width / 3 + Math.abs(out_of_space)), 50, rootNode, (newstrings.size() + 1) / 4, 0, 0);
                                }

                                /**
                                 * DODANIE STACKPANE DO SCROLLPANE
                                 */
                                centerScrollPane.setContent(centerPane);
                            }
                        }

                        /**
                         * LAPANIE WYJATKOW
                         */ catch (IOException e) {
                            e.printStackTrace();
                            exitStage(finalStage);
                        }

                        /**
                         * LAPANIE WYJATKOW
                         */ catch (InterruptedException e) {
                            e.printStackTrace();
                            exitStage(finalStage);
                        }

                    }
                }
            }
        });

        /**
         * JESLI UZYTKOWNIK NACISNIE MOUSE1 NA BOTTOMTEXTFIELD
         */
        bottomTextField.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getEventType()==MouseEvent.MOUSE_CLICKED) bottomTextField.clear();
            }
        });
    }

    /**
     * FUNKCJA ADDRECURSIVE
     * @param current
     * @param value
     * @return
     */
    public NodeValue addRecursive(NodeValue current, String value) {

        /**
         * PRZYPADEK GDY CURRENT NODE JEST NULL
         */
        if(current == null){
            return new NodeValue(value);
        }

        /**
         * JESLI DZREWA SA TYPU LICZBOWEGO
         */
        if(tree_type.equals("number")){

            /**
             * PRZYPADEK GDY VALUE JEST MNIEJSZE OD WARTOSCI OJCA
             */
            if(Double.parseDouble(value)<Double.parseDouble(current.value)) {
                current.leftNodeValue = addRecursive(current.leftNodeValue, value);
            }

            /**
             * PRZYPADEK GDY VALUE JEST WIEKSZE LUB ROWNE OD OJCA
             */
            else if (Double.parseDouble(value)>=Double.parseDouble(current.value)) {
                current.rightNodeValue = addRecursive(current.rightNodeValue, value);
            }
        }

        /**
         * JESLI DRZEWA SA TYPU STRING
         */
        else {

            /**
             * PRZYPADEK GDY VALUE JEST MNIEJSZE OD WARTOSCI OJCA
             */
            if(value.compareTo(current.value)<0) {
                current.leftNodeValue = addRecursive(current.leftNodeValue, value);
            }

            /**
             * PRZYPADEK GDY VALUE JEST WIEKSZE LUB ROWNE OD OJCA
             */
            else if (value.compareTo(current.value)>=0) {
                current.rightNodeValue = addRecursive(current.rightNodeValue, value);
            }
        }

        return current;
    }

    /**
     * FUNKCJA DRAWNODERECURSIVE
     * @param x1
     * @param y1
     * @param x
     * @param y
     * @param nodeValue
     * @param length
     * @param counter
     * @param space
     */
    public void drawNodeRecursive(int x1, int y1, int x, int y, NodeValue nodeValue, int length, int counter, int space) {

        /**
         * DEKLARACJA ZMIENNYCH
         */
        Line line;
        Circle circle;
        Text text;

        /**
         * JESLI JESTESMY NA KORZENIU
         */
        if (counter == 0) {
            line = new Line(x1, y1, x, y);
            counter++;
        }

        /**
         * W PRZECIWNYM WYPADKU
         */
        else line = new Line(x1, y1 + 15, x, y - 15);

        /**
         * ARGUMENTY NODEA
         */
        circle = new Circle(x, y, 15, Paint.valueOf("red"));
        text = new Text(x-nodeValue.value.length()*3,y,nodeValue.value);
        centerPane.getChildren().add(line);
        centerPane.getChildren().add(circle);
        centerPane.getChildren().add(text);

        /**
         * DZIALANIA NA TEXT
         */
        text.setTextOrigin(VPos.CENTER);

        /**
         * JESLI COKOLWIEK WYCHODZI POZA EKRAN
         */
        if (x - 15 < out_of_space) {
            out_of_space += Double.valueOf(x-15);
        }

        /**
         * DRUKOWANIE PO LEWYM PODDRZEWIE
         */
        if (nodeValue.leftNodeValue != null) {
            drawNodeRecursive(x, y, x - length * 30, y + 30, nodeValue.leftNodeValue, length / 2, counter, space);
        }

        /**
         * DRUKOWANIE PO PRAWYM PODDRZEWIE
         */
        if (nodeValue.rightNodeValue != null) {
            drawNodeRecursive(x, y, x + length * 30, y + 30, nodeValue.rightNodeValue, length/ 2, counter, space);
        }

    }

    /**
     * FUNKCJA SETTOPTEXT
     * @param stage
     * @throws IOException
     * @throws InterruptedException
     */
    public void setTopText(Stage stage) throws IOException, InterruptedException {

        /**
         * ZCZYTANIE WARTOSCI Z SERWERA I USTAWIENIE W TOPTEXT
         */
        string = in.readLine();
        topText.setText(string);

        /**
         * JESLI SERWER KONCZY PRACE Z UZYTKOWNIKIEM
         */
        if(string.equals("I end my work, see you !")){
            socket.close();
            exitStage(stage);
        }

        /**
         * ZMIANA PARAMETRU TREE_TYPE
         */
        else if(string.equals("You picked INTEGER type for your tree !") || string.equals("You picked DOUBLE type for your tree !")) tree_type="number";
    }

    /**
     * FUNKCJA EXITSTAGE
     * @param stage
     */
    public void exitStage(Stage stage){
        stage.hide();
    }

    /**
     * FUNKCJA ZWRACA KOD DRZEWA JAKO TABLICE STRINGOW
     * @param string
     * @return
     */
    public ArrayList<String> returnStringArray(String string){

        /**
         * ZMIENNE
         */
        ArrayList<String> strings = new ArrayList<>();
        String test = new String();

        /**
         * SEPARACJA
         */
        for(int i=0; i<string.length(); i++){
            if(string.charAt(i)!=' '){
                test += string.charAt(i);
            }
            else {
                strings.add(test);
                System.out.println(test+"<-----");
                test = new String();
            }
        }
        return strings;
    }
}
