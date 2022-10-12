package View;

import Controllers.TransactionsController;
import Model.Category;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class OverviewView extends AnchorPane{


    private List<CategoryOverviewItem> CategoryOverviewItemArray = new ArrayList<>();



    @FXML
    public void updateCategoryListItem(FlowPane OverviewCategory, List<Category> categoryList, TransactionsController controller) {
        OverviewCategory.getChildren().clear();
        for (Category category : categoryList) {
            CategoryOverviewItem newCategoryList = new CategoryOverviewItem(category, controller);
            CategoryOverviewItemArray.add(newCategoryList);
            OverviewCategory.getChildren().add(newCategoryList);


        }
    }

    public void updateBudgetDisplay(Label left, Label spent, int amountleft, int currentamount){
        left.setText("" + amountleft + " kr");
        spent.setText("" + currentamount + " kr");
    }


}
