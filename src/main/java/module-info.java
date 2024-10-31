module es.aketzagonzalez {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.sql;
    requires java.desktop;
	requires javafx.graphics;
    opens ctrl to javafx.fxml;
    opens models to javafx.base;
    exports es.aketzagonzalez.aeropuertosS;
}