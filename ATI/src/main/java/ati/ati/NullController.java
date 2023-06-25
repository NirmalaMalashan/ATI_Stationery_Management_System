package ati.ati;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NullController {

    public TableColumn<nullList, Integer> itemNo;
    public TableColumn<nullList, String> itemName;
    public TableView<nullList> nulltb;

    ObservableList<nullList> listM;

    public void initialize() throws SQLException {
        // TODO Auto-generated method stub
        itemNo.setCellValueFactory(new PropertyValueFactory<>("ItemNo"));
        itemName.setCellValueFactory(new PropertyValueFactory<>("ItemName"));
        //add your data to the table here.
        listM=getData();
        nulltb.setItems(listM);
    }

     public static ObservableList<nullList>getData() throws SQLException {
         Connection con= Dbconnecter.connect();
         ObservableList<nullList> nullList = FXCollections.observableArrayList();
         try {
             PreparedStatement ps =null;
             ResultSet rs = null;
             String sql = "Select Item_No,Item_Name from item WHERE Qty=0";
             ps= con.prepareStatement(sql);
             rs = ps.executeQuery();
                while (rs.next()){
                    nullList.add(new nullList(Integer.parseInt(rs.getString(1)), rs.getString(2)));
                }
         } catch (SQLException e) {
             throw new RuntimeException(e);
         }
         return nullList;
     }
  /* private ObservableList<nullList> nullList = FXCollections.observableArrayList(
            new nullList(1,"Amos"),
            new nullList(2,"Keep")
            );
*/

   /* public void Load() throws SQLException {
        Connection con= Dbconnecter.connect();
        PreparedStatement ps =null;
        ResultSet rs = null;
        String sql = "Select Item_No,Item_Name from item";
        ps= con.prepareStatement(sql);
        //ps.setString(1, (String) cboxName.getValue());
        rs = ps.executeQuery();
       // txtDItemNo.setText(rs.getString(1));
        //itemNo.setText(rs.getString(1));
        itemName.setText(rs.getString(2));
        itemNo.cellValueFactoryProperty().setValue(rs.getString(1));
        rs.close();
        ps.close();
        con.close();

    }*/
}