// Will Westrich and Seth Hunter


public class Cone {
    private int coneType;

    public Cone(){}

    public Cone(int type) {
        if(type < 1 || type > 3)
            coneType = 3;
        else
            coneType = type;
    }

    public double price(){
        if(coneType == 1)
            return 0.59;
        else if(coneType == 2)
            return 0.79;
        else
            return 0;
    }

    public String toString(){
        String type = "";

        if(coneType == 1)
            type = "Cone: Sugar";
        else if(coneType == 2)
            type = "Cone: Waffle";
        else
            type = "Cone: Cup";

        return type;
    }

}
