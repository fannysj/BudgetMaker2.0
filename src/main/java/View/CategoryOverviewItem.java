package View;

import Controllers.TransactionsController;
import Model.Category;
import Model.ObserverHandler;
import Model.TransactionObserver;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class CategoryOverviewItem extends AnchorPane {


    @FXML
    private Label currentAmount;

    @FXML
    private Label goalAmount;

    @FXML
    private Label CategoryNameOV;

    @FXML
    private Button OVbutton;



    private Category category;
    private TransactionsController controller;

    public CategoryOverviewItem(Category category, TransactionsController controller){

        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/cateOVList.fxml"));
        myLoader.setRoot(this);
        myLoader.setController(this);

        this.category = category;


        try {
            myLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.controller = controller;

        CategoryNameOV.setText(category.getName());
        currentAmount.setText(String.valueOf(category.spentAmount));
        goalAmount.setText(String.valueOf(category.getGoalAmount()));

    }

    @FXML
    public void onClick() {

/*
        Parent root = FXMLLoader.load(getClass().getResource("/hello-view.fxml"));
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        */
        controller.openTransactionDetailView(category);
    }

    @FXML
    public void onHover(){
        CategoryNameOV.setUnderline(true);

    }
    @FXML
    public void stopHover(){
        CategoryNameOV.setUnderline(false);
    }
}


