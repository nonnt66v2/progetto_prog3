module org.ldp.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;

    opens org.ldp.demo to javafx.fxml;
    exports org.ldp.demo;
}
