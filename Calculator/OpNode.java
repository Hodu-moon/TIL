public class OpNode extends Node{
    char op;


    OpNode(char op){
        this.op = op;
    }

    @Override
    public String toString() {
        return "(" + this.left + "," + this.op + "," + this.right + ")";
    }

    public char getOp(){
        return op;
    }
}