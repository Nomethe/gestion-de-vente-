package com.example.gestionvende.classes;

import java.util.Date;

public class Order {

    private int orderId;
    private Date date;
    private String status;
    private Customer customer;
    private Product product;

    public Order(int orderId, Date date, String status, Customer customer, Product product) {
        this.orderId = orderId;
        this.date = date;
        this.status = status;
        this.customer = customer;
        this.product = product;
    }

    public int getOrderId() {
        return orderId;
    }

    public Date getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Product getProduct() {
        return product;
    }
}
