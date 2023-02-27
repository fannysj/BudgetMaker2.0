package View;

import Controllers.BudgetController;
import Model.Budget;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.List;

/**
 * This class has methods that handle displaying and dynamically changing our budgetlistitem FXML
 */

public class BudgetListItem extends AnchorPane {

    @FXML
    private Label listItemBudgetId;

    @FXML
    private Label listItemBudgetIncome;

    @FXML
    private Label Spent;

    @FXML
    private Label Saved;


    private BudgetController parentController;
    private Budget budget;




    public BudgetListItem(Budget budget, BudgetController budgetcontroller){
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

        this.listItemBudgetId.setText("Namn: " + String.valueOf(budget.getName()));
        this.listItemBudgetIncome.setText("Budgeten var: " + String.valueOf(budget.getBudgetAmount()) + " kr");
        this.Spent.setText("Spenderat: " + String.valueOf(budget.getCurrentAmount()) + "kr");
        this.Saved.setText(budget.SavedOrLost());

    }


    @FXML
    public void showDetailedView() throws IOException {
        Label view = parentController.DetailedViewToFront();
        view.setText(budget.toString());



    }

}
