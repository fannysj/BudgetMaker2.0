package View;

import Controllers.TransactionsController;
import Model.Transaction;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * This class has methods that handle displaying and dynamically changing our categorytransactionitem FXML
 */

public class CategoryTransactionItem extends AnchorPane {
    @FXML
    private Label transactionName;

    @FXML
    private Label transactionDate;

    @FXML
    private Label transactionAmount;

    @FXML
    private Label transactionNote;

    Transaction transaction;

    TransactionsController controller;

    public CategoryTransactionItem(Transaction t, TransactionsController controller){
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/categorytransactionitem.fxml"));
        myLoader.setRoot(this);
        myLoader.setController(this);

        try {
            myLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.controller = controller;
        this.transaction = t;

        this.transactionName.setText(t.getName());
        this.transactionDate.setText(String.valueOf(t.getDate()));
        this.transactionAmount.setText(String.valueOf(t.getTransactionAmount()));
        this.transactionNote.setText(t.getNotes());

    }

    private Transaction getTransaction(){
        return this.transaction;
    }


    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }

}
