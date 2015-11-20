package com.example.capstone.invoice;

/**
 * Created by Pierro on 11/17/2015.
 */
public class Invoice {
    private int InvoiceID;
    private int CustomerID;
    private String InvoiceDate;
    private String InvoiceNotes;

    public Invoice(){}

    public Invoice(int invoiceID, int customerID, int invoiceItemID, String invoiceDate, String invoiceNotes) {
        InvoiceID = invoiceID;
        CustomerID = customerID;
        InvoiceDate = invoiceDate;
        InvoiceNotes = invoiceNotes;
    }

    public int getInvoiceID() {
        return InvoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        InvoiceID = invoiceID;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(int customerID) {
        CustomerID = customerID;
    }



    public String getInvoiceDate() {
        return InvoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        InvoiceDate = invoiceDate;
    }

    public String getInvoiceNotes() {
        return InvoiceNotes;
    }

    public void setInvoiceNotes(String invoiceNotes) {
        InvoiceNotes = invoiceNotes;
    }
}
