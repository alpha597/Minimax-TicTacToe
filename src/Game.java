import java.util.*;
public class Game{
    board Board;
    Random random;

    public Game(){
        initializeGame();
        displayboard();
        makefirstMove();
        playGame();
        checkStatus();

    }

    public void checkStatus() {
        if (Board.isWinning(CellState.COMPUTER)) {
	        System.out.println("Computer has won");
	    } else if (Board.isWinning(CellState.USER)) {
	        System.out.println("Player has won");
	    } else {
	        System.out.println("It's a draw!");
	    }
    }

    public void playGame() {
       while(Board.isRunning()){
         System.out.println("User move");
         cell userCell = new cell(Board.getScanner().nextInt(), Board.getScanner().nextInt());
         Board.move(userCell, CellState.USER);
         displayboard();
         if(!Board.isRunning()){
             break;
         }
         Board.callMiniMax(0, CellState.COMPUTER);
         for(cell c:Board.getRootValues()){
            System.err.println("Cell Values "+ c.toString()+ "Minimax values"+ c.getMinimaxVal());
         }
         Board.move(Board.getBestMove(),CellState.COMPUTER);
         Board.printBoard();
       }
    }

    public void makefirstMove() {
        System.out.println("Who will make the first move : 1 for user , 2 for computer");
        int choice=Board.getScanner().nextInt();
        if(choice ==2){
            cell temp = new cell(random.nextInt(Board.board_size),random.nextInt(Board.board_size));
            Board.move(temp , CellState.COMPUTER);
            displayboard();
        }
    }

    public void displayboard() {
        Board.printBoard();
    }

    public void initializeGame() {
       this.Board = new board();
       this.Board.setUpBoard();
       this.random = new Random();
    }
}