package com.example.capstone.invoice;

/**
 * Created by Pierro on 10/29/2015.
 */
public class Customer {
    private int CustomerID;
    private String CustomerFirstName;
    private String CustomerLastName;
    private String CustomerPhone;
    private String CustomerEmail;
    private String CustomerNotes;
    private String CustomerStreet;
    private String CustomerCity;
    private String CustomerState;
    private String CustomerZip;

    public Customer (){}

    // Constructor with id to build from database
    public Customer (int CustomerID, String Fname, String Lname, String Phone, String Email, String Notes,
                     String Street, String City, String State, String Zip){
        this.CustomerID= CustomerID;
        this.CustomerFirstName= Fname;
        this.CustomerLastName= Lname;
        this.CustomerPhone= Phone;
        this.CustomerEmail= Email;
        this.CustomerNotes= Notes;
        this.CustomerStreet= Street;
        this.CustomerCity= City;
        this.CustomerState= State;
        this.CustomerZip= Zip;
    }

    // Constructor with no id to put in database
    public Customer (String Fname, String Lname, String Phone, String Email, String Notes,
                     String Street, String City, String State, String Zip){

        this.CustomerFirstName= Fname;
        this.CustomerLastName= Lname;
        this.CustomerPhone= Phone;
        this.CustomerEmail= Email;
        this.CustomerNotes= Notes;
        this.CustomerStreet= Street;
        this.CustomerCity= City;
        this.CustomerState= State;
        this.CustomerZip= Zip;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(int customerID) {
        CustomerID = customerID;
    }

    public String getCustomerFirstName() {
        return CustomerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        CustomerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return CustomerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        CustomerLastName = customerLastName;
    }

    public String getCustomerPhone() {
        return CustomerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        CustomerPhone = customerPhone;
    }

    public String getCustomerEmail() {
        return CustomerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        CustomerEmail = customerEmail;
    }

    public String getCustomerNotes() {
        return CustomerNotes;
    }

    public void setCustomerNotes(String customerNotes) {
        CustomerNotes = customerNotes;
    }

    public String getCustomerStreet() {
        return CustomerStreet;
    }

    public void setCustomerStreet(String customerStreet) {
        CustomerStreet = customerStreet;
    }

    public String getCustomerCity() {
        return CustomerCity;
    }

    public void setCustomerCity(String customerCity) {
        CustomerCity = customerCity;
    }

    public String getCustomerState() {
        return CustomerState;
    }

    public void setCustomerState(String customerState) {
        CustomerState = customerState;
    }

    public String getCustomerZip() {
        return CustomerZip;
    }

    public void setCustomerZip(String customerZip) {
        CustomerZip = customerZip;
    }
}
