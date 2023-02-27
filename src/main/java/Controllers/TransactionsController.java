package Controllers;

import Interfaces.Observable;
import Interfaces.Observer;
import Model.*;
import View.*;
import Model.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.net.URL;

import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * The TransactionController class represent a Controller in the Model-View-Controller pattern.
 * Has a responsibility to listen to the view
 * Handles and updates information and views included transactions
 */

public class TransactionsController implements Initializable, Observer {


    User currentUser = User.getInstance();

    Budget currentBudget;

    OverviewView overviewView = new OverviewView();

    TransactionView transactionView = new TransactionView();


    @FXML
    private Button extraButton;

    @FXML
    private TextField extraTextField;

    @FXML
    private ChoiceBox extraChoiceBox;

    @FXML
    private TextField extraAmount;


    @FXML
    private AnchorPane anchorPaneExtra;

    @FXML
    private AnchorPane overviewAnchorPane;

    @FXML
    public FlowPane OverviewCategory;

    @FXML
    private SplitPane addExpenseSplit;

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
    FlowPane transactionGrid;


    @FXML
    public AnchorPane addExpenseAnchorPane;

    @FXML
    private AnchorPane detailPane;

    @FXML
    private Label leftOfBudgetDisplay;

    @FXML
    private Label spentOfBudgetDisplay;

    @FXML
    private AnchorPane categoryOverview;

    @FXML
    private Label title;

    @FXML
    private Label spent;

    @FXML
    private Label left;

    @FXML
    private ListView<Transaction> transactionListView = new ListView<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentBudget = currentUser.getBudget();
        updateBudgetDisplay();
        updateCategoryListItem();
        for (Category c : currentBudget.getCategoryList()) {
            c.subscribe(this);
        }

    }


    @FXML
    public void updateCategoryListItem() {
        overviewView.updateCategoryListItem(OverviewCategory, currentBudget.getCategoryList(), this);
    }

    /**
     * Adding the new transaction to all views that are involved
     */
    @FXML
    public void createNewTransaction() {
        currentBudget.addTransactionsToCategoryTransactionList();
        addTransactionToHistoryFlowPane();
        updateBudgetDisplay();
        goBacktoOverview();


    }


    public void updateBudgetDisplay() {
        overviewView.updateBudgetDisplay(leftOfBudgetDisplay, spentOfBudgetDisplay, currentBudget.getAmountLeft(), currentBudget.getCurrentAmount());
    }

    /**
     * Loading the choice box with all existing categories
     */

    @FXML
    public void populateCategoryChoiceBox() {
        transactionView.populateCategoryChoiceBox(transactionCategoryChoiceBox, currentBudget.getCategoryList());

    }

    /**
     * Take values from user inputs and send them to create a new transaction
     */
    public void addTransactionToFlowPane() {
        int a = Integer.parseInt(transactionAmountTextField.getText());
        String na = transactionNameTextField.getText();
        String no = transactionNoteTextField.getText();
        LocalDate d = transactionDatePicker.getValue();
        int i = transactionCategoryChoiceBox.getSelectionModel().getSelectedIndex();


        transactionView.addTransactionToFlowPane(transactionFlowPane, currentBudget.createNewTransaction(a, na, no, d, i), this);
    }

    @FXML
    public void populateExtraChoiceBox() {
        overviewView.populateExtraChoiceBox(extraChoiceBox, currentBudget.getCategoryList());
    }

