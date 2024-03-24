import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;


public class Main {
    // GLOBAL VARIABLES
    private static  boolean bool_value= true;
    private static  int catch1=0;             // THIS VARIABLE USE TO FIND OUT IF A PREVIOUSLY ENTERED NAME HAS BEEN RE ENTERED
    private static  int BURGERS_STOCK = 50;
    private static int num1=0;               // THIS VARIABLE USE TO COUNT NUMBER OF PEOPLE IN ALL THREE QUEUES
    private static int CASH1=0;             // THESE CASH1,CASH2,CASH3 VARIABLES USE TO COUNT THE NUMBER OF PEOPLE IN THE QUEUES
    private static int CASH2=0;             // AND THESE THREE VARIABLES USE STORE DATA IN A FILE
    private static int CASH3=0;
    private static String[] CASHIER1 = new String[2];           // FIRST ARRAY
    private static String[] CASHIER2 = new String[3];           // SECOND ARRAY
    private static String[] CASHIER3 = new String[5];           // THIRD ARRAY
    private static String[] ALLCASHIER= new String[10];         // SORTED ARRAY
    private static Scanner input = new Scanner(System.in);
//------------------------------------------MAIN METHOD PART-----------------------------------------------------------------------

    public static void main(String[] args) {

        while (bool_value) {    //THIS WHILE LOOP USE LOOPS THE MENU
            // PRINT MENU
            System.out.println("Menu Options:");
            System.out.println("100 or VFQ: View all Queues");
            System.out.println("101 or VEQ: View all Empty Queues.");
            System.out.println("102 or ACQ: Add customer to a Queue.");
            System.out.println("103 or RCQ: Remove a customer from a Queue. (From a specific location)");
            System.out.println("104 or PCQ: Remove a served customer.");
            System.out.println("105 or VCS: View Customers Sorted in alphabetical order `");
            System.out.println("106 or SPD: Store Program Data into file.");
            System.out.println("107 or LPD: Load Program Data from file.");
            System.out.println("108 or STK: View Remaining burgers Stock.");
            System.out.println("109 or AFS: Add burgers to Stock.");
            System.out.println("999 or EXT: Exit the Program.\n");

            System.out.print("What menu options do you need?:");
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
                case "999","EXT":
                    System.out.println("-------PROGRAM IS END----------");
                     bool_value=false;
                     break;
                default:
                    System.out.println("Wrong Menu Option.Please try again");

            }
        }
    }
