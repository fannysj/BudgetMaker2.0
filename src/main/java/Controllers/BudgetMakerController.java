package Controllers;

import Model.newBudget;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    public void bytSida(){
        testSida.toFront();
        startSida.setVisible(false);
        testSida.setVisible(true);

        new newBudget(EnterBudget, budgetAmount);

        String s = "HEJ";


    }

}
