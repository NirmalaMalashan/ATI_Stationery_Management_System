module ati.ati {
    requires javafx.controls;
    requires javafx.fxml;
            
            requires com.dlsc.formsfx;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires jasperreports;
    requires org.controlsfx.controls;

    opens ati.ati to javafx.fxml;
    exports ati.ati;
}