//-------------------------------------------------- OTHER ALL METHODS PART------------------------------------------------------------------
    //View all  Queues PART---------------------------------------------------------------------------------------------
    public static void View_all_Queues(){
        System.out.println("********************");
        System.out.println("****  Cashiers  ****");
        System.out.println("********************");
        for (int i = 0; i < 5; i++) {
            View_all_Queues_OTHER( CASHIER1,i);
            View_all_Queues_OTHER( CASHIER2,i);  //THIS METHODS CREATE IN LINE 450
            View_all_Queues_OTHER( CASHIER3,i);
            System.out.println("");
        }
        System.out.println("X – Not Occupied O – Occupied");
    }
    //View all Empty Queues PART----------------------------------------------------------------------------------------
    public static void View_all_Empty_Queues(){
        System.out.println("********************");
        System.out.println("****  Cashiers  ****");
        System.out.println("********************");
        for (int i = 0; i < 5; i++) {
            View_all_Empty_Queues_OTHER(CASHIER1,i);
            View_all_Empty_Queues_OTHER(CASHIER2,i); //THIS METHODS CREATE IN LINE 463
            View_all_Empty_Queues_OTHER(CASHIER3,i);
            System.out.println("");}
    }
    //Add customer to a Queue PART--------------------------------------------------------------------------------------
    public static void Add_customer_to_a_Queue() {
       try{ System.out.print("To which cashier should it be added.(1 or 2 or 3):");
        int cashier_no = input.nextInt();
        if(cashier_no==1||cashier_no==2||cashier_no==3){
        System.out.print("plese enter customer  first name:");
        String customer_fname = input.next();
        System.out.print("plese enter customer  second name:");
        String customer_sname = input.next();
        String fullname = customer_fname.concat(" " + customer_sname);
        String fullnameup = fullname.toUpperCase();//------------GET CUSTOMER NAME-------------------------------------
        for (int i = 0; i < 5; i++) {
            if (i < CASH1) {
                if (CASHIER1[i].equals(fullnameup)) {
                    System.out.println("this customer is already in the cashier. But re enter this customer py pressing 'A' ");
                    String leatter=input.next();
                    catch1 = 1;
                    if(leatter.toUpperCase().equals("A")){catch1=0;}
                    break;
                } else {
                    catch1 = 0;}
            }
            if (i < CASH2) {
                if (CASHIER2[i].equals(fullnameup)) {
                    System.out.println("this customer is already in the cashier. But re enter this customer py pressing 'A' ");
                    String leatter=input.next();
                    catch1 = 1;
                    if(leatter.toUpperCase().equals("A")){catch1=0;}
                    break;
                } else {
                    catch1 = 0;}
            }
            if (i < CASH3) {
                if (CASHIER3[i].equals(fullnameup)) {
                    System.out.println("this customer is already in the cashier. But re enter this customer py pressing 'A' ");
                    String leatter=input.next();
                    catch1 = 1;
                    if(leatter.toUpperCase().equals("A")){catch1=0;}
                    break;
                } else {
                    catch1 = 0;}
            }
        }//-------------------------------------------- FORM ABOVE PART CHECK CUSTOMER IS ALREADY IN THE CASHIER --------
        if (catch1 != 1) {
            if (cashier_no == 1) {
                for (int i = 0; i < 2; i++) {
                    String customer_CASH1 = CASHIER1[i];
                    if (customer_CASH1 == (null)) {
                        CASHIER1[i] = fullnameup;
                        CASH1 = CASH1 + 1;
                        BURGERS_STOCK=BURGERS_STOCK-5;
                        System.out.println("Customer added cashier 1");
                        break;
                    } else if (CASHIER1[1] != null) {
                        System.out.println("Cashier1 is full");
                        break;}}
            }
            if (cashier_no == 2) {
                for (int i = 0; i < 3; i++) {
                    String customer_CASH2 = CASHIER2[i];
                    if (customer_CASH2 == null) {
                        CASHIER2[i] = fullnameup;                      //-------CUSTOMER ADD CASHIERS PART----
                        CASH2 = CASH2 + 1;
                        BURGERS_STOCK=BURGERS_STOCK-5;
                        System.out.println("Customer added cashier 2");
                        break;
                    } else if (CASHIER2[2] != null) {
                        System.out.println("Cashier2 is full");
                        break;}}
            }
            if (cashier_no == 3) {
                for (int i = 0; i < 5; i++) {
                    String customer_CASH3 = CASHIER3[i];
                    if (customer_CASH3 == null) {
                        CASHIER3[i] = fullnameup;
                        CASH3 = CASH3 + 1;
                        BURGERS_STOCK=BURGERS_STOCK-5;
                        System.out.println("Customer added cashier 3");
                        break;
                    } else if (CASHIER3[4] != null) {
                        System.out.println("Cashier3 is full");
                        break;}}
            }
            if(BURGERS_STOCK<=10){
                System.out.print("WARNING  (burger stock is less than 10)");   //CHECK BURGER STOCK IS LESS THAN 10
            }}
        }else{System.out.println("Wrong input.try again");}
    }catch(Exception e){System.out.println("wrong input.try again");}}
    //Remove a customer from a Queue. (From a specific location) PART---------------------------------------------------
    public static void Remove_a_customer_from_a_Queue(){
        try{
        System.out.print("From which cashier should it be removed (1 or 2 or 3):");
        int cashier_remove_no = input.nextInt();

        if(cashier_remove_no==1||cashier_remove_no==2||cashier_remove_no==3){
        System.out.print("cashier1(0,1)  cashier2(0,1,2)  cashier3(0,1,2,3,4)");
        System.out.print("To which station should be removed:");
        int cashier_remove_place = input.nextInt();//-----THE PART GET THE PLACE TO BE REMOVE--------------------------

        if (cashier_remove_no == 1) {
        if(CASHIER1[cashier_remove_place]!=null){
            if(cashier_remove_place==1){
                CASHIER1[1] = null;
                CASH1=CASH1-1;
                BURGERS_STOCK=BURGERS_STOCK+5;
                System.out.println("Customer removed");
            }
            else if(cashier_remove_place==(0)){
                for(int i=cashier_remove_place;i<1;i++){
                    CASHIER1[i]=CASHIER1[i+1];
                } CASHIER1[1] = null;
                CASH1=CASH1-1;
                BURGERS_STOCK=BURGERS_STOCK+5;
                System.out.println("Customer removed");
            }
            else{System.out.print("wrong");}   }
        else{System.out.print("this place is already empty");}}

        else if (cashier_remove_no == 2){
            if(CASHIER2[cashier_remove_place]!=null){
            if(cashier_remove_place==2){CASHIER2[2] = null;               //-----THE PART CUSTOMER REMOVE------------
            CASH2=CASH2-1;
            BURGERS_STOCK=BURGERS_STOCK+5;
            System.out.println("Customer removed");}
            else if(cashier_remove_place==0||cashier_remove_place==1){
                for(int i=cashier_remove_place;i<2;i++){
                    CASHIER2[i]=CASHIER2[i+1];
                } CASHIER2[2] = null;
            CASH2=CASH2-1;
            BURGERS_STOCK=BURGERS_STOCK+5;
            System.out.println("Customer removed");}
            else{System.out.print("wong");}
            }
        else{System.out.print("this place is already empty");}}

        else if (cashier_remove_no == 3) {
            if(CASHIER3[cashier_remove_place]!=null){
            if(cashier_remove_place==4){CASHIER3[4]=null;
            CASH3=CASH3-1;
            BURGERS_STOCK=BURGERS_STOCK+5;
            System.out.println("Customer removed");}
            else if(cashier_remove_place==0||cashier_remove_place==1||cashier_remove_place==2||cashier_remove_place==3){
                for(int i=cashier_remove_place;i<4;i++){
                    CASHIER3[i]=CASHIER3[i+1];
                } CASHIER3[4] = null;
            CASH3=CASH3-1;
            BURGERS_STOCK=BURGERS_STOCK+5;
            System.out.println("Customer removed");}
            else{System.out.print("wrong");}}
            else{System.out.print("this place is already empty");}
        }System.out.println("");}
        else{System.out.println("Wrong input,try again");}
        } catch (Exception e ){System.out.println("The place you entered is incorrect. try again");}
    System.out.print("");}
    //Remove a served customer PART--------------------------------------------------------------------------------------
    public static void Remove_a_served_customer(){
       try{ System.out.print("From which row was served?;(1 or 2 or 3)");
        int served_row=input.nextInt();
        if (served_row==1){
            if(CASHIER1[0]!=null){
                CASH1=CASH1-1;
            for(int i=0;i<1;i++){
                CASHIER1[i]=CASHIER1[i+1];
            }CASHIER1[1]=null;
            System.out.println("removed served customer");}
            else{System.out.print("WRONG input.   first cashier is empty");}
        }
        else if (served_row==2){
            if(CASHIER2[0]!=null){
                CASH2=CASH2-1;
            for(int i=0;i<2;i++){
                CASHIER2[i]=CASHIER2[i+1];
            }CASHIER2[2]=null;
            System.out.println("removed served customer");}
            else{System.out.print("WRONG input.   second cashier is empty");}
        }
        else if (served_row==3){
            if (CASHIER3[0] != null) {
                CASH3=CASH3-1;
            for(int i=0;i<4;i++){
                CASHIER3[i]=CASHIER3[i+1];
            }CASHIER3[4]=null;
            System.out.println("removed served customer");}
            else{System.out.print("WRONG input.   third cashier is empty");}
        }
        else{System.out.print("Wrong input.try again");}
        System.out.println("");}catch (Exception e){System.out.println("Wrong input.try again");}
    }
    //View Customers Sorted in alphabetical order PART------------------------------------------------------------------
    public static void  View_Customers_Sorted_in_alphabetical_order(){
       num1=0;
        for(int i=0;i<2;i++){
            if(CASHIER1[i]!=null){
                ALLCASHIER[i]=CASHIER1[i];
                num1=num1+1;}
            else{
                break;}
        }
        for(int i=0;i<3;i++){
            if(CASHIER2[i]!=null){
                ALLCASHIER[num1]=CASHIER2[i];
                num1=num1+1;}
            else{
                break;}
        }
        for(int i=0;i<5;i++){
            if(CASHIER3[i]!=null){
                ALLCASHIER[num1]=CASHIER3[i];
                num1=num1+1;}
            else{
                break;}
        }
         //----------------------THE ABOVE SECTIONS MERGES THE 3 ARRAYS INTO A SINGLE ARRAY---------------------------
        for(int i=0;i<num1;i++){
            for(int j=i+1;j<num1;j++){
                if(ALLCASHIER[i].compareTo(ALLCASHIER[j])>0){ // THIS PART SINGLE ARRAY SORTED ALPHABETICAL ORDER
                    String temporary=ALLCASHIER[i];
                    ALLCASHIER[i]=ALLCASHIER[j];
                    ALLCASHIER[j]=temporary;}
            }
        }
        for (int i=0;i<num1;i++){
            System.out.println(ALLCASHIER[i]); //PRINT SORTED ARRAY
        }
    }
    //Store Program Data into file PART---------------------------------------------------------------------------------
    public static void  Store_Program_Data_into_file(){
        Store_Program_Data_into_file_OTHER("textfile1.txt",CASH1,CASHIER1,"CASHIER1:");
        Store_Program_Data_into_file_OTHER("textfile2.txt",CASH2,CASHIER2,"CASHIER2:");
        Store_Program_Data_into_file_OTHER("textfile3.txt",CASH3,CASHIER3,"CASHIER3:");
        Store_Program_Data_into_file_OTHER("textfile4.txt",num1,ALLCASHIER,"alphabetical order:");
        //THIS ABOVE PART 4 ARRAYS ADD IN TXT FILE---------------------------------------------------------------------

        try{File file4=new File("textfile5.txt");
        FileWriter textfile5=new FileWriter("textfile5.txt");
        String BS=Integer.toString(BURGERS_STOCK);
        textfile5.write(BS);
        textfile5.write("\n");
        String num1s=Integer.toString(num1);
        textfile5.write(num1s);
        textfile5.write("\n");
        String CASH1S=Integer.toString(CASH1);
        textfile5.write(CASH1S);
        textfile5.write("\n");
        String CASH2S=Integer.toString(CASH2);
        textfile5.write(CASH2S);
        textfile5.write("\n");
        String CASH3S=Integer.toString(CASH3);
        textfile5.write(CASH3S);
        textfile5.close();}
        catch (Exception e){System.out.print(e);}//------------THIS ABOVE PART GLOBAL VARIABLES ADD IN TXT FILE------------------
        System.out.println("program store is successful");
    }
    //Load Program Data from file PART----------------------------------------------------------------------------------
    public static void Load_Program_Data_from_file(){

        Load_Program_Data_from_file_OTHER("textfile1.txt",CASHIER1);
        Load_Program_Data_from_file_OTHER("textfile2.txt",CASHIER2);
        Load_Program_Data_from_file_OTHER("textfile3.txt",CASHIER3);
        Load_Program_Data_from_file_OTHER("textfile4.txt",ALLCASHIER);
        //THIS ABOVE PART 4 ARRAYS LOAD IN TXT FILE-------------------------------------------------------------------------------

        File file8=new File("textfile5.txt");
        try{Scanner reader4=new Scanner(file8);
        int BURGERS_STOCK_PREVIOUS=reader4.nextInt();
        BURGERS_STOCK=BURGERS_STOCK_PREVIOUS;
        int num1_PREVIOUS= reader4.nextInt();
        num1=num1_PREVIOUS;
        int CASH1_PREVIOUS=reader4.nextInt();
        CASH1=CASH1_PREVIOUS;
        int CASH2_PREVIOUS=reader4.nextInt();
        CASH2=CASH2_PREVIOUS;
        int CASH3_PREVIOUS=reader4.nextInt();
        CASH3=CASH3_PREVIOUS;}
        catch (Exception e){System.out.print(e);}//--THIS ABOVE PART textfile5 DATA LOAD IN PROGRAM------
        System.out.println("program load is successful");
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
    // THIS SECTIONS IS USED IN SWITCHES CASE PART.THIS SECTION CONTROLS THE AUTOMATIC DISPLAY OF THE MENU.-------------
    public static void control(){
        System.out.println(" ");
        System.out.print("Enter any key to return to the menu:  ");
        String output=input.next();
        System.out.print("");
    }
    // THIS METHODS USE View_all_Queues PART----------------------------------------------------------------------------
    public static void View_all_Queues_OTHER(String[] CASHIER_EX, int a){
        if (a < CASHIER_EX.length) {
            String customer_cash = CASHIER_EX[a];
            if (customer_cash == null) {
                System.out.print("X");}                               //CHECK CUSTOMERS IN THE QUEUE
            else {
                System.out.print("O");}}
        else {
            System.out.print(" ");}
        System.out.print("\t \t");
    }
    // THIS METHODS USE View all Empty Queues PART----------------------------------------------------------------------
    public static void View_all_Empty_Queues_OTHER(String[]CASHIER_EX,int b){
        if (b < CASHIER_EX.length) {
            String customer_cash1 = CASHIER_EX[b];
            if (customer_cash1 == null) {
                System.out.print("X");}
            else {                                                        //CHECK CUSTOMER NOT IN THE QUEUE
                System.out.print(" ");}}
        else {
            System.out.print(" ");}
        System.out.print("\t \t");
    }
    //THIS METHODS USE IN Store_Program_Data_into_file METHOD------------------------------------------------------------
    public static void  Store_Program_Data_into_file_OTHER(String textfile,int number,String[] CASHIER_EX,String cashierName){
        try{File file=new File(textfile);
            FileWriter textfile1=new FileWriter(textfile);
            textfile1.write(cashierName);
            textfile1.write("\n");
            for(int i=0;i<number;i++){
                textfile1.write(CASHIER_EX[i]);
                textfile1.write("\n");}
            textfile1.close();}
        catch(Exception e){
            System.out.print(e);}
    }

    //THIS METHODS USE IN Load_Program_Data_into_file METHOD------------------------------------------------------------
    public static void Load_Program_Data_from_file_OTHER(String textfile,String[] CASHIER_EX){
         int a=0;
        File file=new File(textfile);
        try{Scanner reader=new Scanner(file);
            while(reader.hasNextLine()){
                a=a+1;
                String txline=reader.nextLine();
                System.out.println(txline);
                if(a>1) {
                    CASHIER_EX[a-2] = txline;
                }
            }System.out.println(" ");
            reader.close();}catch(Exception e){System.out.print(e);}
    }
}
