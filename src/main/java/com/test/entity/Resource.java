package com.test.entity;

/**
 * Created by Optical Illusion on 28.04.2015.
 */

import javax.persistence.*;

@Entity
@Table(name = "resource")
public class Resource {
    @Id
    @Column(name = "idresource")
    @GeneratedValue()
    private long id;

    @Column(name = "sku")
    private int sku;

    @Column(name = "description")
    private String description;

    @Column(name = "imgurl")
    private String imgurl;

    @Column(name = "width")
    private int width;

    @Column(name = "length")
    private int length;

    @Column(name = "costpermetr")
    private int costpermetr;

    @Column(name = "totalcost")
    private int totalcost;

    @Column(name = "cost")
    private int cost;

    @Column(name = "profit")
    private int profit;

    @Column(name = "status")
    private int status;

    @Column(name = "type")
    private int type;

    private String costumerLogin;

    public String getCostumerLogin() {
        return costumerLogin;
    }

    public void setCostumerLogin(String costumerLogin) {
        this.costumerLogin = costumerLogin;
    }

    public Resource(int sku, String description, String imgurl, int width, int length, int costpermetr, int totalcost, int cost, int profit, int status, int type, String costumerLogin) {
        this.sku = sku;
        this.description = description;
        this.imgurl = imgurl;
        this.width = width;
        this.length = length;
        this.costpermetr = costpermetr;
        this.totalcost = totalcost;
        this.cost = cost;
        this.profit = profit;
        this.status = status;
        this.type = type;
        this.costumerLogin= costumerLogin;

    }

    public Resource() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSku() {
        return sku;
    }

    public void setSku(int sku) {
        this.sku = sku;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getCostpermetr() {
        return costpermetr;
    }

    public void setCostpermetr(int costpermetr) {
        this.costpermetr = costpermetr;
    }

    public int getTotalcost() {return totalcost;}

    public void setTotalcost(int totalcost) {
        this.totalcost = totalcost;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
