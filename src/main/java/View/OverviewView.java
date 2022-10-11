package View;

import Controllers.TransactionsController;
import Model.Category;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class OverviewView extends AnchorPane implements Initializable {

    private Stage stage;

    private Scene scene;

    private Parent root;

    private List<CategoryOverviewItem> CategoryOverviewItemArray = new ArrayList<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    public void switchToTransactionOverview(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/hello-view.fxml"));
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void updateCategoryListItem(FlowPane OverviewCategory, List<Category> categoryList, TransactionsController controller) {
        OverviewCategory.getChildren().clear();
        for (Category category : categoryList) {
            CategoryOverviewItem newCategoryList = new CategoryOverviewItem(category, controller);
            CategoryOverviewItemArray.add(newCategoryList);
            OverviewCategory.getChildren().add(newCategoryList);


        }
    }


}
