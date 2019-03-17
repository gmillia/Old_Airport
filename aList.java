/*
Illia Shershun

CS202: Winter: Program 5

Liner linked list of airports - used to store connections that each airport has: i.e. Portland, Seattle, LA etc
 */

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class aList
{
    private aNode head;
    private int size = 0;


    private class aNode extends Airport
    {
        private aNode next;
        aNode(Airport airport_)
        {
            this.setAirportName(airport_.getName());
            this.setFlights(airport_.getFlights());
        }
    }
    /*
    Default constructor
     */
    aList()
    {
        head = null;
        size = 0;
    }

    /*
    Function to add airport object to the list
     */
    public void addAirport(Airport airport_)
    {
        aNode newNode = new aNode(airport_);
        aNode curr = head;
        if(head == null)
        {
            head = newNode;
        }
        else
        {
            while(curr.next != null)
            {
                curr = curr.next;
            }

            curr.next = newNode;
            size++;
        }
    }

    /*
    Function to display the list
     */
    public void display()
    {
        aNode curr = head;

        System.out.print("[Home] ");
        while(curr != null)
        {
            double rndf = Math.round((curr.getFlights()/24.0) * 100.0) / 100.0;
            //System.out.print(curr.getName() + "(" + Math.round(curr.getFlights()/24.00) + ")" );
            System.out.print(curr.getName() + "(" + rndf + ")" );
            if(curr.next != null)
            {
                System.out.print(" -> ");
            }
            curr = curr.next;
        }
        System.out.println();
    }

    /*
    Wrapper Function to check if airport with given name exists in the list
     */
    public boolean exists(String airport)
    {
        aNode head_ref = head;
        return exists(head_ref, airport);

    }

    /*
    Helper function to check if airport with given name exists on the list
     */
    private boolean exists(aNode head_ref, String airport)
    {
        if(head_ref == null)
            return false;
        else if(head_ref.getName().compareTo(airport) == 0)
        {
            return true;
        }
        else
            return exists(head_ref.next, airport);
    }

    /*
    Function to return airport with given name (it's been checked that it's on the list)
     */
    public Airport contains(String airport)
    {
        aNode curr = head;
        Airport toReturn = new Airport();

        while(curr != null)
        {
            if(curr.getName().compareTo(airport) == 0)
            {
                toReturn = curr;
                break;
            }
            curr = curr.next;
        }

        return toReturn;
    }

    /*
    Function to get size of the list
     */
    public int getSize()
    {
        return this.size;
    }

    public String getNames()
    {
        aNode curr = head;
        String toReturn = new String();

        while(curr != null)
        {
            toReturn = toReturn + curr.getName() + ", ";
            curr = curr.next;
        }
        return toReturn;
    }

    /*
    Wrapper function to check what is the last element in the list (since I didn't implement tail)
     */
    public String lastElement()
    {
        aNode curr = head;
        while(curr.next != null)
        {
            curr = curr.next;
        }
        return curr.getName();
    }

    /*
    Function to remove last element on the list - used when compiling a list of flights
     */
    public void removeLast()
    {
        aNode curr = head;
        while(curr.next.next != null)
        {
            curr = curr.next;
        }
        curr.next = null;

    }
}
