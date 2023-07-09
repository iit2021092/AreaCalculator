import java.util.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;

class area_calculating_algo {
    // defining the maximum no of sides for the Polygon
    static final int MAXSIDES = 200;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    static class Corner {
        double x, y;
    }

    static class Polygon {
        Corner p[] = new Corner[MAXSIDES];
        int n;

        Polygon() {
            for (int i = 0; i < MAXSIDES; i++)
                p[i] = new Corner();
        }
    }

    static double area(Polygon p) {
        double total = 0;
        for (int i = 0; i < p.n; i++) {
            int j = (i + 1) % p.n;
            total += (1 / 2.0) * (p.p[i].y + p.p[j].y) * (p.p[j].x - p.p[i].x);
            // System.out.println(total);
        }
        return total;
    }

    static public void main(String[] args) {
        Polygon p = new Polygon();

        Scanner sc = new Scanner(System.in);

        // Taking inputs from the user

        System.out.print("Enter number of sides of the Polygon: ");

        p.n = sc.nextInt();

        System.out.println("Enter the coordinates as : <x> <y>");

        // Taking the coordinates of each Corner
        for (int i = 0; i < p.n; i++) {
            p.p[i].x = sc.nextDouble();
            p.p[i].y = sc.nextDouble();
        }
        df.setRoundingMode(RoundingMode.UP);
        double area = area(p);

        if (area >= 0)
            System.out.print("The Area of Polygon with " + p.n + " points using Slicker Algorithm is : "+ df.format(area));
        else{
            area=area*(-1);
            System.out.print("The Area of Polygon with " + p.n + " points using Slicker Algorithm is : "+ df.format(area));
        }
            
        
        sc.close();
    }
}
