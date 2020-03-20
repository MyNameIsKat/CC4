package prefixinfixpostfix;
import java.io.*;

public class PrefixInfixPostfix {

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Node output = new Node('a'); //create link list
        Node operand = new Node('a');
        System.out.print("Enter Equation: ");
        String equation = br.readLine();
        
        System.out.println("[1]postfix [2]prefix");
        System.out.print(" : ");
        int cho = Integer.parseInt(br.readLine());
        switch(cho){
            case 1: 
                prefix(equation, output, operand);
                break;
            case 2:
                break;
        }
    }
    public static void prefix(String equation, Node output, Node operator){
        char[] cArr = equation.toCharArray();
        char token='0';
        operator.operandProcess('#', output);
        System.out.println("Token \t\t Stack \t\t Output");
        for(int i =0;i<cArr.length;i++){
            
            token=cArr[i];
            switch(cArr[i]){
                case '+':
                case '-':
                case '*':
                case '/':  
                case '%':                     
                case '(':   
                case ')':
                case '#':
                     operator.operandProcess(cArr[i],output);
                    break;   
                default:
                    output.add(cArr[i]);
                    break;
                    
            }
            System.out.print("\n"+token);
            System.out.print("\t\t"); operator.print();
            System.out.print("\t\t"); output.print();
            
        }
    }
    
}
