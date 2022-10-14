package Controllers;


import Interfaces.Observer;
import Interfaces.Observable;
import Model.BudgetModel;
import Model.Category;
import View.CategoryListItem;
import View.CategoryOverviewItem;
import View.OverviewView;
import com.example.budgetmaker2_0.HelloApplication;
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
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;


public class BudgetModelController implements Initializable {

    private ArrayList<CategoryListItem> categoryListArray = new ArrayList<>();

    private List<CategoryOverviewItem> CategoryOverviewItemArray = new ArrayList<>();

    User currentUser = User.getInstance();
    BudgetModel currentBudget;

    TransactionsController controller;

    @FXML
    private TextField EnterBudget;

    @FXML
    private Button Budgetknapp;

    @FXML
    private AnchorPane startSida;

    @FXML
    private AnchorPane budgetingPage;

    @FXML
    private Label budgetAmount;

    @FXML
    private Label currentAmount;

    @FXML
    private Button goBack;

    @FXML
    private FlowPane CategoryDivideFlowpane;

    @FXML
    private AnchorPane categoryOverview;

    @FXML
    private Button closeCategorydetailOverview;

    @FXML
    private Button klarKnapp;

    @FXML
    private FlowPane OverviewCategory;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



    }

    @FXML
    public void setNewBudgetModel(){
        currentUser.createNewBudget(Integer.parseInt(EnterBudget.getText()));
        currentBudget = currentUser.getBudgetModel();
        String str = EnterBudget.getText();
        budgetAmount.setText(str);

    }

    public void setCategoryAmount(){
        for(int i = 0; i<categoryListArray.size(); i++){
            currentBudget.getCategory(i).setGoalAmount(categoryListArray.get(i).getCategoryAmount());
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
    public void goBackonePage() {
        startSida.toFront();
        startSida.setVisible(true);
        budgetingPage.setVisible(false);
    }

    void updateCategoryList() {
        CategoryDivideFlowpane.getChildren().clear();
        for (Category category : currentBudget.getCategoryList()) {
            CategoryListItem newCategoryList = new CategoryListItem(category, this);
            categoryListArray.add(newCategoryList);
            CategoryDivideFlowpane.getChildren().add(newCategoryList);

        }
    }

    @FXML
    public void switchToScene2(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        setCategoryAmount();
        Parent root = FXMLLoader.load(getClass().getResource("/overview2.fxml"));
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }



    private void updateOverviewCategoryList(){
        OverviewCategory.getChildren().clear();
        for(Category c : currentBudget.getCategoryList()){
            CategoryOverviewItem newCategoryOverviewItem = new CategoryOverviewItem(c, controller);
            CategoryOverviewItemArray.add(newCategoryOverviewItem);
            OverviewCategory.getChildren().add(newCategoryOverviewItem);

        }


    }


}


