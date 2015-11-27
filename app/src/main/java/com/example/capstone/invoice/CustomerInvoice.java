package com.example.capstone.invoice;

/**
 * Created by Pierro on 11/25/2015.
 */
public class CustomerInvoice {
    private int invoiceID;
    private String invoiceDate;
    private String LastName;
    private String FirstName;
    private int CustomerID;

    public CustomerInvoice(){};

    public CustomerInvoice(int invoiceID, String invoiceDate, String lastName, String firstName, int customerID) {
        this.invoiceID = invoiceID;
        this.invoiceDate = invoiceDate;
        LastName = lastName;
        FirstName = firstName;
        CustomerID = customerID;
    }

    public int getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(int customerID) {
        CustomerID = customerID;
    }
}
