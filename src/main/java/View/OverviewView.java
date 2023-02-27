package View;

import Controllers.TransactionsController;
import Model.BudgetModel;
import Model.Category;
import Model.FixedCost;
import Model.Transaction;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * This class has methods that handle displaying and dynamically changing our Overview
 */

public class OverviewView extends AnchorPane{



    private List<CategoryOverviewItem> CategoryOverviewItemArray = new ArrayList<>();
    public FixedCost totalCost;



    public void switchToHomePage(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/budgetmaker2_0/hello-view.fxml"));
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

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
        int totalCost = FixedCost.getTotalCost();
        int totalrecurringCost = FixedCost.getTotalrecurringCost();
        left.setText("" + (amountleft - totalCost) + " kr");
        spent.setText("" + currentamount + " kr");

    }
    //vill att den nya listan med fasta utgifter (skapad i en budget) ska subtraheras samt fasta utgifter som är
    //återkommande


    public void setBackToOverview(AnchorPane anchor, SplitPane split) {
        anchor.toFront();
        split.setVisible(false);
        anchor.setVisible(true);
    }


    public void updateCategoryItems(){
        for (CategoryOverviewItem c : CategoryOverviewItemArray){
            c.updateCurrentAmount();
            c.updateProgressBar();
        }
    }

}
