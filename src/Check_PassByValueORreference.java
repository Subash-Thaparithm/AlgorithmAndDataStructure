public class Check_PassByValueORreference
{
    public static void main(String[] args)
    {

        int[] initialCoordinates = { 26, 0 };
        int[] positions = { 1, 2, 3, 4 };
        int[][] coordinates = { { 0, 0 }, { 0, 0 }, { 0, 0 }, { 0, 0 } };
        for (int i = 0; i < 4; i++)
        {
            System.out.println("Initial: " + initialCoordinates[1]);
            coordinates[i] = initialCoordinates;
            coordinates[i][1] += positions[i];
        }


        Sample obj1 = new Sample(5); // new memory created and reference assigned

       Sample obj2 = new Sample(); // new memory created and reference assigned

       Sample obj3 ;

       Sample obj4 = obj1;   // no new memory created and  reference of obj1 assigned
       int out= obj1.doubleA(5); // Both the objects points to the same reference and their values get changed

       Sample obj5 = new Sample(obj2.getA()); // new memory created and reference assigned
       int out1 = obj2.doubleA(10);       // Only obj2 changes

       obj3 = obj5;                 // no new memory created and  reference of obj1 assigned

       Sample duplicateofobj3 =  createDuplicate(obj3);

       System.out.println("End");
    }

    static Sample  createDuplicate(Sample orignal)
    {
        orignal.doubleA(5);
        Sample obj1 = new Sample(orignal.getA()) ;
        return obj1;
    }


}
class Sample
{
private int a;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public Sample(int b)
    {
        this.a = b;
    }
    public Sample()
    {

    }

    public int doubleA(int b)
    {   this.a = 2*b;
        return a;
    }

}
