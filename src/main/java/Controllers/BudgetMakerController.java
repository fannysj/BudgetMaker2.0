package Controllers;

import Model.Category;
import Model.CategoryListItem;
import Model.newBudget;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.util.*;
import java.net.URL;


public class BudgetMakerController implements Initializable {
    private List<String> catelist = new ArrayList<>(Arrays.asList("mat","shopping"));
    private Map<Label, CategoryListItem> CategoryListItemMap = new HashMap<Label, CategoryListItem>();


    @FXML
    private TextField EnterBudget;

    @FXML
    private Button Budgetknapp;

    @FXML
    private AnchorPane startSida;

    @FXML
    private AnchorPane testSida;

    @FXML
    private Label budgetAmount;

    @FXML
    private Button goBack;

    @FXML
    private FlowPane categorylist;

    @FXML
    private Button backToOverview;

    @FXML
    private Button addExpense;

    @FXML
    private SplitPane addExpenseSplit;

    @FXML
    private AnchorPane overviewAnchorPane;

    @FXML
    private AnchorPane addExpenseAnchorPane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        for (String cate : catelist){
            categorylist.getChildren().add(new CategoryListItem(cate, this));

        }
    }

    @FXML
    public void setAddExpense(){
        addExpenseSplit.toFront();
        overviewAnchorPane.setVisible(false);
        addExpenseSplit.setVisible(true);
    }

    @FXML
    public void setBackToOverview() {
        overviewAnchorPane.toFront();
        addExpenseSplit.setVisible(false);
        overviewAnchorPane.setVisible(true);
    }
    @FXML
    private Label felmeddelande;

    @FXML
    public void bytSida() {

        testSida.toFront();
        startSida.setVisible(false);
        testSida.setVisible(true);
        populatecategories("Mat",1000);

        new newBudget(EnterBudget, budgetAmount);

    }

    @FXML
    public void goBackonePage(){
        startSida.toFront();
        startSida.setVisible(true);
        testSida.setVisible(false);
    }


    @FXML
    public void hoverOverBudgetButton(){
        Budgetknapp.setStyle("-fx-background-color: #cbc8f6; -fx-background-radius: 20px; -fx-border-color: #fff; -fx-border-radius: 20px;");

    }

    @FXML
    public void stopHoverOverBudgetButton(){
        Budgetknapp.setStyle("-fx-background-color: #a8a2f8; -fx-background-radius: 20px; -fx-border-color: #fff; -fx-border-radius: 20px;");
    }

    @FXML
    public void budgetButtonPressed(){
        Budgetknapp.setStyle("-fx-background-color: #6a66a8; -fx-background-radius: 20px; -fx-border-color: #fff; -fx-border-radius: 20px;");
    }

    public void populatecategories(String name, int amount){
        new Category(name,amount);
    }

//    public void updateCategoryList(){
//        categorylist.getChildren().clear();
//
//        for(int i =0; i<catelist.size(); i++){
//            CategoryListItem listItem = (CategoryListItem) catelist.listIterator();
//            categorylist.getChildren().add(listItem);
//
//        }
//
//    }






}
