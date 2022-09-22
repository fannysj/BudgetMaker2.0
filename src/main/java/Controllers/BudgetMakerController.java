package Controllers;

import Model.Category;
import View.CategoryListItem;
import Model.newBudget;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


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
    public void setCloseCatOversikt() {
        startSida.toFront();
        startSida.setVisible(true);
        oversiktKategori.setVisible(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setAddExpense(){
        addExpenseSplit.toFront();
        overviewAnchorPane.setVisible(false);
        addExpenseSplit.setVisible(true);
    }


    public void setBackToOverview() {
        overviewAnchorPane.toFront();
        addExpenseSplit.setVisible(false);
        overviewAnchorPane.setVisible(true);
    }
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
    public void goBackonePage(){
        startSida.toFront();
        startSida.setVisible(true);
        testSida.setVisible(false);
    }

    @FXML
    public void doneButton(){
        for(int i = 0; i < catelist.size(); i++){
            catelist.get(i).setAmount(categoryListArray.get(i).getCategoryAmount());
        }
    }

    public void onUpdateBudget(int amount){
        currentBudget.addCategoryExpense(amount);
    }


    public void hoverOverBudgetButton(){
        Budgetknapp.setStyle("-fx-background-color: #cbc8f6; -fx-background-radius: 20px; -fx-border-color: #fff; -fx-border-radius: 20px;");


    }

    public void stopHoverOverBudgetButton(){
        Budgetknapp.setStyle("-fx-background-color: #a8a2f8; -fx-background-radius: 20px; -fx-border-color: #fff; -fx-border-radius: 20px;");
    }

    public void budgetButtonPressed(){
        Budgetknapp.setStyle("-fx-background-color: #6a66a8; -fx-background-radius: 20px; -fx-border-color: #fff; -fx-border-radius: 20px;");
    }

    private void updateCategoryList() {
        Categorylist.getChildren().clear();
        for (Category category : catelist){
            CategoryListItem newCategoryList = new CategoryListItem(category, this);
            categoryListArray.add(newCategoryList);
            Categorylist.getChildren().add(newCategoryList);

        }
    }
}
