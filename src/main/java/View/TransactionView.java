package View;

import Controllers.TransactionsController;
import Model.Transaction;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public class TransactionView {

    private ArrayList<TransactionListItem> transactionListArray = new ArrayList<>();

    public void addTransactionToFlowPane(FlowPane pane, List<Transaction> list, TransactionsController controller) {
        pane.getChildren().clear();
        for (Transaction transaction : list) {
            TransactionListItem newTransactionList = new TransactionListItem(transaction, controller);
            transactionListArray.add(newTransactionList);
            pane.getChildren().add(newTransactionList);

        }

    }

    public void updateTransactionList(FlowPane grid, FlowPane flow, List<Transaction> transactions, TransactionsController controller){
        grid.getChildren().clear();
        for (Transaction transaction: transactions) {
            TransactionListItem newTransactionList = new TransactionListItem(transaction, controller);
            flow.getChildren().add(newTransactionList);
        }
    }

    public void addTransactionToHistoryFlowPane(FlowPane flow, List<Transaction> list, TransactionsController controller){
        flow.getChildren().clear();
        for (Transaction transaction: list){
            TransactionHistoryItem newHistoryList = new TransactionHistoryItem(transaction, controller);
            flow.getChildren().add(newHistoryList);
        }
    }
}
