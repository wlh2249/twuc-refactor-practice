package com.twu.refactoring;

public class LineItem {
	private final String description;
	private final double price;
	private final int quantity;

	public LineItem(String desc, double p, int quantity) {
		super();
		this.description = desc;
		this.price = p;
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public double getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

    double totalAmount() {
        return price * quantity;
    }
}