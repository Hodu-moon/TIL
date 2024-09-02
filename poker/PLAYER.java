import java.util.*;

public class PLAYER implements Comparable<PLAYER>{
    private String name;
    private CARD[] hand = new CARD[5];
    private boolean[] checked = new boolean[5];
    private CARD highCard;
    private COMBINATION combinationRank;
    // private boolean[] nameCheck = new boolean[5];
    // 이름 체크해서 다시 안나오게 
    private int PAIR;
    private int TWO_PAIR;
    private int TRIPLE;
    private int FOUR_CARD;


    // 애기동물 이름 
    String[] randomName = {"piglet", "Joey", "Stot", "Kit", "Foal", "pinkie", "infant",
    "Cub", "cub", "Pup", "Shoat",  "Calf", "bunny",  "Fawn", "Fawn" };


    public PLAYER(String name){
        this.name = name;

        for(int i = 0; i < 5; i++){
            this.hand[i] = new CARD();
        }

        Arrays.sort(hand);
        highCard = hand[4];
        combinationRank = COMBINATION.HIGHCARD;

        this.PAIR = 0;
        this.TWO_PAIR = 0;
        this.TRIPLE = 0;

    }

    public PLAYER(){
        Random random = new Random();
        String name = randomName[random.nextInt(randomName.length)];

        this.name = name;
        for(int i = 0; i < 5; i++){
            this.hand[i] = new CARD();
        }

        Arrays.sort(hand);
        highCard = hand[4];
        combinationRank = COMBINATION.HIGHCARD;

        this.PAIR = 0;
        this.TWO_PAIR = 0;
        this.TRIPLE = 0;
    }

    @Override
    public String toString(){

        return this.name + " ->  " + hand[0] + " " + hand[1] 
                            + " " + hand[2] + " " + hand[3] + " " 
                            + hand[4] + " < " + this.getCombination() + " > ";
    }

