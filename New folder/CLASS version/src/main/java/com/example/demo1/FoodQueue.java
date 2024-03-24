package com.example.demo1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;



public class FoodQueue implements Serializable {
    private  int Size;                                              //QUEUE DETAILS
    private ArrayList<Customer>queue=new ArrayList<>(Size);         //QUEUE
    public FoodQueue(int size) {
        this.Size = size;
    }                //CONSTRUCTORS
    public int getSize() {
        return queue.size();
    }                   //GETTER


   //CHECK QUEUE AND DISPLAY EMPTY PLACE AND NONEMPTY PLACE  ↓-----------------------------------------------------------
   public void checkPlace(int a){
       if (a < this.Size) {
           if(a<queue.size()){
               Customer customer_cash = queue.get(a);
               if (customer_cash != null) {
                   System.out.print("0");}
           }else{
               System.out.print("x");}
           }
       else {
           System.out.print(" ");}
       System.out.print("\t \t");
   }
    //CHECK QUEUE AND DISPLAY EMPTY PLACE  ↓----------------------------------------------------------------------------
    public void emptyPlace(int a){
        if (a < this.Size) {
            if(a<queue.size()){
                Customer customer_cash = queue.get(a);
                if (customer_cash != null) {
                    System.out.print(" ");}
            }else{
                System.out.print("x");}
        } else {
            System.out.print(" ");}
        System.out.print("\t \t");
    }
    //CUSTOMER ADD QUEUE  ↓---------------------------------------------------------------------------------------------
   public void addCustomer(Customer customer){
        queue.add(customer);
   }
    //CUSTOMER REMOVE QUEUE  ↓------------------------------------------------------------------------------------------
    public void removeCustomer(int a){
        if(queue.get(a)!=null){
            queue.remove(a);
            }
        else {
            System.out.println("This place is already empty");
        }
    }
    //SERVED CUSTOMER REMOVE QUEUE  ↓-----------------------------------------------------------------------------------
    public void removeServedCustomer(){
        if(queue.get(0)!=null){
            queue.remove(0);
         }
        else{System.out.print("WRONG input.This cashier is already empty");}
    }
    //RETURN CUSTOMER FULL NAME   ↓-------------------------------------------------------------------------------------
    public String addNewArray(int a) {
          String fname= (queue.get(a).fullName());
          return fname;}
    //STORE CUSTOMER TEXT FILE  ↓---------------------------------------------------------------------------------------
    public void addTextfile(String textfile){
        try{File file=new File(textfile);
            FileOutputStream filein=new FileOutputStream(file);
            ObjectOutputStream objectin=new ObjectOutputStream(filein);

            Iterator it =queue.iterator();
            for(int i=0;i< queue.size();i++){
                Customer customer1=(Customer) it.next();
                objectin.writeObject(customer1);
            }filein.close();objectin.close();
            }
        catch(Exception e){
        System.out.print(e);}
    }
    //LOAD CUSTOMER TEXT FILE   ↓---------------------------------------------------------------------------------------
    public void reedTextfile(String textfile) {
        try{FileInputStream fileout=new FileInputStream(textfile);
            ObjectInputStream objectout=new ObjectInputStream(fileout);
            int i=0;
            while(true){
            try{Customer customer1=(Customer) objectout.readObject();
            queue.add(customer1);
            i++;}
        catch (Exception e){
            break;}}
        }catch (Exception e){}
    }
    //RETURN  FIRST CUSTOMER'S BURGERS AMOUNT   ↓-----------------------------------------------------------------------
    public int burgerAmount(){
        int c=queue.get(0).getBurgersAmount();
        return c;}
    //CLEAR QUEUE   ↓---------------------------------------------------------------------------------------------------
    public void clearArray(){
        queue.clear();
    }

    public String returnFName(int a){return queue.get(a).getFirstname();}                //RETURN CUSTOMER FIRST NAME
    public String returnSName(int a){
        return queue.get(a).getSecondname();
    }           //RETURN CUSTOMER SECOND NAME
    public int returnBurger(int a){return queue.get(a).getBurgersAmount();}               //RETURN CUSTOMER'S BURGERS AMOUNT

}




