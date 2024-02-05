public class Grid {

    private boolean isGrenade;
    private boolean isPship;
    private boolean isCship;
    private boolean shotAt;
    private char state;

    public Grid(){
        isGrenade = false;
        isPship = false;
        isCship = false;
        shotAt = false;
        state = '_';
    }

    //sets a player ship
    public void setPship(Grid[][] a, int i, int j){
        a[i][j].isPship = true;
        a[i][j].state = 'S';
    }
    //sets a computer ship
    public void setCship(Grid[][] a, int i, int j){
        a[i][j].isCship = true;
        a[i][j].state = 's';
    }
    //sets a player grenade
    public void setPGrenade(Grid[][] a, int i, int j){
        a[i][j].state = 'G';
    }
    //sets a computer grenade
    public void setCGrenade(Grid[][] a, int i, int j){
        a[i][j].state = 'g';
    }
    //check if position already shot at
    public boolean isAlreadyShotAt(Grid[][] a, int i, int j){
        return a[i][j].shotAt;
    }
    //change cell state to shot
    public void isShotAt(Grid[][] a, int i, int j){
        a[i][j].state = '*';
        a[i][j].shotAt = true;;
    }
    //returns whether a cell is already occupied when setting ships/grenades
    public boolean isOccupied(){
        return(this.state!='_');
    }
    //prints out the cell's state
    public char printState(Grid[][] a, int i, int j){
        if (a[i][j].shotAt == true){
            return a[i][j].state;
        }
        else{
            return '_';
        }
    }
    //check the state of a cell
    public char revealState(Grid[][] a, int i, int j){
        return a[i][j].state;
    }
}
