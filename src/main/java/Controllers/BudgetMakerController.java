package Controllers;

import Model.newBudget;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class BudgetMakerController {
    newBudget budget;

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
    private Button backToOverview;

    @FXML
    private Button addNewExpense;

    @FXML
    private SplitPane addExpenseSplit;

    @FXML
    private AnchorPane overviewAnchorPane;

    @FXML
    private AnchorPane addExpenseAnchorPane;

    @FXML
    private AnchorPane addExpenseLeft;

    @FXML
    private Button addCurrentExpense;

    @FXML
    public void setAddExpense(){
        addExpenseSplit.toFront();
    }

    @FXML
    public void setBackToOverview() {
        overviewAnchorPane.toFront();
        addExpenseSplit.setVisible(false);
        overviewAnchorPane.setVisible(true);
    }

    @FXML
    public void bytSida() {

        testSida.toFront();
        startSida.setVisible(false);
        testSida.setVisible(true);

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


}
