package mx.edu.j2se.trinidad.evaluacion;

public class Evaluation1 {
    public static void main(String[] args) {
        // invalid circle
        try{
            Circle circle = new Circle(-1);
        }catch (IllegalArgumentException ex){
            //ex.printStackTrace(System.out);
            System.out.println(ex.getMessage());//print Exception
        }

        Circle[] circles ={new Circle(4), new Circle(7), new Circle()};

        int biggest = biggestCircle(circles);
        System.out.println("biggest = " + biggest);//1
    }

    public static int biggestCircle(Circle[] circles){
        int idx=-1;
        int currentRadio=-1;
        for (int i=0; i<circles.length;i++) {
            if(i==0){
                currentRadio = circles[i].getRadius();
            }else if(circles[i].getRadius() > currentRadio){
                idx = i;
            }
        }
        return idx;
    }
}
