package Controllers;


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
import java.util.ResourceBundle;


public class BudgetModelController implements Initializable {

    private ArrayList<CategoryListItem> categoryListItemArray = new ArrayList<>();

    BudgetModel currentBudget;


    public User u;

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
    private Button goBack;

    @FXML
    private FlowPane CategoryDivideFlowpane;

    @FXML
    private Button klarKnapp;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        u = new User();

    }

    @FXML
    public void setNewBudgetModel(){
        int budgetValue = Integer.parseInt(EnterBudget.getText());
        u.createNewBudget(budgetValue);
        String str = EnterBudget.getText();
        budgetAmount.setText(str);

    }

    @FXML
    public void changeToBudgetingSide() {
        budgetingPage.toFront();
        startSida.setVisible(false);
        budgetingPage.setVisible(true);

        updateCategoryList();

    }

    @FXML
    public void goBackonePage() {
        startSida.toFront();
        startSida.setVisible(true);
        budgetingPage.setVisible(false);
    }

    void updateCategoryList() {
        CategoryDivideFlowpane.getChildren().clear();
        for (Category category : u.getBudgetModel().categoryList) {
            CategoryListItem newCategoryList = new CategoryListItem(category, this);
            categoryListItemArray.add(newCategoryList);
            CategoryDivideFlowpane.getChildren().add(newCategoryList);

        }
    }

    @FXML
    public void setCategoryAmount(){
        for (CategoryListItem c : categoryListItemArray){
            System.out.println(c.getCategory().getGoalAmount());
        }
    }

    @FXML
    public void switchToScene2(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/overview2.fxml"));
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }

    @FXML
    public void goToCategoryOverview() throws IOException {
        HelloApplication.setRoot("hello-view");
        budgetingPage.setVisible(false);
        startSida.setVisible(false);
    }


}

