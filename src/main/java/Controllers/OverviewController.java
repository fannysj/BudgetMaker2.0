package Controllers;

import Model.BudgetModel;
import Model.Category;
import View.CategoryListItem;
import View.CategoryOverviewItem;
import View.OverviewView;
import com.example.budgetmaker2_0.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class OverviewController implements Initializable {

    private List<CategoryOverviewItem> CategoryOverviewItemArray = new ArrayList<>();

    BudgetModel model = new BudgetModel(10000);

    OverviewView overviewView = new OverviewView();



    public User u;

    @FXML
    private Button closeCatOversikt;

    @FXML
    private AnchorPane oversiktKategori;


    private OverviewView Oview;

    @FXML
    public FlowPane OverviewCategory;

    @FXML
    private Button backToOverview;

    @FXML
    private Button addExpense;

    @FXML
    private SplitPane addExpenseSplit;

    @FXML
    private AnchorPane overviewAnchorPane;

    @FXML
    private AnchorPane addExpenseAnchorPane;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateCategoryList();
    }

    public void updateCategoryList() {
        OverviewCategory.getChildren().clear();
        for (Category category : model.categoryList) {
            CategoryOverviewItem newCategoryList = new CategoryOverviewItem(category, this);
            CategoryOverviewItemArray.add(newCategoryList);
            OverviewCategory.getChildren().add(newCategoryList);

        }
    }

    @FXML
    public void setAddExpense() {
        addExpenseSplit.toFront();
        overviewAnchorPane.setVisible(false);
        addExpenseSplit.setVisible(true);
    }


    @FXML
    public void setBackToOverview() {
        overviewAnchorPane.toFront();
        addExpenseSplit.setVisible(false);
        overviewAnchorPane.setVisible(true);
    }

    @FXML
    public void switchToTransactionOverview(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        overviewView.switchToTransactionOverview(mouseEvent);

    }


}
