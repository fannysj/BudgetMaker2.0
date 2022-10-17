package View;

import Controllers.BudgetModelController;
import Controllers.TransactionsController;
import Model.Budget;
import Model.Transaction;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class BudgetListItem extends AnchorPane {

    @FXML
    private Label listItemBudgetId;

    @FXML
    private Label listItemBudgetIncome;

    private BudgetModelController parentController;
    private Budget budget;


    public BudgetListItem(Budget budget, BudgetModelController budgetcontroller){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/budgetlistitem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.parentController = budgetcontroller;
        this.budget = budget;

        this.listItemBudgetId.setText(String.valueOf(budget.getId()));
        this.listItemBudgetIncome.setText(String.valueOf(budget.getBudget()));

    }

}
