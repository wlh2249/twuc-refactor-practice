package com.twu.refactoring;

import java.util.ArrayList;
import java.util.Iterator;

public class Customer {

	private final String name;
	private final ArrayList<Rental> rentalList = new ArrayList<>();

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Rental arg) {
		rentalList.add(arg);
	}

	public String getName() {
		return name;
	}

	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		Iterator<Rental> rentals = rentalList.iterator();
		StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");
		while (rentals.hasNext()) {
			double thisAmount = 0;
			Rental each = rentals.next();

			thisAmount = getLineAmount(thisAmount, each);

			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two day new release rental
			if ((each.getMovie().getPriceCode() == Movie.NEW_RELEASE)
					&& each.getDaysRented() > 1)
				frequentRenterPoints++;

			// show figures for this rental
			result.append("\t").append(each.getMovie().getTitle()).append("\t").append(thisAmount).append("\n");
			totalAmount += thisAmount;

		}
		// add footer lines
		result.append("Amount owed is ").append(totalAmount).append("\n");
		result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points");
		return result.toString();
	}

	private double getLineAmount(double thisAmount, Rental each) {
		switch (each.getMovie().getPriceCode()) {
		case Movie.REGULAR:
			thisAmount = 2 + calculateAmountHelper(thisAmount, each, 2);
			break;
		case Movie.NEW_RELEASE:
			thisAmount += each.getDaysRented() * 3;
			break;
		case Movie.CHILDREN:
			thisAmount = 1.5 + calculateAmountHelper(thisAmount, each, 3);
			break;
		}
		return thisAmount;
	}

	private double calculateAmountHelper(double thisAmount, Rental each, int daysRented) {
		if (each.getDaysRented() > daysRented)
			thisAmount += (each.getDaysRented() - daysRented) * 1.5;
		return thisAmount;
	}

}
