
module com.example.budgetmaker2_0 {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires com.google.gson;
    requires java.base;


    opens com.example.budgetmaker2_0 to javafx.fxml, com.google.gson;
    exports com.example.budgetmaker2_0;
    exports Controllers;
    opens Controllers to javafx.fxml;

    opens Model to javafx.fxml, com.google.gson;
    exports Model;

    opens View to javafx.fxml;
    exports View;

}