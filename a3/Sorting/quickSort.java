package Sorting;

import java.lang.*;

public class quickSort{
  public static <T extends Comparable<T>> void sort(T[] a){
    quickSort(a, 0, a.length-1);
  }
  
  private static <T extends Comparable<T>> void quickSort(T[] a, int p, int r){
    if (p < r){
      int q = partition(a, p, r);
      quickSort(a,p,q);
      quickSort(a,q+1,r);
    }
    
  }
  private static <T extends Comparable<T>> int partition(T[] a,int p,int r){
    //x is the pivot value to be used
    // the median returns the index --> a[median]
    T x=a[median(a, p, (r+p)/2 ,r)];
    
    //initial indices
    int i=p-1;
    int j=r+1;
    while (i < j){
      i++;
      while (a[i].compareTo(x)<0&&i<r){
        i++;
      }
      j--;
      while (a[j].compareTo(x)>0&&j>p){
        j--;
      }
      if (i < j){
        swap(a, i, j);
      }
    }
    return j;
  }
  
  // returns the index of the median element of first, middle last elements
  private static <T extends Comparable<T>> int median(T[] a, int p, int d, int r){
    if (a[p].compareTo(a[d])==1||a[p].compareTo(a[d])==0){
      if (a[d].compareTo(a[r])==1||(a[d].compareTo(a[r])==0)){
        return d;
      }
      else if (a[p].compareTo(a[r])==1||a[p].compareTo(a[r])==0){
        return r;
      }
      else {
        return p;
      }
    }
    else {
      if (a[p].compareTo(a[r])==1||a[p].compareTo(a[r])==0){
        return p;
      }
      else if (a[d].compareTo(a[r])==1||a[d].compareTo(a[r])==0){
        return r;
      }
      else{
        return d;
      }
    }
    
  }
  //swap from SelectionSort of Prof.Doina
  private static <T extends Comparable<T>> void swap(T[] a, int i, int j) {
    T tmp = a[i];
    a[i] = a[j];
    a[j] = tmp;
  }
  public static void main(String[] args){
    Integer[] a = new Integer[5];
    a[0] = new Integer(5);
    a[1] = new Integer(4);
    a[2] = new Integer(1);
    a[3] = new Integer(3);  
    a[4] = new Integer(7);
       
    quickSort.sort(a);
    // Print the result
    for (int i = 0; i < a.length; i++){
      System.out.println(a[i].toString());
    }
    
  }
}