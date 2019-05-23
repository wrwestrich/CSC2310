public class Crust{

    private char size;
    private String type;

    public Crust(String type, char size){
        this.size = size;
        this.type = type;
    }

    public double crustCost(){
        CrustSize crustSize = CrustSize.valueOf(String.valueOf(size));
        CrustType crustType = CrustType.valueOf(type);

        return (crustSize.getCost() + crustType.getCost());
    }

    public String toString(){
        return "Size: " + size + "\n" + "Crust: " + type + "\n";
    }

    public char getCrust(){
        return size;
    }

    public String getType(){
        return type;
    }

    /*
    public void setCrust(char size){
        this.size = size;
    }

    public void setType(String type){
        this.type = type;
    }
    */
}
