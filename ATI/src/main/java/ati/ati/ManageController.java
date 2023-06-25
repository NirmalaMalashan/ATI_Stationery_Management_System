package ati.ati;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManageController {
    public TextField txtAItemNo;
    public TextField txtAItemName;
    public TextField txtDItemNo;
    public ComboBox cboxName;

    public void initialize() throws SQLException {
        // TODO Auto-generated method stub
        loadnames();
    }
    public void AClear(ActionEvent actionEvent) {
        txtAItemName.setText(null);
        txtAItemNo.setText(null);
    }

    public void ASave(ActionEvent actionEvent) throws SQLException {
        Connection con= Dbconnecter.connect();
        PreparedStatement ps =null;
        String sql= "INSERT INTO item(Item_No,Item_Name,Qty) VALUES(?,?,?)";
        ps= con.prepareStatement(sql);
        ps.setString(1, txtAItemNo.getText());
        ps.setString(2, txtAItemName.getText());
        ps.setString(3, "0");
        ps.execute();
        ps.close();
        con.close();
    }

    public void DClear(ActionEvent actionEvent) throws SQLException {
//        txtDItemNo.setText(null);
//        cboxName.setValue(null);
            ff();
    }

    public void DDelete(ActionEvent actionEvent) throws SQLException {
        Connection con= Dbconnecter.connect();
        PreparedStatement ps =null;
        String sql= ("delete from item where Item_No = ?");
        ps= con.prepareStatement(sql);
        ps.setString(1, txtDItemNo.getText());
        ps.execute();
        ps.close();
        con.close();
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

}
