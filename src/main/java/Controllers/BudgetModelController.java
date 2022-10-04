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

    private ArrayList<Category> catelist = new ArrayList<>();

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
    private FlowPane Categorylist;

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

        catelist.add(new Category("Mat", 500));
        catelist.add(new Category("Shopping", 200));
        catelist.add(new Category("Nöje", 100));
        catelist.add(new Category("Övrigt", 50));
        catelist.add(new Category("Bajs", 800));

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
        Categorylist.getChildren().clear();
        for (Category category : catelist) {
            CategoryListItem newCategoryList = new CategoryListItem(category, this);
            categoryListArray.add(newCategoryList);
            Categorylist.getChildren().add(newCategoryList);

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


}
