import java.util.*;
public class board {
    private List<cell> rootValues;
    private CellState[][] Board;
    private Scanner scanner;
    private List<cell> emptyCells;
    public final static int board_size =3;

    public board(){
        initializeBoard();
    }

    public void initializeBoard() {
        rootValues = new ArrayList<>();
        scanner = new Scanner(System.in);
        Board = new CellState[board_size][board_size];
    }
    public void printBoard(){
        for(int i=0;i<board_size;i++){
            for(int j=0;j<board_size;j++){
                System.out.print(Board[i][j]+" ");
            }
            System.out.println();
        }
    }
    public boolean isWinning(CellState p){
        if(Board[0][0] ==p && Board[1][1]==p && Board[2][2]==p){
            return true;
        }
        if(Board[2][0]==p && Board[1][1]==p && Board[0][2]==p){
            return true;
        }
        for(int i=0;i<board_size;i++){
            if(Board[i][0]==p && Board[i][1]==p && Board[i][2]==p){
                return true;
            }
        }
        for(int j=0;j<board_size;j++){
            if(Board[0][j]==p && Board[1][j]==p && Board[2][j]==p){
                return true;
            }
        }
        return false;

    }
    public boolean isRunning(){
        if(isWinning(CellState.COMPUTER)){
            return false;
        }
        if(isWinning(CellState.USER)){
            return false;
        }
        List<cell> temp = getEmptyCells();
        if(temp.size()==0){
            return false;
        }
        return true;
    }
    public cell getBestMove(){
        int max = Integer.MIN_VALUE;
        int best = Integer.MIN_VALUE;
        for(int a=0;a<rootValues.size();a++){
            if(rootValues.get(a).getMinimaxVal() > max){
                max = rootValues.get(a).getMinimaxVal();
                best = a;
            }
        }
        return rootValues.get(best);
    }

    public void setAvailablePoints(List<cell> availableCells){
        emptyCells= availableCells;
    }

    public List<cell> getRootValues() {
        return rootValues;
    }

    public void setRootValues(List<cell> rootValues) {
        this.rootValues = rootValues;
    }
    public int getMax(List<Integer> list){
        int val = Integer.MIN_VALUE;
        int ind =0;
        for(int a=0;a<list.size();a++){
            if(list.get(a)>val){
                val =list.get(a);
                ind =a;
            }
        }
        return list.get(ind);
    }
    public int getMin(List<Integer> list){
        int val = Integer.MAX_VALUE;
        int ind =0;
        for(int a=0;a<list.size();a++){
            if(list.get(a)<val){
                val = list.get(a);
                ind = a;
            }
        }
        return list.get(ind);

    }
    public void callMiniMax(int depth, CellState c){
        rootValues.clear();
        miniMax(depth,c);
    }

    private int miniMax(int depth, CellState c) {
        if(isWinning(CellState.COMPUTER)){
            return 1;
        }
        if(isWinning(CellState.USER)){
            return -1;
        }
        List<cell> available = getEmptyCells();
        if(available.size()==0){
            return 0;
        }
        List<Integer> scores = new ArrayList<>();
        for(int a=0;a<available.size();a++){
            cell temp = available.get(a);
            if(c == CellState.COMPUTER){
                move(temp,c);
                int currentscore=miniMax(depth+1, CellState.USER);
                scores.add(currentscore);
                if(depth==0){
                    temp.setMinimaxVal(currentscore);
                    scores.add(currentscore);
                    rootValues.add(temp);
                }
            }
            else if(c==CellState.USER){
                move(temp,c);
                scores.add(miniMax(depth+1, CellState.COMPUTER));
            }
            Board[temp.getX()][temp.getY()]= CellState.EMPTY;
        }
        if(c==CellState.COMPUTER){
            return getMax(scores);
        }
        return getMin(scores);
    }

    public List<cell> getEmptyCells() {
        emptyCells = new ArrayList<>();
        for(int i=0;i<board_size;i++){
            for(int j=0;j<board_size;j++){
                if(Board[i][j] == CellState.EMPTY){
                    emptyCells.add(new cell(i,j));
                }
            }
        }
        return emptyCells;
    }
     
    public void move(cell c, CellState state){
          Board[c.getX()][c.getY()] = state;
    }
    public void setUpBoard(){
        for(int i=0;i<board_size;i++){
            for(int j=0;j<board_size;j++){
                Board[i][j] = CellState.EMPTY; 
            }
        }
    }
    public void userInput(){
        System.out.println("Enter user input");
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        cell point = new cell(x,y);
        move(point,CellState.USER);
    }

    public void setEmptyCells(List<cell> emptyCells) {
        this.emptyCells = emptyCells;
    }

    public static int getBoardSize() {
        return board_size;
    }

    public Scanner getScanner() {
        return scanner;
    }
    
}
