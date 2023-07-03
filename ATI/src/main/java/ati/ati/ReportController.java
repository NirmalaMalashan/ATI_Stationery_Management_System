package ati.ati;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.controlsfx.control.Notifications;

public class ReportController {

    public TextField txtIINo;
    public DatePicker DateIF;
    public DatePicker DateIT;
    public ComboBox cboxNameR;
    public TextField txtSINO;
    public DatePicker dateSF;
    public ComboBox cboxNameS;
    public DatePicker DateST;

    public void initialize() throws SQLException {
        // TODO Auto-generated method stub
        loadnames();
    }
     public void Notification(){
         Notifications notification = Notifications.create()
                 .title("WAIT...")
                 .text("Report is generated.")
                 .hideAfter(Duration.seconds(5))
                 .position(Pos.BOTTOM_RIGHT);
         notification.show();
     }

    public void StockRe(ActionEvent actionEvent) {
        item();
    }
    public void loadnames() throws SQLException {
        Connection con= Dbconnecter.connect();
        ResultSet rs = con.createStatement().executeQuery("select * from item");
        ObservableList data = FXCollections.observableArrayList();
        while (rs.next()){
            data.add(rs.getString(2));
        }
        cboxNameR.setItems(data);
        cboxNameS.setItems(data);

    }
    public void item(){
        Notification();
        Connection con;

        try {

            con = Dbconnecter.connect();
            String Srt =("SELECT * from item");
            JasperDesign jdesign = JRXmlLoader.load(getClass().getResourceAsStream("/ati/ati/ItemRE.jrxml"));
            JRDesignQuery updateQuery =new JRDesignQuery();
            updateQuery.setText(Srt);
            jdesign.setQuery(updateQuery);
            JasperReport Jreport =JasperCompileManager.compileReport(jdesign);
            JasperPrint JasperPrint =JasperFillManager.fillReport(Jreport,null,con);
            JasperViewer.viewReport(JasperPrint, false);
            con.close();
            //JasperExportManager.exportReportToPdfFile(JasperPrint, "C:\\Users\\Nirmala Malshan\\Desktop\\Poject\\fg.pdf" );
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }

    public void IitemRe(ActionEvent actionEvent) {
        Notification();
        Connection con;

        try {

            con = Dbconnecter.connect();
            String Srt =("SELECT issued. No ,\n" +
                    "\tissued. Item_No ,\n" +
                    "\tissued. Issued_To ,\n" +
                    "\tissued. Issued_No ,\n" +
                    "\tissued. Issued_Qty ,\n" +
                    "\tissued. Des ,\n" +
                    "\tissued. Date ,\n" +
                    "\titem. Item_Name \n" +
                    "FROM issued\n" +
                    "\tINNER JOIN item ON \n" +
                    "\t item. Item_No  = issued. Item_No ");
            JasperDesign jdesign = JRXmlLoader.load(getClass().getResourceAsStream("/ati/ati/Issued.jrxml"));
            JRDesignQuery updateQuery =new JRDesignQuery();
            updateQuery.setText(Srt);
            jdesign.setQuery(updateQuery);
            JasperReport Jreport =JasperCompileManager.compileReport(jdesign);
            JasperPrint JasperPrint =JasperFillManager.fillReport(Jreport,null,con);
            JasperViewer.viewReport(JasperPrint, false);
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }

    public void SitemRE(ActionEvent actionEvent) {
        Notification();
        Connection con;

        try {

            con = Dbconnecter.connect();
            String Srt =("SELECT item. Item_Name ,\n" +
                    "\tsupply. NO ,\n" +
                    "\tsupply. Item_No ,\n" +
                    "\tsupply. Supply_From ,\n" +
                    "\tsupply. Supply_No ,\n" +
                    "\tsupply. Supply_Qty ,\n" +
                    "\tsupply. Des ,\n" +
                    "\tsupply. Date \n" +
                    "FROM item\n" +
                    "\tINNER JOIN supply ON \n" +
                    "\t supply. Item_No  = item. Item_No");
            JasperDesign jdesign = JRXmlLoader.load(getClass().getResourceAsStream("/ati/ati/Supply.jrxml"));
            JRDesignQuery updateQuery =new JRDesignQuery();
            updateQuery.setText(Srt);
            jdesign.setQuery(updateQuery);
            JasperReport Jreport =JasperCompileManager.compileReport(jdesign);
            JasperPrint JasperPrint =JasperFillManager.fillReport(Jreport,null,con);
            JasperViewer.viewReport(JasperPrint, false);
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }

    public void NitemRE(ActionEvent actionEvent) {
        Notification();
        Connection con;

        try {

            con = Dbconnecter.connect();
            String Srt =("SELECT  Item_No ,\n" +
                    "\t Item_Name ,\n" +
                    "\t Qty \n" +
                    "FROM item WHERE Qty=0");
            JasperDesign jdesign = JRXmlLoader.load(getClass().getResourceAsStream("/ati/ati/NullRE.jrxml"));
            JRDesignQuery updateQuery =new JRDesignQuery();
            updateQuery.setText(Srt);
            jdesign.setQuery(updateQuery);
            JasperReport Jreport =JasperCompileManager.compileReport(jdesign);
            JasperPrint JasperPrint =JasperFillManager.fillReport(Jreport,null,con);
            JasperViewer.viewReport(JasperPrint, false);
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnSRE(ActionEvent actionEvent) {
        Notification();
        if("%".equals(txtSINO.getText())){
            Connection con;

            try {

                con = Dbconnecter.connect();
                String Srt =("SELECT item. Item_Name ,\n" +
                        "\tsupply. NO ,\n" +
                        "\tsupply. Item_No ,\n" +
                        "\tsupply. Supply_From ,\n" +
                        "\tsupply. Supply_No ,\n" +
                        "\tsupply. Supply_Qty ,\n" +
                        "\tsupply. Des ,\n" +
                        "\tsupply. Date \n" +
                        "FROM item\n" +
                        "\tINNER JOIN supply ON \n" +
                        "\t supply. Item_No  = item. Item_No WHERE (Date BETWEEN '"+dateSF.getValue()+"' AND '"+DateST.getValue()+"')");
                JasperDesign jdesign = JRXmlLoader.load(getClass().getResourceAsStream("/ati/ati/Supply.jrxml"));
                JRDesignQuery updateQuery =new JRDesignQuery();
                updateQuery.setText(Srt);
                jdesign.setQuery(updateQuery);
                JasperReport Jreport =JasperCompileManager.compileReport(jdesign);
                JasperPrint JasperPrint =JasperFillManager.fillReport(Jreport,null,con);
                JasperViewer.viewReport(JasperPrint, false);
                con.close();

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (JRException e) {
                throw new RuntimeException(e);
            }

        }else {
            Connection con;
            try {

                con = Dbconnecter.connect();
                String Srt =("SELECT item. Item_Name ,\n" +
                        "\tsupply. NO ,\n" +
                        "\tsupply. Item_No ,\n" +
                        "\tsupply. Supply_From ,\n" +
                        "\tsupply. Supply_No ,\n" +
                        "\tsupply. Supply_Qty ,\n" +
                        "\tsupply. Des ,\n" +
                        "\tsupply. Date \n" +
                        "FROM item\n" +
                        "\tINNER JOIN supply ON \n" +
                        "\t supply. Item_No  = item. Item_No WHERE (Date BETWEEN '"+dateSF.getValue()+"' AND '"+DateST.getValue()+"') AND (supply.Item_NO ="+txtSINO.getText()+") ");
                JasperDesign jdesign = JRXmlLoader.load(getClass().getResourceAsStream("/ati/ati/Supply.jrxml"));
                JRDesignQuery updateQuery =new JRDesignQuery();
                updateQuery.setText(Srt);
                jdesign.setQuery(updateQuery);
                JasperReport Jreport =JasperCompileManager.compileReport(jdesign);
                JasperPrint JasperPrint =JasperFillManager.fillReport(Jreport,null,con);
                JasperViewer.viewReport(JasperPrint, false);
                con.close();

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (JRException e) {
                throw new RuntimeException(e);
            }
        }

    }


    public void btnIRE(ActionEvent actionEvent) {
        Notification();
        if(txtIINo.getText().equals("%")){
            Connection con;

            try {

                con = Dbconnecter.connect();
                String Srt =("SELECT issued. No ,\n" +
                        "\tissued. Item_No ,\n" +
                        "\tissued. Issued_To ,\n" +
                        "\tissued. Issued_No ,\n" +
                        "\tissued. Issued_Qty ,\n" +
                        "\tissued. Des ,\n" +
                        "\tissued. Date ,\n" +
                        "\titem. Item_Name \n" +
                        "FROM issued \n" +
                        "\tINNER JOIN item ON \n" +
                        "\t item. Item_No  = issued. Item_No WHERE (Date BETWEEN '"+DateIF.getValue()+"' AND '"+DateIT.getValue()+"') ");
                JasperDesign jdesign = JRXmlLoader.load(getClass().getResourceAsStream("/ati/ati/Issued.jrxml"));
                JRDesignQuery updateQuery =new JRDesignQuery();
                updateQuery.setText(Srt);
                jdesign.setQuery(updateQuery);
                JasperReport Jreport =JasperCompileManager.compileReport(jdesign);
                JasperPrint JasperPrint =JasperFillManager.fillReport(Jreport,null,con);
                JasperViewer.viewReport(JasperPrint, false);
                con.close();


            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (JRException e) {
                throw new RuntimeException(e);
            }
        }else {
            Connection con;
            try {

                con = Dbconnecter.connect();
                String Srt =("SELECT issued. No ,\n" +
                        "\tissued. Item_No ,\n" +
                        "\tissued. Issued_To ,\n" +
                        "\tissued. Issued_No ,\n" +
                        "\tissued. Issued_Qty ,\n" +
                        "\tissued. Des ,\n" +
                        "\tissued. Date ,\n" +
                        "\titem. Item_Name \n" +
                        "FROM issued \n" +
                        "\tINNER JOIN item ON \n" +
                        "\t item. Item_No  = issued. Item_No WHERE (Date BETWEEN '"+DateIF.getValue()+"' AND '"+DateIT.getValue()+"') AND (issued.Item_NO ="+txtIINo.getText()+") ");
                JasperDesign jdesign = JRXmlLoader.load(getClass().getResourceAsStream("/ati/ati/Issued.jrxml"));
                JRDesignQuery updateQuery =new JRDesignQuery();
                updateQuery.setText(Srt);
                jdesign.setQuery(updateQuery);
                JasperReport Jreport =JasperCompileManager.compileReport(jdesign);
                JasperPrint JasperPrint =JasperFillManager.fillReport(Jreport,null,con);
                JasperViewer.viewReport(JasperPrint, false);
                con.close();


            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (JRException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void findR(ActionEvent actionEvent) throws SQLException {
        Connection con= Dbconnecter.connect();
        PreparedStatement ps =null;
        ResultSet rs = null;
        String sql = "Select Item_No from item where Item_Name = ?";
        ps= con.prepareStatement(sql);
        ps.setString(1, (String) cboxNameR.getValue());
        rs = ps.executeQuery();
        txtIINo.setText(rs.getString(1));
        rs.close();
        ps.close();
        con.close();
    }


    public void AIINo(KeyEvent keyEvent) throws SQLException {
        Connection con= Dbconnecter.connect();
        PreparedStatement ps =null;
        ResultSet rs = null;
        String sql = "Select Item_Name from item where Item_No = ?";
        ps= con.prepareStatement(sql);
        ps.setString(1, (String) txtIINo.getText());
        rs = ps.executeQuery();
        cboxNameR.setValue(rs.getString(1));
        rs.close();
        ps.close();
        con.close();
    }

    public void ASINO(KeyEvent keyEvent) throws SQLException {
        Connection con= Dbconnecter.connect();
        PreparedStatement ps =null;
        ResultSet rs = null;
        String sql = "Select Item_Name from item where Item_No = ?";
        ps= con.prepareStatement(sql);
        ps.setString(1, (String) txtSINO.getText());
        rs = ps.executeQuery();
        cboxNameS.setValue(rs.getString(1));
        rs.close();
        ps.close();
        con.close();
    }

    public void findS(ActionEvent actionEvent) throws SQLException {
        Connection con= Dbconnecter.connect();
        PreparedStatement ps =null;
        ResultSet rs = null;
        String sql = "Select Item_No from item where Item_Name = ?";
        ps= con.prepareStatement(sql);
        ps.setString(1, (String) cboxNameS.getValue());
        rs = ps.executeQuery();
        txtSINO.setText(rs.getString(1));
        rs.close();
        ps.close();
        con.close();
    }
}