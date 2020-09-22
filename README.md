# DollarsBankCoreJavaAppVl
- Project Name: DollarsBankCoreJavaAppVl
- Domain : Banking {Internet Banking)
- Application Type : Console App
- Architecture Used : Layered Architecture
- User Interface : Menu based with multicolor.

### Objectives:
1. Use layered architecture (abstractedfiles under SOLID principles) to make a MVC banking application.
2. Create a user login system.
3. Once logged in, have a user menu display in console.
4. Allow user to make:
   - [x] Deposit
   - [x] Withdrawl
   - [x] Funds Transfer
   - [x] Recent transaction history
   - [x] Display customer information
   - [x] Sign out
5. Apply business logic to handle illegaloperations in:
   - [x] login system
   - [x] Transactions (withdrawls /depsosits, etc.)
6. Use JDBC and DAO to connect user info to a database.
7. Extra Features:
   - [x] View All Accounts
   - [ ] Open a new Account
   - [x] Reopen an existing Account that was closed
   - [x] Close an existing Account
   - [ ] Update Customer Profile
   - [ ] Open a new Account with another existing customer

### Running dollarsbank.jar:
1. Make sure your mysql server connection for localhost in ConnectionMangerProperties matches your localhost connection properties.
   - If your localhost connection properties doesn't match then you will have to adjust the values, then compile and re-export the jar.
2. Run the dollars_bank.sql script on your loaclhost connection.
3. Double-click the dollarsbank.jar and the program should work smoothly.

### Running in an IDE:
1. Make sure your mysql server connection for localhost in ConnectionMangerProperties matches your localhost connection properties.
2. Run the dollars_bank.sql script on your loaclhost connection.
3. Run the main method within the Main.java class, in order to avoid the opening of the cmd prompt with an error of unable to run jar.
   - The program will still print to the console.
