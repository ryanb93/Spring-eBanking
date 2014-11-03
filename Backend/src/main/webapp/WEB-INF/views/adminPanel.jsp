<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="java.util.List"%>
<%@page import="core.domain.Customer"%>
<%@page import="core.domain.Account"%>
<%@page import="core.domain.Transaction"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/app.css">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/fontawesome.min.css">

        <title>e-Banking Admin Panel</title>
    </head>
    <body>
        <nav role="navigation" class="navbar topnavbar">
            <div class="navbar-header">
                <div class="navbar-brand">
                    <div class="brand-logo">
                        e-Banking Admin Panel
                    </div>
                </div>
            </div>
            <div class="nav-wrapper">
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a href="http://localhost:8000/index.html" class="nav-link">Accounts</a>
                    </li>
                </ul>
            </div>
        </nav>


        <div class="container panel-footer">
            <div class="panel panel-primary">
                <div class="panel panel-heading">
                    <h1>Customers</h1>
                </div>
                <table class="panel-footer btn-block centertextalign">
                    <tr>
                        <td id="twentypercent">Customer Id</td>
                        <td id="tenpercent">First Name</td>
                        <td id="tenpercent">Last Name</td>
                        <td id="twentypercent">Date Of Birth</td>
                        <td id="fortypercent">Postal Address</td>
                    </tr>
                    <% List<Customer> allCustomers = (List<Customer>) request.getAttribute( "customers" ); %>
                    <% for (Customer customer : allCustomers){
                           pageContext.setAttribute("customerId",customer.getCustomerId());
                           pageContext.setAttribute("firstName", customer.getFirstName());
                           pageContext.setAttribute("lastName", customer.getLastName());
                           pageContext.setAttribute("dateOfBirth", customer.getDateOfBirth());
                           pageContext.setAttribute("address", customer.getPostalAddress());
                    %>
                    <tr>
                        <td>${customerId}</td>
                        <td>${firstName}</td>
                        <td>${lastName}</td>
                        <td>${dateOfBirth}</td>
                        <td>${address}</td>
                    </tr>
                    <% } %>
                </table>
            </div>


            <div class="panel-footer centertextalign rightpanelapp">
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
                        <input type="submit" value="Remove Customer" />
                    </form>
            </div>


                <div class="panel-footer centertextalign leftpanelapp">
                    <h2>Add New Customer</h2>
                    <form name="input" action="/adminPanel/addCustomer" method="post" >
                        <label>First Name</label>
                        <input type="text" name="firstName"/>
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
                        <input type="submit" value="Add Customer" />
                    </form>
                </div>


            <hr>
            <div class="panel panel-primary">
                <div class="panel panel-heading">
                    <h1>Accounts</h1>
                </div>
                <table class="panel-footer btn-block centertextalign">
                    <tr>
                        <td id="twentypercent">Account Id</td>
                        <td id="twentypercent">Customer Id</td>
                        <td id="twentypercent">Account Number</td>
                        <td id="twentypercent">Sort Code</td>
                        <td id="twentypercent">Account Type</td>
                        <td id="twentypercent">Balance</td>
                    </tr>
                    <% List<Account> allAccounts = (List<Account>) request.getAttribute( "accounts" ); %>
                    <% for (Account account : allAccounts){
                           pageContext.setAttribute("accountId",account.getAccountId());
                           pageContext.setAttribute("customerId", account.getCustomerId());
                           pageContext.setAttribute("accountNumber", account.getAccountNumber());
                           pageContext.setAttribute("sortCode", account.getSortCode());
                           pageContext.setAttribute("accountType", account.getAccountType());
                           pageContext.setAttribute("balance", account.getBalance());
                    %>
                    <tr>
                        <td>${accountId}</td>
                        <td>${customerId}</td>
                        <td>${accountNumber}</td>
                        <td>${sortCode}</td>
                        <td>${accountType}</td>
                        <td>${balance}</td>
                    </tr>
                    <% } %>
                </table>
            </div>

                
                <div class="panel-footer centertextalign rightpanelapp">
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
                        <input type="submit" value="Remove Account" />
                    </form>
                </div> 


                <div class="panel-footer centertextalign leftpanelapp">
                    <h2>Add Customer Account</h2>
                    <form name="AddAccount" action="/adminPanel/addAccount" method="post">
                        <label>Customer:</label>
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
                        <label>Account Type:</label>
                        <select name="selectedAccountType">
                            <option value="current" name="selectedAccountType">Current</option>
                            <option value="savings" name="selectedAccountType">Savings</option>
                            <option value = "isa" name="selectedAccountType">ISA</option>
                        </select>
                        <br />
                        <label>Sort Code</label>
                        <input type="text" name="sortCode" />
                        <br />
                        <label>Account Number:</label>
                        <select name="accountNumber" />
                        <% List<Account> accounts = (List<Account>) request.getAttribute("accounts"); %>
                        <% for (Account account : accounts) {
                            pageContext.setAttribute("accountId", account.getAccountId()); %>
                        <option value="${accountId}" name="accountId">${accountId}</option>
                        <% } %>
                        </select>
                        <br />
                        <input type="submit" value="Add Account" />
                    </form>
                    <br />
                </div>  
            <hr>  


            <div class="panel panel-primary">
                <div class="panel panel-heading">
                    <h1>Transactions</h1>
                </div>
                <table  class="panel-footer btn-block centertextalign">
                    <tr>
                        <td id="twentypercent">Transaction Id</td>
                        <td id="twentypercent">Account Id</td>
                        <td id="tenpercent">Sender</td>
                        <td id="tenpercent">Recipient</td>
                        <td id="tenpercent">Value</td>
                        <td id="tenpercent">Date</td>
                        <td id="tenpercent">Transaction Type </td>
                    </tr>
                    <% List<Transaction> allTransactions = (List<Transaction>) request.getAttribute( "transactions" ); %>
                    <% for (Transaction transaction : allTransactions){
                           pageContext.setAttribute("transactionId",transaction.getTransactionId());
                           pageContext.setAttribute("accId", transaction.getAccountId());
                           pageContext.setAttribute("sender", transaction.getSender());
                           pageContext.setAttribute("recipient", transaction.getRecipient());
                           pageContext.setAttribute("value", transaction.getValue());
                           pageContext.setAttribute("date", transaction.getDate());
                           pageContext.setAttribute("type", transaction.getTransactionType());%>
                    <tr>
                        <td>${transactionId}</td>
                        <td>${accId}</td>
                        <td>${sender}</td>
                        <td>${recipient}</td>
                        <td>${value}</td>
                        <td>${date}</td>
                        <td>${type}</td>
                    </tr>
                    <% } %>
                </table>
            </div>


                <div class="panel-footer centertextalign rightpanelapp">
                    <h2>Remove Transaction</h2>
                    <form name="RemoveTransaction" action="/adminPanel/removeTransaction" method="post">
                        <select name="selectedTransactionId">
                            <% List<Transaction> removeTransactions = (List<Transaction>) request.getAttribute( "transactions" ); %>
                            <% for (Transaction transaction : removeTransactions){
                                   pageContext.setAttribute("transactionId",transaction.getTransactionId());
                                    pageContext.setAttribute("accId", transaction.getAccountId());%>
                            <option value="${transactionId}">Transaction Id: ${transactionId}, Account Id ${accId}</option>
                            <% } %>
                        </select>
                        <input type="submit" value="Remove Transaction" />
                    </form>
                </div> 


                <div class="panel-footer centertextalign leftpanelapp">
                    <h2>Add Transaction</h2>
                    <form name="addTransaction" action="/adminPanel/addTransaction" method="post">
                        <label>Sender</label>
                        <input type="text" name="sender" />
                        <br />
                        <label>Recipient</label>
                        <input type="text" name="recipient"/>
                        <br />
                        <label>Date</label>
                        <input type="text" name="date" />
                        <br />
                        <label>Value</label>
                        <input type="text" name="value"/>
                        <br />
                        <label>Account Id</label>
                        <input type="text" name="accId"/>
                        <br />
                        <label>Transaction Type</label>
                        <select name="selectedTransactionType">
                            <option value="cash" name="selectedTransactionType">Cash</option>
                            <option value="debitCard" name="selectedTransactionType">Debit Card</option>
                            <option value = "creditCard" name="selectedTransactionType">Credit Card</option>
                            <option value = "bacs" name="selectedTransactionType">BACS</option>
                            <option value = "directDebit" name="selectedTransactionType">Direct Debit</option>
                            <option value = "standingOrder" name="selectedTransactionType">Standing Order</option>
                            <option value = "paypal" name="selectedTransactionType">PayPal</option>
                            <option value = "other" name="selectedTransactionType">Other</option>
                        </select>
                        <br />
                        <input type="submit" value="Add Transaction" />
                    </form>
                </div>
            </div>
    </body>
</html>