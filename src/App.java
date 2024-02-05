import java.util.*;
import java.math.*;
import java.util.Scanner;

public class App {

    //Global constant declarations
    public static final int GRID_ROWS = 8;
    public static final int GRID_COLS = 8;
    public static final int SHIPS = 6;
    public static final int GRENADES = 4;

    //Global variable declarations
    public static int playerLives = 6;
    public static int compLives = 6;
    public static Scanner scan = new Scanner(System.in);


    public static void main(String[] args) throws Exception {
        printMainTitle();
        startGame();
    }


    //print main title
    public static void printMainTitle(){
        System.out.println("8 888888888o          .8.    8888888 8888888888 8888888 8888888888 8 8888         8 8888888888     d888888o.   8 8888        8  8 8888 8 888888888o");   
            System.out.println("8 8888    `88.       .888.         8 8888             8 8888       8 8888         8 8888         .`8888:' `88. 8 8888        8  8 8888 8 8888    `88."); 
            System.out.println("8 8888     `88      :88888.        8 8888             8 8888       8 8888         8 8888         8.`8888.   Y8 8 8888        8  8 8888 8 8888     `88 ");
            System.out.println("8 8888     ,88     . `88888.       8 8888             8 8888       8 8888         8 8888         `8.`8888.     8 8888        8  8 8888 8 8888     ,88 ");
            System.out.println("8 8888.   ,88'    .8. `88888.      8 8888             8 8888       8 8888         8 888888888888  `8.`8888.    8 8888        8  8 8888 8 8888.   ,88' ");
            System.out.println("8 8888888888     .8`8. `88888.     8 8888             8 8888       8 8888         8 8888           `8.`8888.   8 8888        8  8 8888 8 888888888P'  ");
            System.out.println("8 8888    `88.  .8' `8. `88888.    8 8888             8 8888       8 8888         8 8888            `8.`8888.  8 8888888888888  8 8888 8 8888         ");
            System.out.println("8 8888      88 .8'   `8. `88888.   8 8888             8 8888       8 8888         8 8888        8b   `8.`8888. 8 8888        8  8 8888 8 8888         ");
            System.out.println("8 8888    ,88'.888888888. `88888.  8 8888             8 8888       8 8888         8 8888        `8b.  ;8.`8888 8 8888        8  8 8888 8 8888         ");
            System.out.println("8 888888888P .8'       `8. `88888. 8 8888             8 8888       8 888888888888 8 888888888888 `Y8888P ,88P' 8 8888        8  8 8888 8 8888         ");
            System.out.println();
    }
    //print the grid revealing only the shot at areas
    public static void printGrid(Grid[][] game){
        System.out.print("  ");
        for (char ch = 'A'; ch < 'I'; ch++){
            System.out.print(ch + " ");
        }
        System.out.println();
        for (int i=0; i<GRID_ROWS; i++){
            System.out.print((i+1) + " ");
            for (int j=0; j<GRID_ROWS; j++){
                System.out.print(game[i][j].printState(game, i, j)+ " ");
            }
            System.out.println();
        }
        System.out.println("LEGEND: ");
        System.out.println("S - your ship        s - enemy ship");
        System.out.println("G - your grenade     g - enemy grenade");
        System.out.println("* - cell has already been shot");
        System.out.printf("Player Ships Left: %d\n", playerLives);
        System.out.printf("Computer Ships Left: %d\n", compLives);
        System.out.println();
    }

