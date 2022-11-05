package ADT_Stack.Test;

import ADT_Stack.ADT.ArrayStack;
import ADT_Stack.ADT.StackInterface;

public class StackSolution {

    public static void main(String[] args) {

        //System.out.println(checkBalancedDelimiters("(3+2)-(5+3)"));
        System.out.println(convertInfixToPostFix("a+b*(c^d-e)^(f+g*h)-i"));
    }

    private static String convertInfixToPostFix(String args){

        StackInterface<Character> operatorStack = new ArrayStack<>();
        StringBuilder postFix = new StringBuilder();

        for(char ch : args.toCharArray()){

            switch(ch){
                case '^' :
                    operatorStack.push(ch);
                    break;
                case '+' : case '-' : case '*' : case '/':
                    while(!operatorStack.isEmpty() && checkPrecedence(ch, operatorStack.peek())){
                        postFix.append(operatorStack.peek());
                        operatorStack.pop();
                    }
                    operatorStack.push(ch);
                    break;
                case '(':
                    operatorStack.push(ch);
                    break;
                case ')':
                    char topOperator = operatorStack.pop();
                    while(topOperator != '('){
                        postFix.append(topOperator);
                        topOperator = operatorStack.pop();
                    }
                    break;
                default:
                    postFix.append(ch);
                    break;
            }
        }
        while(!operatorStack.isEmpty()){
            postFix.append(operatorStack.pop());
        }

        return postFix.toString();
    }

    private static boolean checkPrecedence(char opt1, char opt2){

        //opt1 <= opt2 = true
        if(opt2 == '(')
            return false;


        switch(opt2){
            //opt2 has the biggest one then its true
            case '*' : case '/':
                return true;
            case '+' : case '-':
                if(opt1 == '*' || opt1 == '/')
                    return false;
                break;
            default:
                break;
        }
        return true;
    }

    private static boolean checkBalancedDelimiters(String expression){

        StackInterface<Character> openDelimeterStack = new ArrayStack<>();

        int characterCount = expression.length();
        boolean isBalanced = true;
        int index =0;
        char nextCharacter = ' ';

        while(isBalanced && (index < characterCount)){
            nextCharacter = expression.charAt(index);
            switch(nextCharacter){
                case '(' : case '[' : case '{':
                    openDelimeterStack.push(nextCharacter);
                    break;
                case ')' : case ']' : case '}':{
                    if(openDelimeterStack.isEmpty()){
                        isBalanced = false;
                    }
                    else{
                        Character openDelimiter = openDelimeterStack.peek();
                        openDelimeterStack.pop();
                        isBalanced = isPaired(openDelimiter,nextCharacter);
                    }
                    break;
                }
                default : break;
            }
            index++;
        }
        if(!openDelimeterStack.isEmpty())
            isBalanced = false;

        return isBalanced;
    }
    private static boolean isPaired(char open, char close){
        return (open == '(' && close == ')') ||
                (open == '[' && close == ']') ||
                (open == '{' && close == '}');
    }
}
