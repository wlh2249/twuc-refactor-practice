package com.twu.refactoring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class CustomerTest {


	private static final String GOLD_PATH = "data/";

    private final Customer dinsdale = new Customer("Dinsdale Pirhana");

    private final Movie python = new RegularMovie("Monty Python and the Holy Grail");
	private final Movie ran = new RegularMovie("Ran");
	private Movie la = new NewReleaseMovie("LA Confidential");
	private final Movie trek = new NewReleaseMovie("Star Trek 13.2");
	private final Movie wallace = new ChildrenMovie("Wallace and Gromit");

    @BeforeEach
    public void setUpData(){
       dinsdale.addRental(new Rental (python, 3));
       dinsdale.addRental(new Rental (ran, 1));
       dinsdale.addRental(new Rental (la, 2));
       dinsdale.addRental(new Rental (trek, 1));
       dinsdale.addRental(new Rental (wallace, 6));
   }

    @Test
    public void shouldOutputEmptyStatement() throws Exception {
        Customer customer = new Customer("Golden Shark");
        verifyOutput(customer.statement(), "outputEmpty");
    }

    @Test
    public void shouldOutputCustomerStatement() throws Exception {
        verifyOutput(dinsdale.statement(), "output1");
    }

    @Test
    public void shouldOutputChangedStatement() throws Exception {
        la = new RegularMovie("LA Confidential");
        dinsdale.updateRental(la);
        verifyOutput(dinsdale.statement(), "outputChange");
    }

    protected void verifyOutput(String actualValue, String fileName) throws IOException{
        String filePath = Objects.requireNonNull(getClass().getClassLoader().getResource(GOLD_PATH + fileName)).getPath();
        BufferedReader file = new BufferedReader (new FileReader (filePath));
        BufferedReader actualStream = new BufferedReader (new StringReader (actualValue));
        String thisFileLine;
        while  ((thisFileLine = file.readLine()) != null) {
            assertThat("in file: " + fileName, actualStream.readLine(), equalTo(thisFileLine));
        }
    }

}
