module ati.ati {
    requires javafx.controls;
    requires javafx.fxml;
            
            requires com.dlsc.formsfx;
                        
    opens ati.ati to javafx.fxml;
    exports ati.ati;
}