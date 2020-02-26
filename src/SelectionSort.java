public class SelectionSort
{
    public void sort(int[] arr)
    {
        int n = arr.length;
        for (int i = 0; i < n; i++)
        {
            for (int j = 1; j < n; j++)
            {
               if(arr[i] < arr[j]) swap(arr[i], arr[j]);
            }


        }
    }

    private void swap(int a, int b)
    {
        int temp = b;
        a = b;
        b = temp;
    }

    public static void main()
    {

    }
}
