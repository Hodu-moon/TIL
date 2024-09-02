public enum SUIT {
    Spades(4),
    Hearts(3),
    Diamonds(2),
    Clubs(1);

    int priority;

    SUIT(int priority){
        this.priority = priority;
    }

    public int getPriority(){
        return this.priority;
    }

}
