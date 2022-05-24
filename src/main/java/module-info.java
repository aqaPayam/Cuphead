module com.example.cuphead2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.media;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires com.google.gson;

    opens com.example.cuphead2 to javafx.fxml;
    exports com.example.cuphead2;
    exports com.example.cuphead2.SceneControllers;
    opens com.example.cuphead2.SceneControllers to javafx.fxml;
    opens com.example.cuphead2.Models to com.google.gson;
}