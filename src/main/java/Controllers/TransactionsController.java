package Controllers;

import Model.BudgetModel;
import Model.Category;
import Model.Transaction;
import View.CategoryListItem;
import View.TransactionListItem;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.util.converter.LocalDateStringConverter;
import java.time.LocalDate.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class TransactionsController {

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
    private TextField searchbar;

    @FXML FlowPane transactionGrid;

    Category category;




    //Hårdkodat dessa för vet inte hur jag ska få in dem från användar-inputs
    LocalDate date = LocalDate.now();
    BudgetModel currentBudget =  new BudgetModel(1000);

    //Metoder som ska visa transaktioner men fungerar ej :(
    @FXML
    public void createNewTransaction(){
        int a = Integer.parseInt(transactionAmountTextField.getText());
        String na = transactionNameTextField.getText();
        String no = transactionNoteTextField.getText();
        new Transaction(a,na,no, currentBudget.categoryList.get(0), date);

    }

    public void updateOverviewTransactionList(List<Transaction> transactions) {
        transactionFlowPane.getChildren().clear();
        for (Transaction transaction: currentBudget.categoryList.get(0).transactionsList) {
            TransactionListItem newTransactionList = new TransactionListItem(transaction, this);
            transactionListArray.add(newTransactionList);
            transactionFlowPane.getChildren().add(newTransactionList);
        }
    }

    public void updateTransactionList(List<Transaction> transactions) {
        transactionGrid.getChildren().clear();
        for (Transaction transaction: transactions) {
            TransactionListItem newTransactionList = new TransactionListItem(transaction, this);
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

    @FXML
    public void search() {
        List<Transaction> matches = new ArrayList<>();
        try {
            Pattern pattern = Pattern.compile(String.format(".*%s.*", searchbar.getText()), Pattern.CASE_INSENSITIVE);
            category.getTransactionsList().forEach(transaction -> {
                Matcher m = pattern.matcher(transaction.getName());
                if (m.matches()) {
                    matches.add(transaction);
                }
            }) ;
        } catch (PatternSyntaxException e) {
            e.getMessage();
            updateTransactionList(matches);
        }
       // updateTransactionList(matches);
    }
}

