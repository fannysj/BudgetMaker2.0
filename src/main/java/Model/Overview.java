package Model;

import com.example.budgetmaker2_0.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class Overview extends AnchorPane {
    public Overview(){
        FXMLLoader fxmlLoader = new FXMLLoader(Overview.class.getResource("overview2.fxml"));
        fxmlLoader.setRoot(this);




    }
}
