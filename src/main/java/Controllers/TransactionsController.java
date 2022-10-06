package Controllers;

import Model.BudgetModel;
import Model.Category;
import Model.Transaction;
import View.CategoryListItem;
import View.TransactionListItem;
import java.net.URL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.util.converter.LocalDateStringConverter;

import java.net.URL;
import java.time.LocalDate.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TransactionsController implements Initializable {

    private ArrayList<TransactionListItem> transactionListArray = new ArrayList<>();

    @FXML
    private AnchorPane overviewAnchorPane;

    @FXML
    private SplitPane addExpenseSplit;

    @FXML
    private Button addTransactionButton;

    @FXML
    private DatePicker transactionDatePicker;

    @FXML
    private TextField transactionAmountTextField;

    @FXML
    private ChoiceBox transactionCategoryChoiceBox;

    @FXML
    private TextField transactionNoteTextField;

    @FXML
    private TextField transactionNameTextField;

    @FXML
    private FlowPane transactionFlowPane;

    @FXML
    private AnchorPane detailPane;


    //Hårdkodat dessa för vet inte hur jag ska få in dem från användar-inputs
    LocalDate date = LocalDate.now();
    BudgetModel currentBudget = new BudgetModel(100);


    //Metoder som ska visa transaktioner men fungerar ej :(
    @FXML
    public void createNewTransaction(){
        int a = Integer.parseInt(transactionAmountTextField.getText());
        String na = transactionNameTextField.getText();
        String no = transactionNoteTextField.getText();
        new Transaction(a,na,no, currentBudget.categoryList.get(0), date);

    }

    public void initialize(URL url, ResourceBundle rb) {
        transactionCategoryChoiceBox.getItems().addAll(currentBudget.categoryList);

    }

    public void getCategoryFromChoiceBox(){
        transactionCategoryChoiceBox.getSelectionModel().select(1);
    }

    public void addTransactionToFlowPane() {
        transactionFlowPane.getChildren().clear();
        for (Transaction transaction: currentBudget.categoryList.get(0).transactionsList) {
            TransactionListItem newTransactionList = new TransactionListItem(transaction, this);
            transactionListArray.add(newTransactionList);
            transactionFlowPane.getChildren().add(newTransactionList);
        }
    }


    //Metoder som tar hand om att byta sida
    @FXML
    public void setBackToOverview() {
        overviewAnchorPane.toFront();
        addExpenseSplit.setVisible(false);
        overviewAnchorPane.setVisible(true);
    }

    @FXML
    public void setAddExpense() {
        addExpenseSplit.toFront();
        overviewAnchorPane.setVisible(false);
        addExpenseSplit.setVisible(true);
    }

    //Öppna en specifik transaktion
    public void openDetailTransaction(Transaction transaction){
        transactionView(transaction);
        detailPane.toFront();
    }

    //Läser vyn för den transaktion som just nu ska visas
    public void transactionView(Transaction transaction){
        transactionNameTextField.setText(transaction.getName());
        transactionAmountTextField.setText(String.valueOf(transaction.getTransactionAmount()));
        transactionNoteTextField.setText(transaction.getNotes());
    }

    public void changeTransaction(Transaction T){
        openDetailTransaction(T);
        //changeOldTransaction(T);
    }

}
