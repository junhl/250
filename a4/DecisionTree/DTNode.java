package DecisionTree;

public class DTNode {
  Instance[] a;
  double testValue;
  DTNode left, right;
  public DTNode(Instance[] b, double c){
    a=b;
    testValue=c;
    left=null;
    right=null;
  }
  public Instance[] getInstance(){
    return a;
  }
  public double getTestValue(){
    return testValue;
  }
  
}
