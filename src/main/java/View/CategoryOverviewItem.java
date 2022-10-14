package View;

import Controllers.TransactionsController;
import Model.Category;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
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

    @FXML
    public ProgressBar progressBar;



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
        currentAmount.setText(String.valueOf(category.getSpentAmount()));
        goalAmount.setText(String.valueOf(category.getGoalAmount()));

    }

    public Category getCategory(){
        return category;
    }

    @FXML
    public void onClick() {

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


    public void updateCurrentAmount (){
        currentAmount.setText(String.valueOf(category.getSpentAmount()));
    }

    public void updateProgressBar(){
        double valueProgressBar = Double.parseDouble((currentAmount.getText())) / Double.parseDouble(goalAmount.getText());;
        progressBar.setProgress(valueProgressBar);
    }

}
