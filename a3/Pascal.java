public class Pascal{
  public static void main(String[] args){
    // printing the Pascal's triangle shown in the assignment 3 pdf
    // from row 0 to 5
    for (int i=0;i<=5;i++){
          for (int j=0;j<=i;j++){
              System.out.print(pascalTriangle(i,j)+" ");
          }
          //changing line
          System.out.println();
    }
  }
  // the method pascalTriangle
  public static int pascalTriangle(int m, int n){
    if (m <=1){
      return 1;
    }
    else if (n == 0){
      return 1;
    }
    else if (m == n){
      return 1;
    }
    else{
      return pascalTriangle(m-1, n-1)+pascalTriangle(m-1, n);
    }
  }
}