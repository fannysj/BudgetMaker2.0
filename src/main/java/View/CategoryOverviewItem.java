package View;

import Controllers.BudgetModelController;
import Model.Category;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CategoryOverviewItem extends Node {
    @FXML
    private Label currentAmount;

    @FXML
    private Label goalAmount;

    private Category category;
    private BudgetModelController controller;

    public CategoryOverviewItem(Category category, BudgetModelController controller){

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
        goalAmount.setText(String.valueOf(category.getGoalAmount()));

    }
}
