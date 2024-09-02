import java.util.*;

public class GAME {
    static int numberOfPeople;

    public static void main(String[] args) {

        numberOfPeople = getNumberOfPeople();
        PLAYERGROUP playerGroup = makePlayers();
        
        judgeCards(playerGroup);

        System.out.println();
        playerGroup.sort();

        printPlayers(playerGroup);

        
        playerGroup.printWinner();

        
    }

    public static void judgeCards(PLAYERGROUP playerGroup){
        PLAYER[] players = playerGroup.getPlayers();

        for(int i = 0; i < players.length; i++){
            isPair(players[i]);
        }

        for(int i = 0; i < players.length; i++){
            isStraight(players[i]);
        }

        for(int i = 0; i < players.length; i++){
            isFlush(players[i]);
        }
        
    
    }

    // JUDGE 여기부터 
    public static void isPair(PLAYER player){
        CARD[] hand = player.gethand();
        boolean[] checked = player.getChecked();

        // 이걸 2번 돌리면 되겠지
        for(int j = 0; j < 2; j++){
            for(int i = 0; i < 4; i++){
                //One Pair
                if(hand[i].getRank() == hand[i + 1].getRank()
                    && !checked[i] && ! checked[i + 1]){
                    checked[i] = checked[i + 1] = true;
                    if(player.getCombination() == COMBINATION.HIGHCARD){
                        // PAIR
                        player.setCombination(COMBINATION.ONE_PAIR);
                        player.setPAIR(hand[i].getRank());

                    }else if(player.getCombination() == COMBINATION.ONE_PAIR){
                        //TWO_PAIR
                        player.setCombination(COMBINATION.TWO_PAIR);
                        player.setTRIPLE(hand[i].getRank());
                        

                    }else if( player.getCombination() == COMBINATION.TRIPLE){

                        player.setCombination(COMBINATION.FULL_HOUSE);
                    }

                    if(i + 2 <= 4 && hand[i].getRank() == hand[i + 2].getRank()
                        && !checked[i + 2]){
                        // TRIPLE 
                        checked[i + 2] = true;
                        player.setCombination(COMBINATION.TRIPLE);
                        player.setTRIPLE(hand[i].getRank());


                        if(i + 3 <= 4 && hand[i].getRank() == hand[i + 3].getRank()
                            && !checked[i + 3]){

                                checked[i + 3] = true;
                                player.setCombination(COMBINATION.FOUR_CARD);
                                player.setFOUR_CARD(hand[i].getRank());

                            }
                    }
                    
                }
            } // for문
        }

       
    } 

    public static void isStraight(PLAYER player){
        CARD[] hand = player.gethand();
        boolean token = true;
        int start = 0;
        
        if(hand[4].getRank() == 14){
            start = 1;
        }else{
            start = hand[0].getRank();
        }
        // A가 지금 14로 되어있음. 1로 바꾸는 작업을 해야함 .
        
        
        if(start + 1 == hand[1].getRank()
        && start + 2 == hand[2].getRank()
        && start + 3 == hand[3].getRank()
        && start + 4 == hand[4].getRank() ){

            player.setCombination(COMBINATION.STRAIGHT);
            
        }
        
    }

    public static void isFlush(PLAYER player){
        CARD[] hand = player.gethand();

        if(hand[0].getSuit() == hand[1].getSuit() 
        && hand[0].getSuit() == hand[2].getSuit()
        && hand[0].getSuit() == hand[3].getSuit()
        && hand[0].getSuit() == hand[4].getSuit()){

            if(player.getCombination() == COMBINATION.STRAIGHT){
                player.setCombination(COMBINATION.STRAIGHT_FLUSH);
            }else{
                player.setCombination(COMBINATION.FLUSH);
            }
        }
    }

    // JUDGE 끝 full house 이면서 staright일 땐 없으니까 비교 안해도 된다.
    // 정확하다 이거 엎지 마라 다시 엎으면 머리 아파진다. 

    
    public static PLAYERGROUP makePlayers(){
        // 사용자의 이름을 입력 받습니다. 사용자 이외의 플레이어는 임의로 이름을 부여합니다.
        String name;
        do{
            System.out.print("Input user name : ");
            try( Scanner scanner = new Scanner(System.in)){
                name = scanner.nextLine();
                break;
            }catch(Exception e){e.printStackTrace();}

        }while(true);

        return new PLAYERGROUP(new PLAYER(name), numberOfPeople);
    }

    

    public static int getNumberOfPeople(){
        int numberOfPeople = 0;
        Scanner scanner = new Scanner(System.in);

        while(true){
            try {
                System.out.print("Enter player number (2 ~ 5): ");
                numberOfPeople = scanner.nextInt();
                if(numberOfPeople < 2 || numberOfPeople > 5){
                    throw new InputMismatchException();
                }

                break;
            } catch (InputMismatchException e) {
                scanner.nextLine();
            }
        }
        

        return numberOfPeople;
    }

    public static void printPlayers(PLAYERGROUP playergroup){
        System.out.println("-------------------------------------------------");
        System.out.println("<Print Players>");
        // 오류 발생
        // for(PLAYER player : playergroup){
        //     System.out.println(player);
        // }
        PLAYER[] players = playergroup.getPlayers();
        for(int i = 0; i < players.length; i++){

            System.out.println(players[i]);
        }

    }
}
