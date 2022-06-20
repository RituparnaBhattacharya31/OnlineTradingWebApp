/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trading.ot.actions;

import com.opensymphony.xwork2.ActionSupport;
import com.trading.ot.beans.StockOrdered;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.trading.ot.beans.Stocks;
import com.trading.ot.beans.wishlist;
import com.trading.ot.dao.Admin;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author adity
 */
public class StockAction extends ActionSupport implements SessionAware {

    /**
     * @return the sellQuantity
     */
    public int getSellQuantity() {
        return sellQuantity;
    }

    /**
     * @param sellQuantity the sellQuantity to set
     */
    public void setSellQuantity(int sellQuantity) {
        this.sellQuantity = sellQuantity;
    }

    /**
     * @return the orderId
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * @param orderId the orderId to set
     */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    /**
     * @return the quantityOrdered
     */
    public int getQuantityOrdered() {
        return quantityOrdered;
    }

    /**
     * @param quantityOrdered the quantityOrdered to set
     */
    public void setQuantityOrdered(int quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }

    /**
     * @return the orderList
     */
    public List<StockOrdered> getOrderList() {
        return orderList;
    }

    /**
     * @param orderList the orderList to set
     */
    public void setOrderList(List<StockOrdered> orderList) {
        this.orderList = orderList;
    }

    /**
     * @return the wishList
     */
    private static long serialVersionUID = 4821216272008282533L;
    private SessionMap<String, Object> sessionMap;

    @Override
    public void setSession(Map<String, Object> map) {
        setSessionMap((SessionMap<String, Object>) (SessionMap) map);
    }

    private ResultSet rs = null;
    private Stocks product = null;
    private List<Stocks> stockList = null;
    private List<wishlist> wishList = null;
    private List<StockOrdered> orderList = null;
    private Admin admin = new Admin();
    private boolean noData = false;
    private String submitType;

    private int userId;
    private int id;

    private int stockId;
    private String stockName;
    private double price;
    private int availability;
    private int orderId;
    private int quantityOrdered;
    private int sellQuantity;

    private String msg = "";
    private int ctr = 0;
    private String csvFilePath;

    private int quantity;
    private double totalPrice;
    private int addtocart;

