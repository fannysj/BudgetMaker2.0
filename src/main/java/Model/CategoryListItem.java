package Model;

import Controllers.BudgetMakerController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class CategoryListItem extends AnchorPane {
    @FXML
    private Label categoryName;

    @FXML
    private TextField categoryAmount;

    private BudgetMakerController controller;

    public CategoryListItem(String category, BudgetMakerController controller){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Categoryinput.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.controller = controller;
        categoryName.setText(category);
    }


}
