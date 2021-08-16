package com.twu.refactoring;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class Customer {

	private final String name;
	private final ArrayList<Rental> rentalList = new ArrayList<>();

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Rental arg) {
		rentalList.add(arg);
	}

	public void updateRental(Movie movie) {
		Optional<Rental> oldRental = rentalList.stream().filter(rental -> rental.getMovie().getTitle().equals(movie.getTitle())).findAny();
		oldRental.ifPresent(Rental -> Rental.setMovie(movie));
	}

	public String getName() {
		return name;
	}

	public String statement() {
		final double[] totalAmount = {0};
		final AtomicInteger frequentRenterPoints = new AtomicInteger();
		StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");
		rentalList.forEach(rental -> updateStatement(rental, totalAmount, frequentRenterPoints, result));
		return addFooterLines(totalAmount[0], frequentRenterPoints, result);
	}

	private String addFooterLines(double d, AtomicInteger frequentRenterPoints, StringBuilder result) {
		result.append("Amount owed is ").append(d).append("\n");
		result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points");
		return result.toString();
	}

	private void updateStatement(Rental rental, double[] totalAmount, AtomicInteger frequentRenterPoints, StringBuilder result) {
		double amount = rental.getMovie().getAmount(rental.getDaysRented());
		frequentRenterPoints.getAndIncrement();
		addBonusForATwoDayNewReleaseRental(rental.getMovie(), rental.getDaysRented(), frequentRenterPoints);
		totalAmount[0] += amount;
		result.append("\t")
				.append(rental.getMovie().getTitle())
				.append("\t")
				.append(amount)
				.append("\n");
	}

	private void addBonusForATwoDayNewReleaseRental(Movie movie, int rentDays, AtomicInteger frequentRenterPoints) {
		if (movie instanceof NewReleaseMovie && rentDays > 1) {
			frequentRenterPoints.getAndIncrement();
		}
	}

}