    public String StockReportAction() {
        HttpSession session = ServletActionContext.getRequest().getSession(false);
        if (session == null || session.getAttribute("login") == null) {
            return "LOGOUT1";
        } else {
            try {
                setStockList(new ArrayList<>());
                setStockList(getAdmin().reportStock());

                if (!stockList.isEmpty()) {
                    setNoData(false);
                    System.out.println("Stocks retrieve = " + getStockList().size());
                    System.out.println("setting nodata=false");
                } else {
                    setNoData(true);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "REPORTSTOCK";
        }

    }

    public String StockInfo() {
        Admin dao = new Admin();
        HttpSession session = ServletActionContext.getRequest().getSession(false);
        if (session == null || session.getAttribute("login") == null) {
            return "LOGOUT1";
        } else {
            try {
                int j = dao.countPurchase();
                int i = dao.countBuyer();
                double r = dao.calRevenue();
                session.setAttribute("orders", j);
                session.setAttribute("buyers", i);
                session.setAttribute("revenue", r);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "REPORTINFO";
    }

    public String StockUpdateAction() {

        HttpSession session = ServletActionContext.getRequest().getSession(false);
        if (session == null || session.getAttribute("login") == null) {
            return "LOGOUT1";
        } else {
            try {
                setStockList(new ArrayList<>());
                setStockList(getAdmin().reportStock());

                if (!stockList.isEmpty()) {
                    setNoData(false);
                    System.out.println("Stocks retrieve = " + getStockList().size());
                    System.out.println("setting nodata=false");
                } else {
                    setNoData(true);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "REPORTSTOCK";
        }

    }

    public String updateStock() {
        Admin dao = new Admin();
        HttpSession session = ServletActionContext.getRequest().getSession(false);
        if (session == null || session.getAttribute("login") == null) {
            return "LOGOUT1";
        } else {
            try {
                int i = dao.updateStockDetails(getStockId(), getStockName(), getPrice(), getAvailability());
                if (i > 0) {
                    setMsg("Stocks updated successfully");
                } else {
                    setMsg("error");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return "UPDATESTOCK";
        }

    }

    public String wishList() {
        Admin dao = new Admin();
        HttpSession session = ServletActionContext.getRequest().getSession(false);
        if (session == null || session.getAttribute("login") == null) {
            return "LOGOUT1";
        } else {
            try {
                setWishList(new ArrayList<>());
                setWishList(dao.wishList(getUserId()));
                if (!wishList.isEmpty()) {
                    setNoData(false);
                    System.out.println("Stocks retrieve = " + getWishList().size());
                    System.out.println("setting nodata=false");
                } else {
                    setNoData(true);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "WISHLIST";
        }
    }

    public String updateStockList() {
        Admin dao = new Admin();
        HttpSession session = ServletActionContext.getRequest().getSession(false);
        if (session == null || session.getAttribute("login") == null) {
            return "LOGOUT1";
        } else {
            try {
                int i = dao.updateStockLists(getCsvFilePath());
                if (i > 0) {
                    setMsg("Stocks updated successfully");
                } else {
                    setMsg("error");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return "UPDATESTOCKLIST";
        }

    }

    public String deleteWishlist() {
        Admin dao = new Admin();
        HttpSession session = ServletActionContext.getRequest().getSession(false);
        if (session == null || session.getAttribute("login") == null) {
            return "LOGOUT1";
        } else {
            try {
                int i = dao.deleteWishlist(getId(), getUserId());
                if (i > 0) {
                    int j = dao.counterCart(getUserId());
                    session.setAttribute("addtocart", j);
                    setMsg("Removed from wishlist successfully");
                } else {
                    setMsg("error");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return "REMOVEWISHLIST";
        }

    }

    public String deleteStock() {
        Admin dao = new Admin();

        HttpSession session = ServletActionContext.getRequest().getSession(false);
        if (session == null || session.getAttribute("login") == null) {
            return "LOGOUT1";
        } else {
            try {
                int isDeleted = dao.deleteStock(getStockId());
                if (isDeleted > 0) {
                    setMsg("Stocks updated successfully");
                } else {
                    setMsg("Some error");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "DELETESTOCK";
        }

    }

    public String StockAddToCartAction() {
        HttpSession session = ServletActionContext.getRequest().getSession(false);
        if (session == null || session.getAttribute("login") == null) {
            return "LOGOUT1";
        } else {
            try {
                setStockList(new ArrayList<>());
                setStockList(getAdmin().reportStock());

                if (!stockList.isEmpty()) {
                    setNoData(false);
                    System.out.println("Stocks retrieve = " + getStockList().size());
                    System.out.println("setting nodata=false");
                } else {
                    setNoData(true);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "REPORTSTOCK";
        }

    }

    public String addToCart() {
        HttpSession session = ServletActionContext.getRequest().getSession(false);

        if (session == null || session.getAttribute("login") == null) {
            return "LOGOUT1";
        } else {
            try {
                if (getAvailability() >= getQuantity() && getQuantity() > 0) {
                    Admin dao = new Admin();
                    double TotalPrice = getQuantity() * getPrice();

                    int k = dao.addToCart(getUserId(), getStockId(), getAvailability(), getQuantity(), TotalPrice);
                    if (k > 0) {

                        int j = dao.counterCart(getUserId());
                        session.setAttribute("addtocart", j);
                        setAddtocart(j);

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "ADDTOCART";

        }

    }

    public String buyStock() {
        Admin dao = new Admin();
        HttpSession session = ServletActionContext.getRequest().getSession(false);

        if (session == null || session.getAttribute("login") == null) {
            return "LOGOUT1";
        } else {
            try {

                int isBuy = dao.buyStock(getId(), getStockId(), getAvailability(), getQuantity(), getUserId(), getTotalPrice());
                if (isBuy > 0) {
                    int j = dao.counterCart(getUserId());
                    session.setAttribute("addtocart", j);
                    setMsg("purchsed successfully");
                } else {
                    setMsg("Some error");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return "BUYSTOCK";
    }

    public String sellStock() {
        Admin dao = new Admin();
        HttpSession session = ServletActionContext.getRequest().getSession(false);

        if (session == null || session.getAttribute("login") == null) {
            return "LOGOUT1";
        } else {
            if (getQuantityOrdered() >= getSellQuantity() && getSellQuantity() > 0) {
                try {
                    int isSell = dao.sellStock(getUserId(), getOrderId(), getStockId(), getQuantityOrdered(), getSellQuantity(), getTotalPrice());
                    if (isSell > 0) {
                        setMsg("sold successfully");
                    } else {
                        setMsg("Some error");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }

        return "SELLSTOCK";
    }

    public String StockOrderAction() {
        HttpSession session = ServletActionContext.getRequest().getSession(false);
        if (session == null || session.getAttribute("login") == null) {
            return "LOGOUT1";
        } else {
            try {
                setOrderList(new ArrayList<>());
                setOrderList(getAdmin().orderList(getUserId()));

                if (!orderList.isEmpty()) {
                    setNoData(false);
                    System.out.println("Stocks retrieve = " + getOrderList().size());
                    System.out.println("setting nodata=false");
                } else {
                    setNoData(true);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "REPORTORDERSTOCK";
        }

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
     * @return the product
     */
    public Stocks getProduct() {
        return product;
    }

    /**
     * @param product the product to set
     */
    public void setProduct(Stocks product) {
        this.product = product;
    }

    /**
     * @return the stockList
     */
    public List<Stocks> getStockList() {
        return stockList;
    }

    /**
     * @param stockList the stockList to set
     */
    public void setStockList(List<Stocks> stockList) {
        this.stockList = stockList;
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
     * @return the stockId
     */
    public int getStockId() {
        return stockId;
    }

    /**
     * @param stockId the stockId to set
     */
    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    /**
     * @return the stockName
     */
    public String getStockName() {
        return stockName;
    }

    /**
     * @param stockName the stockName to set
     */
    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the availability
     */
    public int getAvailability() {
        return availability;
    }

    /**
     * @param availability the availability to set
     */
    public void setAvailability(int availability) {
        this.availability = availability;
    }

    /**
     * @return the csvFilePath
     */
    public String getCsvFilePath() {
        return csvFilePath;
    }

    /**
     * @param csvFilePath the csvFilePath to set
     */
    public void setCsvFilePath(String csvFilePath) {
        this.csvFilePath = csvFilePath;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the addtocart
     */
    public int getAddtocart() {
        return addtocart;
    }

    /**
     * @param addtocart the addtocart to set
     */
    public void setAddtocart(int addtocart) {
        this.addtocart = addtocart;
    }

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
     * @return the totalPrice
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * @param totalPrice the totalPrice to set
     */
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
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
     * @return the wishList
     */
    public List<wishlist> getWishList() {
        return wishList;
    }

    /**
     * @param wishList the wishList to set
     */
    public void setWishList(List<wishlist> wishList) {
        this.wishList = wishList;
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
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the orderList
     */
}
