package SingleLinkedLists;

public class SingleLinkedList {

  Node head;
  
  public SingleLinkedList() {
    head = null;
  }
  
  public boolean isEmpty() {
    return (head == null);
  }
  
  // Method for inserting an object in the first position of a list
  public void insertFirst(Object o) {
    // Pack o into a Node 
    Node n = new Node(o);
    
    // put n at the beginning of the list
    n.next = head;
    
    // and modify the head of the list
    head = n;
  }
  
  // Method for inserting an object in the last position of a list                                         
  public void insertLast(Object o) {
    // Pack o into a Node
    Node n = new Node(o);
   
    // If the list is empty, we simply have to change head
    if (head == null) {
      head = n;
      return;
    }
    // The list is not empty, so we have to traverse it, stopping at the last element
    Node crt = head; 
    while (crt.next != null) 
      crt = crt.next;
    crt.next = n;
  }
  
  // Method for printing the entire list (demonstrates traversal)
  public void print() {
    for (Node crt = head; crt != null; crt = crt.next) 
      System.out.println(crt.content);
  }
  
  // Method for removing the first element in the list
  // Note that this is O(1), since we have a sequence of primitive operations
  public Object removeFirst() throws EmptyListException {
    
    if (head == null) throw new EmptyListException();
    
    // Put a "finger" on the current head
    Node crt = head;
    
    // Move the head to the appropriate spot
    head = head.next;
    
    // Figure out what we need to return
    Object o = crt.content;
    
    // Clear out all pointer - the garbage collector will take care of this area in memory
    crt.content = null;
    crt.next = null;
    
    // And we're done
    return o;
  }
  
  
  // Method for removing the last element in the list; the object is returned
  // This is O(n) because we need to traverse the list
  public Object removeLast() throws EmptyListException {
    
    
    if (head == null) throw new EmptyListException();
    
    Object o;
    
    // If there is only one element, we need to modify the head of the list
    if (head.next == null) {
      o = head.content;
      head.content = null;
      head = null;
      return o;
    }
    
    // We need to go to the second-to-last element
    Node crt = head;
    while (crt.next.next != null)
      crt = crt.next;
    
    // Now get the content
    o = crt.next.content;
    
    // Remove all references that are not needed
    crt.next.content = null;
    crt.next = null;
    
    // And we're done
    return o;
  }
  
  // Method for finding an object in a list; returns true if the object is found
  public boolean find (Object o) {
    if (head == null) return false;
    
    // We need an index in the list
    for (Node crt = head; crt != null; crt = crt.next) {
      if (o.equals(crt.content)) return true;
    }
    return false;
  }
  
  // Q4 starts here !
  
  public Object removeBefore(Object o) throws NoSuchElementException{
    Node n = new Node(o);
    Node crt = head;
    // if empty list, theres nothing to do
    if (head == null) throw new NoSuchElementException();
    
    // if not, then we search the object o
    else if (find(n)==true){
      
      // if only the head is the only element in the list and it got the object we are looking for, there is nothing before or after to be return
      if(head.next==null) throw new NoSuchElementException();
      // if the one right after head is the wanted element, we remove head and make a new head
      else if (head.next.content == o){
        Object out = new Object();
        out = head.content; 
        head = head.next;
        return out;
      }
      // if not the one right after the head, we check the one after and keep on going
      else {
        
        while (crt.next != null){
          if ((crt.next).next.content==o){
            Object out = new Object();
            out = (crt.next).content;
            crt.next = (crt.next).next;
            return out;
          }
        }
      }
      
    }
    
    return new NoSuchElementException();
    
  }
  
  public Object removeAfter(Object o) throws NoSuchElementException{// head.next gives you the first node!!??
    
    Node n = new Node(o);
    
    
    // if empty list, theres nothing to do
    if (head == null) throw new NoSuchElementException();
    
    // if not, then we keep on searching after
    else if (find(n)==true){
      Node crt=head;
      // if only the head is in list, and it is the object we are looking for, there is nothing before or after
      if (head.next == null) throw new NoSuchElementException();
      
      // if the list got more than head, but the head still got the object we want
      else if(head.content==o){
        Object out = new Object();
        out = head.next.content;
        head.next = head.next.next;
        return out;
      }
      // if not the head, we keep on moving on
      else {
        while (crt.next.next != null){
          if (crt.content == o){
            Object out = new Object();
            out = crt.content;
            crt.next = crt.next.next;
            return out;
            
          }
        }
      }
    }
    
    return new NoSuchElementException();
    
    
  }
  
  
}

