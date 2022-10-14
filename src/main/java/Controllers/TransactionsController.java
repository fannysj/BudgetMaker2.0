package Controllers;

import Interfaces.Observable;
import Interfaces.Observer;
import Model.BudgetModel;
import Model.Category;
import Model.SortCategory;
import Model.Transaction;
import View.*;
import com.example.budgetmaker2_0.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.net.URL;

import java.time.LocalDate;
import java.util.ResourceBundle;

public class TransactionsController implements Initializable, Observer {






    User currentUser = User.getInstance();

    BudgetModel currentBudget;

    OverviewView overviewView = new OverviewView();

    TransactionView transactionView = new TransactionView();

    @FXML
    private AnchorPane overviewAnchorPane;

    @FXML
    public FlowPane OverviewCategory;

    @FXML
    private SplitPane addExpenseSplit;

    @FXML
    private Button addTransactionButton;

    @FXML
    private FlowPane transactionHistoryFlowPane;

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

    @FXML
    private ListView<Transaction> transactionListView = new ListView<>() ;


    //Hårdkodat dessa för vet inte hur jag ska få in dem från användar-inputs


    //Metoder som ska visa transaktioner men fungerar ej :(

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentBudget = currentUser.getBudgetModel();
        updateBudgetDisplay();
        updateCategoryListItem();
        for(Category c : currentBudget.getCategoryList()){
            c.subscribe(this);
        }

    }

    public void updateBudgetDisplay(){
        overviewView.updateBudgetDisplay(leftOfBudgetDisplay,spentOfBudgetDisplay, currentBudget.getAmountLeft(),currentBudget.getAmountSpent());
    }

    @FXML
    public void updateCategoryListItem(){
        overviewView.updateCategoryListItem(OverviewCategory, currentBudget.getCategoryList(), this);
    }

    @FXML
    public void goBacktoOverview(){
        transactionView.clearTransactionPane(transactionFlowPane);
        setBackToOverview();

    }

    @FXML
    public void createNewTransaction(){
        currentBudget.addTemporaryTransactionsToCategoryTransactionList();
        updateBudgetDisplay();
        goBacktoOverview();
        addTransactionToHistoryFlowPane();

    }

    @FXML
    public void populateCategoryChoiceBox() {
        transactionView.populateCategoryChoiceBox(transactionCategoryChoiceBox, currentUser.getCategoryList());

    }

    public void addTransactionToFlowPane() {
        int a = Integer.parseInt(transactionAmountTextField.getText());
        String na = transactionNameTextField.getText();
        String no = transactionNoteTextField.getText();
        LocalDate d = transactionDatePicker.getValue();
        int i = transactionCategoryChoiceBox.getSelectionModel().getSelectedIndex();


        transactionView.addTransactionToFlowPane(transactionFlowPane, currentBudget.createNewTransaction(a,na,no,i,d),this);

    }




//    public void updateTransactionList(List<Transaction> transactions) {
//        transactionView.updateTransactionList(transactionGrid,transactionFlowPane,transactions,this);
//    }

    public void addTransactionToHistoryFlowPane(){
        transactionView.addTransactionToHistoryFlowPane(transactionHistoryFlowPane, currentBudget.getRecentTransactions(), this);
    }


    //Metoder som tar hand om att byta sida
    @FXML
    public void setBackToOverview() {
        overviewView.setBackToOverview(overviewAnchorPane, addExpenseSplit);

    }

    @FXML
    public void setAddExpense() {
        transactionView.clearInput(transactionNameTextField,transactionDatePicker,transactionAmountTextField,transactionCategoryChoiceBox,transactionNoteTextField);
        transactionView.setAddExpense(addExpenseAnchorPane, overviewAnchorPane, addExpenseSplit);

    }

    //Öppna en specifik transaktion ????
    public void openDetailTransaction(Transaction transaction){
        transactionView.readTransactionView(transaction, transactionNameTextField, transactionAmountTextField, transactionNoteTextField);
        detailPane.toFront();
    }

    @FXML
    public void search(int categoryindex){
        SortCategory sortCategory = new SortCategory();
        sortCategory.search(currentBudget.getCategory(categoryindex), searchbar);
    }

    @FXML
    private void deleteTransaction(){
        category.deleteTransactionFromList();
    }

    @FXML
    public void openTransactionDetailView(Category category){
        TransactionOverviewItem transactionOverviewItem = new TransactionOverviewItem(this, category, title, spent, left);
        categoryOverview.toFront();

    }


    @FXML
    private void closeCategoryDetailView(){
        categoryOverview.toBack();
    }

    @Override
    public void update(Observable observable) {
        overviewView.updateCategoryItems();

    }


    // update()
    // updateProgressBar()
    //


}
