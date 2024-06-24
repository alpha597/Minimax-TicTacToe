public class cell{
    private int x;
    private int y;
    private int minimaxVal;

    public cell(int x,int y){
        this.x = x;
        this.y =y;
    }
    public int getMinimaxVal() {
        return minimaxVal;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setMinimaxVal(int minimaxVal) {
        this.minimaxVal = minimaxVal;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    @Override
    public String toString(){
        return (getX()+", "+getY());
    }
}