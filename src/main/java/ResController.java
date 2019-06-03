import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.Date;

public class ResController {

    public static String name;
    public static String message;
    public static Stage stage;

    @FXML
    Label n;

    @FXML
    TextArea m;


    public void initialize() {
        n.setText(name);
        m.setText(message);
    }

    public void close(){
        stage.close();
    }

}
