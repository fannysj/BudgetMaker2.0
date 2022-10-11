package Controllers;

import Model.BudgetModel;
import Model.Category;
import Model.Transaction;
import View.*;
import com.example.budgetmaker2_0.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
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

public class TransactionsController implements Initializable {

    private ArrayList<TransactionListItem> transactionListArray = new ArrayList<>();

    ObservableList<String> categoriesName = FXCollections.observableArrayList();



    User currentUser = User.getInstance();

    BudgetModel currentBudget;

    OverviewView overviewView = new OverviewView();

    @FXML
    private AnchorPane overviewAnchorPane;

    @FXML
    public FlowPane OverviewCategory;

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
    public AnchorPane addExpenseAnchorPane;

    @FXML
    private AnchorPane detailPane;

    @FXML
    private Label leftOfBudgetDisplay;

    @FXML
    private Label spentOfBudgetDisplay;

    @FXML
    private Button closeCategoryDetailView;

    @FXML
    private AnchorPane categoryOverview;

    @FXML
    private Label title;

    @FXML
    private Label spent;

    @FXML
    private Label left;


    //Hårdkodat dessa för vet inte hur jag ska få in dem från användar-inputs



    //Metoder som ska visa transaktioner men fungerar ej :(
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentBudget = currentUser.getBudgetModel();
        updateBudgetDisplay();
        updateCategoryListItem();
        setBackToOverview();

    }

    public void updateBudgetDisplay(){
        leftOfBudgetDisplay.setText("" + currentBudget.getAmountLeft() + " kr");
        spentOfBudgetDisplay.setText("" + currentBudget.currentAmount() + " kr");
    }

    @FXML
    public void createNewTransaction(){
        int a = Integer.parseInt(transactionAmountTextField.getText());
        String na = transactionNameTextField.getText();
        String no = transactionNoteTextField.getText();
        int i = transactionCategoryChoiceBox.getSelectionModel().getSelectedIndex();
        LocalDate d = transactionDatePicker.getValue();

        currentBudget.addTransaction(a,na,no, i, d);
    }

    @FXML
    public void getCategoryFromChoiceBox(){
        transactionCategoryChoiceBox.getSelectionModel().select(1);
    }

    @FXML
    public void populateCategoryChoiceBox() {
        transactionCategoryChoiceBox.getItems().clear();
        for(Category c: currentUser.getCategoryList()){
            transactionCategoryChoiceBox.getItems().add(c.getName());
        }

    }

    public void addTransactionToFlowPane() {
        transactionFlowPane.getChildren().clear();
        for (Transaction transaction: currentBudget.getTransactionList()) {
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
        addExpenseAnchorPane.toFront();
        overviewAnchorPane.setVisible(false);
        addExpenseSplit.setVisible(true);

    }

    @FXML
    public void toCatDetailView(){

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

    @FXML
    public void updateCategoryListItem(){
            overviewView.updateCategoryListItem(OverviewCategory, currentBudget.getCategoryList(), this);
            }


    @FXML
    private void deleteTransaction(){
        category.deleteTransactionFromList();
        addTransactionToFlowPane();

    }

    @FXML
    public void openTransactionDetailView(Category category){
        TransactionOverviewItem transactionOverviewItem = new TransactionOverviewItem(this, category, title, spent, left);
        transactionOverviewItem.openView(categoryOverview);

    }

    @FXML
    private void closeCategoryDetailView(){
        categoryOverview.toBack();
    }

}
