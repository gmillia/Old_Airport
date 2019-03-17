/*
Illia Shershun
CS202 Winter: Program 5

Class to represent each airport
 */

public class Airport
{
    private String name;
    private double flights;
    private double flightTime;

    /*
    Default constructor
     */
    Airport()
    {
        this.name = null;
        this.flights = 0;
        this.flightTime = 0;
    }

    /*
    Constructor with parameters
     */
    Airport(String newName, double newFlights)

    {
        setAirportName(newName);
        setFlights(newFlights);
        setFlightTime(newFlights);
    }

    /*
    Function to set airport name
     */
    public void setAirportName(String newName)
    {
        this.name = newName;
    }

    /*
    Function to set flight frequency number
     */
    public void setFlights(double newFlights)
    {
        this.flights = newFlights;
    }

    /*
    Function to set flight frequency time
     */
    public void setFlightTime(double newFlights)
    {
        if(newFlights != 0)
        {
            this.flightTime = 24.0/newFlights;
        }
    }

    /*
    Function to get airports name
     */
    public String getName()
    {
        return this.name;
    }

    /*
    Function to get flights
     */
    public double getFlights()
    {
        return this.flights;
    }
}
