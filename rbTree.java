/*
Illia Shershun
CS202 Winter: Program 5

Class to implement red-black tree. Each node stores name of the home airport, and a list of connections to which this airport flies.
 */

public class rbTree
{
    static final int BLACK = 1;
    static final int RED = 0;
    private final rbNode nil = new rbNode(null, null);
    private rbNode root = nil;
    private int size = 0;

    private class rbNode
    {
        private Airport airport;
        private aList connections;
        private int color;
        private rbNode left;
        private rbNode right;
        private rbNode parent;

        /*
        Default red-black Node constructor
         */
        rbNode(Airport airport_, aList connections_)
        {
            this.airport = airport_;
            this.connections = connections_;
        }
    }

    /*
    Wrapper Function to insert new airport and its list of connection into the tree
     */
    public void insert(Airport airport_, aList connections_)
    {
        rbNode newNode = new rbNode(airport_, connections_);
        newNode.color = RED;

        root = recInsert(root, newNode);

        repair(newNode);
        size++;
    }

    /*
    Helper recursive function to insert data into the tree
     */
    private rbNode recInsert(rbNode root_ref, rbNode newNode)
    {
        if(root_ref == nil || root_ref == null)
        {
            return newNode;
        }

        if(newNode.airport.getName().compareTo(root_ref.airport.getName()) < 0)
        {
            root_ref.left = recInsert(root_ref.left, newNode);
            root_ref.left.parent = root_ref;
        }
        else
        {
            root_ref.right = recInsert(root_ref.right, newNode);
            root_ref.right.parent = root_ref;
        }

        return root_ref;
    }

    /*
    Once node it inserted, the tree might get unbalanced, so this function fixes the tree.
    There are 2 main cases that this function handles: when uncle is RED and when it (he) is BLACK:
    1)When it (uncle) is RED, we just need to recolor the tree "all-the waaay up".
    2)When it(uncle) is BLACK, we need to rotate and than do some recoloring.
     */
    private void repair(rbNode n)
    {
        rbNode p = null;
        rbNode gp = null;

        while(n != root && n.color != BLACK && n.parent.color == RED)
        {
            p = n.parent;
            gp = n.parent.parent;

            /*
            Node is on the left side from its grandparent
            */
            if(p == gp.left)
            {
                rbNode uncle = gp.right;

                /*
                Uncle is red
                 */
                if(uncle != null && uncle.color == RED)
                {
                    gp.color = RED;
                    p.color = BLACK;
                    uncle.color = BLACK;
                    n = gp;
                }
                else
                {
                    /*
                    Left-right case -> need to do left rotation first
                     */
                    if(n == p.right)
                    {
                        rotateLeft(p);
                        n = p;
                        p = p.parent;
                    }

                    /*
                    Left case -> need to do right rotation
                     */
                    rotateRight(gp);
                    int np = gp.color;
                    int ngp = p.color;
                    p.color = np;
                    gp.color = ngp;
                    n = p;
                }
            }
            /*
            Node is on the right side from its grandparent
             */
            else
            {
                rbNode uncle = gp.left;

                /*
                Uncle is red
                 */
                if(uncle != null && uncle.color == RED)
                {
                    gp.color = RED;
                    p.color = BLACK;
                    uncle.color = BLACK;
                    n = gp;
                }
                else
                {
                    /*
                    Right-left case -> need to do right rotation first
                     */
                    if(n == p.left)
                    {
                        rotateRight(p);
                        n = p;
                        p = n.parent;
                    }

                    /*
                    Right case -> need to rotate left
                     */
                    rotateLeft(gp);
                    int np = gp.color;
                    int ngp = p.color;
                    p.color = np;
                    gp.color = ngp;
                    n = p;
                }
            }
        }

        root.color = BLACK;
    }

    /*
    Helper function to rotate left
     */
    private void rotateLeft(rbNode n)
    {
        rbNode right = n.right;
        n.right = right.left;

        if(n.right != null)
        {
            n.right.parent = n;
        }

        right.parent = n.parent;

        if(n.parent == null)
        {
            root = right;
        }
        else if(n == right.parent.left)
        {
            n.parent.left = right;
        }
        else
        {
            n.parent.right = right;
        }
        right.left = n;
        n.parent = right;
    }

    /*
    Helper function to rotate right
     */
    private void rotateRight(rbNode n)
    {
        rbNode left = n.left;
        n.left = left.right;

        if(n.left != null)
        {
            n.left.parent = n;
        }

        left.parent = n.parent;

        if(n.parent == null)
        {
            root = left;
        }
        else if(n == n.parent.left)
        {
            n.parent.left = left;
        }
        else
        {
            n.parent.right = left;
        }

        left.right = n;
        n.parent = left;
    }

