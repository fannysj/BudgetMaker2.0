package View;

import Controllers.TransactionsController;
import Model.Transaction;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.time.LocalDate;

/**
 * This class has methods that handle displaying and dynamically changing our transactionlistitem FXML
 */

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

    private int CategoryIndex;



    public TransactionListItem(Transaction transaction, TransactionsController transactionscontroller){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/transactionlistitem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.parentController = transactionscontroller;

        this.transaction = transaction;

        this.listItemTransactionName.setText(transaction.getName());
        this.listItemTransactionAmount.setText(String.valueOf(transaction.getTransactionAmount()));
        this.listItemTransactionDate.setText(String.valueOf(transaction.getDate()));
        this.listItemTransactionNote.setText(String.valueOf(transaction.getNotes()));
    }

    public Transaction getTransaction(){
        return transaction;
    }

    public String getName(){
        return listItemTransactionName.getText();
    }

    public int getAmount(){
        return Integer.parseInt(listItemTransactionAmount.getText());
    }

    public String getNote(){
        return listItemTransactionNote.getText();
    }

    public int getIndex(){
        return CategoryIndex;
    }

    public LocalDate getDate(){
        String dateLabel = listItemTransactionDate.getText();
        System.out.println(dateLabel);
        LocalDate date = LocalDate.parse(dateLabel);
        return date;
    }

    @FXML
    protected void onClick(Event event){
        parentController.deleteTransaction(this.getTransaction());
        parentController.openDetailTransaction(transaction);
        parentController.removeTransactionFromFlowPane(this);

    }
}
