
package com.trading.ot.dao;

import com.trading.ot.beans.User;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author BeingJay
 */
public class AdminTest {
    
    public AdminTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of updateUserPassword method, of class Admin.
     */
    @Test
    public void testUpdateUserPassword() throws Exception {
        System.out.println("updateUserPassword");
        String curpassword = "0000";
        String newpassword = "1111";
        String renewpassword = "1111";
        String emailId = "jdas@gmail.com";
        Admin instance = new Admin();
        int expResult = 0;
        int result = instance.updateUserPassword(curpassword, newpassword, renewpassword, emailId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of updateUserDetails method, of class Admin.
     */
    @Test
    public void testUpdateUserDetails() throws Exception {
        System.out.println("updateUserDetails");
        String name = "Jay";
        String dob = "20/4/2011";
        String address = "sili";
        String phoneNumber = "123456789";
        String emailId = "Jdas@gmail.com";
        Admin instance = new Admin();
        int expResult = 0;
        int result = instance.updateUserDetails(name, dob, address, phoneNumber, emailId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of updateStockDetails method, of class Admin.
     */
    @Test
    public void testUpdateStockDetails() throws Exception {
        System.out.println("updateStockDetails");
        int stockId = 0;
        String stockName = "vaodafone";
        double price = 130.20;
        int availability = 10;
        Admin instance = new Admin();
        int expResult = 0;
        int result = instance.updateStockDetails(stockId, stockName, price, availability);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of updateStockLists method, of class Admin.
     */
    @Test
    public void testUpdateStockLists() throws Exception {
        System.out.println("updateStockLists");
        String csvFilePath = "fgh.csv";
        Admin instance = new Admin();
        int expResult = 0;
        int result = instance.updateStockLists(csvFilePath);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteStock method, of class Admin.
     */
    @Test
    public void testDeleteStock() throws Exception {
        System.out.println("deleteStock");
        int stockId = 0;
        Admin instance = new Admin();
        int expResult = 0;
        int result = instance.deleteStock(stockId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of registerUser method, of class Admin.
     */
    @Test
    public void testRegisterUser() throws Exception {
        System.out.println("registerUser");
        String name = "Jay";
        String emailId = "jdas@gmail.com";
        String phoneNumber = "123456789";
        String dob = "20/4/1988";
        String password = "0000";
        String address = "sili";
        Admin instance = new Admin();
        int expResult = 0;
        int result = instance.registerUser(name, emailId, phoneNumber, dob, password, address);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of updateOtp method, of class Admin.
     */
    @Test
    public void testUpdateOtp() throws Exception {
        System.out.println("updateOtp");
        String otp = "0000";
        String emailId = "jdas@gmail.com";
        Admin instance = new Admin();
        int expResult = 0;
        int result = instance.updateOtp(otp, emailId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of updateOtpPassword method, of class Admin.
     */
    @Test
    public void testUpdateOtpPassword() throws Exception {
        System.out.println("updateOtpPassword");
        String newotp = "1111";
        String newpassword = "2222";
        String confirmpassword = "2222";
        String emailId = "jdas@gmail.com";
        Admin instance = new Admin();
        int expResult = 0;
        int result = instance.updateOtpPassword(newotp, newpassword, confirmpassword, emailId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of checkValidUser method, of class Admin.
     */
    @Test
    public void testCheckValidUser() throws Exception {
        System.out.println("checkValidUser");
        String emailId = "jdas@gmail.com";
        String password = "2222";
        Admin instance = new Admin();
        User expResult = null;
        User result = instance.checkValidUser(emailId, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of checkForgetPassword method, of class Admin.
     */
    @Test
    public void testCheckForgetPassword() throws Exception {
        System.out.println("checkForgetPassword");
        String emailId = "jdas@gmail.com";
        Admin instance = new Admin();
        User expResult = null;
        User result = instance.checkForgetPassword(emailId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of reportStock method, of class Admin.
     */
    @Test
    public void testReportStock() throws Exception {
        System.out.println("reportStock");
        Admin instance = new Admin();
        List expResult = null;
        List result = instance.reportStock();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of generateOTP method, of class Admin.
     */
    @Test
    public void testGenerateOTP() {
        System.out.println("generateOTP");
        Admin instance = new Admin();
        String expResult = "0012";
        String result = instance.generateOTP();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of addToCart method, of class Admin.
     */
    @Test
    public void testAddToCart() throws Exception {
        System.out.println("addToCart");
        int userId = 0;
        int stockId = 2;
        int availability = 100;
        int quantity = 10;
        double totalPrice = 120.30;
        Admin instance = new Admin();
        int expResult = 0;
        int result = instance.addToCart(userId, stockId, availability, quantity, totalPrice);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of buyStock method, of class Admin.
     */
    @Test
    public void testBuyStock() throws Exception {
        System.out.println("buyStock");
        int id = 0;
        int stockId = 2;
        int availability = 30;
        int quanity = 40;
        int userId = 2;
        double totalPrice = 1330.0;
        Admin instance = new Admin();
        int expResult = 0;
        int result = instance.buyStock(id, stockId, availability, quanity, userId, totalPrice);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of sellStock method, of class Admin.
     */
    @Test
    public void testSellStock() throws Exception {
        System.out.println("sellStock");
        int userId = 0;
        int orderId = 30;
        int stockId = 40;
        int quantityOrdered = 20;
        int sellQuantity = 30;
        double totalPrice = 450.0;
        Admin instance = new Admin();
        int expResult = 0;
        int result = instance.sellStock(userId, orderId, stockId, quantityOrdered, sellQuantity, totalPrice);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteWishlist method, of class Admin.
     */
    @Test
    public void testDeleteWishlist() throws Exception {
        System.out.println("deleteWishlist");
        int id = 0;
        int userId = 0;
        Admin instance = new Admin();
        int expResult = 0;
        int result = instance.deleteWishlist(id, userId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of counterCart method, of class Admin.
     */
    @Test
    public void testCounterCart() throws Exception {
        System.out.println("counterCart");
        int userId = 0;
        Admin instance = new Admin();
        int expResult = 0;
        int result = instance.counterCart(userId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of send method, of class Admin.
     */
    @Test
    public void testSend() {
        System.out.println("send");
        String from = "1111";
        String password = "2222";
        String to = "3333";
        String sub = "sil";
        String msg = "hi";
        Admin.send(from, password, to, sub, msg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of wishList method, of class Admin.
     */
    @Test
    public void testWishList() throws Exception {
        System.out.println("wishList");
        int userId = 0;
        Admin instance = new Admin();
        List expResult = null;
        List result = instance.wishList(userId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of orderList method, of class Admin.
     */
    @Test
    public void testOrderList() throws Exception {
        System.out.println("orderList");
        int userId = 0;
        Admin instance = new Admin();
        List expResult = null;
        List result = instance.orderList(userId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of reportUser method, of class Admin.
     */
    @Test
    public void testReportUser() throws Exception {
        System.out.println("reportUser");
        Admin instance = new Admin();
        List expResult = null;
        List result = instance.reportUser();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of promoteUserDetails method, of class Admin.
     */
    @Test
    public void testPromoteUserDetails() throws Exception {
        System.out.println("promoteUserDetails");
        int userId = 0;
        String name = "Jay";
        String emailId = "jdas1@gmail.com";
        String phoneNumber = "7894561230";
        String dob = "20/4/1996";
        String password = "4444";
        String address = "aj";
        int status = 0;
        Admin instance = new Admin();
        int expResult = 0;
        int result = instance.promoteUserDetails(userId, name, emailId, phoneNumber, dob, password, address, status);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
    
}