package ati.ati;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManageController {
    public TextField txtAItemNo;
    public TextField txtAItemName;
    public TextField txtDItemNo;
    public ComboBox cboxName;
    public int NextNo;

    public void initialize() throws SQLException {
        // TODO Auto-generated method stub
        loadnames();
        AutoItemNum();
    }
    public void AClear(ActionEvent actionEvent) throws SQLException {
        txtAItemName.setText(null);
        txtAItemNo.setText(null);
        initialize();

    }

    public void ASave(ActionEvent actionEvent) {
        Connection con= null;
        try {
            con = Dbconnecter.connect();
            PreparedStatement ps =null;
            String sql= "INSERT INTO item(Item_No,Item_Name,Qty) VALUES(?,?,?)";
            ps= con.prepareStatement(sql);
            ps.setString(1, txtAItemNo.getText());
            ps.setString(2, txtAItemName.getText());
            ps.setString(3, "0");
            ps.execute();
            ps.close();
            con.close();
            Notifications notification1 = Notifications.create()
                    .title("SUCCESSFUL!")
                    .text("Added Item To Database.")
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT);
            notification1.show();
            txtAItemName.setText(null);
            txtAItemNo.setText(null);
            initialize();
        } catch (SQLException e) {
            Notifications notification = Notifications.create()
                    .title("UNSUCCESSFUL!")
                    .text("Could Not Added Item To Database.\n Please Try Again. ")
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT);
            notification.show();
        }

    }

    public void DClear(ActionEvent actionEvent) throws SQLException {
        txtDItemNo.setText(null);
        cboxName.setValue(null);
        initialize();
    }

    public void DDelete(ActionEvent actionEvent) {
        Connection con= null;
        try {
            con = Dbconnecter.connect();
            PreparedStatement ps =null;
            String sql= ("delete from item where Item_No = ?");
            ps= con.prepareStatement(sql);
            ps.setString(1, txtDItemNo.getText());
            ps.execute();
            ps.close();
            con.close();
            Notifications notification3 = Notifications.create()
                    .title("SUCCESSFUL!")
                    .text("Delete Item To Database.")
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT);
            notification3.show();
            txtDItemNo.setText(null);
            cboxName.setValue(null);
            initialize();
        } catch (SQLException e) {
            Notifications notification4 = Notifications.create()
                    .title("UNSUCCESSFUL!")
                    .text("Could Not Delete Item To Database.\n Please Try Again. ")
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT);
            notification4.show();

        }

    }
    public void loadnames() throws SQLException {
        Connection con= Dbconnecter.connect();
        ResultSet rs = con.createStatement().executeQuery("select * from item");
        ObservableList data = FXCollections.observableArrayList();


        while (rs.next()){
            data.add(rs.getString(2));
        }
        cboxName.setItems(data);

    }


    public void find(ActionEvent actionEvent) throws SQLException {
        Connection con= Dbconnecter.connect();
        PreparedStatement ps =null;
        ResultSet rs = null;
        String sql = "Select Item_No from item where Item_Name = ?";
        ps= con.prepareStatement(sql);
        ps.setString(1, (String) cboxName.getValue());
        rs = ps.executeQuery();
        txtDItemNo.setText(rs.getString(1));
        rs.close();
        ps.close();
        con.close();
    }
    public void ff() throws SQLException {
        Connection con= Dbconnecter.connect();
        PreparedStatement ps =null;
        ResultSet rs = null;
        String sql = "Select Item_Name from item where Item_No = ?";
        ps= con.prepareStatement(sql);
        ps.setString(1, (String) txtDItemNo.getText());
        rs = ps.executeQuery();
        cboxName.setValue(rs.getString(1));
        rs.close();
        ps.close();
        con.close();
    }

    public void as(KeyEvent keyEvent) throws SQLException {
        ff();
    }
    public void AutoItemNum() throws SQLException {

        Connection con= Dbconnecter.connect();
        PreparedStatement ps =null;
        ResultSet rs=null;
        String sql= "SELECT Max(Item_No) from item desc";
        ps= con.prepareStatement(sql);
        rs = ps.executeQuery();
        NextNo =(1+0+Integer.parseInt(rs.getString(1)));
        txtAItemNo.setText(Integer.toString(NextNo));
        rs.close();
        ps.close();
        con.close();
    }

}
