import java.util.*;

public class CARD implements Comparable<CARD>{
    private SUIT suit;
    private int rank;
    static Random random = new Random();
    static boolean[][] checked = new boolean[4][15];

    CARD(){
        int suitNumber = 0;
        int rankNumber = 0;

        do{
            suitNumber = random.nextInt(4);
            // 1부터 13까지 사용하기로 했다.
            rankNumber = random.nextInt(13) + 2;
            if(!checked[suitNumber][rankNumber]){
                checked[suitNumber][rankNumber] = true;
                break;
            }
        }while(true);

        switch (suitNumber) {
            case 0:
                this.suit = SUIT.Spades;
                break;
            case 1:
                this.suit = SUIT.Diamonds;
                break;
            case 2:
                this.suit = SUIT.Hearts;
                break;
            case 3:
                this.suit = SUIT.Clubs;
                break;
        }

        this.rank = rankNumber;

    }


    public String toString(){
        String s = Integer.toString(this.rank);
        switch (this.rank) {
            
            case 11:
                s = "J";
                break;
            case 12:
                s = "Q";
                break;
            case 13:
                s = "K";
                break;
            case 14:
                s = "A";
                break;
        }



        return "["+suit + ", " + s + "]";
    }

    @Override
    public int compareTo(CARD card) {
        if(this.rank == card.rank){
            return this.suit.getPriority() - card.suit.getPriority();
        }
        return this.rank - card.rank;
    }

    public int getRank(){
        return this.rank;
    }

    public SUIT getSuit(){
        return this.suit;
    }


}