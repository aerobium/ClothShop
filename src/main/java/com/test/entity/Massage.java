package com.test.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by RAYANT on 22.05.2015.
 */


@Entity
@Table(name = "message")
public class Massage implements Comparable {

    @Id
    @GeneratedValue()
    private long id;


    @Column(name = "author_id")
    private long authorId;

    private String text;

    private Date date;

    @Column(name = "recipient_id")
    private int recipientId;

    private int visibility;

    private int place;

    private String author;

    private String recipient;

    public Massage() {
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public Massage(long authorId, String text, Date date, int recipientId, int visibility, int place, String author, String recipient) {

        this.authorId = authorId;
        this.text = text;
        this.date = date;
        this.recipientId = recipientId;
        this.visibility = visibility;
        this.place = place;
        this.author = author;
        this.recipient = recipient;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(int recipientId) {
        this.recipientId = recipientId;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    @Override
    public int compareTo(Object o) {
        Massage comp = (Massage) o;
        return (int) (this.date.getTime() -comp.date.getTime());

    }
}
