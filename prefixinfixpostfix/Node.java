package prefixinfixpostfix;

public class Node {
    Node next;
    Node prev;
    char data;
    Node head;
    
    Node(char d){
        this.data = d;
    }
    
    public void add(char d){
        Node newNode = new Node(d);
        if(head==null){
            head = newNode;
        }else{
            Node current = head;  
            while(current.next!=null){
                current = current.next;
            }
            current.next = newNode;
        }
    }
    
    public void remove(char dis){
        Node current = head;
        if(head==null){
            return;
        }else if(head.data==dis){
            head = head.next;
        }else{
            while(current.next!=null){
                if(current.next.data==dis){
                    current.next=current.next.next;
                }
                current = current.next;
            }
        }
    }
    
    public void print(){
        Node current = head;  
        while(current!=null){
            System.out.print(current.data+" ");
            current = current.next;
        }
    }
      
    public int preCheckISP(char d){
        switch(d){
            case '(': return 0; 
            case '^': return 3;
            case '*': return 2;
            case '/': return 2;
            case '%': return 2;
            case '+': return 1;
            case '-': return 1;
            case ')': return 0;
        }
        return 0;
    }
    public int preCheckICP(char d){
        switch(d){
            case '(': return 0; 
            case '^': return 4;
            case '*': return 2;
            case '/': return 2;
            case '%': return 2;
            case '+': return 1;
            case '-': return 1;
            case ')': return 4;
            case '#': return -1;
        }
        return 0;
    }
    
    public void operandProcess(char d, Node output){
        Node current = head;  
        if(head==null){
            add(d);
        }else{
            while(current.next!=null){
                current= current.next;
            }
            if(preCheckISP(current.data)>=preCheckICP(d)){
                addToOutput(output);
            }else{
                add(d);
            }
        }
    }
    public void addToOutput(Node output){
        Node current = head;
            while(current.next!=null){
                if(current.data=='('){
                    add(current.data);
                }else if(current.data=='#'){
                    //do nothing
                }else{
                    if(current.next.data=='('||current.next.data== '#'){
                        output.add(current.data);
                    }else if(current.next.data!='('||current.next.data!='#'){
                        output.add(current.data);
                    }
                }
                current = current.next;
            }
            if(current.data=='#'){
                //do nothing
            }else if(current.data=='('){
                add(current.data);
            }
        
    }
}
