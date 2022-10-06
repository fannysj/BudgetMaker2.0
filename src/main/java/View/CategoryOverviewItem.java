package View;

import Controllers.TransactionsController;
import Model.Category;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class CategoryOverviewItem extends AnchorPane {


    @FXML
    private Label currentAmount;

    @FXML
    private Label goalAmount;

    @FXML
    private Label CategoryNameOV;

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

}
