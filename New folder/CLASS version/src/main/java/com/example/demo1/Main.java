package com.example.demo1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.ObjectInputStream;



public class Main extends Application {

//------------------------------------------------------GL0BAL VARIABLE  ↓--------------------------------------------------
    public static  boolean bool_value= true;    //THIS IS USED CONTROL WHILE LOOP
    private static  int BURGERS_STOCK = 50;     //BURGER STOCK
    private static int CASH1=0;                 //AMOUNT OF BURGERS THE FIRST CASHIER SOLD
    private static int CASH2=0;                 //AMOUNT OF BURGERS THE SECOND CASHIER SOLD
    private static int CASH3=0;                 //AMOUNT OF BURGERS THE THIRD CASHIER SOLD
    private static int num1=0;                  //THIS VARIABLE USE COUNT HOW MANY PEOPLE IN THA WAITING QUEUE
    private static int front=-1;                 //FRONT INDEX IN CIRCULAR WAITING QUEUE
    private static int rear=-1;                 //REAR INDEX IN CIRCULAR WAITING QUEUE
//---------------------------------------------------------------------------------------------------------------------------

    public static Scanner input = new Scanner(System.in); //CODE DECLARES A STATIC VARIABLE NAMED "INPUT" OF TYPE "SCANNER."

//-----------------------------------------BUILD 3 ARRAY USING THE "FoodQueue" CLASS  ↓--------------------------------------
    private static FoodQueue q1=new FoodQueue(2);   //FOR THE FIRST QUEUE
    private static FoodQueue q2=new FoodQueue(3);   //FOR THE SECOND QUEUE
    private static FoodQueue q3=new FoodQueue(5);   //FOR THE THIRD QUEUE
//---------------------------------------------------------------------------------------------------------------------------
    private static Customer[] wq =new Customer[10];                  //WAITING QUEUE
    private static ArrayList<String> alph=new ArrayList<>();         //THIS IS USED STORE NAMES ALPHABETICAL ORDER IN QUEUES CUSTOMERS
    public static ArrayList<String> alph_wq =new ArrayList<>();     ////THIS IS USED STORE NAMES ALPHABETICAL ORDER IN WAITING QUEUES CUSTOMERS

//-----------------------------------------------------------JAVA FX PART-----------------------------------------------------
    @Override
    public void start(Stage stage) throws IOException {
        Parent root=FXMLLoader.load(getClass().getResource("queuesGUI.fxml"));
        stage.setTitle("FOOD QUEUE");
        stage.setScene(new Scene(root));
        stage.show();
    }
//----------------------------------------------------MAIN METHOD -----------------------------------------------------------------
    public static void main(String[] args)  {

        while (bool_value) {
            //----------PRINT MENU-----------
            System.out.println("    Menu Options:\n");
            System.out.println("100 or VFQ: View all Queues");
            System.out.println("101 or VEQ: View all Empty Queues");
            System.out.println("102 or ACQ: Add customer to a Queue.");
            System.out.println("103 or RCQ: Remove a customer from a Queue. (From a specific location)");
            System.out.println("104 or PCQ: Remove a served customer");
            System.out.println("105 or VCS: View Customers Sorted in alphabetical order `");
            System.out.println("106 or SPD: Store Program Data into file");
            System.out.println("107 or LPD: Load Program Data from file");
            System.out.println("108 or STK: View Remaining burgers Stock");
            System.out.println("109 or AFS: Add burgers to Stock");
            System.out.println("110 or IFQ: Income of each queue");
            System.out.println("112 or GUI: See the customer’s details");
            System.out.println("999 or EXT: Exit the Program\n");
            //--------GET CUSTOMER INPUT WHAT NEED FROM THE MENU-------
            System.out.print("What menu options do you need?");
            String Menu_Options = input.next();



            //---------------------------------------- CALL OTHER METHODS--------------------------------------------
            switch (Menu_Options) {
                case "100","VFQ":
                    View_all_Queues();
                    control();
                    break;
                case "101","VEQ":
                    View_all_Empty_Queues();
                    control();
                    break;
                case "102","ACQ":
                    Add_customer_to_a_Queue();
                    control();
                    break;
                case "103","RCQ":
                    Remove_a_customer_from_a_Queue();
                    control();
                    break;
                case "104","PCQ":
                    Remove_a_served_customer();
                    control();
                    break;
                case "105","VCS":
                    View_Customers_Sorted_in_alphabetical_order();
                    control();
                    break;
                case "106","SPD":
                    Store_Program_Data_into_file();
                    control();
                    break;
                case "107","LPD":
                    Load_Program_Data_from_file();
                    control();
                    break;
                case "108","STK":
                    View_Remaining_burgers_Stock();
                    control();
                    break;
                case "109","AFS":
                    Add_burgers_to_Stock();
                    control();
                    break;
                case "110","IFQ":
                    price();
                    control();
                    break;
                case "112","GUI":
                    GUI();
                    break;
                case "999","EXT":
                    System.out.println("--------PROGRAM IS END----------");
                    javafx.application.Platform.exit();
                    bool_value=false;
                    break;
                default:
                    System.out.println("Wrong Menu Option.Please try again");
            }
        }
    }
//-------------------------------------------------- OTHER ALL METHODS PART-----------------------------------------------------


