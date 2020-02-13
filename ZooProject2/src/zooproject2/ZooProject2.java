/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zooproject2;
//Buffered reader to read from animal and habitat file
import java.io.BufferedReader;
//import to read file
import java.io.FileReader;
//throws exception for errors in file
import java.io.IOException;
//import for hash map and map
import java.util.HashMap;

import java.util.Map;
//import for scanner
import javax.swing.ImageIcon;
//import for dialog box
import javax.swing.JOptionPane;

public class ZooProject2 {

    //main class
   public static void main(String[] args) {
   
       //while loop for main menu print
       while(true){
         
        //Joption pane graphic diplays menu to user
        String option = JOptionPane.showInputDialog("Hello Welcome to the Zoo Monitoring Application\n"
               + "Enter (1) to Monitor an Animal\n"
               + "Enter (2) to Monitor a Habitat\n"
               + "Enter (3) to Exit");       
       
       try{
           //assigning the value to choice 1 to pass to switch statement
           int choice1 = Integer.parseInt(option);
    
          
       //switch setup for the different choices the user can enter and where it will go
        switch(choice1){

           case 1:

               monitorAnimal();

               break;

           case 2:

               monitorHabitat();

               break;

           case 3:
               
               //Confirmation Dialog box for exiting
                int a = JOptionPane.showConfirmDialog(null, "Are you sure you want to Exit?");
                if(a==JOptionPane.YES_OPTION){
                }
                String bye ="GoodBye :)";
                ImageIcon BYE = new ImageIcon("goodbye.jpeg");
                JOptionPane.showMessageDialog(null,bye,"Bye",JOptionPane.INFORMATION_MESSAGE,BYE);
                System.exit(0);
               
         
           default:
               
               //Error Handling if a number other than the options is entered
               String error ="You must enter 1 for Animals, 2 for Habitats, or 3 to Exit";
               JOptionPane.showMessageDialog(null,error,"Alert",JOptionPane.WARNING_MESSAGE);

           }
       
       }
       //Error Handling if a character is entered other than an Int. Had trouble with this as I was trying InputMisMatch exception first
       catch (NumberFormatException exception){
           String error1 ="Please Enter a Number for your Selection. Characters are not Allowed";
           JOptionPane.showMessageDialog(null,error1,"Alert",JOptionPane.WARNING_MESSAGE);
       }
   }

}
//monitoranimal formating 
   public static void monitorAnimal(){

       int choice;

       BufferedReader br = null;

       int count=0;
       
        //creating hashmap <key, value>
       Map<Integer,String> animals=new HashMap<Integer,String>();

            // String animal1 = JOptionPane.showInputDialog("****Animals****");
            //setup try and catch to see if input from file contains details if it does add a count and "details on" plus animal
       try {

           String sCurrentLine;

           br = new BufferedReader(new FileReader("animals.txt"));
           
            //while loop to print out details on animal
           JOptionPane.showMessageDialog(null,"****Animals****");
           
           while ((sCurrentLine = br.readLine()) != null) {
          
               if(sCurrentLine.startsWith("Details")){

                String animal=sCurrentLine.split(" ")[2];
                JOptionPane.showMessageDialog(null,++count+". Details on " +animal);

                animals.put(count, animal);

               }

           }
           //close the buffered reader
           br.close();
           
            //grab input from animal file 
           br = new BufferedReader(new FileReader("animals.txt"));
           
           //Asking for input from user to see which animal they want to get info on and intialize to choice 
           String option2 = JOptionPane.showInputDialog("Enter choice : ");

           int choice1 = Integer.parseInt(option2);

           String selectedAnimal=animals.get(choice1);

           if(selectedAnimal!=null){

               while ((sCurrentLine = br.readLine()) != null) {

               if(sCurrentLine.startsWith("Animal")&&(sCurrentLine.split(" ")[2].equalsIgnoreCase(selectedAnimal)||selectedAnimal.startsWith(sCurrentLine.split(" ")[2].toLowerCase()))){
               
               //for loop
               for(int i=0;i<5;i++){
                   
               //Dialog box to diplay information if input has *
               if(sCurrentLine.startsWith("*")){
                  System.err.println(sCurrentLine.substring(5));
                  JOptionPane.showMessageDialog(null, sCurrentLine.substring(5), "WARNING" , JOptionPane.WARNING_MESSAGE);}
               
               
               else  
                  JOptionPane.showMessageDialog(null,sCurrentLine);
                  sCurrentLine=br.readLine();
                  
                       }

                   }

               }  

           }
        //catch statement and thrown execption
       } catch (IOException e) {
       } finally {

           try {

           if (br != null)br.close();
            //catch statement and thrown execption
           } catch (IOException ex) {
           }

       }

   }

  
   //monitor habitat formating
   public static void monitorHabitat(){

       int choice;

       BufferedReader br = null;

       int count=0;
       //creating hashmap <key, value>
       Map<Integer,String> habitats=new HashMap<Integer,String>();

       JOptionPane.showMessageDialog(null,"****Habitats****");
       
       //setup try and catch to see if input from file contains details if it does add a count and "details on" plus habitat
       try {

           String sCurrentLine;
           
           
           //grab input from animal file
           br = new BufferedReader(new FileReader("habitats.txt"));
          
          //while loop to print out details on habitat
          
          while ((sCurrentLine = br.readLine()) != null) {

               if(sCurrentLine.startsWith("Details")){

                   String habitat=sCurrentLine.split(" ")[2];

                    JOptionPane.showMessageDialog(null,++count+". Details on " +habitat);

                   habitats.put(count, habitat);

               }

           }
           
           //close the buffered reader
           br.close();
           
           //Asking for input from user to see which animal they want to get info on and intialize to choice
           br = new BufferedReader(new FileReader("habitats.txt"));

          String option2 = JOptionPane.showInputDialog("Enter choice : ");

          int choice1 = Integer.parseInt(option2);

          String selectedAnimal=habitats.get(choice1);

           if(selectedAnimal!=null){

               while ((sCurrentLine = br.readLine()) != null) {

               if(sCurrentLine.startsWith("Habitat")&&(sCurrentLine.split(" ")[2].equalsIgnoreCase(selectedAnimal)||selectedAnimal.startsWith(sCurrentLine.split(" ")[2].toLowerCase()))){
               
               //for loop to see if one has *
               for(int i=0;i<4;i++){
               
               //Dialog box to diplay information if input has *
               if(sCurrentLine.startsWith("*")){

                   System.err.println(sCurrentLine.substring(5));
                   JOptionPane.showMessageDialog(null, sCurrentLine.substring(5), "WARNING" , JOptionPane.WARNING_MESSAGE);}

                   else
                       JOptionPane.showMessageDialog(null,sCurrentLine);

                        sCurrentLine=br.readLine();

                       }

                   }

               }  

           }
        //catch statement and thrown execption
       } catch (IOException e) {
       } finally {

           try {

           if (br != null)br.close();
            //catch statement and thrown execption
           } catch (IOException ex) {
           }

       }

   }

}


  
    

