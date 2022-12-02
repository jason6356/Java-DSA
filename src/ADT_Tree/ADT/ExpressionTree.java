package ADT_Tree.ADT;

public class ExpressionTree extends BinaryTree<String> implements ExpressionTreeInterface {

   public ExpressionTree(){

   }

    @Override
    public double evaluate() {
        return evaluate(getRoot());
    }

    private double evaluate(BinaryNode<String> rootNode){

       double result;
       if(rootNode == null)
           result = 0;
       else if(rootNode.isLeaf()){
           String var = rootNode.getData();
           result = getValueOf(var);
       }
       else{
           double firstOperand = evaluate(rootNode.getLeft());
           double secondOperand = evaluate(rootNode.getRight());
           String operator = rootNode.getData();
           result = compute(operator, firstOperand, secondOperand);
       }

       return result;
    }

    private double getValueOf(String var){
       return Double.parseDouble(var);
    }

    private double compute(String operator, double firstOperand, double secondOperand){

       double result = 0.0;

       switch(operator){
           case "+":
               return firstOperand + secondOperand;
           case "-":
               return secondOperand - firstOperand;
           case "*":
               return firstOperand * secondOperand;
           case "/":
               try{
                  result = secondOperand / firstOperand;
               }catch(ArithmeticException e){
                   System.out.println("Error Occur, cannot divisble by 0");
               }
       }
       return result;
    }


}
