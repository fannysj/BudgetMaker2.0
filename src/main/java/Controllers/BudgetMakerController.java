package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class BudgetMakerController {

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
    public void bytSida() {
        testSida.toFront();
        startSida.setVisible(false);
        testSida.setVisible(true);

        budgetAmount.setText(EnterBudget.getText());
        int x = getIntFromTextField(EnterBudget);
        budgetAmount.setText(Integer.toString(x));


    }


    public static int getIntFromTextField(TextField EnterBudget) {
        String text = EnterBudget.getText();
        return Integer.parseInt(text);

    }
}
