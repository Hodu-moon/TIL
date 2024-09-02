import java.util.Iterator;
import java.util.Arrays;

public class PLAYERGROUP {
    private static int numberOfPeople;
    private PLAYER[] players;

    public PLAYERGROUP(PLAYER player,int numberOfPeople ){
        this.players = new PLAYER[numberOfPeople];
        this.numberOfPeople = numberOfPeople;

        players[0] = player;
        for(int i = 1; i <= numberOfPeople - 1; i++){
            players[i] = new PLAYER();
        }
    }

    // public Iterator<PLAYER> iterator(){
    //     return new GROUPITERATOR(this);
    // }

    public static int getNumberOfPeople(){
        return numberOfPeople;
    }
    
    public PLAYER[] getPlayers(){
        return players;
    }

    public void sort(){
        Arrays.sort(this.getPlayers());
    }

    public void printWinner(){
        System.out.println("WINENENENNERNRENRENERNNERNER");
        System.out.println(this.players[numberOfPeople-1]);
    }

}
