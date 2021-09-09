module com.drbaltar.gameoflifegui {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.drbaltar.gameoflifegui to javafx.fxml;
    exports com.drbaltar.gameoflifegui;
}