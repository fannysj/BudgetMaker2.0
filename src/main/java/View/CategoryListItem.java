package View;

import Controllers.BudgetController;
import Model.Category;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * This class has methods that handle displaying and dynamically changing our Categoryinput FXML
 */

public class CategoryListItem extends AnchorPane {

    @FXML
    private Label categoryName;
    @FXML
    private TextField categoryAmount;

    private int amountChanged = 0;

    private Category category;
    private BudgetController controller;

    public CategoryListItem(Category category, BudgetController controller){

        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/Categoryinput.fxml"));
        myLoader.setRoot(this);
        myLoader.setController(this);

        this.category = category;

        try {
            myLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.controller = controller;
        categoryName.setText(category.getName());

    }

    public Label getCategoryName(){
        return categoryName;
    }

    public int getCategoryAmount(){
       return Integer.parseInt(categoryAmount.getText());
    }

    public Category getCategory(){
        return category;
    }


    public int onUpdateAmount(){
        int newValue = - amountChanged;
        if(this.categoryAmount.getText().isEmpty()){
            amountChanged = 0;
        } else {
            newValue = Integer.parseInt(this.categoryAmount.getText());
            newValue = newValue - amountChanged;
            amountChanged = Integer.parseInt(this.categoryAmount.getText());
        }
        return newValue;
    }


}

