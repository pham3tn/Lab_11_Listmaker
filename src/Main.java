import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    private static String menu =
            "Please choose one of the command options below:\n" +
            "A – Add an item to the list\n" +
            "D – Delete an item from the list\n" +
            "I – Insert an item into the list\n" +
            "P – Print the list\n" +
            "Q – Quit the program\n";

    private static ArrayList<String> myArrList = new ArrayList<>();

    private static Scanner in = new Scanner (System.in);

    public static void main(){

        String userVal = "";
        int userChoice = 0;
        boolean done = false;
        IO.println("Welcome to the list maker!");
        IO.println(menu);
        do{
            userVal = SafeInput.getRegExString(in,"Choose your command [AaDdIiPpQq]","^[AaDdIiPpQq]$");
            switch(userVal.toLowerCase()) {

                case "a":
                    addItem(in,userVal);
                    display(myArrList);
                    break;

                case "d":
                    deleteItem(in,userChoice);
                    break;

                case "i":
                    insertItem(in,userChoice,userVal);
                    display(myArrList);
                    break;

                case "p":
                    printList(myArrList);
                    break;

                case "q":
                    done = quit();
                    break;
                }
        }while (!done);
    }

    private static void display(ArrayList<String>myArrList){
        IO.println("Your list:");
        for(int i =0; i<myArrList.size();i++){
            IO.println((i+1)+" "+myArrList.get(i));
        }
        IO.println(menu);
    }

    private static void addItem(Scanner in, String userVal){
        userVal = SafeInput.getNonZeroLenString(in, "Enter your item");
        myArrList.add(userVal);
    }

    private static void deleteItem(Scanner in, int userChoice){
        if (myArrList.size() > 0) {
            userChoice = SafeInput.getRangedInt(in, "Please specify the item number you want to delete", 1, myArrList.size());
            myArrList.remove(userChoice - 1);
            display(myArrList);
        } else
            IO.println("You have no items to delete!");
    }

    private static void insertItem(Scanner in, int userChoice, String userVal) {
        userChoice = SafeInput.getRangedInt(in, "Where do you want to insert the item?", 1, myArrList.size()+1);
        userVal = SafeInput.getNonZeroLenString(in, "Enter your item");
        myArrList.add(userChoice - 1, userVal);
    }

    private static void printList(ArrayList<String>myArrList) {
        IO.println(myArrList);
    }

    private static boolean quit (){
        return SafeInput.getYNConfirm(in, "Are you sure?");
    }
}
