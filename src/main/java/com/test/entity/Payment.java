package com.test.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by RAYANT on 06.07.2015.
 */

@Entity
@Table(name = "payment")
public class Payment implements Comparable {

    @Id
    @GeneratedValue()
    private long id;
    private String login;
    private String comment;
    private Date date;
    private long transaction;

    @Override
    public int compareTo(Object o) {
        Payment comp = (Payment) o;
        return (int) (-this.date.getTime() +comp.date.getTime());
    }


    public long getTransaction() {
        return transaction;
    }

    public void setTransaction(int transaction) {
        this.transaction = transaction;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Payment(String login, String comment, Date date, long transaction) {
        this.login = login;
        this.comment = comment;
        this.date = date;
        this.transaction = transaction;
    }

    public Payment() {
    }
}
