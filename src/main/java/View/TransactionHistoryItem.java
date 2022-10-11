package View;

import Controllers.TransactionsController;
import Model.Transaction;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class TransactionHistoryItem extends AnchorPane {

    @FXML
    private Label amountLabel;

    @FXML
    private Label dateLabel;

    private TransactionsController parentController;
    private Transaction transaction;

    public TransactionHistoryItem(Transaction transaction, TransactionsController transactionscontroller){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/transactionhistoryitem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.transaction = transaction;
        this.parentController = transactionscontroller;

        dateLabel.setText(String.valueOf(transaction.getDate()));
        amountLabel.setText(String.valueOf(transaction.getTransactionAmount()));
    }

}
