package Controllers;

import Model.BudgetModel;
import Model.Category;
import Model.Transaction;
import View.CategoryListItem;
import View.CategoryOverviewItem;
import View.OverviewView;
import View.TransactionListItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.util.converter.LocalDateStringConverter;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class TransactionsController {

    private ArrayList<TransactionListItem> transactionListArray = new ArrayList<>();

    ObservableList<Category> categories = FXCollections.observableArrayList();

    private List<CategoryOverviewItem> CategoryOverviewItemArray = new ArrayList<>();

    OverviewView overviewView = new OverviewView();

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

    @FXML
    public FlowPane OverviewCategory;


    @FXML
    private AnchorPane detailPane;


    //Hårdkodat dessa för vet inte hur jag ska få in dem från användar-inputs
    BudgetModel currentBudget = new BudgetModel(100);


    //Metoder som ska visa transaktioner men fungerar ej :(
    @FXML
    public void createNewTransaction(){
        int a = Integer.parseInt(transactionAmountTextField.getText());
        String na = transactionNameTextField.getText();
        String no = transactionNoteTextField.getText();
        Transaction t = new Transaction(a,na,no, currentBudget.categoryList.get(0), LocalDate.now()); //Behövs ändras så category inte är hårdkodat
        currentBudget.categoryList.get(0).transactionsList.add(t); //Behövs ändras så category inte är hårdkodat

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
        updateCategoryListItem();

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


    public void updateCategoryListItem(){
            OverviewCategory.getChildren().clear();
            for (Category category : currentBudget.categoryList) {
                CategoryOverviewItem newCategoryList = new CategoryOverviewItem(category, this);
                CategoryOverviewItemArray.add(newCategoryList);
                OverviewCategory.getChildren().add(newCategoryList);
            }

    }


    @FXML
    public void switchToTransactionOverview(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        overviewView.switchToTransactionOverview(mouseEvent);


    }
}
