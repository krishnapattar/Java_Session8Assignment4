
package Java_Assignment84;
/*
 * Write a Program to reserve tickets in an online bus reservation system using
synchronization.
 */

public class Java_Assignment8_4 
{

	public static void main(String[] args) 
	{
		Reservation reserve = new Reservation(); 	        
		Person thread1 = new Person(reserve, 5,"Kiran"); 
		thread1.start();
		Person thread2 = new Person(reserve, 4,"Dipti");
		thread2.start();
		Person thread3 = new Person(reserve, 2,"Krushna");
		thread3.start();
	}

}
class Reservation //Reservation class
{

    static int availableSeats = 10;

    synchronized void reserveSeat(int requestedSeats,String pname) 
    {
        System.out.println(Thread.currentThread().getName() + " entered.");
        System.out.println("Availableseats : " + availableSeats + " \nRequestedsetas : " + requestedSeats+"\nPerson Name : " +pname);
        if (availableSeats >= requestedSeats)
        {
            System.out.println("Seat Available. Reserve now :-)");
            try
            {
                Thread.sleep(100);     
            }
            catch (InterruptedException e)
            {
                System.out.println("Thread interrupted");
            }
            System.out.println(requestedSeats + " seats reserved.");
            availableSeats = availableSeats - requestedSeats;
        }
        else
        {
            System.out.println("Requested seats not available :-(");
        }
        System.out.println(Thread.currentThread().getName() + " leaving.");
        System.out.println("----------------------------------------------");
    }
}

//Person class inherited from thead interface
class Person extends Thread
{

    Reservation reserve;
    int requestedSeats;
    String strname;

    //Constructor 
    public Person(Reservation reserve, int requestedSeats,String strn)
    {
        this.reserve = reserve;
        this.requestedSeats = requestedSeats;
        this.strname=strn;
    }

  
    public void run() // Overriding run method
    {
        reserve.reserveSeat(requestedSeats,strname);
    }
}