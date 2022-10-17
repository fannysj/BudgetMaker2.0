package View;

import Controllers.TransactionsController;
import Model.Category;
import Model.Transaction;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Flow;

public class TransactionView {

    private ArrayList<TransactionListItem> transactionListArray = new ArrayList<>();

    public void addTransactionToFlowPane(FlowPane pane, Transaction t, TransactionsController controller) {
        TransactionListItem transactionListItem = new TransactionListItem(t, controller);
        transactionListArray.add(transactionListItem);
        pane.getChildren().add(transactionListItem);
    }



    public void clearInput(TextField name, DatePicker date, TextField amount, ChoiceBox cate, TextField note){
        name.clear();
        date.cancelEdit();
        amount.clear();
        cate.getSelectionModel().clearAndSelect(0);
        note.clear();

    }

    public void clearTransactionPane(FlowPane pane){
        pane.getChildren().clear();
        transactionListArray.clear();

    }

    public void setAddExpense(AnchorPane expenseanchor, AnchorPane viewanchor, SplitPane split){
        expenseanchor.toFront();
        viewanchor.setVisible(false);
        split.setVisible(true);
    }

//    public void updateTransactionList(FlowPane grid, FlowPane flow, List<Transaction> transactions, TransactionsController controller){
//        grid.getChildren().clear();
//        for (Transaction transaction: transactions) {
//            flow.getChildren().add(newTransactionList);
//        }
//    }

    public void addTransactionToHistoryFlowPane(FlowPane flow, List<Transaction> list, TransactionsController controller){

        flow.getChildren().clear();
        Collections.reverse(list);
        for (Transaction transaction: list){
            TransactionHistoryItem newHistoryList = new TransactionHistoryItem(transaction, controller);
            flow.getChildren().add(newHistoryList);
        }
    }

    public void addTransactionToCategoryFlowPane (FlowPane pane, List<Transaction> list, TransactionsController controller){
        pane.getChildren().clear();
        Collections.reverse(list);
        for (Transaction t : list){
            TransactionListItem transactionListItem = new TransactionListItem(t, controller);
            pane.getChildren().add(transactionListItem);
        }
    }

    public void populateCategoryChoiceBox(ChoiceBox choiceBox, List<Category> list) {
        choiceBox.getItems().clear();
        for(Category c: list){
            choiceBox.getItems().add(c.getName());
        }
    }

    public void readTransactionView(Transaction transaction, TextField name, TextField amount, TextField note){
        name.setText(transaction.getName());
        amount.setText(String.valueOf(transaction.getTransactionAmount()));
        note.setText(transaction.getNotes());
    }

    public List<TransactionListItem> getTransactionListItems(){
        return transactionListArray;
    }


}
