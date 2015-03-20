public class Recursion{
  
  public static void main (String[] args){
    System.out.println(countBinaryStrings(Integer.parseInt(args[0])));
  }
  // the outcome of countBinaryStrings simply correspond to the Fibonacci sequence numbers, but not starting at 1
  // It is because the total number of the combinaisions of binary strings that dont have two consecutive 0's is
  // the sum of the two previous numbers of the combinasions, which is exactly the case of the Fibonacci sequence nubmers
  // The sequence wont start at 1 because when the n is 1, there are already 2 possibilities of such binary strings (01 and 10)
  // So, the sequence will go 2,3,5,8 .. etc
  public static int countBinaryStrings(int n){
    // n < 0 is not possible because a negative number can't be a length size
    
    if (n==0){    // in case the user puts 0 as the int parameter : there is no binary strings, so there wont be any binary strings that dont have two consecutive 0's
      return 0;
    }
    if (n==1){
      return 2;
    }
    if (n==2){
      return 3;
    }
    else{
    return (countBinaryStrings(n-1)+countBinaryStrings(n-2));
    }
  }
}
