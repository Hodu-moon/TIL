import java.util.*;

public class AST {
    // 1. post Order 
    // 2. makeNode
    // 3. 만들어진 노드 프린트 하면 ()

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String user;

        while(true){
            System.out.print("> ");
            user = scanner.nextLine();

            if(user.equals("exit()")){
                break;
            }
            try{
                Node node = generateAST(user);
                int result = (int)evaluation(node);
                if(!(result == 0)){
                    System.out.println(result);
                }

            }catch(Exception e){}
            
        }
        

        scanner.close();
    }


    public static Node generateAST(String expression){
        Deque<String> deque = postOrder(expression);
        Node node = makeNodes(deque);

        return node;
    }

    public static double evaluation(Node node) {
        double result = 0;
    
        if (node != null) {
            double left = evaluation(node.left);
    
            if (node instanceof OpNode) {
                double right = evaluation(node.right); 
    
                if (((OpNode) node).getOp() == '-') {
                    result = left - right;
                } else if (((OpNode) node).getOp() == '+') {
                    result = left + right;
                } else if (((OpNode) node).getOp() == '*') {
                    result = left * right;
                } else if (((OpNode) node).getOp() == '/') {
                    result = left / right;
                }
    
                node.key = result;
    
            } else {
                result = node.key;
            }
    
        }
    
        return result;
    }


    public static  void inOrderPrint(Node node){

        if(node != null){
            inOrderPrint(node.left);
            if(node instanceof OpNode){
                System.out.print(((OpNode)node).op + " ");
            }else if(node instanceof IntNode){
                System.out.print(((IntNode)node).key + " ");
            }

            inOrderPrint(node.right);
        }
    }

    public static Node makeNodes(Deque<String> postOrder){
        // 계산을 두번 하자.  어차피 저기 뭐야 곱셈 나눗셈 먼저 하니 곱셈 나눗셈 한걸 노드에 넣어두고
        // 더하기 하는거 노드로 만들어서 InOrder 순회
        Deque<Node> deque = new ArrayDeque<>();
        for(String s : postOrder){
            if(Character.isDigit(s.charAt(0))){
                deque.add(new IntNode(Integer.parseInt(s)));
            }else if(s.equals("/") || s.equals("*")){
                OpNode newNode = new OpNode(s.charAt(0));
                newNode.right = deque.pollLast();
                newNode.left = deque.pollLast();

                deque.add(newNode);
            }else if(s.equals("+") || s.equals("-")){
                deque.add(new OpNode(s.charAt(0)));
            }
        }


        Deque<Node> nodeDeque2 = new ArrayDeque<>();

        for(Node node : deque){
            if(node instanceof OpNode && (((OpNode) node).getOp() == '+' ||((OpNode) node).getOp() == '-'  )){
                
                ((OpNode)node).right = nodeDeque2.removeLast();
                ((OpNode)node).left = nodeDeque2.removeLast();
                nodeDeque2.add(node);
                
            } else{
                nodeDeque2.add(node);
            }

        }



        return nodeDeque2.peek();
    }

    public static Deque<String> postOrder(String string){
        String[] token = string.split(" ");
        Deque<String> deque = new LinkedList<>();


        // to PostOrder expression

        Stack<String> stack = new Stack<>();
        for(String s :  token){
            if(Character.isDigit(s.charAt(0))){
                deque.add(s);
            }

            
            if(s.equals("+") || s.equals("-") || s.equals("/") || s.equals("*")){
                while(!stack.isEmpty() && getPriority(stack.peek()) >= getPriority(s)){
                    deque.add(stack.pop());
                }
                stack.add(s);
            }
        }

        while(!stack.isEmpty()){
            deque.add(stack.pop());
        }

        return deque;

    }

    public static int getPriority(String s){

        switch (s) {
            case "+":
            case "-":
                return 1;
            case "/":
            case "*":
                return 2;
        }

        return -1;
    }
}
