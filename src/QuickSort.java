public class QuickSort
{
    /* This function takes last element as pivot, places  the pivot element
    at its correct position in sorted  array, and places all smaller (smaller
    than pivot) to left of pivot and all greater elements to right  of pivot */
   static int partition(int arr[], int left, int right)
    {
        int i = left, j = right;

        int tmp;

        int pivot = arr[(left + right) / 2];



        while (i <= j) {

            while (arr[i] < pivot)

                i++;

            while (arr[j] > pivot)

                j--;

            if (i <= j) {

                tmp = arr[i];

                arr[i] = arr[j];

                arr[j] = tmp;

                i++;

                j--;

            }

        };


        return i;

    }
    static void quickSort(int arr[], int low, int high)
    { int index = partition(arr, low, high);

        if (low < index - 1)

            quickSort(arr, low, index - 1);

        if (index < high)

            quickSort(arr, index, high);
    }

    public static void main(String args[])
    {
        int [] input = {119, 160, 390, 947, 604, 251};
        QuickSort.quickSort(input,0,5);

        for (int i:input)
        {
            System.out.print(i + " ");
        }

    }
}