    //View all Queues PART---------------------------------------------------------------------------------------------
    public static void View_all_Queues(){
        System.out.println("********************");
        System.out.println("****  Cashiers  ****");
        System.out.println("********************");

        for (int i = 0; i < 5; i++) {
            q1.checkPlace(i);
            q2.checkPlace(i);
            q3.checkPlace(i);
            System.out.println("");
        }
        System.out.println("X – Not Occupied O – Occupied");
    }
    //View all Empty Queues PART---------------------------------------------------------------------------------------
    public static void View_all_Empty_Queues(){
        System.out.println("********************");
        System.out.println("****  Cashiers  ****");
        System.out.println("********************");

        for (int i = 0; i < 5; i++) {
            q1.emptyPlace(i);
            q2.emptyPlace(i);
            q3.emptyPlace(i);
            System.out.println("");
        }
    }
    //Add customer to a Queue PART--------------------------------------------------------------------------------------
    public static void Add_customer_to_a_Queue() {
        int a=0;  // THIS VARIABLE USED IDENTIFY CUSTOMER IS ALREADY QUEUES
        try{//--------GET NAME AND BURGERS AMOUNT  ↓-----------
            System.out.print("plese enter customer  first name:");
            String customer_fname = input.next();
            System.out.print("plese enter customer  second name:");
            String customer_sname = input.next();
            System.out.print("How many burgers customer want");
            int burger_no=input.nextInt();

            String fullname=customer_fname+" "+customer_sname;     //CREATING THE FULL NAME

            //------------------------ CHECK CUSTOMER IS ALREADY IN THE CASHIER ↓--------------------------------------
            for(int i=0;i<q1.getSize();i++){
                if(q1.addNewArray(i).equals(fullname.toUpperCase())){
                    a=1;
                    System.out.println("A customer with this name is already in tha queue.Check if he is re entering.If someone else press 'A'.Pres 'B' if a same customer");
                    String letter=input.next();
                    if(letter.toUpperCase().equals("A")) { a=0;}
                    else if (letter.toUpperCase().equals("B")) {a=1;}
                    else {
                        System.out.println("Wrong input");
                        a=1;
                    }break;
                }
            }
            for(int i=0;i< q2.getSize();i++){
                if(q2.addNewArray(i).equals(fullname.toUpperCase())){
                    a=1;
                    System.out.println("A customer with this name is already in tha queue.Check if he is re entering.If someone else press 'A'.Pres 'B' if a same customer");
                    String letter=input.next();
                    if(letter.toUpperCase().equals("A")){ a=0;}
                    else if (letter.toUpperCase().equals("B")) {a=1;}
                    else {
                        System.out.println("Wrong input");
                        a=1;
                    }break;
                }
            }
            for(int i=0;i< q3.getSize();i++){
                if(q3.addNewArray(i).equals(fullname.toUpperCase())){
                    a=1;
                    System.out.println("A customer with this name is already in tha queue.Check if he is re entering.If someone else press 'A'.Pres 'B' if a same customer");
                    String letter=input.next();
                    if(letter.toUpperCase().equals("A")){ a=0;}
                    else if (letter.toUpperCase().equals("B")) {a=1;}
                    else {
                        System.out.println("Wrong input");
                        a = 1;
                    }break;
                }
            }
            for(int i=0;i< 10;i++){
                if(wq[i]!=null){
                    String wqFullname=wqFname(i).toUpperCase()+" "+wqSname(i).toUpperCase();
                if(wqFullname.equals(fullname.toUpperCase())){
                    a=1;
                    System.out.println("A customer with this name is already in tha waiting list.Check if he is re entering.If someone else press 'A'.Pres 'B' if a same customer");
                    String letter=input.next();
                    if(letter.toUpperCase().equals("A")){ a=0;}
                    else if (letter.toUpperCase().equals("B")) {a=1;}
                    else {
                        System.out.println("Wrong input");
                        a = 1;
                    }break;
                }
                }
            }
            //---------------------------------CUSTOMER ADD QUEUES  ↓---------------------------------------------------

            if(a==0){
                for(int i=0;i<5;i++){
                    if(q1.getSize()<2){
                        if(q1.getSize()<=i || q1.getSize()==0){
                            q1.addCustomer(new Customer(burger_no,customer_fname,customer_sname));
                            BURGERS_STOCK=BURGERS_STOCK-q1.returnBurger(i);
                            System.out.println("Successfully ,the customer add");
                            break;
                        }
                    }
                    if(q2.getSize()<3){
                        if(q2.getSize()<=i || q2.getSize()==0){
                            q2.addCustomer(new Customer(burger_no,customer_fname,customer_sname));
                            BURGERS_STOCK=BURGERS_STOCK-q2.returnBurger(i);
                            System.out.println("Successfully ,the customer add");
                            break;
                        }
                    }
                    if(q3.getSize()<5){
                        if(q3.getSize()<=i || q3.getSize()==0){
                            q3.addCustomer(new Customer(burger_no,customer_fname,customer_sname));
                            BURGERS_STOCK=BURGERS_STOCK-q3.returnBurger(i);
                            System.out.println("Successfully ,the customer add");
                            break;
                        }
                    }
            //---------------------------------CUSTOMER ADD WAITING LIST  ↓--------------------------------------------
                    if(q1.getSize()==2&&q2.getSize()==3&&q3.getSize()==5){
                        System.out.println("all cash is full.This customer add waiting list");
                        if ((front== 0 && rear == wq.length - 1) || (front == rear + 1)) {
                            System.out.println(" waiting Queue is full.");
                            break;
                        } else {
                            rear++;
                            num1++;
                            wq[rear]=new Customer(burger_no,customer_fname,customer_sname);
                            if (rear == wq.length-1) {
                                rear=-1;
                            }
                         break;}
                    } }
                if(BURGERS_STOCK<=10){ //CHECK BURGERS STOCK LESS THAN 10
                    System.out.print("WARNING  (burger stock is less than 10)");
                }}
        }catch (Exception e){
            System.out.println("Wrong input.Try again");
        }
    }
    //Remove a customer from a Queue. (From a specific location) PART---------------------------------------------------
    public static void Remove_a_customer_from_a_Queue() {
        try {
            System.out.print("From which cashier should it be removed (1 or 2 or 3):");
            int cashier_remove_no = input.nextInt();

            if (cashier_remove_no == 1 || cashier_remove_no == 2 || cashier_remove_no == 3) {
                System.out.print("cashier1(0,1)  cashier2(0,1,2)  cashier3(0,1,2,3,4)");
                System.out.print("To which station should be removed:");
                int cashier_remove_place = input.nextInt();//----- GET THE PLACE TO BE REMOVE  ↑--------------------------

                if (cashier_remove_no == 1) {
                    BURGERS_STOCK=BURGERS_STOCK+q1.returnBurger(cashier_remove_place);
                    q1.removeCustomer(cashier_remove_place);
                    System.out.println("Successfully ,the customer remove");
                } else if (cashier_remove_no == 2) {
                    BURGERS_STOCK=BURGERS_STOCK+q2.returnBurger(cashier_remove_place);
                    q2.removeCustomer(cashier_remove_place);
                    System.out.println("Successfully ,the customer remove");
                } else if (cashier_remove_no == 3) {
                    BURGERS_STOCK=BURGERS_STOCK+q3.returnBurger(cashier_remove_place);
                    q3.removeCustomer(cashier_remove_place);
                    System.out.println("Successfully ,the customer remove");
                } else {
                    System.out.println("Wrong input.Try again");
                }

            } else {
                System.out.println("Wrong input.Try again");
            }
        } catch (Exception e) {
            System.out.println("The place you entered is incorrect. try again");
        }
    }
    //Remove a served customer PART--------------------------------------------------------------------------------------
    public static void Remove_a_served_customer(){
         try{
             System.out.print("From which row was served?;(1 or 2 or 3)");
            int served_row=input.nextInt();                      //GET WHICH ROW WAS SERVED
            if (served_row==1){
                CASH1=CASH1+ q1.burgerAmount();                 //CALCULATING THE QUANTITY SOLD
                q1.removeServedCustomer();                      //REMOVE SERVED CUSTOMER
                System.out.println("Successfully ,the served customer remove");

                //-----------ADDING CUSTOMER FROM WAITING QUEUE TO QUEUES   ↓-------------------------------------------
                int a=front+1;
                if(wq[a]!=null){
                    front++;
                    BURGERS_STOCK=BURGERS_STOCK-wq[front].getBurgersAmount();
                    q1.addCustomer(wq[front]);
                    wq[front]=null;
                    num1--;
                    if(front== wq.length-1){
                        front=-1;
                    }
                }
            }
            else if (served_row==2){
                CASH2=CASH2+ q2.burgerAmount();                 //CALCULATING THE QUANTITY SOLD
                q2.removeServedCustomer();                      //REMOVE SERVED CUSTOMER
                System.out.println("Successfully ,the served customer remove");

                //-----------ADDING CUSTOMER FROM WAITING QUEUE TO QUEUES   ↓-------------------------------------------
                int a=front+1;
                if(wq[a]!=null){
                    front++;
                    BURGERS_STOCK=BURGERS_STOCK-wq[front].getBurgersAmount();
                    q2.addCustomer(wq[front]);
                    wq[front]=null;
                    num1--;
                    if(front== wq.length-1){
                        front=-1;
                    }
                }
            }
            else if (served_row==3){
                CASH3=CASH3+ q3.burgerAmount();                 //CALCULATING THE QUANTITY SOLD
                q3.removeServedCustomer();                      //REMOVE SERVED CUSTOMER
                System.out.println("Successfully ,the served customer remove");

                //-----------ADDING CUSTOMER FROM WAITING QUEUE TO QUEUES   ↓-------------------------------------------
                int a=front+1;
                if(wq[a]!=null){
                    front++;
                    BURGERS_STOCK=BURGERS_STOCK-wq[front].getBurgersAmount();
                    q3.addCustomer(wq[front]);
                    wq[front]=null;
                    num1--;
                    if(front== wq.length-1){
                        front=-1;
                    }
                }
            }
            else{System.out.print("Wrong input.try again");}
         }
         catch (Exception e){
            System.out.println("Wrong input.Try again");
        }
    }
    //View Customers Sorted in alphabetical order PART------------------------------------------------------------------
    public static void  View_Customers_Sorted_in_alphabetical_order(){
        alph.clear();
        alph_wq.clear();

        //-------------MERGES THE 3 ARRAYS INTO A SINGLE ARRAY  ↓---------------------------
        for(int i=0;i<q1.getSize();i++){
            String name=q1.addNewArray(i);
            alph.add(name) ;}

        for(int i=0;i<q2.getSize();i++){
            String name= q2.addNewArray(i) ;
            alph.add(name) ;}

        for(int i=0;i<q3.getSize();i++){
            String name= q3.addNewArray(i) ;
            alph.add(name) ;}
        //-----------------------------3 QUEUES SORTED ALPHABETICAL  ↓-----------------------
        for(int i=0;i<alph.size();i++) {
            for (int j = i + 1; j < alph.size(); j++) {
                if (alph.get(i).compareTo(alph.get(j)) > 0) {
                    String temporary = alph.get(i);
                    alph.set(i, alph.get(j));
                    alph.set(j, temporary);
                }
            }
        }
        //-----------------------------WAITING QUEUE STORE IN A NEW ARRAY  ↓---------------------
        for(int i=0;i<10;i++){
            if(wq[i]!=null){
            alph_wq.add(wq[i].fullName());}
        }
        //-----------------------------WAITING QUEUE SORTED ALPHABETICAL  ↓----------------------
        for(int i=0;i<alph_wq.size();i++) {
            for (int j = i + 1; j < alph_wq.size(); j++) {
                if (alph_wq.get(i).compareTo(alph_wq.get(j)) > 0) {
                    String temporary = alph_wq.get(i);
                    alph_wq.set(i, alph_wq.get(j));
                    alph_wq.set(j, temporary);
                }
            }
        }
       //----------------------------PRINT SORTED ARRAYS  ↓------------------------------------------
        System.out.println("Food queues :");
        for(int i=0;i<alph.size();i++){
            System.out.println(alph.get(i));
        }
        System.out.println("Waiting queue :");
        for(int i = 0; i< alph_wq.size(); i++){
            System.out.println(alph_wq.get(i));
        }
    }
    //Store Program Data into file PART---------------------------------------------------------------------------------
    public static void  Store_Program_Data_into_file(){
        q1.addTextfile("textfile1.txt");         //CASHIER1 ARRAY STORE IN TEXT FILE
        q2.addTextfile("textfile2.txt");         //CASHIER2 ARRAY STORE IN TEXT FILE
        q3.addTextfile("textfile3.txt");         //CASHIER3 ARRAY STORE IN TEXT FILE

        //--------------WAITING QUEUE STORE IN TEXT FILE  ↓-----------------------------
        try{
            File file=new File("textfile4.txt");
            FileOutputStream filein=new FileOutputStream(file);
            ObjectOutputStream objectin=new ObjectOutputStream(filein);
            for(int i=0;i< 10;i++){
                if(wq[i]!=null){
                Customer customer1=wq[i];
                objectin.writeObject(customer1);}
            }filein.close();
            objectin.close();
        }
        catch(Exception e){
            System.out.print(e);}


        //-----------------------SOME VARIABLE STORE IN TEXT FILE  ↓-----------------------
        try{
            File file4=new File("textfile5.txt");
            FileWriter textfile=new FileWriter("textfile5.txt");
            String BS=Integer.toString(BURGERS_STOCK);
            textfile.write(BS);
            textfile.write("\n");
            String CASH1S=Integer.toString(CASH1);
            textfile.write(CASH1S);
            textfile.write("\n");
            String CASH2S=Integer.toString(CASH2);
            textfile.write(CASH2S);
            textfile.write("\n");
            String CASH3S=Integer.toString(CASH3);
            textfile.write(CASH3S);
            textfile.write("\n");
            String frontS=Integer.toString(front);
            textfile.write(frontS);
            textfile.write("\n");
            String rearS=Integer.toString(rear);
            textfile.write(rearS);
            textfile.write("\n");
            String num1S=Integer.toString(num1);
            textfile.write(num1S);
            textfile.write("\n");
            textfile.close();
        }catch (Exception e){}

        System.out.println("Data store successfully");
    }
    //Load Program Data from file PART----------------------------------------------------------------------------------
    public static void Load_Program_Data_from_file(){
        //---CLEAR EXISTING DATA  ↓-------------------------------
        q1.clearArray();
        q2.clearArray();
        q3.clearArray();
        //---CLEAR EXISTING DATA IN WAITING QUEUE  ↓-------------
        for(int i=0;i<10;i++){
            if(wq[i]!=null){
                wq[i]=null;
            }
        }

        q1.reedTextfile("textfile1.txt");   //CASHIER1 DATA LOAD
        q2.reedTextfile("textfile2.txt");   //CASHIER2 DATA LOAD
        q3.reedTextfile("textfile3.txt");   //CASHIER2 DATA LOAD

        //------------------------WAITING QUEUE DATA LOAD  ↓-------------------------------
        try{FileInputStream fileout=new FileInputStream("textfile4.txt");
            ObjectInputStream objectout=new ObjectInputStream(fileout);
            int i=0;
            while(true){
                try{Customer customer1=(Customer) objectout.readObject();
                    wq[i]=customer1;
                    i++;}
                catch (Exception e){
                    break;}}
        }catch (Exception e){}

        //--------------------------VARIABLE DATA LOAD ↓-------------------------------------
        File file=new File("textfile5.txt");
        try{Scanner reader=new Scanner(file);
            int BURGERS_STOCK_PREVIOUS=reader.nextInt();
            BURGERS_STOCK=BURGERS_STOCK_PREVIOUS;
            int CASH1_PREVIOUS=reader.nextInt();
            CASH1=CASH1_PREVIOUS;
            int CASH2_PREVIOUS=reader.nextInt();
            CASH2=CASH2_PREVIOUS;
            int CASH3_PREVIOUS=reader.nextInt();
            CASH3=CASH3_PREVIOUS;
            int front_PREVIOUS=reader.nextInt();
            front=front_PREVIOUS;
            int rear_PREVIOUS=reader.nextInt();
            rear=rear_PREVIOUS;
            int num1_PREVIOUS=reader.nextInt();
            num1=num1_PREVIOUS;
        }catch (Exception e){}
        System.out.println("Data load successfully");
    }
    // View Remaining burgers Stock PART---------------------------------------------------------------------------------
    public static void View_Remaining_burgers_Stock(){
        System.out.print( "THERE ARE CURRENTLY  "+BURGERS_STOCK+"  BURGERS");
        System.out.println();
    }
    //Add burgers to Stock PART-----------------------------------------------------------------------------------------
    public static void Add_burgers_to_Stock(){
        try{ System.out.println("you can add  "+(50-BURGERS_STOCK));
            System.out.print("How many burgers add?");
            int add_burgers=input.nextInt();
            if(BURGERS_STOCK+add_burgers<50){
                BURGERS_STOCK=BURGERS_STOCK+add_burgers;}
            else{System.out.print("this amount can't add.because max burger amount is 50");}
            System.out.println();}catch (Exception e){System.out.println("Wrong input.try again");}
    }

