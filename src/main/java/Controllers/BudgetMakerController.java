package Controllers;


import Model.Category;
import Model.CategoryListItem;
import Model.newBudget;
import com.example.budgetmaker2_0.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.util.*;
import java.net.URL;


public class BudgetMakerController implements Initializable {

    private ArrayList<Category> catelist = new ArrayList<>();

    private ArrayList<CategoryListItem> categoryListArray = new ArrayList<>();

    newBudget currentBudget;

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

        catelist.add(new Category("Mat"));
        catelist.add(new Category("Shopping"));
        catelist.add(new Category("Nöje"));
        catelist.add(new Category("Övrigt"));
        catelist.add(new Category("Bajs"));

        testSida.toFront();
        startSida.setVisible(false);
        testSida.setVisible(true);
        currentBudget = new newBudget(EnterBudget, budgetAmount);

        updateCategoryList();

    }

    @FXML
    public void goBackonePage() {
        startSida.toFront();
        startSida.setVisible(true);
        testSida.setVisible(false);
    }

    @FXML
    public void doneButton() {
        for (int i = 0; i < catelist.size(); i++) {
            catelist.get(i).setAmount(categoryListArray.get(i).getCategoryAmount());
        }
    }

    public void onUpdateBudget(int amount) {
        currentBudget.addCategoryExpense(amount);
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
