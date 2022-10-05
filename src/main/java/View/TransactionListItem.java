package View;

import Controllers.BudgetModelController;
import Controllers.TransactionsController;
import Model.Transaction;
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

    private TransactionsController parentController;
    private Transaction transaction;


    public TransactionListItem(Transaction transaction, TransactionsController transactionscontroller){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("transactionlistitem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.transaction = transaction;
        this.parentController = transactionscontroller;
    }
}
