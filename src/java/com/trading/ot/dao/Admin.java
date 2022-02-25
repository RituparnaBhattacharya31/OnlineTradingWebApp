/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trading.ot.dao;

import com.trading.ot.beans.StockOrdered;
import com.trading.ot.core.ConnectionManager;
import com.trading.ot.beans.Stocks;
import com.trading.ot.beans.User;
import com.trading.ot.beans.wishlist;
import java.io.BufferedReader;
import java.io.FileReader;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.log4j.Logger;

/**
 *
 * @author Adity
 */
public class Admin {

    public int updateUserPassword(String curpassword, String newpassword, String renewpassword, String emailId) throws SQLException, Exception {

        Connection con = ConnectionManager.getConnection();
        ResultSet rs = null;
        User user = new User();
        int i = 0;
        try {

            String sql1 = "SELECT * FROM user WHERE emailId=?";
            PreparedStatement ps1 = con.prepareStatement(sql1);
            ps1.setString(1, emailId);
            rs = ps1.executeQuery();
            if (rs.next()) {
                String npassword = rs.getString("password");
                if (npassword.equals(curpassword) && newpassword.equals(renewpassword)) {
                    String sql = "UPDATE user SET password = ?"
                            + "WHERE emailId = ?";
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setString(1, newpassword);
                    ps.setString(2, emailId);
                    System.out.println("Select SQL = " + ps);
                    i = ps.executeUpdate();
                    return i;
                } else {
                    return 0;
                }
            } else {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    public int updateUserDetails(String name, String dob, String address, String phoneNumber,
            String emailId) throws SQLException, Exception {

        Connection con = ConnectionManager.getConnection();
        int i = 0;
        try {
            String sql = "UPDATE user SET name = ?, dob = ?, "
                    + "address = ?, phoneNumber = ?"
                    + "WHERE emailId = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, dob);
            ps.setString(3, address);
            ps.setString(4, phoneNumber);
            ps.setString(5, emailId);
            System.out.println("Select SQL = " + ps);
            i = ps.executeUpdate();
            return i;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    public int updateStockDetails(int stockId, String stockName, double price, int availability) throws SQLException, Exception {

        Connection con = ConnectionManager.getConnection();
        int i = 0;
        try {
            String sql = "UPDATE stocks SET stockName = ?, price = ?, availability = ? WHERE stockId = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, stockName);
            ps.setDouble(2, price);
            ps.setInt(3, availability);
            ps.setInt(4, stockId);
            System.out.println("Select SQL = " + ps);
            i = ps.executeUpdate();
            return i;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    public int updateStockLists(String csvFilePath) throws SQLException, Exception {

        Connection con = ConnectionManager.getConnection();
        int i = 0;
        int p = 0;
        int batchSize = 10;
        try {

            String sql = "INSERT INTO trading.stocks (stockName,price,availability) VALUES(?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);
            BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath));
            String lineText = null;

            int count = 0;
            lineReader.readLine();
            while ((lineText = lineReader.readLine()) != null) {
                String[] data = lineText.split(",");
                String stockName = data[0];
                String price = data[1];
                String availability = data[2];

                System.out.println(stockName);
                System.out.println(price);
                System.out.println(availability);

                ps.setString(1, stockName);
                ps.setDouble(2, Double.parseDouble(price));
                ps.setInt(3, Integer.parseInt(availability));

                System.out.println("Select SQL = " + ps);

                ps.addBatch();

                if (count % batchSize == 0) {
                    ps.executeBatch();
                }
                i = i + 1;
            }
            lineReader.close();

            // execute the remaining queries
            ps.executeBatch();
            return i;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            if (con != null) {
                con.close();
            }
        }

    }

    public int deleteStock(int stockId) throws SQLException, Exception {

        Connection con = ConnectionManager.getConnection();
        int i = 0;
        try {
            String sql = "UPDATE trading.stocks SET availability = 0 WHERE stockId = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, stockId);
            i = ps.executeUpdate();
            return i;

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            if (con != null) {
                con.close();
            }
        }

    }

    public int registerUser(String name, String emailId, String phoneNumber, String dob,
            String password, String address) throws Exception {
        int i = 0;
        Connection con = null;
        String encryptedpass=null;
        try {
            con = ConnectionManager.getConnection();
            String sql = "INSERT INTO trading.user(name,emailId,phoneNumber,dob,password,address,status) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            /* MessageDigest instance for MD5. */
//            MessageDigest m = MessageDigest.getInstance("MD5");
//            /* Add plain-text password bytes to digest using MD5 update() method. */ 
//            m.update(password.getBytes());
//            
//            byte[] bytes = m.digest();  
//              
//            /* The bytes array has bytes in decimal form. Converting it into hexadecimal format. */  
//            StringBuilder s = new StringBuilder();  
//            for(int j=0; j< bytes.length ;j++)  
//            {  
//                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));  
//            }
//            encryptedpass = s.toString();
//            String encryptedpass=BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());

            ps.setString(1, name);
            ps.setString(2, emailId);
            ps.setString(3, phoneNumber);
            ps.setString(4, dob);
            ps.setString(5, password);
            ps.setString(6, address);
            ps.setInt(7, 0);
            System.out.println("SQL for insert=" + ps);
            i = ps.executeUpdate();
            return i;
        } catch (Exception e) {
            e.printStackTrace();
            return i;
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }
  
    public int updateOtp(String otp, String emailId) throws Exception {
        int i = 0;
        Connection con = null;
        try {
            con = ConnectionManager.getConnection();
            String sql = "UPDATE trading.user SET otp=? WHERE emailId=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, otp);
            ps.setString(2, emailId);
            System.out.println("SQL for insert=" + ps);
            i = ps.executeUpdate();
            return i;
        } catch (Exception e) {
            e.printStackTrace();
            return i;
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    public int updateOtpPassword(String newotp, String newpassword, String confirmpassword, String emailId) throws SQLException, Exception {

        Connection con = ConnectionManager.getConnection();
        ResultSet rs = null;
        User user = new User();
        int i = 0;
        try {

            String sql1 = "SELECT * FROM user WHERE emailId=?";
            PreparedStatement ps1 = con.prepareStatement(sql1);
            ps1.setString(1, emailId);
            rs = ps1.executeQuery();
            if (rs.next()) {
                String notp = rs.getString("otp");
                if (notp.equals(newotp) && newpassword.equals(confirmpassword)) {
                    String sql = "UPDATE user SET password = ?"
                            + "WHERE emailId = ?";
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setString(1, newpassword);
                    ps.setString(2, emailId);
                    System.out.println("Select SQL = " + ps);
                    i = ps.executeUpdate();
                    return i;
                } else {
                    return 0;
                }
            } else {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    public User checkValidUser(String emailId, String password) throws SQLException, Exception {
        ResultSet rs = null;
        User user = new User();
        Connection con = null;
        boolean valid = true;
        try {
            con = ConnectionManager.getConnection();
            String sql = "SELECT * FROM user WHERE emailId=? and password=?";
            System.out.println("Got the connection.........................." + con);
            PreparedStatement ps = con.prepareStatement(sql);
            System.out.println("emailId = " + emailId);
            
            ps.setString(1, emailId);
            ps.setString(2, password);
            System.out.println("Select SQL = " + ps);

            rs = ps.executeQuery();
            if (rs.next()) {
                user.setUserId(rs.getInt("userId")); //here you need to change
                user.setName(rs.getString("name"));
                user.setEmailId(rs.getString("emailID"));
                user.setPhoneNumber(rs.getString("phoneNumber"));
                user.setDob(rs.getString("dob"));
                user.setPassword(rs.getString("password"));
                user.setAddress(rs.getString("address"));
                user.setStatus(rs.getInt("status"));
                user.setValidUser(valid);
            } else {
                valid = false;
                user.setValidUser(valid);

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return user;

    }

    public User checkForgetPassword(String emailId) throws SQLException, Exception {
        ResultSet rs = null;
        User user = new User();
        Connection con = null;
        boolean valid = true;
        try {
            con = ConnectionManager.getConnection();
            String sql = "SELECT * FROM user WHERE emailId=?";
            System.out.println("Got the connection.........................." + con);
            PreparedStatement ps = con.prepareStatement(sql);
            System.out.println("emailId = " + emailId);
            ps.setString(1, emailId);
            System.out.println("Select SQL = " + ps);

            rs = ps.executeQuery();
            if (rs.next()) {

                user.setEmailId(rs.getString("emailID"));
                user.setValidUser(valid);
            } else {
                valid = false;
                user.setValidUser(valid);

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return user;

    }

    public List reportStock() throws SQLException, Exception {
        ResultSet rs = null;
        Connection con = null;
        List<Stocks> stockList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM trading.stocks";
            con = ConnectionManager.getConnection();
            System.out.println("Connection is " + con);
            PreparedStatement ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Stocks stocks = new Stocks();
                stocks.setStockId(rs.getInt("stockId"));
                stocks.setStockName(rs.getString("stockName"));
                stocks.setPrice(rs.getDouble("price"));
                stocks.setAvailability(rs.getInt("availability"));
                stockList.add(stocks);
            }
            return stockList;
        } catch (SQLException e) {
            return null;
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    public String generateOTP() {
        String values = "0123456789";
        Random rndm_method = new Random();

        char[] otp = new char[4];

        for (int j = 0; j < 4; j++) {
            otp[j] = values.charAt(rndm_method.nextInt(values.length()));
        }

        String generatedOTP = new String(otp);
        return generatedOTP;
    }
 public String generatePass() {
        String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String specialCharacters = "!@#$";
        String numbers = "1234567890";
        String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
        Random random = new Random();
        char[] password = new char[16];

        password[0] = lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
        password[1] = capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
        password[2] = specialCharacters.charAt(random.nextInt(specialCharacters.length()));
        password[3] = numbers.charAt(random.nextInt(numbers.length()));

        for(int i = 4; i< 16 ; i++) {
           password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
        }
        String pass=String.valueOf(password);
        return pass;
    }
    public int addToCart(int userId, int stockId, int availability, int quantity, double totalPrice) throws SQLException {
        int i = 0;
        Connection con = null;

        try {
            con = ConnectionManager.getConnection();
            String sql = "INSERT INTO trading.wishlist(userId,stockId,availability,quantity,totalPrice) VALUES (?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, stockId);
            ps.setInt(3, availability);
            ps.setInt(4, quantity);
            ps.setDouble(5, totalPrice);

            System.out.println("SQL for insert=" + ps);
            i = ps.executeUpdate();
            return i;
        } catch (Exception e) {
            e.printStackTrace();
            return i;
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    public int buyStock(int id, int stockId, int availability, int quanity, int userId, double totalPrice) throws SQLException, Exception {
        int i = 0;

        Connection con = ConnectionManager.getConnection();
        ResultSet rs = null;
        Stocks stock = new Stocks();
        availability = availability - quanity;
        try {
            con = ConnectionManager.getConnection();
            String sql = "UPDATE trading.stocks SET availability=? WHERE stockId=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, availability);
            ps.setInt(2, stockId);
            int j = ps.executeUpdate();
            System.out.println("SQL for insert=" + ps);
            if (j > 0) {
                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
                String orderDate = formatter.format(date);
                String sql1 = "INSERT INTO trading.stockordered(userId,stockId,quantityOrdered,orderDate,totalPrice) VALUES(?,?,?,?,?)";
                PreparedStatement ps1 = con.prepareStatement(sql1);
                ps1.setInt(1, userId);
                ps1.setInt(2, stockId);
                ps1.setInt(3, quanity);
                ps1.setString(4, orderDate);
                ps1.setDouble(5, totalPrice);
                int k = ps1.executeUpdate();
                if (k > 0) {
                    String sql2 = "DELETE FROM wishlist where id=?";
                    PreparedStatement ps2 = con.prepareStatement(sql2);
                    ps2.setInt(1, id);
                    i = ps2.executeUpdate();
                }
                return i;
            } else {
                return i;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return i;
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }
    public double showInvestment(int userId) throws SQLException
    {
        double i=0;
         ResultSet rs = null;
        Connection con = ConnectionManager.getConnection();
        try {
            String sql = "SELECT * FROM stockordered WHERE userId = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                double totalPrice=rs.getDouble("totalPrice");
                 i  = i + totalPrice;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            if (con != null) {
                con.close();
            }
        }
        
        return i;
    }
    public int sellStock(int userId, int orderId, int stockId, int quantityOrdered, int sellQuantity, double totalPrice) throws SQLException, Exception {
        int i = 0;

        Connection con = ConnectionManager.getConnection();
        ResultSet rs = null;
        StockOrdered stockordered = new StockOrdered();
        try {
            con = ConnectionManager.getConnection();
            if (sellQuantity == quantityOrdered) {
                String sql = "DELETE FROM stockordered WHERE orderId=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, orderId);
                int j = ps.executeUpdate();
                System.out.println("SQL for insert=" + ps);
                System.out.println(j);
                if (j > 0) {
                    String sql1 = "SELECT * FROM trading.stocks WHERE stockId=?";
                    PreparedStatement ps1 = con.prepareStatement(sql1);
                    ps1.setInt(1, stockId);
                    rs = ps1.executeQuery();
                    System.out.println("SQL for select=" + ps1);
                    if (rs.next()) {
                        String sql2 = "UPDATE trading.stocks SET availability=? WHERE stockId=?";
                        PreparedStatement ps2 = con.prepareStatement(sql2);
                        int avail = rs.getInt("availability");
                        int availability = avail + sellQuantity;
                        ps2.setInt(1, availability);
                        ps2.setInt(2, stockId);
                        System.out.println("SQL for update=" + ps2);
                        i = ps2.executeUpdate();

                    }

                }
                return i;
            } else {
                String sql = "UPDATE stockordered SET quantityOrdered=?, totalPrice=? WHERE orderId=?";
                PreparedStatement ps = con.prepareStatement(sql);
                int quanord = quantityOrdered - sellQuantity;
                System.out.println(quanord);
                double tprice = ((totalPrice)) - (((totalPrice) / (quantityOrdered)) * sellQuantity);
                ps.setInt(1, quanord);
                ps.setDouble(2, tprice);
                ps.setInt(3, orderId);
                int j = ps.executeUpdate();
                System.out.println("SQL for insert=" + ps);
                if (j > 0) {
                    String sql1 = "SELECT * FROM trading.stocks WHERE stockId=?";
                    PreparedStatement ps1 = con.prepareStatement(sql1);
                    ps1.setInt(1, stockId);
                    rs = ps1.executeQuery();
                    System.out.println("SQL for select=" + ps1);
                    if (rs.next()) {
                        String sql2 = "UPDATE trading.stocks SET availability=? WHERE stockId=?";
                        PreparedStatement ps2 = con.prepareStatement(sql2);
                        int avail = rs.getInt("availability");
                        int availability = avail + sellQuantity;
                        ps2.setInt(1, availability);
                        ps2.setInt(2, stockId);
                        System.out.println("SQL for update=" + ps2);
                        i = ps2.executeUpdate();

                    }

                }
                return i;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return i;
        } finally {
            if (con != null) {
                con.close();
            }
        }

    }

    public int deleteWishlist(int id, int userId) throws SQLException {
        int i = 0;
        Connection con = ConnectionManager.getConnection();
        try {
            String sql = "DELETE FROM wishlist WHERE id =? and userId = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, userId);
            i = ps.executeUpdate();
            return i;

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    public int counterCart(int userId) throws SQLException {
        int c = 0;
        ResultSet rs = null;
        Connection con = ConnectionManager.getConnection();
        try {
            String sql = "SELECT * FROM wishlist WHERE userId = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                c = c + 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return c;

    }

    public static void send(String from, String password, String to, String sub, String msg) {
        //Get properties object    
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        //get Session   
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });
        //compose message    
        try {
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(sub);
            message.setText(msg);
            //send message  
            Transport.send(message);
            System.out.println("message sent successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    public List wishList(int userId) throws SQLException {
        Connection con = ConnectionManager.getConnection();
        ResultSet rs = null;
        List<wishlist> wishList = new ArrayList<>();
        int i = 0;
        try {

            String sql = "SELECT * FROM wishlist where userId=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {

                String sql1 = "SELECT * FROM stocks where stockId=?";
                PreparedStatement ps1 = con.prepareStatement(sql1);
                int stockId = rs.getInt("stockId");
                ps1.setInt(1, stockId);
                ResultSet rs1 = ps1.executeQuery();
                if (rs1.next()) {
                    wishlist wishlist = new wishlist();
                    wishlist.setId(rs.getInt("id"));
                    wishlist.setUserId(rs.getInt("userId"));
                    wishlist.setStockId(rs.getInt("stockId"));
                    wishlist.setStockName(rs1.getString("stockName"));
                    wishlist.setQuantity(rs.getInt("quantity"));
                    wishlist.setAvailability(rs.getInt("availability"));
                    wishlist.setTotalPrice(rs.getDouble("totalPrice"));
                    wishList.add(wishlist);

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.close();
            }
        }
        System.out.println(wishList);
        return wishList;

    }

    public List orderList(int userId) throws SQLException {
        Connection con = ConnectionManager.getConnection();
        ResultSet rs = null;
        List<StockOrdered> orderList = new ArrayList<>();
        int i = 0;
        try {

            String sql = "SELECT * FROM stockordered where userId=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {

                String sql1 = "SELECT * FROM stocks where stockId=?";
                PreparedStatement ps1 = con.prepareStatement(sql1);
                int stockId = rs.getInt("stockId");
                ps1.setInt(1, stockId);
                ResultSet rs1 = ps1.executeQuery();
                if (rs1.next()) {
                    StockOrdered stockordered = new StockOrdered();
                    stockordered.setOrderId(rs.getInt("orderId"));
                    stockordered.setUserId(rs.getInt("userId"));
                    stockordered.setStockId(rs.getInt("stockId"));
                    stockordered.setStockName(rs1.getString("stockName"));
                    stockordered.setQuantityOrdered(rs.getInt("quantityOrdered"));
                    stockordered.setOrderDate(rs.getString("orderDate"));

                    stockordered.setTotalPrice(rs.getDouble("totalPrice"));
                    orderList.add(stockordered);

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.close();
            }
        }
        System.out.println(orderList);
        return orderList;

    }

    public List reportUser() throws SQLException, Exception {
        ResultSet rs = null;
        Connection con = null;
        List<User> userList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM trading.user";
            con = ConnectionManager.getConnection();
            System.out.println("Connection is " + con);
            PreparedStatement ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("userId"));
                user.setName(rs.getString("name"));
                user.setEmailId(rs.getString("emailId"));
                user.setPhoneNumber(rs.getString("phoneNumber"));
                user.setStatus(rs.getInt("status"));
                userList.add(user);
            }
            return userList;
        } catch (SQLException e) {
            return null;
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    public int promoteUserDetails(int userId, String name, String emailId, String phoneNumber, String dob, String password, String address, int status) throws SQLException, Exception {

        Connection con = ConnectionManager.getConnection();
        int i = 0;
        try {
            String sql = "UPDATE user SET status = 1 WHERE userId = ?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, userId);
            System.out.println("Select SQL = " + ps);
            i = ps.executeUpdate();
            return i;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    public static void main(String args[]) {
        final Logger logger = Logger.getLogger(Admin.class);
        logger.debug("This is debug" + "parameter/exception object");
        logger.info("This is info" + "parameter/exception object");
        logger.warn("This is warn" + "parameter/exception object");
        logger.error("This is error" + "parameter/exception object");
        logger.fatal("This is fatal" + "parameter/exception object");
    }

    public int countBuyer() throws SQLException {
        int i = 0;
        ResultSet rs = null;
        Connection con = ConnectionManager.getConnection();
        try {
            String sql = "SELECT DISTINCT userId FROM stockordered ";
            PreparedStatement ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                i = i + 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return i;

    }

    public int countPurchase() throws SQLException {
        int j = 0;
        ResultSet rs = null;
        Connection con = ConnectionManager.getConnection();
        try {
            String sql = "SELECT * FROM stockordered ";
            PreparedStatement ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                j = j + 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return j;

    }

    public double calRevenue() throws SQLException {
        double r = 0;
        ResultSet rs = null;
        Connection con = ConnectionManager.getConnection();
        try {
            String sql = "SELECT * FROM stockordered ";
            PreparedStatement ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                r = r + rs.getDouble("totalPrice");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return r;

    }

    public int registerUserSign(String name, String emailId, String pass)throws Exception {
        int i = 0;
        Connection con = null;
        try {
            con = ConnectionManager.getConnection();
            String sql = "INSERT INTO trading.user(name,emailId,phoneNumber,dob,password,address,status) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, name);
            ps.setString(2, emailId);
            ps.setString(3, "0");
            ps.setString(4, "0");
            ps.setString(5, pass);
            ps.setString(6, "0");
            ps.setInt(7, 0);
            System.out.println("SQL for insert=" + ps);
            i = ps.executeUpdate();
            return i;
        } catch (Exception e) {
            e.printStackTrace();
            return i;
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

     public static void sendpass(String from, String password, String to, String sub, String msg) {
        //Get properties object    
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        //get Session   
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });
        //compose message    
        try {
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(sub);
            message.setText(msg);
            //send message  
            Transport.send(message);
            System.out.println("Password sent successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}
