module com.library.my_library{
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.library.my_library to javafx.fxml;
    exports com.library.my_library;
    exports com.library.my_library.controllers;
    opens com.library.my_library.controllers to javafx.fxml;
}