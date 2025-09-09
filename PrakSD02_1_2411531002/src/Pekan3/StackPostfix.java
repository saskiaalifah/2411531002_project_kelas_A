package Pekan3;
import java.util.*;
public class StackPostfix {
	public static int postfixEvaluate (String expression) {
		Stack<Integer> s= new Stack<Integer>();
		Scanner input=new Scanner(expression);
		while (input.hasNext()) {
			if (input.hasNextInt()) { // an operand (integer) 
				s.push(input.nextInt());
				
			}else {// an operator

	String operator=input.next();
	int operand2=s.pop();
	int operand1=s.pop();
	if (operator.equals("+")) {
		s.push (operand1+ operand2);
	}else if (operator.equals("-")) {
		s.push(operand1- operand2); 
	}else if (operator.equals("*")) { 
		s.push(operand1* operand2);
	} else { 
		s.push (operand1 / operand2);
				}
			}
		}
	return s.pop();
	}
	public static void main(String[] args) {
	System.out.println("hasil postfix = " +postfixEvaluate("5 2 5 * + 7 -"));
	}
}