package Model;

import Controllers.BudgetMakerController;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class newBudget {
    TextField enter;
    Label amount;
    public newBudget(TextField enterbudget, Label budgetamount) {
        this.enter = enterbudget;
        this.amount = budgetamount;

        placeBudgetonLabel(this.enter, this.amount);
    }

    public void placeBudgetonLabel(TextField amount, Label enter){
        enter.setText(amount.getText());

    }

    public void textfieldtointeger(){
       String s = String.valueOf(enter);
       int foo = Integer.parseInt(s);
    }
}
