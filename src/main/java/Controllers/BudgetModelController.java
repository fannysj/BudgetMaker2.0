package Controllers;


import Model.BudgetModel;
import Model.Category;
import View.CategoryListItem;
import com.example.budgetmaker2_0.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class BudgetModelController implements Initializable {

    private ArrayList<CategoryListItem> categoryListArray = new ArrayList<>();

    BudgetModel currentBudget;

    @FXML
    private TextField EnterBudget;

    @FXML
    private Button Budgetknapp;

    @FXML
    private AnchorPane startSida;

    @FXML
    private AnchorPane testSida;

    @FXML
    private Label budgetAmount;

    @FXML
    private Button goBack;

    @FXML
    private FlowPane CategoryDivideFlowpane;

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

    @FXML
    private AnchorPane oversiktKategori;

    @FXML
    private Button closeCatOversikt;

    @FXML
    private Button klarKnapp;

    @FXML
    private FlowPane OverviewCategory;

    public void setCloseCatOversikt() {
        startSida.toFront();
        startSida.setVisible(true);
        oversiktKategori.setVisible(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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
    private Label felmeddelande;

    @FXML
    public void bytSida() {
        testSida.toFront();
        startSida.setVisible(false);
        testSida.setVisible(true);
        currentBudget = new BudgetModel(5000);

        updateCategoryList();

    }

    @FXML
    public void goBackonePage() {
        startSida.toFront();
        startSida.setVisible(true);
        testSida.setVisible(false);
    }

    private void updateCategoryList() {
        CategoryDivideFlowpane.getChildren().clear();
        for (Category category : currentBudget.categoryList) {
            CategoryListItem newCategoryList = new CategoryListItem(category, this);
            categoryListArray.add(newCategoryList);
            CategoryDivideFlowpane.getChildren().add(newCategoryList);

        }
    }


    @FXML
    public void switchToScene2() throws IOException {
        HelloApplication.setRoot("overview2");
    }

    @FXML
    public void switchToScene1() throws IOException {
        HelloApplication.setRoot("hello-view");
    }

    @FXML
    public void goToCategoryOverview() throws IOException {
        HelloApplication.setRoot("hello-view");
        testSida.setVisible(false);
        startSida.setVisible(false);
        oversiktKategori.setVisible(true);
    }
}