    //Income of each queue PART-----------------------------------------------------------------------------------------
    public static void price(){
        System.out.println("cashier 1:" + CASH1*650+"Rs");
        System.out.println("cashier 2:" + CASH2*650+"Rs");
        System.out.println("cashier 3:" + CASH3*650+"Rs");
    }
    // THIS SECTIONS IS USED IN SWITCHES CASE PART.THIS SECTION CONTROLS THE AUTOMATIC DISPLAY OF THE MENU.-------------
    public static void control(){
        System.out.println(" ");
        System.out.print("Enter any key to return to the menu:  ");
        String output=input.next();
        System.out.println(" ");
    }

    // THIS METHOD USED LAUNCH GUI  ↓------------------------------------------------------------------------------------
    public static void GUI(){
    try{launch();}catch (Exception e){
    System.out.println("GUI can only be used once.If you want relaunch please restart the program.You can use '106' store date and use '107' load date");}
    }

    //--------------------------THIS METHOD USED JAVA FX PART  ↓------------------------------------
    public static int q1Size(){return q1.getSize();}                       //RETURN Q1 ARRAY SIZE
    public static String q1Fname( int a){return q1.returnFName(a);}       //RETURNING THA FIRST NAME OF THE CUSTOMER IN THA FIRST CASHIER
    public static String q1Sname( int a){return q1.returnSName(a);}       //RETURNING THA SECOND NAME OF THE CUSTOMER IN THA FIRST CASHIER
    public static int q1Burgers( int a){return q1.returnBurger(a);}       //RETURNING THA BURGER AMOUNT OF THE CUSTOMER IN THA FIRST CASHIER
    public static int q2Size(){return q2.getSize();}                      //RETURN Q2 ARRAY SIZE
    public static String q2Fname( int a){return q2.returnFName(a);}       //RETURNING THA FIRST NAME OF THE CUSTOMER IN THA SECOND CASHIER
    public static String q2Sname( int a){return q2.returnSName(a);}       //RETURNING THA SECOND NAME OF THE CUSTOMER IN THA SECOND CASHIER
    public static int q2Burgers( int a){return q2.returnBurger(a);}       //RETURNING THA BURGER AMOUNT OF THE CUSTOMER IN THA SECOND CASHIER
    public static int q3Size(){return q3.getSize();}                      //RETURN Q3 ARRAY SIZE
    public static String q3Fname( int a){return q3.returnFName(a);}       //RETURNING THA FIRST NAME OF THE CUSTOMER IN THA THIRD CASHIER
    public static String q3Sname( int a){return q3.returnSName(a);}       //RETURNING THA SECOND NAME OF THE CUSTOMER IN THA THIRD CASHIER
    public static int q3Burgers( int a){return q3.returnBurger(a);}       //RETURNING THA BURGER AMOUNT OF THE CUSTOMER IN THA THIRD CASHIER
    public static String wqFname(int a){return wq[a].getFirstname();}     //RETURNING THA FIRST NAME OF THE CUSTOMER IN THA WAITING QUEUE
    public static String wqSname(int a){return wq[a].getSecondname();}   //RETURNING THA SECOND NAME OF THE CUSTOMER IN THA WAITING QUEUE
    public static int wqBurgers(int a){return wq[a].getBurgersAmount();}  //RETURNING THA BURGER AMOUNT OF THE CUSTOMER IN THA WAITING QUEUE
    public static Customer wqObject(int a){return wq[a];}                  //RETURNING WAITING QUEUE CUSTOMER
}