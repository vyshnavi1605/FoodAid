package com.FoodAid.Group35.model;

import java.util.Date;

public class FoodBean {

    private int foodID;
    private String foodName;
    private String companyName;
    private int numberOfItems;
    private Date itemAddedDate;
    private Date itemAcceptedDate;
    private String status; //(Active, Deleted)
    private String flag;//(Accepted, Created )

    public FoodBean() {
    }

    public FoodBean(int foodID, String foodName, String companyName, int numberOfItems,
                    Date itemAddedDate, Date itemAcceptedDate, String status, String flag) {
        this.foodID = foodID;
        this.foodName = foodName;
        this.companyName = companyName;
        this.numberOfItems = numberOfItems;
        this.itemAddedDate = itemAddedDate;
        this.itemAcceptedDate = itemAcceptedDate;
        this.status = status;
        this.flag = flag;
    }

    public int getFoodID() {
        return foodID;
    }

    public void setFoodID(int foodID) {
        this.foodID = foodID;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(int numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    public Date getItemAddedDate() {
        return itemAddedDate;
    }

    public void setItemAddedDate(Date itemAddedDate) {
        this.itemAddedDate = itemAddedDate;
    }

    public Date getItemAcceptedDate() {
        return itemAcceptedDate;
    }

    public void setItemAcceptedDate(Date itemAcceptedDate) {
        this.itemAcceptedDate = itemAcceptedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