    //prints the grid revealing the exact state of each cell be it shot at or not
    public static void revealedGrid(Grid[][] game){
        System.out.print("  ");
        for (char ch='A'; ch <= 'H'; ch++){
            System.out.print(ch + " ");
        }
        System.out.println();

        for (int i=0; i<GRID_ROWS; i++){
            System.out.print((i+1) + " ");
            for (int j=0; j<GRID_ROWS; j++){
                System.out.print(game[i][j].revealState(game, i, j) + " ");
            }
            System.out.println();
        }
        System.out.println("LEGEND: ");
        System.out.println("S - your ship        s - enemy ship");
        System.out.println("G - your grenade     g - enemy grenade");
        System.out.println("* - cell has already been shot");
        System.out.printf("Player Ships Left: %d\n", playerLives);
        System.out.printf("Computer Ships Left: %d\n", compLives);
        System.out.println();
    }
    //clears the screen by moving down 10 lines
    public static void clearScreen() {
        System.out.println(System.lineSeparator().repeat(10));
    }
    //prompt user to hit enter key
    public static void pressEnter(){
        System.out.println("Press the enter key to continue....");
        try{
            System.in.read();
        }
        catch(Exception e){
            System.out.println("That is not the enter key. Try again");
        }
    }

    //player to set ships and grenades
    public static void playerSetShipsNGrenades(Grid[][] game){
        String coords;
        int x,y = 0;
        int counter = 0;
        //set player ships
        while(counter<SHIPS){
            clearScreen();
            revealedGrid(game);
            System.out.println();
            System.out.println("Please enter the coordinates to place ship " + (counter+1) );
            coords = scan.next();
            x = coords.charAt(1) - 49;
            y = (int) Character.toUpperCase(coords.charAt(0)) - 65;

            if (x>7 || y>7 || x<0){
                System.out.println("Not valid coordinates. Try again");
                pressEnter();
            }
            else if (game[x][y].isOccupied()){
                System.out.println("Sorry that cell is already occupied. Try again.");
                pressEnter();
            }
            else{
                game[x][y].setPship(game, x, y);
                counter++;
            }
        }

        //set player grenades
        counter = 0;
        while(counter<GRENADES){
            clearScreen();
            revealedGrid(game);
            System.out.println();
            System.out.println("Please enter the coordinates to place grenade " + (counter+1));
            coords = scan.next();
            x = coords.charAt(1) - 49;
            y = (int)Character.toUpperCase(coords.charAt(0)) - 65;

            if (x > 7 || y > 7 || x < 0){
                System.out.println("Not valid coordinates. Try again");
                pressEnter();
            }
            else if (game[x][y].isOccupied()){
                System.out.println("Sorry that cell is already occupied. Try again.");
                pressEnter();
            }
            else{
                game[x][y].setPGrenade(game, x,y);
                counter++;
            }

        }
        clearScreen();
    }
    //computer to set ships and grenades
    public static void compSetShipsNGrenades(Grid[][] game){
        int x,y =0;
        int counter = 0;
        //comp set ships
        while(counter<SHIPS){
            x = (int) (Math.random() * GRID_ROWS);
            y = (int) (Math.random() * GRID_ROWS);
            while (!game[x][y].isOccupied()){
                game[x][y].setCship(game, x, y);
                System.out.println("Comp has set ship " + (counter+1));
                counter++;
            }
        }
        counter = 0;
        while(counter<GRENADES){
            x = (int) (Math.random() * GRID_ROWS);
            y = (int) (Math.random() * GRID_COLS);
            while(!game[x][y].isOccupied()){
                game[x][y].setCGrenade(game, x, y);
                System.out.println("Comp has set grenade " + (counter+1));
                counter++;
            }
        }
    }

