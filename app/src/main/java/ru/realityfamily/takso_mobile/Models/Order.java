package ru.realityfamily.takso_mobile.Models;

import androidx.annotation.NonNull;

public class Order {
    long id;
    double price;
    String addressFrom = "";
    String pointFrom = "";
    String addressTo = "";
    String pointTo = "";
    String comment = "";
    String createTime = "";

    String distanceTo = "";
    String timeTo = "";
    String orderDistance = "";

    Statuses status = Statuses.Created;


    private static Order Instance;

    public static Order getInstance() {
        if (Instance == null) {
            Instance = new Order();
        }
        return Instance;
    }

    private Order() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAddressFrom() {
        return addressFrom;
    }

    public void setAddressFrom(String addressFrom) {
        this.addressFrom = addressFrom;
    }

    public String getAddressTo() {
        return addressTo;
    }

    public void setAddressTo(String addressTo) {
        this.addressTo = addressTo;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDistanceTo() {
        return distanceTo;
    }

    public void setDistanceTo(String distanceTo) {
        this.distanceTo = distanceTo;
    }

    public String getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(String timeTo) {
        this.timeTo = timeTo;
    }

    public String getOrderDistance() {
        return orderDistance;
    }

    public void setOrderDistance(String orderDistance) {
        this.orderDistance = orderDistance;
    }

    public Statuses getStatus() {
        return status;
    }

    public void setStatus(Statuses status) {
        this.status = status;
    }

    public String getPointFrom() {
        return pointFrom;
    }

    public void setPointFrom(String pointFrom) {
        this.pointFrom = pointFrom;
    }

    public String getPointTo() {
        return pointTo;
    }

    public void setPointTo(String pointTo) {
        this.pointTo = pointTo;
    }

    public Order Reload() {
        Instance = new Order();
        return Instance;
    }

    public static Order setInstance(Order order) {
        Instance = order;
        return Instance;
    }


    @NonNull
    @Override
    public String toString() {
        String out = "";

        out += "Id: " + id + "\n";
        out += "Price: " + price + "\n";
        out += "Address from: " + addressFrom + "\n";
        out += "Address to: " + addressTo + "\n";

        return out;
    }

    public enum Statuses {
        Created, Waiting, Performing, Finished, Canceled_by_Client, Canceled_by_Driver
    }
}
