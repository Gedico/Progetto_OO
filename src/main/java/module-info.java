module com.example.progetto_oo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;

    opens com.example.progetto_oo to javafx.fxml;
    exports com.example.progetto_oo;
    exports com.example.progetto_oo.Controllers;
    exports com.example.progetto_oo.gui;
    opens com.example.progetto_oo.gui to javafx.fxml;
}

