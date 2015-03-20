public class QuestionThree{
  public static void main(String[] args){
    int[] array1 = {1, 4, 4, 3, 3, 4, 3, 5, 5, 2,2, 1};     //the original given array
  
    // This will make a new array, which consists of the number of occurence of each element from array1    
    int[] array2 = new int[array1.length];  // array2 is the new array
    int occurences = 1;                     // this is to count the number of occurences of each element of array1
    int lowest = array2.length;             // this is the lowest occurence number
    
    // this loop is the most complicated loop and it decides the Big-O to O(n^2) --> see Question3b.pdf
    for (int i = 0; i < array1.length ; i++){
      for (int k = 0; k < array1.length ; k++){
        if (i == k){
          
        }  
        else if (array1[i] == array1[k]){
          occurences++;
        }
        
      }
      array2[i] = occurences;   // array2 will have the occurence number of the corresponding index of array1
                                // for instance, at index 0 of array1, the element is 1. The element 1 comes up twice in the array 1. So, array2[0] is 2.
      occurences = 1;           // this resets the occurence counting 
        
    }
    //finding the lowest occurence number from array2
    for (int z=0; z < ((array2.length)-1) ; z++){
      if (array2[z] < array2[z+1]){
        lowest = array2[z];
      }
      else if (array2[z] > array2[z+1]){
        lowest = array2[z+1];
      }
      
    }
    
    // finding the corresponding element from array1 and priting it as well as the lowest occurence number
    for ( int k=0; k< array2.length-1;k++){
      for (int l=k+1; l< array2.length ;l++){
        if(array1[k]==array1[l]&&array2[k]==lowest){
          array2[l]=array2[l]+1;// try to eliminate the multiple of the least elements
        }
      }
    }
    System.out.print("Elements : ");
    for (int x = 0; x < array2.length ; x++){
      if (array2[x]==lowest){
        System.out.print(array1[x]+ "  ");
      }
    }
    
    System.out.println("");
    System.out.println("number of occurence : " + lowest);  
      
    }
  }
        
  
  
  
  
   
    
