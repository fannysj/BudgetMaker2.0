package View;

import Controllers.TransactionsController;
import Model.Category;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;

import java.io.IOException;

public class TransactionOverviewItem {


    @FXML
    private Label title;
    @FXML
    private Label spent;
    @FXML
    private Label left;


    private Category category;
    private TransactionsController root;




    public TransactionOverviewItem(){


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/TransactionOverview.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try{
            fxmlLoader.load();
        } catch (IOException exception){
            throw new RuntimeException(exception);
        }


        init();
    }

    private void init(){
        title.setText(category.getName());
        // Update Spent and Left

    }





}
