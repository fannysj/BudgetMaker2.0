package Model;

import Controllers.BudgetMakerController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class CategoryListItem extends AnchorPane {

    @FXML
    private Label categoryName;

    @FXML
    private TextField categoryAmount;

    private BudgetMakerController controller;

    public CategoryListItem(String category, BudgetMakerController controller){

        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/Categoryinput.fxml"));
        myLoader.setRoot(this);
        myLoader.setController(this);

        try {
            myLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.controller = controller;
        categoryName.setText(category);
        categoryName.setAccessibleText(category.toLowerCase().replace("visa ", ""));


    }

    public Label getName(){
        return this.categoryName; }

    public void setCategoryAmount(int amount){

    }

}

