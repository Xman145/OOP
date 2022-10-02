package ru.nsu.a.solovev4;
import java.util.Scanner;
import java.util.Arrays;
public class heapsort
{
    private static void printArray(int arr[])
    {
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i]+" ");
        System.out.println();
    }

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter array length: ");
        int size = input.nextInt();
        int arr[] = new int[size];
        for (int i = 0; i < size; i++)
        {
            arr[i] = input.nextInt();
        }
        sort(arr);
        printArray(arr);
    }
    public static void sort(int[] arr) {

        int n = arr.length;
        for (int i = n/2-1; i >=0; i--)
            heapify(arr,i,n);

        for (int i = n - 1; i >=0; i--)
        {
            int temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;

            heapify(arr,0,i);
        }
    }
    private static void heapify(int[] arr, int i, int n) {
        int leftchild = i * 2 +1;
        int rightchild = i* 2 + 2;
        int largest = i;
        if ( leftchild<n && arr[leftchild] > arr[largest])
            largest = leftchild;
        if (rightchild < n && arr[rightchild] > arr[largest])
            largest = rightchild;

        if (i!=largest){
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;

            heapify(arr,largest,n);
        }

    }
}