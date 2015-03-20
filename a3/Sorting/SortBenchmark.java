package Sorting;
import java.util.Random;
import java.lang.System;
import Sorting.*;
public class SortBenchmark{
  public static void main(String[] args){
  Random r = new Random();
  
  // for maunal size input to test manually in compiler
  //int size=9500;
  
  //for the command line size input
  int size=(Integer.parseInt(args[0]));
  Integer[] a = new Integer[size];
  
  //setSeed to re-initialize back to the same random array later on
  r.setSeed(a.length);
  for (int i=0; i < a.length; i++){
    // the max and min values of the random number's range were not provided
    // so I didn't the boundary
    Integer g = r.nextInt();
    a[i] = g;
  }
  //quickSort
  long time1;
  long start1=System.currentTimeMillis();
  quickSort.sort(a);
  long end1=System.currentTimeMillis();
  time1= end1-start1;
  
  //setSeed to reinitialize back to the same initial random array
  r.setSeed(a.length);
  for (int i = 0; i <a.length; i++){
    Integer g = r.nextInt();
    a[i] = g;
  }
  //MergeSort
  long time2;
  long start2=System.currentTimeMillis();
  MergeSort.sort(a);
  long end2=System.currentTimeMillis();
  time2 = end1-start1;
  
  //setSeed again to re-initialize
  r.setSeed(a.length);
  for (int i = 0; i < a.length; i++){
    Integer g =r.nextInt();
    a[i] = g;
  }
  //SelectionSort
  long time3;
  long start3=System.currentTimeMillis();
  SelectionSort.sort(a);
  long end3=System.currentTimeMillis();
  time3 = end3-start3;
  
  System.out.println("quickSort took " + time1 + " milliseconds");
  System.out.println("MergeSort took " + time2 + " milliseconds");
  System.out.println("SelectionSort took " + time3 + " milliseconds");
  
  }
}