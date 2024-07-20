module com.example.tuhorario2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    //requires org.xerial.sqlitejsbc;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens com.example.tuhorario2 to javafx.fxml;
    exports com.example.tuhorario2;
    exports com.example.tuhorario2.Controllers;
    exports com.example.tuhorario2.Controllers.User;
    exports com.example.tuhorario2.Controllers.Admin;
    exports com.example.tuhorario2.Models;
    exports com.example.tuhorario2.Views;
}