package com.example.demo1;
import java.io.Serializable;

public class Customer implements Serializable{

   //CUSTOMER DETAILS VARIABLE------------------------------------------------------------------------------------------
   private String Firstname;
   private String Secondname;
   private int BurgersAmount;

   //GETTERS------------------------------------------------------------------------------------------------------------
   public String getFirstname() {return Firstname;}
   public String getSecondname() {
       return Secondname;
    }
   public int getBurgersAmount() {
        return BurgersAmount;
    }

    //CONSTRUCTORS------------------------------------------------------------------------------------------------------
   public  Customer(int bueger_amount,String firstname,String secoundname){
       this.BurgersAmount=bueger_amount;
       this.Firstname=firstname;
       this.Secondname=secoundname;
   }
    //THIS METHODS USE RETURN CUSTOMER FULL NAME------------------------------------------------------------------------
   public String fullName(){
        String Fullname=Firstname+" "+Secondname;
        String Fullnameup = Fullname.toUpperCase();
        return Fullnameup;
   }
}

