package View;

import Controllers.BudgetController;
import Model.Budget;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

import java.util.ArrayList;

/**
 * This class has methods that handle displaying and dynamically changing our BudgetView
 */

public class BudgetView {

    private ArrayList<BudgetListItem> budgetListArray = new ArrayList<>();


    public void addBudgetToFlowPane(FlowPane pane, Budget b, BudgetController controller) {
        BudgetListItem budgetListItem = new BudgetListItem(b, controller);
        budgetListArray.add(budgetListItem);
        pane.getChildren().add(budgetListItem);
    }


    public void readBudgetView(Budget budget, Label id, Label amount){
        id.setText(String.valueOf(budget.getId()));
        amount.setText(String.valueOf(budget.getBudget()));
    }

}
