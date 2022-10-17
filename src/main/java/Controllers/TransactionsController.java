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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.time.LocalDate.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

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

    @FXML
    private FlowPane transactionDetailViewHistory;


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

    @FXML
    private Circle homeCircle;


    //Hårdkodat dessa för vet inte hur jag ska få in dem från användar-inputs

    // Add to category observable list, subscribe, kalla till categories addObserver metod.


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
        overviewView.updateBudgetDisplay(leftOfBudgetDisplay,spentOfBudgetDisplay, currentBudget.getAmountLeft(),currentBudget.budgetCurrentAmount());
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
        //addTransactionsToDetailView();

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
        transactionView.addTransactionToHistoryFlowPane(transactionHistoryFlowPane, currentBudget.getTransactionList(), this);
    }

   // public void addTransactionsToDetailView(){
   //     transactionView.addTransactionToHistoryFlowPane(transactionDetailViewHistory, currentBudget.getTransactionList(), this);
   // }



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


    @FXML
    public void switchToHomePage(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        overviewView.switchToHomePage(mouseEvent);
    }


    // update()
    // updateProgressBar()
    //


}
