
module com.example.budgetmaker2_0 {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;

    opens com.example.budgetmaker2_0 to javafx.fxml;
    exports com.example.budgetmaker2_0;
    exports Controllers;
    opens Controllers to javafx.fxml;
}