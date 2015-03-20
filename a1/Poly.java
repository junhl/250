public class Poly {
  
  double[] coefficients;
  
  // this is Poly class written in class (until where it says private static int min).
  // The added methods for the question 4 are found below (I commented at where it starts)
  
  public Poly() {
    coefficients = new double[1];
    coefficients[0] = 0;
  }
  
  public Poly(int degree) {
    coefficients = new double[degree+1];
    for (int i = 0; i <= degree; i++)
      coefficients[i] = 0;
  }
  
  
  public Poly(double[] a) {
    coefficients = new double[a.length];
    for (int i = 0; i < a.length; i++)
      coefficients[i] = a[i];
  }
  
  public int getDegree() {
    return coefficients.length-1;
  }
  
  public double getCoefficient(int i) throws Exception{
    if (i >= coefficients.length)
      throw new Exception("Bad Poly");
    return coefficients[i];
  }
  
  public void setCoefficient(int i, double value) {
    coefficients[i] = value;
  }
  
  public Poly add(Poly p) {
    int n = getDegree();
    int m = p.getDegree();
    Poly result = new Poly(Poly.max(n, m));
    int i;
    
    try{
      for (i = 0; i <= Poly.min(n, m); i++) 
        result.setCoefficient(i, coefficients[i] + p.getCoefficient(i));
      if (i <= n) {
        //we have to copy the remaining coefficients from this object
        for ( ; i <= n; i++) 
          result.setCoefficient(i, coefficients[i]);
      } else {
        // we have to copy the remaining coefficients from p
        for ( ; i <= m; i++) 
          result.setCoefficient(i, p.getCoefficient(i));
      }
    } catch (Exception e) {
    }
    return result;
  }
  
  public void displayPoly () {
    for (int i=0; i < coefficients.length; i++)
      System.out.print(" "+coefficients[i]+"*X^"+i);
    System.out.println();
  }
   
  private static int max (int n, int m) {
    if (n > m)
      return n;
    return m;
  }
  
  private static int min (int n, int m) {
    if (n > m)
      return m;
    return n;
  }
    
  //the question 4 starts here
  
  public Poly multiply(Poly p) throws Exception {
    
    if (p == null){
      throw new Exception ("This is a null polynomia");
    }
    
    int maximumDegree = getDegree() + p.getDegree();   // maxdegree is the biggest degree that the resulting polynominal will get
                                                       // it is generated from the two polynominals that are involved in the multiplication 
    Poly result = new Poly(maximumDegree);             // make a new Poly called 'result' according to the maximum degree obtained
    
    
      for (int i = 0; i <= getDegree() ; i++){       // these two for loops will go through both polynominals p and q
        for (int j = 0; j <= p.getDegree(); j++){
        result.coefficients[i+j] = result.coefficients[i+j]+ coefficients[i]*p.coefficients[j];
      }
    }
        
      return result;
  }
  
  public Poly constantMultiply(double constant) throws Exception{
    
    if (coefficients == null){                            // if the polynomial to multiply is null
      throw new Exception ("This is a null polynomial");
    }
    
    double[] change = {constant};      
    Poly changed = new Poly(change);         // the constant is changed into a polynominal of degree 0
    Poly q = this;
    return this.multiply(changed);          // multiply method was called to calculate
    
  }
  
  public void removeLeadingZero(){
    int size = coefficients.length;               // size will be used to create a new array to re-size
       
    for (int k = (coefficients.length-1) ; k > 0 ; k--){     // we want to omit the leading zero from the highest degree --> start checking from the highest degree
      if (coefficients[k] == 0){
        size--;
      }
      else if (coefficients[k] != 0) {
        break;
      }
    }
    double[] newCoefficients = new double[size];           //make a new re-sized array
    for (int u = 0; u < newCoefficients.length ; u++){
      newCoefficients[u] = coefficients[u];
    }
    coefficients = newCoefficients;                  // now, coefficients' memory location is changed to the newcoefficients array's memory location
  }
  
  public Poly derive(){
    if (getDegree() == 0){                        // if the polymonial to derive is only 0 degree, return 0 such as 4x^0 --> 0x^0
      Poly zero = new Poly(0);
      return zero; 
    }
    Poly derived = new Poly((getDegree()));
    for (int i = getDegree()-1 ; i >= 0; i--){
      derived.coefficients[i] = (i+1)*coefficients[i+1];
      derived.removeLeadingZero();                      //remove leading zero if ever they exist
      
    }
    return derived;
  }
}