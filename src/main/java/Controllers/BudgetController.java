package Controllers;


import Model.*;
import View.BudgetView;
import View.CategoryListItem;
import View.CategoryOverviewItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * The BudgetController class represent a Controller in the Model-View-Controller pattern.
 * Listen to the view and updates the model.
 */

public class BudgetController implements Initializable {

    private ArrayList<CategoryListItem> categoryListArray = new ArrayList<>();
    private List<FixedCost> fixedCostList = new ArrayList<>();
    private List<FixedCost> reccuringfixedCostList = new ArrayList<>();

    User currentUser = User.getInstance();
    Budget currentBudget;

    BudgetView budgetView = new BudgetView();

    @FXML
    private ListView<Budget> BudgetListView = new ListView<>();

    @FXML
    private AnchorPane categoryOverview;

    @FXML
    private TextField EnterBudget;

    @FXML
    private Button Budgetknapp;

    @FXML
    private AnchorPane enterBudgetPage;

    @FXML
    private AnchorPane homePage;

    @FXML
    private AnchorPane splitBudgetPage;

    @FXML
    private Label budgetAmount;

    @FXML
    private Button goBack;

    @FXML
    private FlowPane CategoryDivideFlowpane;

    @FXML
    private Button klarKnapp;

    @FXML
    private FlowPane OverviewCategory;

    @FXML
    private Circle createNewbudget;

    @FXML
    private AnchorPane pastBudget;

    @FXML
    private TextField budgetID;

    @FXML
    private Text errorMessage;

    @FXML
    private FlowPane budgetFlowPane;

    @FXML
    private TextField fixedCostName;
    @FXML
    private AnchorPane fixedcostPage;
    @FXML
    private TextField fixedCostAmount;

    @FXML
    private FixedCost fixedCost;
    @FXML
    private CheckBox checkbox;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
    public void addFixedCost() {
        String name = fixedCostName.getText();
        boolean isRecurring = checkbox.isSelected();
        int cost = Integer.parseInt(fixedCostAmount.getText());

        FixedCost newFixedCost = new FixedCost(name, cost, isRecurring);
        if (isRecurring) {
            if (!reccuringfixedCostList.contains(newFixedCost)) {
                reccuringfixedCostList.add(newFixedCost);
            }
        } else {
            fixedCostList.add(newFixedCost);
        }
        closeFixedcost();
        System.out.println(newFixedCost);
    }


    @FXML
    public void createNewbudget() {
        enterBudgetPage.toFront();
        homePage.setVisible(false);
        enterBudgetPage.setVisible(true);

    }

    @FXML
    public void closeFixedcost() {
        splitBudgetPage.toFront();
        fixedcostPage.setVisible(false);
        splitBudgetPage.setVisible(true);
    }



    public void openFixedcost() {
        fixedcostPage.toFront();
        splitBudgetPage.setVisible(false);
        fixedcostPage.setVisible(true);
    }

    /**
     * Method for creating a new BudgetModel for the User with a budget and an id.
     */
    @FXML
    public void setNewBudgetModel() {
        try {
            currentUser.createNewBudget(Integer.parseInt(EnterBudget.getText()), budgetID.getText());
            currentBudget = currentUser.getBudget();
            String str = EnterBudget.getText();
            String coststring = fixedCostAmount.getText();
            int budget = Integer.parseInt(EnterBudget.getText());
            int fixedCostAmount = 0; // Assume fixed cost amount is 0
                if (fixedCost != null) {
                fixedCostAmount = fixedCost.getFixedcostAmount();
            }

            int totalBudgetAmount = budget + fixedCostAmount;

            budgetAmount.setText(String.valueOf(totalBudgetAmount));
            changeToBudgetingSide();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    /**
     * Set amount for every category in list
     */
    public void setCategoryAmount() {
        for (int i = 0; i < categoryListArray.size(); i++) {
            try {
                currentBudget.getCategory(i).setGoalAmount(categoryListArray.get(i).getCategoryAmount());
            } catch (NumberFormatException e) {
                currentBudget.getCategory(i).setGoalAmount(0);
            }
        }
    }


    @FXML
    void updateCategoryList() {
        CategoryDivideFlowpane.getChildren().clear();
        for (Category category : currentBudget.getCategoryList()) {
            CategoryListItem newCategoryList = new CategoryListItem(category, this);
            categoryListArray.add(newCategoryList);
            CategoryDivideFlowpane.getChildren().add(newCategoryList);

        }
    }


    @FXML
    public void switchToOverview(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        setCategoryAmount();

        if (currentBudget.getTotalGoalAmountOfCategories() == currentBudget.getStartAmount()) {
            Parent root = FXMLLoader.load(getClass().getResource("/overview2.fxml"));
            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } else {
            errorMessage.setVisible(true);
        }
    }

    @FXML
    public void goToPastBudget() {
        homePage.setVisible(false);
        pastBudget.toFront();
        pastBudget.setVisible(true);
        budgetView.addBudgetToFlowPane(budgetFlowPane, currentUser.getBudget(), this);

    }

    @FXML
    public void backToHomePage() {
        pastBudget.setVisible(false);
        homePage.toFront();
        homePage.setVisible(true);
    }

    @FXML
    public void backToSplitBudget() {
        enterBudgetPage.toFront();
        enterBudgetPage.setVisible(true);
        splitBudgetPage.setVisible(false);
    }

    @FXML
    public void changeToBudgetingSide() {
        splitBudgetPage.toFront();
        enterBudgetPage.setVisible(false);
        splitBudgetPage.setVisible(true);
        updateCategoryList();
    }

}



