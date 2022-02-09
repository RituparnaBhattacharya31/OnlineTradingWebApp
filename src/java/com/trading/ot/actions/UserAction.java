/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trading.ot.actions;

import com.opensymphony.xwork2.ActionSupport;
import com.trading.ot.beans.User;
import com.trading.ot.dao.Admin;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Adity
 */
public class UserAction extends ActionSupport implements SessionAware {

    /**
     * @return the confirmpassword
     */
    public String getConfirmpassword() {
        return confirmpassword;
    }

    /**
     * @param confirmpassword the confirmpassword to set
     */
    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }

    private static long serialVersionUID = 4821216272008282533L;
    private SessionMap<String, Object> sessionMap;

    @Override
    public void setSession(Map<String, Object> map) {
        setSessionMap((SessionMap<String, Object>) (SessionMap) map);
    }
    private int userId;
    private String name;
    private String emailId;
    private String phoneNumber;
    private String dob;
    private String password;
    private String address;
    private boolean validUser;
    private int status;

    private String otp;
    private String generatedOTP;
    private String receiverEmail;
    private String newotp;

    private String curpassword;
    private String newpassword;
    private String renewpassword;
    private String confirmpassword;

    private ResultSet rs = null;
    private User user = null;
    private List<User> userList = null;
    private boolean noData = false;

    private String msg = "";
    private Admin admin = null;
    private int ctr = 0;
    private String submitType;

    public String addUsers() {
        setAdmin(new Admin());

        try {
            setCtr(getAdmin().registerUser(getName(), getEmailId(), getPhoneNumber(), getDob(), getPassword(), getAddress()));
            if (getCtr() > 0) {

                setMsg("Registration Successfully");
            } else {

                setMsg("Some error");
                return "ERROR";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "REGISTER";

    }

    public String checkValidUser() {
        //URL url;
        //URLConnection connection;
        setAdmin(new Admin());
        try {
            setUser(getAdmin().checkValidUser(getEmailId(), getPassword()));
            int status = getUser().getStatus();
            int userId = getUser().getUserId();
            String name = getUser().getName();
            String address = getUser().getAddress();
            String phoneNumber = getUser().getPhoneNumber();
            String emailId = getUser().getEmailId();
            String dob = getUser().getDob();

            if (getUser().isValidUser() == true) {

                if (status == 0) {
                    setMsg("Login successful");
                    getSessionMap().put("login", "true");
                    getSessionMap().put("userId", userId);
                    getSessionMap().put("address", address);
                    getSessionMap().put("emailId", emailId);
                    getSessionMap().put("phoneNumber", phoneNumber);
                    getSessionMap().put("dob", dob);
                    getSessionMap().put("name", name);
                    Admin dao=new Admin();
                    double i = dao.showInvestment(userId);
                    getSessionMap().put("investment", i);
                    int j=dao.counterCart(userId);
                    getSessionMap().put("addtocart", j);
                    return "USER";
                }
                if (status == 1) {
                    setMsg("Login successful");
                    
                    getSessionMap().put("login", "true");
                    getSessionMap().put("name", name);
                    getSessionMap().put("address", address);
                    getSessionMap().put("emailId", emailId);
                    getSessionMap().put("phoneNumber", phoneNumber);
                    getSessionMap().put("dob", dob);
                    Admin dao=new Admin();
                    int j=dao.countPurchase();
                    int i=dao.countBuyer();
                    double r=dao.calRevenue();
                    getSessionMap().put("orders", j);
                    getSessionMap().put("buyers", i);
                    getSessionMap().put("revenue", r);
                    return "ADMINUSER";
                }

            } else {
                setMsg("Incorrect username or password!!");
                return "ERRORLOGIN";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String updateOtpPassword() {
        Admin dao = new Admin();
        try {
            int i = dao.updateOtpPassword(getNewotp(), getNewpassword(), getConfirmpassword(), getEmailId());
            if (i > 0) {
                setMsg("Password Updated Successfuly");
            } else {
                setMsg("error");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "UPDATEUSERPASSWORD";
    }

    public String forgetPassword() {
        String from = "ExaTrade1@gmail.com";
        String password = "12we12we";
        //URL url;
        //URLConnection connection;
        setAdmin(new Admin());
        try {
            setUser(getAdmin().checkForgetPassword(getEmailId()));

            String email;
            email = getUser().getEmailId();

            if (getUser().isValidUser() == true) {
                String otp = getAdmin().generateOTP();
                getAdmin().updateOtp(otp, email);
                getAdmin().send(from, password, email, "OTP", otp);
                return "CHANGEPASS";
            } else {
                setMsg("Incorrect username or password!!");
                return "ERRORLOGIN";
            }
        } catch (Exception e) {
        }
        return null;
    }

    public String updateUser() {
        Admin dao = new Admin();

        HttpSession session = ServletActionContext.getRequest().getSession(false);
        if (session == null || session.getAttribute("login") == null) {
            return "LOGOUT1";
        } else {
            try {
                getSessionMap().put("address", getAddress());
                getSessionMap().put("emailId", getEmailId());
                getSessionMap().put("phoneNumber", getPhoneNumber());
                getSessionMap().put("dob", getDob());
                getSessionMap().put("name", getName());
                int i = dao.updateUserDetails(getName(), getDob(), getAddress(), getPhoneNumber(), getEmailId());
                if (i > 0) {
                    setMsg("Record Updated Successfuly");
                } else {
                    setMsg("error");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return "UPDATEUSER";
        }

    }
    public String showInvestment()
    {
        Admin dao = new Admin();

        HttpSession session = ServletActionContext.getRequest().getSession(false);
        if (session == null || session.getAttribute("login") == null) {
            return "LOGOUT1";
        } else {
            try {
                double i = dao.showInvestment(getUserId());
                getSessionMap().put("investment",i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        return "TOTALINVESTMENT";
       }
    }
    public String updateUserPassword() {
        Admin dao = new Admin();

        HttpSession session = ServletActionContext.getRequest().getSession(false);
        if (session == null || session.getAttribute("login") == null) {
            return "LOGOUT1";
        } else {
            try {
                int i = dao.updateUserPassword(getCurpassword(), getNewpassword(), getRenewpassword(), getEmailId());
                if (i > 0) {
                    setMsg("Password Updated Successfuly");
                } else {
                    setMsg("error");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "UPDATEUSERPASSWORD";
        }

    }

    public String logout() {
        if (getSessionMap() != null) {
            getSessionMap().invalidate();
        }
        return "LOGOUT";
    }

    public String sessionout() {
        HttpSession session = ServletActionContext.getRequest().getSession(false);
        if (session == null || session.getAttribute("login") == null) {
            return "LOGOUT1";
        } else {
            return "PASS";
        }
    }

    public String reportUserAction() {
        setAdmin(new Admin());
        try {
            setUserList(new ArrayList<>());
            setUserList(getAdmin().reportUser());

            if (!userList.isEmpty()) {
                setNoData(false);
                System.out.println("User retrieve = " + getUserList().size());
                System.out.println("setting nodata=false");
            } else {
                setNoData(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "REPORTUSER";
    }

    public String promoteUser() {
        Admin dao = new Admin();
        try {
            int i = dao.promoteUserDetails(getUserId(), getName(), getEmailId(), getPhoneNumber(), getDob(), getPassword(), getAddress(), getStatus());
            if (i > 0) {
                setMsg("User promoted successfully");
            } else {
                setMsg("error");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "PROMOTEUSER";
    }

    /**
     * @return the userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the emailId
     */
    public String getEmailId() {
        return emailId;
    }

    /**
     * @param emailId the emailId to set
     */
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    /**
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return the dob
     */
    public String getDob() {
        return dob;
    }

    /**
     * @param dob the dob to set
     */
    public void setDob(String dob) {
        this.dob = dob;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the rs
     */
    public ResultSet getRs() {
        return rs;
    }

    /**
     * @param rs the rs to set
     */
    public void setRs(ResultSet rs) {
        this.rs = rs;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the userList
     */
    public List<User> getUserList() {
        return userList;
    }

    /**
     * @param userList the userList to set
     */
    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    /**
     * @return the noData
     */
    public boolean isNoData() {
        return noData;
    }

    /**
     * @param noData the noData to set
     */
    public void setNoData(boolean noData) {
        this.noData = noData;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return the admin
     */
    public Admin getAdmin() {
        return admin;
    }

    /**
     * @param admin the admin to set
     */
    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    /**
     * @return the ctr
     */
    public int getCtr() {
        return ctr;
    }

    /**
     * @param ctr the ctr to set
     */
    public void setCtr(int ctr) {
        this.ctr = ctr;
    }

    /**
     * @return the submitType
     */
    public String getSubmitType() {
        return submitType;
    }

    /**
     * @param submitType the submitType to set
     */
    public void setSubmitType(String submitType) {
        this.submitType = submitType;
    }

    /**
     * @return the validUser
     */
    public boolean isValidUser() {
        return validUser;
    }

    /**
     * @param validUser the validUser to set
     */
    public void setValidUser(boolean validUser) {
        this.validUser = validUser;
    }

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }

    /**
     * @return the sessionMap
     */
    public SessionMap<String, Object> getSessionMap() {
        return sessionMap;
    }

    /**
     * @param sessionMap the sessionMap to set
     */
    public void setSessionMap(SessionMap<String, Object> sessionMap) {
        this.sessionMap = sessionMap;
    }

    /**
     * @return the otp
     */
    public String getOtp() {
        return otp;
    }

    /**
     * @param otp the otp to set
     */
    public void setOtp(String otp) {
        this.otp = otp;
    }

    /**
     * @return the generatedOTP
     */
    public String getGeneratedOTP() {
        return generatedOTP;
    }

    /**
     * @param generatedOTP the generatedOTP to set
     */
    public void setGeneratedOTP(String generatedOTP) {
        this.generatedOTP = generatedOTP;
    }

    /**
     * @return the receiverEmail
     */
    public String getReceiverEmail() {
        return receiverEmail;
    }

    /**
     * @param receiverEmail the receiverEmail to set
     */
    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    /**
     * @return the curpassword
     */
    public String getCurpassword() {
        return curpassword;
    }

    /**
     * @param curpassword the curpassword to set
     */
    public void setCurpassword(String curpassword) {
        this.curpassword = curpassword;
    }

    /**
     * @return the newpassword
     */
    public String getNewpassword() {
        return newpassword;
    }

    /**
     * @param newpassword the newpassword to set
     */
    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }

    /**
     * @return the renewpassword
     */
    public String getRenewpassword() {
        return renewpassword;
    }

    /**
     * @param renewpassword the renewpassword to set
     */
    public void setRenewpassword(String renewpassword) {
        this.renewpassword = renewpassword;
    }

    /**
     * @return the newotp
     */
    public String getNewotp() {
        return newotp;
    }

    /**
     * @param newotp the newotp to set
     */
    public void setNewotp(String newotp) {
        this.newotp = newotp;
    }

    /**
     * @return the session
     */
    /**
     * @param session the session to set
     */
}
