/*
Illia Shershun
CS202 Winter: Program 5

Class that implements doubly linked list of connections:
When user searches for a flight between 2 airports, different flgiths are input into this list
 */

public class dList
{
    private int max_connections = 0;
    private dNode head;
    private dNode tail;

    /*
    Node for the dlist
     */
    private class dNode
    {
        private dNode next;
        private dNode prev;
        private aList connections;

        dNode(aList connection)
        {
            this.connections = connection;
            this.next = null;
            this.prev = null;
        }
    }

    /*
    Default constructor
     */
    dList()
    {
        head = tail= null;
        max_connections = 0;
    }

    /*
    Function to add another flight option to the list
     */
    public void addOption(aList connections)
    {
        dNode newNode = new dNode(connections);
        dNode curr = head;

        if(head == null)
        {
            newNode.prev = null;
            head = newNode;
        }
        else
        {
            while(curr.next != null)
            {
                curr = curr.next;
            }

            curr.next = newNode;
            newNode.prev = curr;
        }
    }

    /*
    Function to display list: calls display for each alist
     */
    public void display()
    {
        dNode curr = head;

        while(curr != null)
        {
            curr.connections.display();
            curr = curr.next;
        }
    }

    /*
    Function to set max connections
     */
    public void setMax_connections(int max)
    {
        this.max_connections = max;
    }

    /*
    Function to get max connections
     */
    public int getMax_connections()
    {
        return max_connections;
    }
}
