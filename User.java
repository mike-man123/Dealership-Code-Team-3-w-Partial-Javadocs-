package edu.psu.abington.ist.ist242;

/*
Project: Car Dealership Class Creation
Purpose Details: User Class
Course: IST 242
Author: Quenten Calvano
Date Developed: 6/13/20
Last Date Changed: 6/21/2020
Rev: 2
 */

import java.util.ArrayList;
import java.util.Scanner;

public class User {

    //Class level variables
    private String userName;
    private String password;
    private String userRole;
    private int typeID; //Refers to the user type. Customer = 0, Sales Associate = 1, Sales Manager = 2.

    //Constructor Method
    public void User (String _userName, String _password, int _typeID, String _userRole){
        this.userName = _userName;
        this.password = _password;
        this.typeID = _typeID;
        this.userRole = _userRole;
    }

    //Setters and Getters
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUserRole() {
        return userRole;
    }
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
    public int getTypeID() {
        return typeID;
    }
    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    /**
     * This method tries to verify the user login and
     * returns a boolean that represents whether the login
     * passed verification or not.
     *
     * @param uList     An ArrayList of User objects.
     * @return          Verified true or false
     * @author          Quenten Calvano
     * @version         1.0
     * @since           2020-24-06
     */
    public static boolean verifyUserLogin(ArrayList<User> uList) {
        Scanner scnr = new Scanner(System.in);
        boolean loginVerified = false;
        boolean exitUser = false;
        boolean exitPass = false;
        boolean userNameClear = false;
        boolean passwordClear = false;
        do {
            User activeUser = new User();
            User testCheckUser = new User();
            do{
                System.out.print("Username: ");
                activeUser.setUserName(scnr.next());
                for (User user : uList) {
                    if (activeUser.getUserName().equals(user.getUserName())){
                        userNameClear = true;
                        testCheckUser.setUserName(user.getUserName());
                        testCheckUser.setPassword(user.getPassword());
                        break;
                    }
                }
                if (userNameClear == false) {
                    System.out.println("ERROR: Username not found. Type 'T' to try again or 'E' to Exit");
                    String userChoice = scnr.next();
                    if(userChoice.equalsIgnoreCase("T")){
                        System.out.println("Retrying Login...");
                    }
                    else if (userChoice.equalsIgnoreCase("E")){
                        userNameClear = true;
                        exitUser = true;
                        loginVerified = false;
                        return loginVerified;
                    }
                    else {
                        System.out.println("Invalid input. Retrying login...");
                    }
                }
            } while (!userNameClear);
            //If the process hasn't been exited verify password...
            if (!exitUser){
                do {
                    System.out.print("Password: ");
                    activeUser.setPassword(scnr.next());
                    if (activeUser.getPassword().equals(testCheckUser.getPassword())){
                        passwordClear = true;
                        loginVerified = true;
                        exitPass = true;
                        break;
                    }
                    else {
                        System.out.println("Incorrect Password. Type 'T' to try again or 'E' to Exit");
                        String userChoice2 = scnr.next();
                        if (userChoice2.equalsIgnoreCase("E")) {
                            exitPass = true;
                            passwordClear = true;
                            break;
                        } else if (userChoice2.equalsIgnoreCase("T")) {
                            exitPass = false;
                            passwordClear = false;
                        }
                    }
                } while (passwordClear == false);
            }
        } while (!exitPass);
        return loginVerified;
    }

    /**
     * This method is invoked to add a user to the user object list
     * meaning this will be used to create an account.
     *
     * @param uList     An ArrayList of User objects.
     * @author          Quenten Calvano
     * @version         1.0
     * @since           2020-24-06
     */
    public static void addUser(ArrayList<User> uList) {
        String _userName;
        String _password;
        int _typeID = 0;
        Scanner scnr = new Scanner(System.in);
        
        boolean creationComplete = false;
        do {
            System.out.println("Enter account username: ");
            _userName = scnr.nextLine();
            System.out.println("You entered: " + _userName);
            boolean passVerified = false;
            do {
                System.out.println("Create new account password: ");
                _password = scnr.nextLine();
                System.out.println("Retype password: ");
                if (scnr.nextLine().equals(_password)){
                    passVerified = true;
                }
                else {
                    System.out.println("Incorrect password. Please try again.");
                }
            } while (passVerified == false);
            boolean userTypeEntered = false;
            do {
                System.out.println("Are you a customer?(y/n)");
                String yesNoInput = scnr.next();
                if (yesNoInput.startsWith("y")){
                    _typeID = 0;
                    userTypeEntered = true;
                    creationComplete = true;
                }
                else if (yesNoInput.startsWith("n")){
                    System.out.println("Please enter role name (Associate/Manager) below: ");
                    String userInput = scnr.next();
                    if (userInput.equalsIgnoreCase("Associate")){
                        _typeID = 1;
                        userTypeEntered = true;
                        creationComplete = true;
                    }
                    else if (userInput.equalsIgnoreCase("Manager")){
                        _typeID = 2;
                        userTypeEntered = true;
                        creationComplete = true;
                    }
                }
                else {
                    System.out.println("Invalid command. Please Try again.");
                }
            } while (userTypeEntered == false);

        } while (creationComplete == false);

        User user = new User();
        user.setUserName(_userName);
        user.setPassword(_password);
        user.setTypeID(_typeID);
        user.setUserRole(user.generateUserRole(_typeID));
        uList.add(user);
    }

