package com.FoodAid.Group35.entity;
import javax.persistence.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;


//This class "Food.java" is an entity and is mapped with the corresponding "food" table in the database.
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "food")
public class Food implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto generated
    @Column(name = "foodID")	
    private int foodID;
    
    @Column(name = "food_name")
    private String foodName;
    
    @Column(name = "company_name")
    private String companyName;
    
    @Column(name = "quantity")
    private int numberOfItems;
    
    @Column(name = "date_added")
    private Date itemAddedDate;
    
    @Column(name = "date_accepted")
    private Date itemAcceptedDate;
    
    @Column(name = "status")
    private String status; //(Active, Deleted)
    
    @Column(name = "flag")
    private String flag;//(Accepted, Created )
    
    @Column(name = "accepted_user")
    private String acceptedUser;

    @Column(name="posted_user")
    private String postedUser;

    public Food(int foodID, String foodName, String companyName, int numberOfItems, Date itemAddedDate, Date itemAcceptedDate, String status, String flag, String acceptedUser, String postedUser) {
        this.foodID = foodID;
        this.foodName = foodName;
        this.companyName = companyName;
        this.numberOfItems = numberOfItems;
        this.itemAddedDate = itemAddedDate;
        this.itemAcceptedDate = itemAcceptedDate;
        this.status = status;
        this.flag = flag;
        this.acceptedUser = acceptedUser;
        this.postedUser = postedUser;
    }

    public Food() {

    }

    public String getPostedUser() {
        return postedUser;
    }

    public void setPostedUser(String postedUser) {
        this.postedUser = postedUser;
    }

    public String getAcceptedUser() {
        return acceptedUser;
    }

    public void setAcceptedUser(String acceptedUser) {
        this.acceptedUser = acceptedUser;
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
