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

    private int amountChanged = 0;

    private Category category;
    private BudgetMakerController controller;

    public CategoryListItem(Category category, BudgetMakerController controller){

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

    @FXML
    private void onUpdateAmount(){
        int newValue = - amountChanged;
        if(this.categoryAmount.getText().isEmpty()){
            amountChanged = 0;
        } else {
            newValue = Integer.parseInt(this.categoryAmount.getText());
            newValue = newValue - amountChanged;
            amountChanged = Integer.parseInt(this.categoryAmount.getText());
        }
        controller.onUpdateBudget(newValue);
    }

    public int getCategoryAmount() {
        return amountChanged;
    }

}

