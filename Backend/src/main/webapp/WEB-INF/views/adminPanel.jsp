<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="java.util.List"%>
<%@page import="core.domain.Customer"%>
<%@page import="core.domain.Account"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>e-Banking Admin Panel</title>
  </head>
  <body>
    <h1>e-Banking Admin Panel</h1>
    <div>
        <h2>Add New Customer</h2>
        <form name="input" action="/adminPanel/addCustomer" method="post" >
                <label>First Name</label>
                <input type="text" name="firstName" />
                <br />
                <label>Last Name</label>
                <input type="text" name="lastName"/>
                <br />
                <label>Date of Birth</label>
                <input type="text" name="dateOfBirth" />
                <br />
                <label>House Number</label>
                <input type="text" name="houseNumber"/>
                <br />
                <label>Street</label>
                <input type="text" name="street" />
                <br />
                <label>City</label>
                <input type="text" name="city"/>
                <br />
                <label>County</label>
                <input type="text" name="county" />
                <br />
                <label>Country</label>
                <input type="text" name="country"/>
                <br />
                <label>Post Code</label>
                <input type="text" name="postCode" />
                <br />
                <input type="submit" value="addCustomer" />
        </form>
    </div>
    <div>
        <h2>Remove Customer</h2>
        <form name="removeCustomer" action="/adminPanel/removeCustomer" method="post" >
            <select name="selectedCustomerId">
        <% List<Customer> customers = (List<Customer>) request.getAttribute( "customers" ); %>
        <% for (Customer customer : customers) {
                pageContext.setAttribute("firstName", customer.getFirstName() );
                pageContext.setAttribute("lastName", customer.getLastName());
                pageContext.setAttribute("customerId", customer.getCustomerId());%>
                    <option value="${customerId}" name="selectedCustomerId">${firstName} ${lastName}</option>              
        <% } %> 
            </select>   
            <input type="submit" value="submit" />
        </form>
    </div>
    <br />
    <div>
        <h2>Add Customer Account</h2>
        <form name="AddAccount" action="/adminPanel/addAccount" method="post">
            Customer:
        <select name="selectedCustomerId">
        <% List<Customer> addAccCustomers = (List<Customer>) request.getAttribute( "customers" ); %>
        <% for (Customer customer : addAccCustomers) {
                pageContext.setAttribute("addAccFirstName", customer.getFirstName() );
                pageContext.setAttribute("addAccLastName", customer.getLastName());
                pageContext.setAttribute("customerId", customer.getCustomerId());%>
            <option value="${customerId}" name="selectedCustomerId">${addAccFirstName} ${addAccLastName}</option>
        <% } %> 
        </select>
            <br />
            <label>Account Type:    </label>
            <select name="selectedAccountType">
                <option value="current" name="selectedAccountType">Current</option>
                <option value="savings" name="selectedAccountType">Savings</option>
                <option value = "isa" name="selectedAccountType">ISA</option>
            </select>
            <br />
            <label>Sort Code</label>
            <input type="text" name="sortCode" />
            <br />
            <label>Account Number</label>
            <input type="text" name="accountNumber" />
            <br />
            <input type="submit" value="add Account" />
        </form>
        <br />
    </div>
    <div>
        <h2>Remove Account</h2>
        <form name="RemoveAccount" action="/adminPanel/removeAccount" method="post">
            <select name="selectedAccountId">
        <% List<Account> removeAccAccounts = (List<Account>) request.getAttribute( "accounts" ); %>
        <% for (Account account : removeAccAccounts){
               pageContext.setAttribute("accId",account.getAccountId());
                pageContext.setAttribute("accNo", account.getAccountNumber());%>
            <option value="${accId}">Account Id: ${accId}, Account Number: ${accNo}</option>
            <% } %>
       
            </select>
            <input type="submit" value="RemoveAccount" />
        </form>
    </div>    
    <br />
    <div>
        <button name="AddAccountTransaction">Add a new Account Transaction</button>
        <button name="RemoveAccountTransaction">Remove an Account Transaction</button>
    </div>
  </body>
</html>