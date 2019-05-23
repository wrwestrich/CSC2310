//Will Westrich and Seth Hunter

public class PegGameOverState implements PegState{

    private Pegs pegs;

    public PegGameOverState(Pegs pegs){
        this.pegs = pegs;
    }

    public void mouseClicked(int x, int y){

        int select = -1;
        while(select == -1){
            select = pegs.findSelectedPeg(x, y);
            if(select == -1)
                select = pegs.findSelectedSlot(x, y);
        }
        pegs.startGame(select);
    }
}
