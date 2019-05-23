enum CrustSize{
    S (5.99), M (7.99), L (9.99);

    private double cost;

    CrustSize(double cost){
        this.cost = cost;
    }

    double getCost(){
        return cost;
    }
}

enum CrustType{
    THIN (0.00), HAND (0.50), PAN (1.00);

    private double cost;

    CrustType(double cost){
        this.cost = cost;
    }

    double getCost(){
        return cost;
    }
}