    //computer fire missile
    public static boolean compFireMissile(Grid[][] game){
        int x,y = 0;

        do{
            x = (int) (Math.random() * 8);
            y = (int) (Math.random() * 8);
        }while (game[x][y].isAlreadyShotAt(game, x, y));

        char xcoord = (char) (x+49);
        char ycoord = (char) (y+65);

        System.out.printf("The computer has launched its missile at %c%c\n", ycoord, xcoord);

        //comp hits your ship
        if (game[x][y].revealState(game, x, y) == 'S'){
            System.out.println("CAPT, WE'VE BEEN HIT!");
            playerLives --;
            game[x][y].isShotAt(game,x, y);
            return false;
        }
        //comp hits own ship
        else if (game[x][y].revealState(game, x, y) == 's'){
            System.out.println("THE COMP FRIENDLY FIRED HAHAHAHA");
            compLives --;
            game[x][y].isShotAt(game,x, y);
            return false;
        }
        //comp hits a grenade
        else if (game[x][y].revealState(game, x, y) == 'G' || game[x][y].revealState(game, x, y) == 'g'){
            System.out.println("THE COMP HIT A GRENADE HAHAHAHAHA");
            game[x][y].isShotAt(game,x,y);
            return true;
        }
        //comp hit same position
        else if (game[x][y].revealState(game, x, y) == '*'){
            System.out.println("COMP HIT THE SAME POSITION TWICE HAHAHAHAHA");
            game[x][y].isShotAt(game, x, y);
            return false;
        }
        //comp hit nothing
        else if (game[x][y].revealState(game, x, y) == '_'){
            System.out.println("COMP HIT NTH HAHAHAHAHA");
            game[x][y].isShotAt(game, x, y);
            return false;
        }
        return false;

    }

    //check for victory
    public static boolean checkVictory(){
        return((compLives > 0) && (playerLives > 0));
    }

    //player fire missile
    public static void startGame(){
        Grid[][] game = new Grid[8][8];
        String coords = "";
        int x,y = 0;
        boolean playerLostTurn = false;
        boolean compLostTurn = false;

        //Constructor loop
        for (int i = 0; i < GRID_ROWS; i++) {
            for (int j = 0; j < GRID_COLS; j++) {
                game[i][j] = new Grid();
            }
        }

        playerSetShipsNGrenades(game);
        compSetShipsNGrenades(game);

        System.out.println("Let the games begin!");

        //start the game proper
        while (checkVictory()){
            printGrid(game);
            if(playerLostTurn){
                System.out.println("Sorry Capt, you lost a turn");
                playerLostTurn = false;
            }
            else{
                //get the coordinates from the player
                do{
                    System.out.println("Please input your coordinates Capt: ");
                    coords = scan.next();
                    x = coords.charAt(1) - 49;
                    y = (int) Character.toUpperCase(coords.charAt(0)) - 65;

                    if (x > 7 || y > 7 || x < 0){
                        System.out.println("Sorry Capt, invalid coordinates. Try again.");
                        pressEnter();
                    }
                }while (x > 7 || y > 7 || x < 0);

                //player shot comp ship
                if (game[x][y].revealState(game, x, y) == 's'){
                    System.out.println("BINGO CAPT! You shot the computers's ship!!!!!!");
                    game[x][y].isShotAt(game, x, y);
                    compLives --;
                    pressEnter();
                }
                //player shot their own ship
                else if (game[x][y].revealState(game, x, y) == 'S'){
                    System.out.println("OH NO CAPT, we friendly fired! Our men are dying");
                    game[x][y].isShotAt(game, x, y);
                    playerLives --;
                    pressEnter();
                }

                //player shot at nothing
                else if(game[x][y].revealState(game, x, y) == '_'){
                    System.out.println("CAPT that was close, but your shot missed");
                    game[x][y].isShotAt(game, x, y);
                    pressEnter();
                }
                //player shot  at same position
                else if(game[x][y].isAlreadyShotAt(game, x, y)){
                    System.out.println("You hit the same position twice Capt");
                    pressEnter();
                }
                //player shot at grenade
                else if (game[x][y].revealState(game, x, y) == 'G' || game[x][y].revealState(game, x, y) == 'g'){
                    System.out.println("You hit a grenade Capt, you lost a turn...");
                    game[x][y].isShotAt(game, x, y);
                    playerLostTurn = true;
                    pressEnter();
                }
            }
            if (compLostTurn){
                System.out.println("The comp lost a turn, its your turn again Capt");
                compLostTurn = false;
                pressEnter();
            }
            else{
                compLostTurn = compFireMissile(game);
            }
        }
        if (compLives == 0){
            printVictory();
            revealedGrid(game);
            pressEnter();
        }
        else{
            System.out.println("You lost Capt, the president will give his speech in a while");
            revealedGrid(game);
            pressEnter();
        }

    }