    /**
     * This method uses a Scanner to take user input and attempts to match
     * a user entered string to a name in the User ArrayList.
     *
     * @param uList     An ArrayList of User objects.
     * @author          Quenten Calvano
     * @version         1.0
     * @since           2020-24-06
     */
    public static void printSpecificUser(ArrayList<User> uList) {
        Scanner scnr = new Scanner(System.in);
        System.out.println("Enter the user name of the account to look up.");
        String userSearch = scnr.next();
        for (User user: uList){
                if (userSearch.equals(user.getUserName())){
                    System.out.println("Username: " + user.getUserName());
                    System.out.println("Password: " + user.getPassword());
                    System.out.println("TypeID: " + user.getTypeID());
                    System.out.println("Role name: " + user.getUserRole());
                }

        }
    }

    /**
     * This method allows the value of an integer _typeID to match up with a
     * descriptive String representation allowing for user readability.
     *
     * @param _typeID   An integer value correlating to a descriptive message.
     * @return          Returns a descriptive name for the user role corresponding with
     *                  the integer typeID.
     * @author          Quenten Calvano
     * @version         1.0
     * @since           2020-24-06
     */
    public String generateUserRole(int _typeID){
        switch (_typeID) {
            case 0:
                userRole = "Customer";
                break;
            case 1:
                userRole = "Sales Associate";
                break;
            case 2:
                userRole = "Sales Manager";
                break;
        }
        return userRole;
    }

    /**
     * This method brings up the printed user list that is used to access other
     * functions of the program based on user input.
     *
     *
     * @param carList   An ArrayList of Car objects.
     * @param dList     An ArrayList of Dealership objects.
     * @param uList     An ArrayList of User objects.
     * @author          Quenten Calvano
     * @version         1.0
     * @since           2020-24-06
     */
    //View the basic user list...
    public static void userMenu (ArrayList<Car> carList, ArrayList<Dealership> dList, ArrayList<User> uList) {
        Scanner scnr = new Scanner(System.in);
        boolean exitMenu = false;
        do {
            System.out.println("\nUSER MENU:\n\t1.View Dealerships\n\t2.Browse Vehicle Inventory\n\t3.View Financing Options\n\t4.Other Options(For Employees)\n\t5.Exit");
            switch(scnr.next().charAt(0)){
                case '1':
                    Dealership.viewDealerInfo(dList, uList);
                    break;
                case '2':
                    Car.viewCarInventory(carList);
                    break;
                case '3':
                    Car.viewFinancingOptions(carList);
                    break;
                case '4':
                    //TODO: Prompt the user to choose a role between SALES ASSOCIATE and MANAGER
                    // then create an if else to test their typeID and offer respective menu options
                    boolean exitOption = false;
                    do {
                        System.out.println("Re-enter user password for access:");
                        String password = scnr.next();
                        for (User user : uList) {
                            if (password.equals(user.getPassword())) {
                                if (user.getTypeID() == 0){
                                    //TODO: Insert option
                                    System.out.println("Customer prompt");//A test prompt
                                    //Change flag to escape
                                    exitOption = true;
                                }
                                else if (user.getTypeID() == 1) {
                                    //TODO: Insert option
                                    System.out.println("Sales Associate prompt");//A test prompt
                                    //Change flag to escape
                                    exitOption = true;
                                }
                                else if (user.getTypeID() == 2) {
                                    //TODO: Insert option
                                    System.out.println("Manager prompt");//A test prompt
                                    //Change flag to escape
                                    exitOption = true;
                                }

                            }
                        }
                    } while (!exitOption);
                    break;
                case '5':
                    exitMenu = true;
                    break;
                default:
                    System.out.println("Invalid selection please try again.");
                    break;

            }
        } while (!exitMenu);
    }
}
