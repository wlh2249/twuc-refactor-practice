package com.twu.refactoring;

import java.util.List;

public class Order {
    String name;
    String address;
    List<LineItem> lineItemList;

    public Order(String name, String address, List<LineItem> lineItemList) {
        this.name = name;
        this.address = address;
        this.lineItemList = lineItemList;
    }

    public String getCustomerName() {
        return name;
    }

    public String getCustomerAddress() {
        return address;
    }


    public StringBuilder getOrderList() {
        StringBuilder orderList = new StringBuilder();
        lineItemList.forEach(lineItem ->
                orderList.append(lineItem.getDescription())
                        .append('\t')
                        .append(lineItem.getPrice())
                        .append('\t')
                        .append(lineItem.getQuantity())
                        .append('\t')
                        .append(lineItem.totalAmount())
                        .append('\n'));
        return orderList;
    }

    public double getTotalAmount() {
        return lineItemList.stream().mapToDouble(LineItem::totalAmount).sum() + getTotalTax();
    }

    public double getTotalTax() {
        return lineItemList.stream().mapToDouble(LineItem::salesTax).sum();
    }
}
