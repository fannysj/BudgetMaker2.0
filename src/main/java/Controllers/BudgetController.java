package Controllers;


import Model.*;
import View.BudgetView;
import View.CategoryListItem;
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
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
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

    List<TitledPane> panes = new ArrayList<>();

    User currentUser = User.getInstance();
    Budget currentBudget;

    BudgetView budgetView = new BudgetView();


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

   @FXML Button pastBudgetButton;

    @FXML
    private AnchorPane detailedView;

    @FXML
    private Label ChosenBudget;

    @FXML
    private Button GoBack;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void createNewbudget() {
        enterBudgetPage.toFront();
        homePage.setVisible(false);
        enterBudgetPage.setVisible(true);
    }

    /**
     * Method for creating a new BudgetModel for the User with a budget and an id.
     */
    @FXML
    public void setNewBudgetModel() {
        try {
            currentUser.createNewBudget(Integer.parseInt(EnterBudget.getText()),budgetID.getText());
            currentBudget = currentUser.getBudget();
            String str = EnterBudget.getText();
            budgetAmount.setText(str);
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
        void updateCategoryList () {
            CategoryDivideFlowpane.getChildren().clear();
            for (Category category : currentBudget.getCategoryList()) {
                CategoryListItem newCategoryList = new CategoryListItem(category, this);
                categoryListArray.add(newCategoryList);
                CategoryDivideFlowpane.getChildren().add(newCategoryList);
            }
        }


        @FXML
        public void switchToOverview (javafx.scene.input.MouseEvent mouseEvent) throws IOException {
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
        public void goToPastBudget() throws IOException {
            homePage.setVisible(false);
            pastBudget.toFront();
            pastBudget.setVisible(true);

        }

        @FXML
        public void backToHomePage () {
            pastBudget.setVisible(false);
            homePage.toFront();
            homePage.setVisible(true);
        }

        @FXML
        public void backToSplitBudget () {
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


    @FXML
    public void SwitchCurrentBudgetToPastBudget() throws FileNotFoundException {
        BudgetView bv = new BudgetView();
        bv.addBudgetToFlowPane(budgetFlowPane, JSONHandler.getInstance().getListOfBudgetFromJsonFile(), this);
    }

    public Label DetailedViewToFront(){
        detailedView.toFront();
        detailedView.setVisible(true);
        return ChosenBudget;
    }

    public void goBack(){
        detailedView.setVisible(false);
        pastBudget.toFront();
    }

}



