package Sorting;

import java.util.*;

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
    public static void quickSort(int arr[], int low, int high)
    { int pivot = partition(arr, low, high);

        if (low < pivot - 1)//If there are more than one elements before pivot, sort again. Else, already sorted.

            quickSort(arr, low, pivot - 1);

        if (pivot < high) //If pivot is the last element, no need to sort after pivot

            quickSort(arr, pivot, high);
    }
    /*  public static void main(String args[])
    {
        int [] input = {119, 160, 390, 947, 604, 251};
        QuickSort.quickSort(input,0,5);

        for (int i:input)
        {
            System.out.print(i + " ");
        }

    }*/

    public <T> List<T> union(List<T> list1, List<T> list2) {
        Set<T> set = new HashSet<T>();

        set.addAll(list1);
        set.addAll(list2);

        return new ArrayList<T>(set);
    }

    public void mergeSort(int[] list){

    }
}