    public static void printVictory(){
        System.out.println("VVVVVVVV           VVVVVVVVIIIIIIIIII      CCCCCCCCCCCCCTTTTTTTTTTTTTTTTTTTTTTT     OOOOOOOOO     RRRRRRRRRRRRRRRRR   YYYYYYY       YYYYYYY !!!"); 
        System.out.println("V::::::V           V::::::VI::::::::I   CCC::::::::::::CT:::::::::::::::::::::T   OO:::::::::OO   R::::::::::::::::R  Y:::::Y       Y:::::Y!!:!!");
        System.out.println("V::::::V           V::::::VI::::::::I CC:::::::::::::::CT:::::::::::::::::::::T OO:::::::::::::OO R::::::RRRRRR:::::R Y:::::Y       Y:::::Y!:::!");
        System.out.println("V::::::V           V::::::VII::::::IIC:::::CCCCCCCC::::CT:::::TT:::::::TT:::::TO:::::::OOO:::::::ORR:::::R     R:::::RY::::::Y     Y::::::Y!:::!");
        System.out.println("V:::::V           V:::::V   I::::I C:::::C       CCCCCCTTTTTT  T:::::T  TTTTTTO::::::O   O::::::O  R::::R     R:::::RYYY:::::Y   Y:::::YYY!:::!");
        System.out.println("V:::::V         V:::::V    I::::IC:::::C                      T:::::T        O:::::O     O:::::O  R::::R     R:::::R   Y:::::Y Y:::::Y   !:::!");
        System.out.println("V:::::V       V:::::V     I::::IC:::::C                      T:::::T        O:::::O     O:::::O  R::::RRRRRR:::::R     Y:::::Y:::::Y    !:::!");
        System.out.println("V:::::V     V:::::V      I::::IC:::::C                      T:::::T        O:::::O     O:::::O  R:::::::::::::RR       Y:::::::::Y     !:::!");
        System.out.println("V:::::V   V:::::V       I::::IC:::::C                      T:::::T        O:::::O     O:::::O  R::::RRRRRR:::::R       Y:::::::Y      !:::!");
        System.out.println("V:::::V V:::::V        I::::IC:::::C                      T:::::T        O:::::O     O:::::O  R::::R     R:::::R       Y:::::Y       !:::!");
        System.out.println("V:::::V:::::V         I::::IC:::::C                      T:::::T        O:::::O     O:::::O  R::::R     R:::::R       Y:::::Y       !!:!!");
        System.out.println("V:::::::::V          I::::I C:::::C       CCCCCC        T:::::T        O::::::O   O::::::O  R::::R     R:::::R       Y:::::Y        !!! ");
        System.out.println("V:::::::V         II::::::IIC:::::CCCCCCCC::::C      TT:::::::TT      O:::::::OOO:::::::ORR:::::R     R:::::R       Y:::::Y");            
        System.out.println("V:::::V          I::::::::I CC:::::::::::::::C      T:::::::::T       OO:::::::::::::OO R::::::R     R:::::R    YYYY:::::YYYY     !!!"); 
        System.out.println("V:::V           I::::::::I   CCC::::::::::::C      T:::::::::T         OO:::::::::OO   R::::::R     R:::::R    Y:::::::::::Y    !!:!!");
        System.out.println("VVV            IIIIIIIIII      CCCCCCCCCCCCC      TTTTTTTTTTT           OOOOOOOOO     RRRRRRRR     RRRRRRR    YYYYYYYYYYYYY     !!!"); 
        System.exit(0);
    }

}
