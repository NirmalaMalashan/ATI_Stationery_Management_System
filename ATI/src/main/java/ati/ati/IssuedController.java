package ati.ati;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.security.PublicKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class IssuedController {

    public TextField txtItemNo;
    public TextField txtIssuedTo;
    public TextField txtIssuedNo;
    public TextField txtIssuedQty;
    public TextArea txtDes;
    public Label lblQty;
    public ComboBox cboxName;
    public int Qty;
    public LocalDate Today= java.time.LocalDate.now();

    public void initialize() throws SQLException {
        // TODO Auto-generated method stub
        loadnames();
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
    public void DesClear(ActionEvent actionEvent) {
        txtDes.setText(null);
    }
    public void clearr(){
        cboxName.setValue(null);
        txtItemNo.setText(null);
        txtIssuedTo.setText(null);
        txtIssuedNo.setText(null);
        txtIssuedQty.setText(null);
        txtDes.setText(null);
        lblQty.setText(null);
    }
    public void Clear(ActionEvent actionEvent) {
        clearr();
    }

    public void Save(ActionEvent actionEvent)  {
        Connection con= null;
        try {
            con = Dbconnecter.connect();
            PreparedStatement ps =null;
            String sql= "INSERT INTO issued(Item_No,Issued_To,Issued_No,Issued_Qty,Des,Date) VALUES(?,?,?,?,?,?)";
            ps= con.prepareStatement(sql);
            ps.setString(1, txtItemNo.getText());
            ps.setString(2, txtIssuedTo.getText());
            ps.setString(3, txtIssuedNo.getText());
            ps.setString(4, txtIssuedQty.getText());
            ps.setString(5, txtDes.getText());
            ps.setString(6, String.valueOf(Today));
            ps.execute();
            ps.close();
            con.close();
            Qtyadd();
            clearr();
            Notifications notification1 = Notifications.create()
                    .title("SUCCESSFUL!")
                    .text("Reduce Issued Items To Database.")
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT);
            notification1.show();
        } catch (SQLException e) {
            Notifications notification = Notifications.create()
                    .title("UNSUCCESSFUL!")
                    .text("Could Not Reduce Issued Item To Database.\n Please Try Again. ")
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT);
            notification.show();
        }

    }

    public void as(KeyEvent keyEvent) throws SQLException {
        Connection con= Dbconnecter.connect();
        PreparedStatement ps =null;
        ResultSet rs = null;
        String sql = "Select Item_Name from item where Item_No = ?";
        ps= con.prepareStatement(sql);
        ps.setString(1, (String) txtItemNo.getText());
        rs = ps.executeQuery();
        cboxName.setValue(rs.getString(1));
        rs.close();
        ps.close();
        con.close();
        Qtydisply();
    }

    public void find(ActionEvent actionEvent) throws SQLException {
        Connection con= Dbconnecter.connect();
        PreparedStatement ps =null;
        ResultSet rs = null;
        String sql = "Select Item_No from item where Item_Name = ?";
        ps= con.prepareStatement(sql);
        ps.setString(1, (String) cboxName.getValue());
        rs = ps.executeQuery();
        txtItemNo.setText(rs.getString(1));
        rs.close();
        ps.close();
        con.close();
        Qtydisply();
    }
    public void Qtydisply() throws SQLException {
        Connection con= Dbconnecter.connect();
        PreparedStatement ps =null;
        ResultSet rs = null;
        String sql = "Select Qty from item where Item_No = ?";
        ps= con.prepareStatement(sql);
        ps.setString(1, (String) txtItemNo.getText());
        rs = ps.executeQuery();
        Qty = Integer.parseInt((rs.getString(1)));
        lblQty.setText(String.valueOf(Qty));
        rs.close();
        ps.close();
        con.close();
    }
    public void Qtyadd() throws SQLException {
        Qty= Integer.parseInt(String.valueOf(Qty-Integer.parseInt(txtIssuedQty.getText())));
        Connection con= Dbconnecter.connect();
        PreparedStatement ps =null;
        String sql= ("UPDATE item SET Qty=\'"+Qty+"\' WHERE Item_No="+txtItemNo.getText());
        ps= con.prepareStatement(sql);
        ps.execute();
        ps.close();
        con.close();
    }

}