package View;

import Controllers.BudgetModelController;
import Controllers.OverviewController;
import Model.Category;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    private OverviewController controller;

    public CategoryOverviewItem(Category category, OverviewController controller){

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
        currentAmount.setText(String.valueOf(category.currentAmount));
        goalAmount.setText(String.valueOf(category.getGoalAmount()));

    }

}
