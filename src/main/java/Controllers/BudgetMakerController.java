package Controllers;


import Model.CategoryListItem;
import Model.newBudget;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.util.*;
import java.net.URL;


public class BudgetMakerController implements Initializable {

    private ArrayList<String> catelist = new ArrayList(Arrays.asList("Mat","Shopping","Nöje","Övrigt"));


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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateCategoryList();

    }

    @FXML
    public void setAddExpense(){
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
        updateCategoryList();

        new newBudget(EnterBudget, budgetAmount);

    }

    @FXML
    public void goBackonePage(){
        startSida.toFront();
        startSida.setVisible(true);
        testSida.setVisible(false);
    }


    @FXML
    public void hoverOverBudgetButton(){
        Budgetknapp.setStyle("-fx-background-color: #cbc8f6; -fx-background-radius: 20px; -fx-border-color: #fff; -fx-border-radius: 20px;");

    }

    @FXML
    public void stopHoverOverBudgetButton(){
        Budgetknapp.setStyle("-fx-background-color: #a8a2f8; -fx-background-radius: 20px; -fx-border-color: #fff; -fx-border-radius: 20px;");
    }

    @FXML
    public void budgetButtonPressed(){
        Budgetknapp.setStyle("-fx-background-color: #6a66a8; -fx-background-radius: 20px; -fx-border-color: #fff; -fx-border-radius: 20px;");
    }

    private void updateCategoryList() {
        Categorylist.getChildren().clear();
        for (String category : catelist){
            Categorylist.getChildren().add(new CategoryListItem(category, this));

        }
//        ArrayList<String> catelist = new ArrayList<>(Arrays.asList("mat","shopping","nöje", "övrigt"));
//
//        for (String category : catelist){
//            categorylist.getChildren().add(new CategoryListItem(category, this));
//        }
    }







}