    /*
    Function that displays the tree: calls private function
     */
    public void display()
    {
        display(root);
    }

    /*
    Helper function to display the tree
     */
    private void display(rbNode root)
    {
        if(root != null)
        {
            display(root.left);
            System.out.print("(");
            charColor(root);
            System.out.print(")");
            root.connections.display();
            display(root.right);
        }
    }

    /*
    Helper function to display the color of the node instead of 0-1
     */
    private void charColor(rbNode current)
    {
        if(current.color == BLACK)
        {
            System.out.print("B");
        }
        else
            System.out.print("R");
    }

    /*
    Function to display tree in tree like structure
     */
    public void displayCool()
    {
        displayCool(root, 0);
    }

    /*
    Helper function for displayCool
     */
    private void displayCool(rbNode head, int space)
    {
        if(head != null)
        {
            displayCool(head.left, space+6);
            System.out.println(head.airport.getName());
            displayCool(head.right, space+6);
        }
    }

    /*
    Function to get size of the tree
     */
    public int getSize()
    {
        return size;
    }

    /*
    Function to check if tree is empty
     */
    public boolean isEmpty()
    {
        return root.right == null;
    }

    public boolean checkExistance(String airport)
    {
        rbNode root_ref = root;
        return checkExistance(root_ref, airport);
    }

    /*
    Helper function to check if airports exist in the system
     */
    private boolean checkExistance(rbNode root_ref, String airport)
    {
        if(root_ref == null)
            return false;

        String temp = root_ref.airport.getName();
        int big_smal = temp.compareTo(airport);

        if(big_smal == 0)
        {
            return true;
        }
        else if(big_smal < 0)
        {
            return checkExistance(root_ref.right, airport);
        }
        else
            return checkExistance(root_ref.left, airport);
    }

    /*
    Wrapper function for finding connections
     */
    public void checkConnection(dList cons, String src, String dest)
    {
        rbNode root_ref = root;
        //privConnections(root_ref, cons, src, dest);
        getConections(root_ref, cons, src, dest);
    }

    /*
    Function to get flights. If there's no direct flgiths available - calls helper function
     */
    private void getConections(rbNode root_ref, dList dL, String src, String dst)
    {
        rbNode source = findNode(root_ref, src);

        if(checkDirect(source, dst))
        {
            aList tL = new aList();
            tL.addAirport(source.airport);
            Airport destAir = source.connections.contains((dst));
            tL.addAirport(destAir);
            dL.addOption(tL);
        }

        else
        {
            String conNames = source.connections.getNames();
            String[] details = conNames.split(", ");
            boolean found = false;

            for (int i = 1; i < details.length; i++) {
                aList tC = new aList();
                tC.addAirport(source.airport);
                rbNode tN = findNode(root_ref, details[i]);
                helper(root_ref, dL, dst, tC, tN, 0, src, found);
            }
        }
    }

    /*
    Helper function to find flights from one city to another
     */
    private void helper(rbNode root_ref, dList dL, String dst, aList tC, rbNode tN, int layovers, String src, boolean found)
    {
        tC.addAirport(tN.airport);
        if(tC.exists(dst))
        {
            dL.addOption(tC);
            //found = true;
            //we're done
        }
        else if(checkDirect(tN, dst) && layovers < 2 && !found)
        {
            Airport tA = tN.connections.contains(dst);
            tC.addAirport(tA);
            dL.addOption(tC);
            found = true;
        }
        else
        {
            if(layovers >= 2 || !found)
            {
                //no searches anymore
            }
            else
            {
                String cN = tN.connections.getNames();
                String[] details = cN.split(", ");

                for (int i = 1; i < details.length; i++)
                {
                    if (tC.exists(details[i]) || details[i].equals(src))
                    {
                        //do nothing
                    }

                    else
                    {
                        if(tC.lastElement().equals(dst))
                        {
                            tC.removeLast();
                        }
                        rbNode tempNode = findNode(root_ref, details[i]);
                        helper(root_ref, dL, dst, tC, tempNode, layovers + 1, src, found);
                    }
                }
            }
        }
    }

    /*
    Funtion to check if direct flights exists between two cities
     */
    private boolean checkDirect(rbNode src, String dest)
    {
        return src.connections.exists(dest);
    }

    /*
    helper function that returns node from the tree with asked airport
     */
    private rbNode findNode(rbNode root_ref, String nodeName)
    {
        String temp = root_ref.airport.getName();
        int big_smal = temp.compareTo(nodeName);

        if(big_smal == 0)
        {
            return root_ref;
        }
        else if(big_smal < 0)
        {
            return findNode(root_ref.right, nodeName);
        }
        else
            return findNode(root_ref.left, nodeName);
    }
}
