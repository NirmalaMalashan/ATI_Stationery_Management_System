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
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class SupplyController {

    public TextField txtItemNo;
    public TextField txtSFrom;
    public TextField txtSNo;
    public TextField txtSQty;
    public TextArea txtDescription;
    public Label lblQty;
    public ComboBox cboxName;
    public LocalDate Today= java.time.LocalDate.now();

    public int Qty;


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

    public void DesClear(ActionEvent actionEvent) throws SQLException {
       txtDescription.setText(null);


    }
    public void Clearr(){
        cboxName.setValue(null);
        txtItemNo.setText(null);
        txtSFrom.setText(null);
        txtSNo.setText(null);
        txtSQty.setText(null);
        txtDescription.setText(null);
        lblQty.setText(null);
    }
    public void Clear(ActionEvent actionEvent) {
       Clearr();
    }

    public void Save(ActionEvent actionEvent)  {

        Connection con= null;
        try {
            con = Dbconnecter.connect();
            PreparedStatement ps =null;
            String sql= "INSERT INTO supply(Item_No,Supply_From,Supply_No,Supply_Qty,Des,Date) VALUES(?,?,?,?,?,?)";
            ps= con.prepareStatement(sql);
            ps.setString(1, txtItemNo.getText());
            ps.setString(2, txtSFrom.getText());
            ps.setString(3, txtSNo.getText());
            ps.setString(4, txtSQty.getText());
            ps.setString(5, txtDescription.getText());
            ps.setString(6, String.valueOf(Today));
            ps.execute();
            ps.close();
            con.close();
            Qtyadd();
            Notifications notification1 = Notifications.create()
                    .title("SUCCESSFUL!")
                    .text("Added Supply Items To Database.")
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT);
            notification1.show();
            Clearr();

        } catch (SQLException e) {
            Notifications notification = Notifications.create()
                    .title("UNSUCCESSFUL!")
                    .text("Could Not Add Supply Item To Database.\n Please Try Again. ")
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT);
            notification.show();
        }

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
        Qty= Integer.parseInt(String.valueOf(Qty+Integer.parseInt(txtSQty.getText())));
        Connection con= Dbconnecter.connect();
        PreparedStatement ps =null;
        String sql= ("UPDATE item SET Qty=\'"+Qty+"\' WHERE Item_No="+txtItemNo.getText());
        ps= con.prepareStatement(sql);
        ps.execute();
        ps.close();
        con.close();
    }
}