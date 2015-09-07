package com.test.entity;

/**
 * Created by RAYANT on 26.04.2015.
 */

import com.test.service.Coder;

import javax.persistence.*;
import java.io.UnsupportedEncodingException;

@Entity
@Table (name = "users")
public class User {



    @Id
    @Column(name = "id")
    @GeneratedValue()
    private long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "isAdmin")
    private String isAdmin;

    @Column(name = "email")
    private String email;


    private String firstName;

    private String lastName;

    private String adress;

    private String transport;

    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    private long balance;



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(String login, String password, String isAdmin) {
        this.login = login;
        this.password = password;
        this.isAdmin = isAdmin;
    }


    public User() {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstname) {
        this.firstName = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastname) {
        this.lastName = lastname;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public String getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void unCode() throws UnsupportedEncodingException {

        if (firstName!=null && !firstName.isEmpty())  this.setFirstName(Coder.unCode(this.firstName));
        if (lastName!=null && !lastName.isEmpty())  this.setLastName(Coder.unCode(this.lastName));
        if (adress!=null && !adress.isEmpty()) this.setAdress(Coder.unCode(this.adress));
        if (transport!=null && !transport.isEmpty())this.setTransport(Coder.unCode(this.transport));

    }

    public void code() throws UnsupportedEncodingException {

        if (firstName!=null && !firstName.isEmpty()) this.setFirstName(Coder.code(firstName));
        if (lastName!=null && !lastName.isEmpty()) this.setLastName(Coder.code(lastName));
        if (adress!=null && !adress.isEmpty()) this.setAdress(Coder.code(adress));
        if (transport!=null && !transport.isEmpty()) this.setTransport(Coder.code(transport));

    }


}