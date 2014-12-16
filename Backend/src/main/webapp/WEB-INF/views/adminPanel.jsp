<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="java.util.List"%>
<%@page import="core.domain.Customer"%>
<%@page import="core.domain.Account"%>
<%@page import="core.domain.Transaction"%>
<%@page import="web.domain.APIUser"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        <!-- Load Bootstrap -->
        <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="/css/bootstrap-theme.min.css">
        <link rel="stylesheet" type="text/css" href="/css/fontawesome.min.css">
        <!-- Load Bootstrap -->
        <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="/css/bootstrap-theme.min.css">
        <link rel="stylesheet" type="text/css" href="/css/fontawesome.min.css">
        <script src="/js/libs/jquery-2.1.1.min.js"></script>
        <script src="/js/libs/bootstrap.min.js"></script>
        <!-- Custom CSS -->
        <link rel="stylesheet" type="text/css" href="/css/app.css">
        <title>e-Banking Backend - Admin Panel</title>
    </head>
    <body>
        <nav role="navigation" class="navbar topnavbar">
            <div class="navbar-header">
                <a href="#/" class="navbar-brand">
                    <div class="brand-logo">
                        eBanking
                    </div>
                </a>
            </div>
        </nav>
        <div class="container" role="tabpanel">
            <!-- Nav tabs -->
            <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" class="active"><a href="#users" role="tab" data-toggle="tab">Users</a></li>
                <li role="presentation"><a href="#customers" role="tab" data-toggle="tab">Customers</a></li>
                <li role="presentation"><a href="#accounts" role="tab" data-toggle="tab">Accounts</a></li>
                <li role="presentation"><a href="#transactions" role="tab" data-toggle="tab">Transactions</a></li>
            </ul>
            <!-- Tab panes -->
            <div class="tab-content">
                <!-- User tab -->
                <div role="tabpanel" class="tab-pane active" id="users">
                    <h2>Current Users</h2>
                    <table class="panel-footer btn-block centertextalign">
                        <tr>
                            <td id="twentypercent">Email</td>
                            <td id="twentypercent">Password Hash</td>
                            <td id="twentypercent">API User ID</td>
                        </tr>
                        <% List<APIUser> allUsers = (List<APIUser>) request.getAttribute( "users" ); %>
                            <% for (APIUser user : allUsers){
                            pageContext.setAttribute("email", user.getEmailAddress());
                            pageContext.setAttribute("password", user.getPassword());
                            pageContext.setAttribute("apiUserId", user.getId());
                            %>
                            <tr>
                                <td>${email}</td>
                                <td>${password}</td>
                                <td>${apiUserId}</td>
                            </tr>
                            <% } %>
                    </table>
                    <h2>Add User</h2>
                    <form name="input" action="/adminPanel/addUser" method="POST" role="form">
                        <div class="form-group">
                            <label>Email Address</label>
                            <input type="text" name="email" class="form-control" />
                        </div>
                        <div class="form-group">
                            <label>Password</label>
                            <input type="text" name="password" class="form-control" />
                        </div>
                        <input class="btn btn-default" type="submit" value="Add User" />
                    </form>
                </div>
                    <!-- Customer tab -->
                    <div role="tabpanel" class="tab-pane" id="customers">
                        <div class="panel panel-primary">
                            <h2>Current Customers</h2>
                            <table class="panel-footer btn-block centertextalign">
                                <tr>
                                    <td id="twentypercent">Customer Id</td>
                                    <td id="tenpercent">First Name</td>
                                    <td id="tenpercent">Last Name</td>
                                    <td id="fortypercent">Postal Address</td>
                                    <td id="fortypercent">API User ID</td>
                                </tr>
                                <% List<Customer> allCustomers = (List<Customer>) request.getAttribute( "customers" ); %>
                                <% for (Customer customer : allCustomers){
                                pageContext.setAttribute("customerId",customer.getCustomerId());
                                pageContext.setAttribute("firstName", customer.getFirstName());
                                pageContext.setAttribute("lastName", customer.getLastName());
                                pageContext.setAttribute("address", customer.getAddress());
                                pageContext.setAttribute("apiUserID", customer.getApiUserId());
                                %>
                                <tr>
                                    <td>${customerId}</td>
                                    <td>${firstName}</td>
                                    <td>${lastName}</td>
                                    <td>${address}</td>
                                    <td>${apiUserID}</td>
                                </tr>
                                <% } %>
                            </table>
                            <h2>Remove Customer</h2>
                            <form name="removeCustomer" action="/adminPanel/removeCustomer" method="POST" role="form">
                                <div class="form-group">
                                    <select name="selectedCustomerId" class="form-control">
                                        <% List<Customer> customers = (List<Customer>) request.getAttribute( "customers" ); %>
                                        <% for (Customer customer : customers) {
                                        pageContext.setAttribute("firstName", customer.getFirstName() );
                                        pageContext.setAttribute("lastName", customer.getLastName());
                                        pageContext.setAttribute("customerId", customer.getCustomerId());%>
                                        <option value="${customerId}" name="selectedCustomerId">${firstName} ${lastName}</option>
                                        <% } %>
                                    </select>
                                </div>
                                <input type="submit" class="btn btn-default" value="Remove Customer" />
                            </form>
                            <h2>Add New Customer</h2>
                            <form name="input" action="/adminPanel/addCustomer" method="POST" role="form">
                                <div class="form-group">
                                    <label>First Name</label>
                                    <input type="text" name="firstName" class="form-control" />
                                </div>
                                <div class="form-group">
                                    <label>Last Name</label>
                                    <input type="text" name="lastName" class="form-control" />
                                </div>
                                <div class="form-group">
                                    <label>House Number</label>
                                    <input type="text" name="houseNumber" class="form-control" />
                                </div>
                                <div class="form-group">
                                    <label>Street</label>
                                    <input type="text" name="street" class="form-control" />
                                </div>
                                <div class="form-group">
                                    <label>City</label>
                                    <input type="text" name="city" class="form-control" />
                                </div>
                                <div class="form-group">
                                    <label>County</label>
                                    <input type="text" name="county" class="form-control" />
                                </div>
                                <div class="form-group">
                                    <label>Country</label>
                                    <input type="text" name="country" class="form-control" />
                                </div>
                                <div class="form-group">
                                    <label>Post Code</label>
                                    <input type="text" name="postCode" class="form-control" />
                                </div>
                                <div class="form-group">
                                    <label>API User ID</label>
                                    <select name="apiUserId" class="form-control">
                                        <% List<APIUser> users = (List<APIUser>) request.getAttribute( "users" ); %>
                                            <% for (APIUser user : users) {
                                            pageContext.setAttribute("apiUserId", user.getId());%>
                                            <option value="${apiUserId}" name="apiUserId">${apiUserId}</option>
                                            <% } %>
                                        </select>
                                    </div>
                                    <input class="btn btn-default" type="submit" value="Add Customer" />
                                </form>
                            </div>
                        </div>
                        <!-- Accounts tab -->
                        <div role="tabpanel" class="tab-pane" id="accounts">
                            <div class="panel panel-primary">
                                <h2>Current Accounts</h2>
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
                                    <h2>Remove Account</h2>
                                    <form name="RemoveAccount" action="/adminPanel/removeAccount" method="POST" role="form">
                                        <div class="form-group">
                                            <select name="selectedAccountId" class="form-control">
                                                <% List<Account> removeAccAccounts = (List<Account>) request.getAttribute( "accounts" ); %>
                                                    <% for (Account account : removeAccAccounts){
                                                    pageContext.setAttribute("accId",account.getAccountId());
                                                    pageContext.setAttribute("accNo", account.getAccountNumber());%>
                                                    <option value="${accId}">Account Number: ${accNo}</option>
                                                    <% } %>
                                                </select>
                                            </div>
                                            <input class="btn btn-default" type="submit" value="Remove Account" />
                                        </form>
                                    </div>
                                    <div class="panel-header">
                                        <h2>Add Customer Account</h2>
                                        <form name="AddAccount" action="/adminPanel/addAccount" method="POST" role="form">
                                            <div class="form-group">
                                                <label>Customer:</label>
                                                <select name="selectedCustomerId" class="form-control">
                                                    <% List<Customer> addAccCustomers = (List<Customer>) request.getAttribute( "customers" ); %>
                                                    <% for (Customer customer : addAccCustomers) {
                                                    pageContext.setAttribute("addAccFirstName", customer.getFirstName() );
                                                    pageContext.setAttribute("addAccLastName", customer.getLastName());
                                                    pageContext.setAttribute("customerId", customer.getCustomerId());%>
                                                    <option value="${customerId}" name="selectedCustomerId">${addAccFirstName} ${addAccLastName}</option>
                                                    <% } %>
                                                </select>
                                            </div>
                                            <div class="form-group">
                                                <label>Account Type:</label>
                                                <select name="selectedAccountType" class="form-control">
                                                    <option value="current" name="selectedAccountType">Current</option>
                                                    <option value="savings" name="selectedAccountType">Savings</option>
                                                    <option value = "isa" name="selectedAccountType">ISA</option>
                                                </select>
                                            </div>
                                            <div class="form-group">
                                                <label>Sort Code</label>
                                                <input type="text" name="sortCode" class="form-control"/>
                                            </div>
                                            <div class="form-group">
                                                <label>Account Number:</label>
                                                <input type="text" name="accountNumber" class="form-control"/>
                                            </div>
                                            <input class="btn btn-default" type="submit" value="Add Account" />
                                        </form>
                                    </div>
                                </div>
                                
                                <!-- Transactions tab -->
                                <div role="tabpanel" class="tab-pane" id="transactions">
                                    <div class="panel panel-primary">
                                        <h2>Transactions</h2>
                                        <table class="panel-footer btn-block centertextalign">
                                            <tr>
                                                <td id="twentypercent">Transaction Id</td>
                                                <td id="twentypercent">Account Id</td>
                                                <td id="tenpercent">Other Account</td>
                                                <td id="tenpercent">Other Sort</td>
                                                <td id="tenpercent">Send/Receive</td>
                                                <td id="tenpercent">Value</td>
                                                <td id="tenpercent">Date</td>
                                                <td id="tenpercent">Transaction Type </td>
                                            </tr>
                                            <% List<Transaction> allTransactions = (List<Transaction>) request.getAttribute( "transactions" ); %>
                                                <% for (Transaction transaction : allTransactions){
                                                pageContext.setAttribute("transactionId",transaction.getTransactionId());
                                                pageContext.setAttribute("accountNumber", transaction.getAccountNumber());
                                                pageContext.setAttribute("otherAccountNumber", transaction.getOtherAccountNumber());
                                                pageContext.setAttribute("otherSortCode", transaction.getOtherSortCode());
                                                if(transaction.getSending()) {
                                                    pageContext.setAttribute("sending", "Sending");
                                                } else if(!transaction.getSending()) {
                                                    pageContext.setAttribute("sending", "Receiving");
                                                }
                                                pageContext.setAttribute("value", transaction.getValue());
                                                pageContext.setAttribute("date", transaction.getDate());
                                                pageContext.setAttribute("type", transaction.getTransactionType());%>
                                                <tr>
                                                    <td>${transactionId}</td>
                                                    <td>${accountNumber}</td>
                                                    <td>${otherAccountNumber}</td>
                                                    <td>${otherSortCode}</td>
                                                    <td>${sending}</td>
                                                    <td>${value}</td>
                                                    <td>${date}</td>
                                                    <td>${type}</td>
                                                </tr>
                                                <% } %>
                                            </table>
                                            <div class="panel-footer">
                                                <h2>Remove Transaction</h2>
                                                <form name="RemoveTransaction" action="/adminPanel/removeTransaction" method="POST" role="form">
                                                    <div class="form-group">
                                                        <select name="selectedTransactionId" class="form-control">
                                                            <% List<Transaction> removeTransactions = (List<Transaction>) request.getAttribute( "transactions" ); %>
                                                                <% for (Transaction transaction : removeTransactions){
                                                                pageContext.setAttribute("transactionId",transaction.getTransactionId());
                                                                pageContext.setAttribute("accountNumber", transaction.getAccountNumber());%>
                                                                <option value="${transactionId}">Transaction Id: ${transactionId}, Account Number ${accountNumber}</option>
                                                                <% } %>
                                                            </select>
                                                        </div>
                                                        <input class="btn btn-default" type="submit" value="Remove Transaction" />
                                                    </form>
                                                </div>
                                                <div class="panel-footer">
                                                    <h2>Add Transaction</h2>
                                                    <form name="addTransaction" action="/adminPanel/addTransaction" method="POST" role="form">
                                                        <div class="form-group">
                                                            <label>Account Id:</label>
                                                            <select name="accountNumber" class="form-control">
                                                                <% List<Account> accounts = (List<Account>) request.getAttribute("accounts"); %>
                                                                    <% for (Account account : accounts) {
                                                                    pageContext.setAttribute("accountNumber", account.getAccountNumber()); %>
                                                                    <option value="${accountNumber}" name="accountNumber">${accountNumber}</option>
                                                                    <% } %>
                                                                </select>
                                                                <label>Other Sort Code:</label>
                                                                <input type="text" name="otherSortCode" class="form-control"/>
                                                                <label>Other Account Number:</label>
                                                                <input type="text" name="otherAccountNumber" class="form-control"/>
                                                                <label>Sending/Receiving</label>
                                                                <select name="sending" class="form-control">
                                                                    <option value="true" name="accountNumber">Send</option>
                                                                    <option value="false" name="accountNumber">Receive</option>
                                                                </select>
                                                                <label>Value</label>
                                                                <input type="text" name="value" class="form-control" class="form-control"/>
                                                            </div>
                                                            <div class="form-group">
                                                                <label>Transaction Type</label>
                                                                <select name="selectedTransactionType" class="form-control">
                                                                    <option value="cash" name="selectedTransactionType">Cash</option>
                                                                    <option value="debitCard" name="selectedTransactionType">Debit Card</option>
                                                                    <option value = "creditCard" name="selectedTransactionType">Credit Card</option>
                                                                    <option value = "bacs" name="selectedTransactionType">BACS</option>
                                                                    <option value = "directDebit" name="selectedTransactionType">Direct Debit</option>
                                                                    <option value = "standingOrder" name="selectedTransactionType">Standing Order</option>
                                                                    <option value = "paypal" name="selectedTransactionType">PayPal</option>
                                                                    <option value = "other" name="selectedTransactionType">Other</option>
                                                                </select>
                                                            </div>
                                                            <input class="btn btn-default" type="submit" value="Add Transaction" />
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </body>
                                </html>