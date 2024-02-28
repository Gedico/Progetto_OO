module com.example.progetto_oo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.progetto_oo to javafx.fxml;
    exports com.example.progetto_oo;
    exports com.example.progetto_oo.Controllers;
    exports com.example.progetto_oo.View;
    exports com.example.progetto_oo.Modelli;

}