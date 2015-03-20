package DecisionTree;

// part A
public class DecisionTree {
  DTNode root;
  public DecisionTree(Instance[] instances) {
    // we will have a root starting from 0 to build on
    root=new DTNode(instances,0.0);
    root=buildtree(root);
  }
  // log 2 method was created to facilitate its further use
  public static double log2(double f){
    return (Math.log(f)/Math.log(2));
  }
  
  // calculates the entropy for further uses
  public static double entropy(Instance[] x){
    double num_true = 0;
    double num_false = 0;
    
    for (int i = 0 ; i < x.length ; i++){
      if (x[i].getLabel()==true){
        num_true++;
        
      }
      else {
        num_false++;
      }
    }
    
    double prob_true = num_true/(x.length);
    double prob_false = num_false/(x.length);
    
    
    // in case any of the prob is zero, it return 0.0 instead of NaN
    if (prob_true == 0.0 || prob_false == 0.0){
      return 0.0;
    }
    
    return (-1*(prob_true)*log2(prob_true)-((prob_false)*log2(prob_false)));
  }
  
  // building tree
  public DTNode buildtree(DTNode node){
    int size = 0;
    for (int i = 0; i < node.a.length-1 ; i++){
      if (node.a[i].getLabel()!=node.a[i+1].getLabel()){
        size++;
      }
    }
    if (size == 0){
      return node;
    }
    //indices[] got indices within node where label changes
    // loop same thing again now that we have the 'size', the number of label changes
    int[] indices = new int[size];
    int ind = 0;
    for (int i = 0; i < node.a.length-1 ; i++){
      if (node.a[i].getLabel()!=node.a[i+1].getLabel()){
        indices[ind] = i;
        ind++;
      }
    }
    
    // an array that the entry of where the sign flip has a number other then 0.
    // comparing the Entropy
    double[] Entropies = new double[indices.length];
    if (indices.length == 1){
      Entropies[0] = entropy(node.a);
    }
    else{
    for (int u = 0 ; u < indices.length ; u++){
      int count1 = 0;
      int count2 = 0;
      
      Instance[] y = new Instance[indices[u]+1];
      Instance[] z = new Instance[node.a.length-(indices[u]+1)];
      for (int i = 0 ; i < node.a.length ; i++){
        if (count1 < indices[u]+1){
          y[count1] = node.a[i];
          count1++;
        }
        else {
          z[count2] = node.a[i];
          count2++;
        }
        
      }
      // stores avgEntropy to be compared
      double avgEntropy = ((((indices[u]+1)/(double)(node.a.length)))*(entropy(y))) + (((node.a.length-(indices[u]+1))/(double)(node.a.length))*(entropy(z)));
      Entropies[u] = avgEntropy;
    }
    }
             
    //finding minimum index of Entropies, where the smallest avgEntropy is at
    int minimum_index=0;
    for(int k=1;k<Entropies.length;k++){
      if(Entropies[minimum_index]<=Entropies[k]){
        
      }
      else {
        minimum_index=k;
      }
      
    }
    // getting testValue       
    double testValue=(node.a[indices[minimum_index]].getAttribute()+node.a[indices[minimum_index]+1].getAttribute())*0.5;
    // left and right to be distinguished
    Instance[]left=new Instance[indices[minimum_index]+1]; 
    Instance[]right=new Instance[node.a.length-(indices[minimum_index]+1)];
    
    for(int i=0;i<left.length;i++){ 
      left[i]=new Instance(node.a[i].getAttribute(),node.a[i].getLabel()); 
    } 
    for(int i=0;i<right.length;i++){ 
      right[i]=new Instance(node.a[i+left.length].getAttribute(),node.a[i+left.length].getLabel()); 
    } 
    DTNode leftNode=new DTNode(left,testValue); 
    DTNode rightNode=new DTNode(right,testValue); 
    node.left=buildtree(leftNode); 
    node.right=buildtree(rightNode);
    
    return node;
  }

  // part B
  // prune and pruning were written like this on purpose to respect the parameter standards to take l only to facilitate grading
  // I needed node to work with, and I didn't want to include node in prune, which would make grading harder bcz TA need to adjust it
  public void prune(int l) {
    pruning(l, this.root);
  }
  public void pruning(int limit, DTNode node){
    DTNode node1;
    if (node.right == null){
      System.out.println ("Only root is present");
    }
    else {
      while (node.right != null){
        if (node.right.a.length < limit || node.left.a.length < limit){
          node.right = null;
          node.left = null;
          break;
        }
        else {
          node = node.right;
        }
      }
    }
  }
  
  // part c
  // same here. classify and classified seperately to respect the parameter norms to take only input in classify
  public boolean classify(double input) {
    return classified(input, this.root);
  }
  
  public boolean classified(double val, DTNode node){
    int difference = 0;
    int trueCount = 0;
    int falseCount = 0;
    boolean majority = true;
    
    // when leaf is reached
    if (node.left == null || node.right == null){
      System.out.println(node.testValue);
      
      for (int i = 0 ; i< node.a.length-1 ; i++){
        if (node.a[i].getLabel() != node.a[i+1].getLabel()){
          difference++;
          
        }
      }
      // if labels are not all the same
      if (difference > 0){
        for (int i = 0 ; i < node.a.length ; i++){
          if (node.a[i].getLabel() == true){
            trueCount++;
          }
          else {
            falseCount++;
          }
        }
        if (trueCount > falseCount){
          trueCount = 0;
          falseCount = 0;
          difference = 0;
          majority = true;
        }
        else {
          trueCount = 0;
          falseCount = 0;
          difference = 0;
          majority = false;
        }
        
      }
      // when the difference was 0, meaning all label was same, we can return any index's label
      else {
        
        return node.a[0].getLabel();
      }
    }
    
    if (val <= node.left.testValue){
      node = node.left;
      
      return classified(val, node);
    }
    if (val > node.right.testValue){
      node = node.right;
      
      return classified(val, node);
    }
    
    return majority;
  }
  
  
  // part D
  // same here. print() shouldnt take anything in to facilitate grading. Used printByDepth to take node in
  public void print() {
    printByDepth(this.root);
    
  }
  
  public void printByDepth(DTNode node){
      DTNode node1;
      String space = "";
      // printing root 0.0 first
      System.out.println(space+node.testValue);
      // there is no nodes after if a such case like only root exist in tree
      if (node.right == null){
        System.out.println("There is no nodes after");
      }
      else {
        for (int q = 0 ; q < this.root.a.length ; q++){
          // space for indenting       
          space = space + " ";
          node1=node.left;
          node = node.right;
          
          System.out.println(space + node.testValue);
          // for a leaf, we print the label and its portion in fraction
          // we can take index 0 bcz leaf will have same label
          System.out.println(" " + space + node1.a[0].getLabel() + " " + ((node1.a.length/(double)(root.a.length))));
          // the last right is also a leaf, so we need to print its label and its proportion
          if (node.right == null){
            System.out.println(" " + space + node.a[0].getLabel() + " " + (node.a.length/(double)(root.a.length)));
            break;
          }
          if (node.left == null){
            
          }
            
        }
      }
  }
  
}

