package View;

import Controllers.BudgetModelController;
import Model.Transaction;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class TransactionListItem extends AnchorPane {

    @FXML
    private Label transactionName;

    @FXML
    private Label transactionDate;

    @FXML
    private Label transactionNote;

    @FXML
    private Label transactionAmount;

    private BudgetModelController parentController;
    private Transaction transaction;


    public TransactionListItem(Transaction transaction, BudgetModelController budgetcontroller){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("transactionlistitem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.transaction = transaction;
        this.parentController = budgetcontroller;
    }
}
