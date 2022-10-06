package View;

import Controllers.BudgetModelController;
import Controllers.TransactionsController;
import Model.Transaction;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class TransactionListItem extends AnchorPane {

    @FXML
    private Button addTransactionButton;

    @FXML
    private DatePicker transactionDatePicker;

    @FXML
    private Label transactionAmountLabel;

    @FXML
    private ChoiceBox transactionCategoryChoiceBox;

    @FXML
    private TextField transactionNoteTextField;

    @FXML
    private Label transactionNameLabel;

    @FXML
    private Label listItemTransactionName;

    @FXML
    private Label listItemTransactionDate;

    @FXML
    private Label listItemTransactionNote;

    @FXML
    private Label listItemTransactionAmount;

    @FXML
    private Button deleteTransaction;

    @FXML
    private Button changeTransaction;


    private TransactionsController parentController;
    private Transaction transaction;


    public TransactionListItem(Transaction transaction, TransactionsController transactionscontroller){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/transactionlistitem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.transaction = transaction;
        this.parentController = transactionscontroller;

        listItemTransactionName.setText(String.valueOf(transaction.getName()));
        listItemTransactionAmount.setText(String.valueOf(transaction.getTransactionAmount()));
        listItemTransactionDate.setText(String.valueOf(transaction.getDate()));
        listItemTransactionNote.setText(String.valueOf(transaction.getNotes()));
    }

    @FXML
    protected void onClick(Event event){
        parentController.openDetailTransaction(transaction);
    }
}
