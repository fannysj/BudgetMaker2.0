package Controllers;


import Interfaces.Observer;
import Interfaces.Observable;
import Model.Budget;
import Model.BudgetModel;
import Model.Category;
import Model.Transaction;
import View.BudgetView;
import View.CategoryListItem;
import View.CategoryOverviewItem;
import com.example.budgetmaker2_0.User;
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


public class BudgetModelController implements Initializable {

    private ArrayList<CategoryListItem> categoryListArray = new ArrayList<>();

    private List<CategoryOverviewItem> CategoryOverviewItemArray = new ArrayList<>();

    User currentUser = User.getInstance();
    Budget currentBudget;

    TransactionsController controller;

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
    private AnchorPane startSida;

    @FXML
    private AnchorPane homePage;

    @FXML
    private AnchorPane budgetingPage;

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
    private Text budgetErrorMessage;

    @FXML
    private FlowPane budgetFlowPane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void createNewbudget() {
        startSida.toFront();
        homePage.setVisible(false);
        startSida.setVisible(true);
    }

    @FXML
    public void setNewBudgetModel() {
        try {
            currentUser.createNewBudget(Integer.parseInt(EnterBudget.getText()),budgetID.getText());
            currentBudget = currentUser.getBudget();
            String str = EnterBudget.getText();
            budgetAmount.setText(str);
            changeToBudgetingSide();
        } catch (NumberFormatException e) {
            errorMessage.setVisible(true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public void setCategoryAmount() {
        System.out.println(categoryListArray.size());
        for (int i = 0; i < categoryListArray.size(); i++) {
            try {
                currentBudget.getCategory(i).setGoalAmount(categoryListArray.get(i).getCategoryAmount());
            } catch (NumberFormatException e) {
                currentBudget.getCategory(i).setGoalAmount(0);
            }
        }
    }

    @FXML
    public void changeToBudgetingSide() {
        budgetingPage.toFront();
        startSida.setVisible(false);
        budgetingPage.setVisible(true);
        updateCategoryList();
    }
        public void CategoryToFront() {
            budgetingPage.toFront();
            categoryOverview.setVisible(false);
            budgetingPage.setVisible(true);
        }


        @FXML
        public void goBackonePage () {
            startSida.toFront();
            startSida.setVisible(true);
            budgetingPage.setVisible(false);
        }

        @FXML
        void updateCategoryList () {
            CategoryDivideFlowpane.getChildren().clear();
            for (Category category : currentBudget.getCategoryList()) {
                CategoryListItem newCategoryList = new CategoryListItem(category, this);
                categoryListArray.add(newCategoryList);
                CategoryDivideFlowpane.getChildren().add(newCategoryList);

            }

        }


        @FXML
        public void switchToScene2 (javafx.scene.input.MouseEvent mouseEvent) throws IOException {
            setCategoryAmount();

            if (currentBudget.getTotalGoalAmountOfCategories() == currentBudget.getStartAmount()) {
                Parent root = FXMLLoader.load(getClass().getResource("/overview2.fxml"));
                Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } else {
                budgetErrorMessage.setVisible(true);
            }
        }

        private void updateOverviewCategoryList () {
            OverviewCategory.getChildren().clear();
            for (Category c : currentBudget.getCategoryList()) {
                CategoryOverviewItem newCategoryOverviewItem = new CategoryOverviewItem(c, controller);
                CategoryOverviewItemArray.add(newCategoryOverviewItem);
                OverviewCategory.getChildren().add(newCategoryOverviewItem);

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
        public void backToHomePage () {
            pastBudget.setVisible(false);
            homePage.toFront();
            homePage.setVisible(true);
        }
}



