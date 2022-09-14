package Model;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class newBudget {
    TextField enter;
    Label amount;

    public newBudget(TextField enterbudget, Label amountbudget) {
        this.enter = enterbudget;
        this.amount = amountbudget;

//
        int x = getIntfromTextField(enter);
        amount.setText(Integer.toString(x));
    }

    public void placeBudgetonLabel(TextField amount, Label enter){
        enter.setText(amount.getText());

    }

    public static int getIntfromTextField(TextField enter){
        String text = enter.getText();
        return Integer.parseInt(text);
    }




}
