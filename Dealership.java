package edu.psu.abington.ist.ist242;

/*
Project: Car Dealership Class Creation
Purpose Details: Dealership Class
Course: IST 242
Author: Quenten Calvano
Date Developed: 6/13/20
Last Date Changed: 6/21/2020
Rev: 2
 */

import java.util.ArrayList;

public class Dealership {

    //Class level variables - Protect the data...
    private String dealerName;
    private int dealershipId;
    private Car car;
    private ArrayList<SalesAssociate> employees;
    private String managerName;

    //Constructor method...
    public void Dealership(String _dealerName, int _dealershipId, Car _car
            , ArrayList<SalesAssociate> _employee, String _manager){
        this.dealerName = _dealerName;
        this.dealershipId = _dealershipId;
        this.car = _car;
        this.employees = _employee;
        this.managerName = _manager;
    }

    //Setters and Getters...
    public String getDealerName() {
        return dealerName;
    }
    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }
    public int getDealershipId() {
        return dealershipId;
    }
    public void setDealershipId(int dealershipId) {
        this.dealershipId = dealershipId;
    }
    public Car getCar() {
        return car;
    }
    public void setCar(Car car) {
        this.car = car;
    }
    public ArrayList<SalesAssociate> getEmployees() {
        return employees;
    }
    public void setEmployees(ArrayList<SalesAssociate> employees) {
        this.employees = employees;
    }
    public String getManager() {
        return managerName;
    }
    public void setManager(String manager) {
        this.managerName = manager;
    }

    /**
     * This method can be called to create 2 hardcoded Dealership objects
     * to be used to test the functions of the system. These objects are
     * added to the Dealership ArrayList that is passed in.
     *
     * @param dList     An ArrayList of Dealership objects.
     * @param carList   An ArrayList of Car objects.
     * @param saList    An ArrayList of SalesAssociate objects.
     * @author          Quenten Calvano
     * @version         1.0
     * @since           2020-24-06
     */
    public static void createSampleDealerships(ArrayList<Dealership> dList, ArrayList<Car> carList, ArrayList<SalesAssociate> saList) {
        User manager1 = new User();
        Dealership dealer1 = new Dealership();
        dealer1.setDealershipId(1);
        dealer1.setDealerName("Monty's Auto Dealership");
        dealer1.setCar(carList.get(0));
        dealer1.setEmployees(saList);
        dealer1.setManager("Jeff Stone");
        dList.add(dealer1);

        User manager2 = new User();
        Dealership dealer2 = new Dealership();
        dealer2.setDealershipId(2);
        dealer2.setDealerName("Zoey's Automotive");
        dealer2.setCar(carList.get(1));
        dealer2.setEmployees(saList);
        dealer2.setManager("Don Beck");
        dList.add(dealer2);
    }

    /**
     * This method prints out the dealership information as well as the Sales members that
     * work for the dealership.
     *
     * @param dList     An ArrayList of Dealership objects.
     * @param uList     An ArrayList of User objects.
     * @author          Quenten Calvano
     * @version         1.0
     * @since           2020-24-06
     */
    public static void viewDealerInfo(ArrayList<Dealership> dList, ArrayList<User> uList){
        for (Dealership dealership : dList) {
            System.out.println("\nDealership ID: " + dealership.getDealershipId());
            System.out.println("Dealership Name: " + dealership.getDealerName());
            System.out.printf("Car(s): \n\t%s\n\t$%.2f\n", dealership.getCar().getCarDescription(), dealership.getCar().getCarPrice());
            System.out.println("Manager: " + dealership.getManager());
            System.out.println("Sales Members: \n");
            int i = 1;
            for (User user : uList) {
                if (user.getTypeID() == 1) {
                    System.out.println("\t" + i++ + " : " + user.getUserName());
                }
            }

        }
    }
}