//    public void addExtra(){
//        int a = Integer.parseInt(extraTextField.getText());
//        int i = extraChioceBox.getSelectionModel().getSelectedIndex();
//    }

    /**
     * Method removes chosen transaction from list view for adding multiple transactions
     *
     * @param T TransactionListItem card
     */
    @FXML
    public void removeTransactionFromFlowPane(TransactionListItem T) {
        transactionFlowPane.getChildren().remove(T);
        transactionHistoryFlowPane.getChildren().remove(T);
    }

    /**
     * Adding transaction to overview with the latest transactions
     */
    public void addTransactionToHistoryFlowPane() {
        transactionView.addTransactionToHistoryFlowPane(transactionHistoryFlowPane, currentBudget.getAllTransaction(), this);
    }


    /**
     * Adding transaction to the view for belonging category
     */
    public void addTransactionsToDetailView(Category c) {
        transactionView.addTransactionToCategoryFlowPane(transactionGrid, c.getTransactionsList(), this);
    }

    /**
     * Following methods are responsible to load different views
     */

    @FXML
    public void goBacktoOverview() {
        transactionView.clearTransactionPane(transactionFlowPane);
        setBackToOverview();
    }

    @FXML
    public void setBackToOverview() {
        overviewView.setBackToOverview(overviewAnchorPane, addExpenseSplit);

    }

    @FXML
    public void setAddExpense() {
        transactionView.clearInput(transactionNameTextField, transactionDatePicker, transactionAmountTextField, transactionCategoryChoiceBox, transactionNoteTextField);
        transactionView.setAddExpense(addExpenseAnchorPane, overviewAnchorPane, addExpenseSplit);

    }


    public void setAddExtraAmount() {
        overviewView.clearInputExtraAmount(extraTextField, extraChoiceBox);
    }


    public void openDetailTransaction(Transaction transaction) {
        transactionView.readTransactionView(transaction, transactionNameTextField, transactionAmountTextField, transactionNoteTextField);
        detailPane.toFront();
    }

    @FXML
    public void deleteTransaction(Transaction t) {
        currentBudget.deleteTransaction(t);
    }

    @FXML
    public void openTransactionDetailView(Category category) {
        TransactionOverviewItem transactionOverviewItem = new TransactionOverviewItem(this, category, title, spent, left);
        transactionOverviewItem.openView(categoryOverview);
        addTransactionsToDetailView(category);

    }


    @FXML
    private void closeCategoryDetailView() {
        categoryOverview.toBack();
    }


    /**
     * Search and sort
     */
//    @FXML
//    public void search(int categoryindex){
//        SortCategory sortCategory = new SortCategory();
//        sortCategory.search(currentBudget.getCategory(categoryindex), searchbar);
//    }

    @Override
    public void update(Observable observable) {
        overviewView.updateCategoryItems();

    }


    @FXML
    public void switchToHomePage(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        overviewView.switchToHomePage(mouseEvent);
        TextClass.SerializeBudgets();
    }


    /**
     * Following methods are for adding extra amount to a category
     */

    @FXML
    public void createNewExtraAmount(MouseEvent mouseEvent) {
        int extraAmount = Integer.parseInt(extraTextField.getText());
        int categoryIndex = extraChoiceBox.getSelectionModel().getSelectedIndex();
        currentBudget.createNewExtra(extraAmount, categoryIndex);
        updateGoalAmountForCategory(extraAmount, categoryIndex);
        setAddExtraAmount();

    }

    /**
     * Updating current goal amount for a category
     * @param addAmount
     * @param categoryIndex
     */
    public void updateGoalAmountForCategory(int addAmount, int categoryIndex) {
        int previous = getGoalAmountFromCategory(categoryIndex);
        int spent = getSpentAmountFromCategory(categoryIndex);
        int newAmount = previous + addAmount - spent;
        setNewGoalAmount(newAmount, categoryIndex);
    }

    public int getGoalAmountFromCategory(int categoryIndex) {
        return currentBudget.getCategory(categoryIndex).getGoalAmount();
    }

    public int getSpentAmountFromCategory(int categoryIndex) {
        return currentBudget.getCategory(categoryIndex).getSpentAmount();
    }

    /**
     * Gives the updated value for goal amount in terminal.
     * @param newAmount
     * @param categoryIndex
     */
    public void setNewGoalAmount(int newAmount, int categoryIndex){
        currentBudget.getCategory(categoryIndex).setGoalAmount(newAmount);
        System.out.println("Du har nu: " + newAmount + "kr kvar att spendera i denna kategori");
    }

}


    // update()
    // updateProgressBar()
    //