    // 두번째 핵심 플레이어 끼리 비교하기 
    @Override
    public int compareTo(PLAYER player){
        // 우선순위 비교하기

        CARD[] playerHand = player.gethand();
        CARD myCard;
        CARD playerCard;

        // combination 랭크가 같으면 1 ~ 9 까지 다 정의해야함 
        if(this.combinationRank.getCombinationRank() == player.getCombination().getCombinationRank()){
            // high card
            if(this.combinationRank == COMBINATION.HIGHCARD){
                //highCard 가 같으면 4번째 비교 4번째 같으면 3번째 비교 3번째 같으면 2번째 비교 2번째 같으면 1번째 비교 

                if(this.highCard.getRank() == player.getHighCard().getRank()){
                    
                    if(this.hand[3].getRank() == playerHand[3].getRank()){

                        if(this.hand[2].getRank() == playerHand[2].getRank()){

                            if(this.hand[1].getRank() == playerHand[1].getRank()){

                                if(this.hand[0].getRank() == playerHand[0].getRank()){
                                    // 천생연분이네 결혼해라 
                                    return 0;
                                }

                                return this.hand[0].getRank() - playerHand[0].getRank();
                            }

                            return this.hand[1].getRank() - playerHand[1].getRank();
                        }

                        return this.hand[2].getRank() - playerHand[2].getRank();
                    }

                    return this.hand[3].getRank() - playerHand[3].getRank();
                }


                return this.highCard.getRank() - player.getHighCard().getRank();

            } // ONE PAIR
            else if (this.combinationRank == COMBINATION.ONE_PAIR){
                if(this.PAIR == player.getPAIR()){
                    // 3개 비교 완료
                    myCard = findKicker(this);
                    playerCard = findKicker(player);

                    if(myCard.getRank() == playerCard.getRank()){
                        // 4개 비교 완료 
                        myCard = findKicker(this);
                        playerCard = findKicker(player);


                        if(myCard.getRank() == playerCard.getRank()){
                            // 4개 비교 완료 
                            myCard = findKicker(this);
                            playerCard = findKicker(player);

                        
                            if(myCard.getRank() == playerCard.getRank()){
                                return 0;
                            }

                            return myCard.getRank() - playerCard.getRank();
                        }

                        return myCard.getRank() - playerCard.getRank();
                    }
                    

                    return myCard.getRank() - playerCard.getRank();
                }


                // pair 일때 Card 숫자 저장해놨음 
                return this.PAIR - player.getPAIR();

            }   // TWO PAIR 
            else if(this.combinationRank == COMBINATION.TWO_PAIR){
                if(this.TWO_PAIR == player.getTWO_PAIR()){
                    if(this.PAIR == player.getPAIR()){
                        myCard = findKicker(this);
                        playerCard = findKicker(player);

                        return myCard.getRank() - playerCard.getRank();

                    }

                    return this.PAIR - player.getPAIR();
                }

                return this.TWO_PAIR - player.getTWO_PAIR();
            } // TRIPLE 
            else if (this.combinationRank == COMBINATION.TRIPLE ){
                if(this.TRIPLE == player.getTRIPLE()){
                    myCard = findKicker(this);
                    playerCard = findKicker(player);

                    if(myCard.getRank() == playerCard.getRank()){
                        myCard = findKicker(this);
                        playerCard = findKicker(player);
                        return myCard.getRank() - playerCard.getRank();


                    }
                    return myCard.getRank() - playerCard.getRank();

                }
                
                return this.TRIPLE - player.getTRIPLE();

            }else if(this.combinationRank == COMBINATION.STRAIGHT){

                return this.highCard.getRank() - player.getHighCard().getRank();

            }else if(this.combinationRank == COMBINATION.FLUSH){

                return this.highCard.getRank() - player.getHighCard().getRank();
            }else if(this.combinationRank == COMBINATION.FULL_HOUSE){

                return this.TRIPLE - player.getTRIPLE();
            }else if( this.combinationRank == COMBINATION.FOUR_CARD){
                if(this.FOUR_CARD == player.getFOUR_CARD()){

                    myCard = findKicker(this);
                    playerCard = findKicker(player);

                    return myCard.getRank() - playerCard.getRank();                
                }

                return this.FOUR_CARD - player.getFOUR_CARD();
            }else if(this.combinationRank == COMBINATION.STRAIGHT_FLUSH){
                return this.highCard.getRank() - player.getHighCard().getRank();
            }





        }
        
        return this.combinationRank.getCombinationRank() - player.combinationRank.getCombinationRank()  ;
    }

    public CARD findKicker(PLAYER player ){
        // 어차피 정렬 되어있다. 큰것부터 작은순으로 가자 
        boolean [] checked = player.getChecked();

        for(int i = 4; i >=0 ; i--){
            if(!checked[i]){
                checked[i] = true;
                return player.hand[i];
            }
        }

        return null;
    }

    public String getName(){
        return this.name;
    }

    public CARD[] gethand(){
        return this.hand;
    }

    public COMBINATION getCombination(){
        return this.combinationRank;
    }

    public CARD getHighCard(){
        return this.highCard;
    }

    public boolean[] getChecked(){
        return this.checked;
    }

    public void setCombination(COMBINATION combinationRank){
        this.combinationRank =combinationRank; 
    }

    public int getPAIR(){
        return this.PAIR;
    }

    public int getTWO_PAIR(){
        return this.TWO_PAIR;
    }

    public int getTRIPLE(){
        return this.TRIPLE;
    }

    public int getFOUR_CARD(){
        return this.FOUR_CARD;
    }

    public void setPAIR(int PAIR){
        this.PAIR = PAIR;
    }

    public void setTWO_PAIR(int TWO_PAIR){
        this.TWO_PAIR = TWO_PAIR;
    }

    public void setTRIPLE(int TRIPLE){
        this.TRIPLE = TRIPLE;
    }

    public void setFOUR_CARD(int FOUR_CARD){
        this.FOUR_CARD = FOUR_CARD;
    }
}
