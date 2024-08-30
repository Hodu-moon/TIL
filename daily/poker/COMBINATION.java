public enum COMBINATION {
    STRAIGHT_FLUSH(9),
    FOUR_CARD(8),
    FULL_HOUSE(7),
    FLUSH(6),
    STRAIGHT(5),
    TRIPLE(4),
    TWO_PAIR(3),
    ONE_PAIR(2),
    HIGHCARD(1);

    private int combinationRank;

    COMBINATION(int combinationRank){
        this.combinationRank = combinationRank;
    }

    public int getCombinationRank(){
        return this.combinationRank;
    }
}
