/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestaurantOrderProgram;

import java.awt.image.BufferedImage;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class DbCRUD {
    public static Connection conn = null;
    public static String URL = "jdbc:sqlite:D:\\kursova\\SQLiteStudio\\11.db";
    public static boolean IS_ADMIN = false;
    public static String NUMBER = "";
    
    public static boolean IsInput(String login, String password)
    {
        boolean result = true;
        try
        {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(URL);
            String query = "SELECT DISTINCT ID, IsAdmin FROM tbAdmin WHERE Username = '"+login+"' AND Password = '"+password+"'";
            PreparedStatement st = conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            String id = "";
            boolean isAdmin = false;
            while(rs.next())
            {
                id = rs.getString(1);
                isAdmin = rs.getBoolean(2);
            }
            if (id == "")
            {
                result = false;
            }
            IS_ADMIN = isAdmin;
            return result;
        }
        catch(ClassNotFoundException | SQLException ex)
        {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), ex.getMessage());
            return false;
        }
    }

    public static DefaultTableModel getAllMessZakaz()
    {
        DefaultTableModel dm = new DefaultTableModel();
        dm.addColumn("Id");
        dm.addColumn("NameMess");
        //dm.addColumn("Foto");
        dm.addColumn("Price");
        dm.addColumn("");
        String sql = "SELECT * FROM tbMess ";

        try{

            conn = DriverManager.getConnection(URL);
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                dm.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(4),""});
            }
        }catch(Exception ex)
        {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), ex.getMessage());
        }
        return dm;
    }
    public static String getMaxNumber(){
        String result = "0";
        String sql = "SELECT MAX(nr_zakaz) FROM tbHistory ";
        try{
            conn = DriverManager.getConnection(URL);
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                result = rs.getString(1);
            }
        }catch(Exception ex)
        {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), ex.getMessage());
        }
        return result;
    }
    
    public static DefaultTableModel getAllMess(){
        DefaultTableModel dm = new DefaultTableModel();
        dm.addColumn("Id");
        dm.addColumn("NameMess");
        //dm.addColumn("Foto");
        dm.addColumn("Price");
        String sql = "SELECT * FROM tbMess ";
        try{

            conn = DriverManager.getConnection(URL);
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                dm.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(4)});
            }
        }catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), ex.getMessage());
        }
        return dm;
    }

    public static boolean AddUser(String login, String password, boolean isAdmin)
    {
        String sql = "INSERT INTO tbAdmin(Username,Password, IsAdmin) VALUES('"+login+"', '"+password+"', '"+isAdmin+"' )";
        try{
            conn = DriverManager.getConnection(URL);
            PreparedStatement st = conn.prepareStatement(sql);
            st.execute();
            return true;
        }catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), ex.getMessage());
            return false;
        }
    }

    public static boolean updateMess(String id, String name, String price)
    {
        String sql = "UPDATE tbMess SET NameMess = '"+name+"', Price = '"+price+"' WHERE Id = "+id+" ";
        try{
            conn = DriverManager.getConnection(URL);
            PreparedStatement st = conn.prepareStatement(sql);
            st.execute();
            return true;
        }catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), ex.getMessage());
            return false;
        }
    }
    
    public static boolean AddHistory(String s1, String s2, String s3, boolean check)
    {
        String sql = "INSERT INTO tbHistory(nr_zakaz, id_mess, price, date_zakaz, zakaz_status) VALUES('"+s1+"', '"+s2+"', '"+s3+"', DATE('now'), '"+check+"' )";
        try{
            conn = DriverManager.getConnection(URL);
            PreparedStatement st = conn.prepareStatement(sql);
            st.execute();
            return true;
        }catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), ex.getMessage());
            return false;
        }
    }
    
    public static boolean updateZakazStatus(String s, boolean check){
        String sql = "UPDATE tbHistory SET zakaz_status = "+check+" where nr_zakaz = "+s+"";
        try{
            conn = DriverManager.getConnection(URL);
            PreparedStatement st = conn.prepareStatement(sql);
            st.execute();
            return true;
        }catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), ex.getMessage());
            return false;
        }
    }
    
    public static boolean AddMess(String name, String price)
    {
        try{
                String sql = "INSERT INTO tbMess(NameMess, Price) VALUES('"+name+"', '"+price+"' )";
                PreparedStatement st = conn.prepareStatement(sql);
                st.execute();
                st.close();
                return true;
        }catch(Exception ex)
        {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), ex.getMessage());
            return false;
        }
    }
    
    
    
    public static boolean deleteMess(String id)
    {
        String sql = "DELETE FROM tbMess WHERE Id = '"+id+"'";
        try{
            conn = DriverManager.getConnection(URL);
            PreparedStatement st = conn.prepareStatement(sql);
            st.execute();
            return true;
        }catch(Exception ex)
        {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), ex.getMessage());
            return false;
        }
    }
    
    public static DefaultTableModel getNumbersZakaz(){
        DefaultTableModel dm = new DefaultTableModel();
        dm.addColumn("Номер замовлення");
        String sql = "SELECT DISTINCT tbHistory.nr_zakaz FROM tbHistory where zakaz_status = 0";
        try{
            conn = DriverManager.getConnection(URL);
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                dm.addRow(new Object[]{rs.getString(1)});
            }
        }catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), ex.getMessage());
        }
        return dm;
    }
    
    public static DefaultTableModel getAllHistory(){
        DefaultTableModel dm = new DefaultTableModel();
        dm.addColumn("Номер замовлення");
        dm.addColumn("Дата замовлення");
        String sql = "SELECT DISTINCT tbHistory.nr_zakaz , tbHistory.date_zakaz  FROM tbHistory";
        try{

            conn = DriverManager.getConnection(URL);
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                dm.addRow(new Object[]{rs.getString(1),rs.getString(2)});
            }
        }catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), ex.getMessage());
        }
        return dm;
    }
    
    
    
    public static DefaultTableModel getOneZakazHistory(String nrZakaz){
        DefaultTableModel dm = new DefaultTableModel();
        dm.addColumn("Найменування страви");
        dm.addColumn("Ціна");
        dm.addColumn("Дата замовлення");
        String sql = "SELECT   tbMess.NameMess, tbHistory.price, tbHistory.date_zakaz  FROM tbHistory inner JOIN tbMess on tbMess.Id = tbHistory.id_mess \n" +
"WHERE tbHistory.nr_zakaz = '"+nrZakaz+"'";
        try{

            conn = DriverManager.getConnection(URL);
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                dm.addRow(new Object[]{rs.getString(1),rs.getString(2), rs.getString(3)});
            }
        }catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), ex.getMessage());
        }
        return dm;
    }
    public static BufferedImage getImage(String id) throws IOException{
        String sql = "SELECT Foto from tbMess where Id = '"+id+"'";
        BufferedImage img = null;
        try{
            conn = DriverManager.getConnection(URL);
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            Blob blob = rs.getBlob("Foto");
            if(blob == null){
                return null;
            }
            else{
                int blobSize = (int)blob.length();
                byte[] bytes = blob.getBytes(1, blobSize);
                blob.free();
                img = ImageIO.read(new ByteArrayInputStream(bytes));
            }
        }catch(SQLException ex)
        {
            //JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), ex.getMessage());
        }
        return img;
    }
    
    
    
}
