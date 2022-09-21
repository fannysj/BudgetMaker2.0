package Model;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class newBudget {
    TextField enter;
    int totalBudget;
    Label amount;

    public newBudget(TextField enterbudget, Label amountbudget) {
        this.enter = enterbudget;
        this.amount = amountbudget;


//
        int x = getIntfromTextField(enter);
        amount.setText(Integer.toString(x));
        this.totalBudget = getIntFromAmountLabel();
    }

    public void placeBudgetonLabel(TextField amount, Label enter){
        enter.setText(amount.getText());
    }

    public static int getIntfromTextField(TextField enter){
        String text = enter.getText();
        return Integer.parseInt(text);
    }

    private int getIntFromAmountLabel(){
        return Integer.parseInt(amount.getText());
    }

    public void addCategoryExpense(int amount){
        System.out.println(amount);
        //System.out.println("total: " + totalBudget);
        int newAmount = getIntFromAmountLabel() - amount;
        this.amount.setText(String.valueOf(newAmount));
    }
}
