<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>e-Banking Admin Panel</title>
  </head>
  <body>
    <h1>e-Banking Admin Panel</h1>
    <a href="${pageContext.servletContext.contextPath}/admin">Click me</a>
    <div>
        <h2>Add New Customer</h2>
        <form name="input" action="adminPanel/addCustomer" method="post" >
                First name: <input type="text" name="firstname" />
                <br />
                Last name: <input type="text" name="lastname" />
                <br />
                <input type="submit" value="Submit" />
        </form>
    </div>
    <div>
        <h2>Remove Customer</h2>
        <select>
            <option>Customer 1</option>
            <option>Customer 2</option>
        </select>
        <button name="RemoveCustomer">Remove a Customer</button>
    </div>
    <div>
        <h2>Remove All Customers</h2>
        <button name="RemoveAllCustomers">Remove all Customers</button>
    </div>
    <br />
    <div>
        <h2>Add Customer Account</h2>
        <form name="AddAccount" action="action" method="post">
            Customer:
            <select>
                <option>Customer 1</option>
                <option>Customer 2</option>
            </select>
            <br />
            Account Type:
            <select>
                <option>Current</option>
                <option>Savings</option>
                <option>ISA</option>
            </select>
        </form>
        <br />
    </div>
    <div>
        <h2>Remove Account</h2>
        <form name="RemoveAccount" action="action" method="post">
        Customer:
            <select>
                <option>Customer 1</option>
                <option>Customer 2</option>
            </select>
            <br />
            <select>
                <option>Account 1</option>
                <option>Account 2</option>
                <option>Account 3 </option>
            </select>
            <input type="submit" value="Submit" />
        </form>
    </div>    
    <br />
    <div>
        <button name="AddCustomerAccount">Add a new Customer Account</button>
        <button name="RemoveCustomerAccount">Remove a Customer Account</button>
    </div>
    <br />
    <div>
        <button name="AddAccountTransaction">Add a new Account Transaction</button>
        <button name="RemoveAccountTransaction">Remove an Account Transaction</button>
    </div>
  </body>
</html>