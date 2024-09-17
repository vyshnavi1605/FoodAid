package com.FoodAid.Group35.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Items implements Serializable {

    //private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto generated
    @Column(name = "itemID")
    private int itemID;

    @Column(name = "item_name")
    private String item_name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "companyID")
    private Companies companies;

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public Companies getCompanies() {
        return companies;
    }

    public void setCompanies(Companies companies) {
        this.companies = companies;
    }
}
