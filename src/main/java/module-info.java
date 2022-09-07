module com.example.budgetmaker2_0 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.budgetmaker2_0 to javafx.fxml;
    exports com.example.budgetmaker2_0;
}