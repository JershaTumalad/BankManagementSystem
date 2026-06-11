/*
 =====================================================================
 PROGRAM SUMMARY & DOCUMENTATION: USER BANK ACCOUNT MANAGEMENT SYSTEM
 =====================================================================
 
 [1] GLOBAL VARIABLES / DATA STORES
     - userId, password, email (String): Detalye at kredensyal ng user.
     - balance (double): Kasalukuyang pera para sa saktong kuwenta.
     - transactionHistory (String[]): Log ng mga nakaraang transaksyon.
 
 [2] PROGRAM FLOW (main method)
     1. I-initialize ang Scanner para sa mga input ng user.
     2. Patakbuhin ang loginPage() para sa authentication.
     3. Kapag successful, ididiretso ang user sa userDashboard().
 
 [3] SYSTEM SECTIONS & METHODS
 
     SECTION 1: Login Page (loginPage())
     - Create Account: Pagrehistro ng User ID, Password, at Email.
     - Forgot Password/Email: Pagbawi o pagpapalit ng account details.
     - Login Account: Pag-verify ng credentials bago papasukin sa Dashboard.
 
     SECTION 2: User Dashboard (userDashboard())
     - Balance Panel: Real-time display ng balance sa pinakataas ng menu.
     - Financial Transactions:
       * Deposit & Withdraw (May validation kung sapat ang pondo)
       * Transfer (Pagpasa ng pera sa ibang Account ID)
     - Lifestyle & Utilities:
       * Auto Payments (Schedules para sa Netflix, Spotify, atbp.)
       * Bills Payment (Meralco, Maynilad, atbp.)
       * Buy Load (Globe, Smart, DITO)
     - History & Navigation:
       * View Transaction (I-loop ang transactionHistory array)
       * Go to Account Management / Logout
 
     SECTION 3: Account Management (accountManagement())
     - Add Account: Pag-link ng sub-accounts (gaya ng Savings vs Checking).
     - Remove Account: Pagpapasara ng account gamit ang User ID.
     - Search: Paghanap ng lumang record gamit ang keywords.
     - View All Accounts: Pagpapakita ng lahat ng linked accounts at pondo nito.
 =====================================================================
 */
