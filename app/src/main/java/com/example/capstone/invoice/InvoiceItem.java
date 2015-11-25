package com.example.capstone.invoice;

/**
 * Created by Pierro on 11/21/2015.
 */
public class InvoiceItem {

    private int InvoiceId;
    private int InvoiceItemId;
    private String InvoiceItemName;
    private int InvoiceItemRate;
    private int InvoiceItemFQuantity;
    private int InvoiceItemBQuantity;
    private int InvoiceItemLQuantity;
    private int InvoiceItemRQuantity;

    public InvoiceItem(){}

    public InvoiceItem(int invoiceId, String invoiceItemName, int invoiceItemRate, int invoiceItemFQuantity, int invoiceItemBQuantity, int invoiceItemLQuantity, int invoiceItemRQuantity) {
        InvoiceId = invoiceId;
        //InvoiceItemId = invoiceItemId;
        InvoiceItemName = invoiceItemName;
        InvoiceItemRate = invoiceItemRate;
        InvoiceItemFQuantity = invoiceItemFQuantity;
        InvoiceItemBQuantity = invoiceItemBQuantity;
        InvoiceItemLQuantity = invoiceItemLQuantity;
        InvoiceItemRQuantity = invoiceItemRQuantity;
    }

    public int getInvoiceId() {
        return InvoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        InvoiceId = invoiceId;
    }

    public int getInvoiceItemId() {
        return InvoiceItemId;
    }

    public void setInvoiceItemId(int invoiceItemId) {
        InvoiceItemId = invoiceItemId;
    }

    public String getInvoiceItemName() {
        return InvoiceItemName;
    }

    public void setInvoiceItemName(String invoiceItemName) {
        InvoiceItemName = invoiceItemName;
    }

    public int getInvoiceItemRate() {
        return InvoiceItemRate;
    }

    public void setInvoiceItemRate(int invoiceItemRate) {
        InvoiceItemRate = invoiceItemRate;
    }

    public int getInvoiceItemFQuantity() {
        return InvoiceItemFQuantity;
    }

    public void setInvoiceItemFQuantity(int invoiceItemFQuantity) {
        InvoiceItemFQuantity = invoiceItemFQuantity;
    }

    public int getInvoiceItemBQuantity() {
        return InvoiceItemBQuantity;
    }

    public void setInvoiceItemBQuantity(int invoiceItemBQuantity) {
        InvoiceItemBQuantity = invoiceItemBQuantity;
    }

    public int getInvoiceItemLQuantity() {
        return InvoiceItemLQuantity;
    }

    public void setInvoiceItemLQuantity(int invoiceItemLQuantity) {
        InvoiceItemLQuantity = invoiceItemLQuantity;
    }

    public int getInvoiceItemRQuantity() {
        return InvoiceItemRQuantity;
    }

    public void setInvoiceItemRQuantity(int invoiceItemRQuantity) {
        InvoiceItemRQuantity = invoiceItemRQuantity;
    }
}
