package View;

import Controllers.TransactionsController;
import Model.Category;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * This class has methods that handle displaying and dynamically changing our transaction overview
 */

public class TransactionOverviewItem {


    private Label title;

    private Label spent;

    private Label left;

    private Category category;
    private TransactionsController root;


    public TransactionOverviewItem(TransactionsController controller, Category c, Label title ,Label spent, Label left){
        this.root = controller;
        this.category = c;
        this.title = title;
        this.spent = spent;
        this.left = left;

        init();
    }

    private void init(){
        title.setText(category.getName());
        // Update Spent and Left
        spent.setText(String.valueOf(category.getSpentAmount()));
        left.setText(String.valueOf(category.AmountLeftToSpend()));

    }

    public Category getCategory(){
        return category;
    }

    public void openView(AnchorPane pane){
        pane.toFront();

    }




}
