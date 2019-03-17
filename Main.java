/*
Illia Shershun

CS202 Winter: Program 5

Main class - to interact with the user and initialize everything needed to run the program
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main
{
    private static Scanner input = new Scanner(System.in);
    public static void main(String[] args)  throws FileNotFoundException
    {
        Main main = new Main();
        rbTree tree = new rbTree();
        dList cons = new dList();
        main.readData(tree);

        main.readUser(tree, cons);
        cons.display();
    }

    /*
    Function to read data from external data file and "create" initial tree
    */
    private void readData(rbTree tree) throws FileNotFoundException
    {
        String fileName = new String();
        //System.out.println("Please enter pathway to the data file: ");
        //fileName = input.nextLine();
        fileName = "src/Connections.txt";

        Scanner read = new Scanner(new File(fileName));
        //read.useDelimiter(",");

        while(read.hasNextLine())
        {
            aList list = new aList();
            String line = read.nextLine();
            String[] details = line.split(", ");
            for(int i = 0; i < details.length; i = i+2)
            {
                String currName = details[i];
                int currFlights = Integer.parseInt(details[i+1]);
                Airport airport_ = new Airport(currName, currFlights);
                list.addAirport(airport_);
            }
            String homeName = details[0];
            int homeFlights = 0;
            Airport home = new Airport(homeName, homeFlights);
            tree.insert(home, list);
        }
        read.close();
    }

    /*
    Function to read used input
     */
    private void readUser(rbTree tree, dList cons)
    {
        String source = new String();
        String dest = new String();

        System.out.print("Please enter where you're travelling from: ");
        source = input.nextLine();
        System.out.print("Please enter where you are headed: ");
        dest = input.nextLine();

        if(tree.checkExistance(source) && tree.checkExistance(dest))
        {
            //System.out.println("We will find connections!");
            tree.checkConnection(cons, source, dest);
        }
        else
        {
            System.out.println("Sorry, such airports don't exist in our database. Most likely you mispseled name of the airport.");
        }
    }
}
