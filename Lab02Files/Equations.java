// Will Westrich & Seth Hunter
// CSC2400-104

import matrix.*;

public class Equations{

    public static void main(String args[]) {

        MatrixOperationsInterface A = MatrixCreator.create(3, 3);
        MatrixOperationsInterface x = MatrixCreator.create(3, 1);
        MatrixOperationsInterface b = MatrixCreator.create(3, 1);

        A.setElement(1, 1, 1.6);
        A.setElement(1, 2, 2.4);
        A.setElement(1, 3, -3.7);
        A.setElement(2, 1, 17.6);
        A.setElement(2, 2, -5.6);
        A.setElement(2, 3, 0.05);
        A.setElement(3, 1, -2.0);
        A.setElement(3, 2, 2.0);
        A.setElement(3, 3, 25.3);

        b.setElement(1, 1, -22.1);
        b.setElement(2, 1, -4.35);
        b.setElement(3, 1, 233.7);

        A = A.inverse();
        x = A.multiply(b);
        
        System.out.println(x.getElement(1, 1));
        System.out.println("\n");
        System.out.println(x.getElement(2, 1));
        System.out.println("\n");
        System.out.println(x.getElement(3, 1));
    }

}
