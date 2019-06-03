
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;


import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.*;

public class Controller {

    public  static String alf = " абвгдеєжзиіїйклмнопрстуфхцчшщьюя";
    public  static String alf2 = "";
    Integer n = 0;
    String w;

    public static String mess = "";
    public static String shifr = "";

    @FXML
    TextField num;

    @FXML
    TextField word;

    @FXML
    TextArea message;



    public void formAlf2(){
        String a = alf;
        for(int i = 0; i < w.length(); i++){
            a = a.replace(w.charAt(i)+"","");
        }

        for (int i = a.length()-n; i<a.length();i++){
            alf2 += a.charAt(i);
        }

        for(int i = 0; i < w.length(); i++){
            alf2 += w.charAt(i)+"";
        }

        for (int i = 0; i<a.length()-n;i++){
            alf2 +=a.charAt(i);
        }
    }

    public void toshifr(){
        shifr = "";
        for (int i = 0; i < mess.length(); i++){
            int ind = alf.indexOf(mess.charAt(i));
            if(ind>=0){
                shifr += alf2.charAt(ind);
            }
        }
        ResController.message = shifr;
        ResController.name = "Зашифровано";
    }

    public void backshifr(){
        mess = "";
        for (int i = 0; i < shifr.length(); i++){
            int ind = alf2.indexOf(shifr.charAt(i));
            if(ind>=0) {
                mess += alf.charAt(ind);
            }
        }
        ResController.message = mess;
        ResController.name = "Розшифровано";
    }

    public void to(){
        mess = message.getText();
        n = Integer.valueOf(num.getText());
        w = word.getText();
        formAlf2();
        toshifr();
        Stage stage = new Stage();
        stage.getIcons().add(new Image("encry.png"));
        String fxmlFile = "result.fxml";
        FXMLLoader loader = new FXMLLoader();
        Parent root = null;
        try {
            root = loader.load(getClass().getResourceAsStream(fxmlFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("Caesar");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        mess = "";
        n = 0;
        alf2 = "";
        ResController.stage = stage;
    }

    public void back(){
        shifr = message.getText();
        n = Integer.valueOf(num.getText());
        w = word.getText();
        formAlf2();
        backshifr();
        Stage stage = new Stage();
        stage.getIcons().add(new Image("encry.png"));
        String fxmlFile = "result.fxml";
        FXMLLoader loader = new FXMLLoader();
        Parent root = null;
        try {
            root = loader.load(getClass().getResourceAsStream(fxmlFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("Caesar");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        mess = "";
        n = 0;
        alf2 = "";
        ResController.stage = stage;
    }


    //for RSA
    @FXML
    TextField openKey;

    @FXML
    TextField closeKey;

    @FXML
    TextArea rsaMess;


    static RSA rsa = null;



    public void toshifrRSA() throws UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {

        ResController.message = rsa.doEncript(rsaMess.getText());

        ResController.name = "Зашифровано";
    }

    public void backshifrRSA() throws BadPaddingException, IOException, IllegalBlockSizeException {

        ResController.message = rsa.doDecript(rsaMess.getText());
        ResController.name = "Розшифровано";
    }

    public void toRSA() throws UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {

        toshifrRSA();
        Stage stage = new Stage();
        stage.getIcons().add(new Image("encry.png"));
        String fxmlFile = "result.fxml";
        FXMLLoader loader = new FXMLLoader();
        Parent root = null;
        try {
            root = loader.load(getClass().getResourceAsStream(fxmlFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("RSA");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


        ResController.stage = stage;
    }

    public void backRSA() throws BadPaddingException, IOException, IllegalBlockSizeException {
        backshifrRSA();
        Stage stage = new Stage();
        stage.getIcons().add(new Image("encry.png"));
        String fxmlFile = "result.fxml";
        FXMLLoader loader = new FXMLLoader();
        Parent root = null;
        try {
            root = loader.load(getClass().getResourceAsStream(fxmlFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("RSA");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        ResController.stage = stage;
    }

    public void generate() throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException {
        SecureRandom random = new SecureRandom();
        KeyPairGenerator generator = null;
        generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(512, random);
        KeyPair pair = generator.generateKeyPair();

        rsa = new RSA(pair);

        openKey.setText(pair.getPublic().toString());
        closeKey.setText(pair.getPrivate().toString());
    }
}
