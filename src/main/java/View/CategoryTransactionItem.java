package View;

import Controllers.TransactionsController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;

import java.io.IOException;
import java.time.LocalDate;

public class CategoryTransactionItem extends Node {
    @FXML
    private Label transactionName;

    @FXML
    private Label transactionDate;

    @FXML
    private Label transactionAmount;

    @FXML
    private Label transactionNote;

    TransactionsController controller;

    public CategoryTransactionItem(String name, LocalDate date, String amount, String note, TransactionsController controller){
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/categorytransactionitem.fxml"));
        myLoader.setRoot(this);
        myLoader.setController(this);

        try {
            myLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.controller = controller;

        this.transactionName.setText(name);
        this.transactionDate.setText(String.valueOf(date));
        this.transactionAmount.setText(String.valueOf(amount));
        this.transactionNote.setText(note);

    }


    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }
}
