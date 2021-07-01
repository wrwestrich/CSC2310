
import java.util.ArrayList;

public class WorstCDs implements Command{

    private ArrayList<CD> worst = new ArrayList<CD>();
    private int worstRating;

    public WorstCDs(){
        worstRating = 10;
    }

    public void execute(Object node){

        CD cd = (CD)node;
        if(cd.getRating() < worstRating){
            worstRating = cd.getRating();
            worst = new ArrayList<CD>();
            worst.add(cd);
        }
        else if(cd.getRating() == worstRating)
            worst.add(cd);
    }

    public ArrayList<CD> getWorstCDs(){
        return worst;
    }

}
