import java.awt.*;
import java.util.Random;

public class Triangles {

    private CustomCanvas c;

    public static void main (String[] args){
        Triangles t = new Triangles();
        t.drawTriangle(720, 100, 650, Color.white);
        //t.drawTriangle(360, 100,300, -1);
    }


    public Triangles (){
        c = new CustomCanvas("Triangles", 1280, 720);
        c.setVisible();
        c.setSize(500, 500);
    }

    private void drawTriangle(int length, int xpos, int ypos, Color col){
        col = getColor(col);
        int x1 = xpos;
        int y1 = ypos;

        int x2 = xpos + length;
        int y2 = y1;

        int xm = (x1+x2)/2;
        int ym = y1;

        int h = (int) ((length/2)*Math.sqrt(3));

        int y3 = ym - h;
        int x3 = xm;

        int[] xa = {x1, x2, x3};
        int[] ya = {y1, y2, y3};
        Polygon p = new Polygon (xa, ya, 3);

        c.draw(p,col);

        /*c.drawLine(x1, y1, x2, y2);
        c.drawLine(x1, y1, x3, y3);
        c.drawLine(x2, y2, x3, y3);*/


        int newlength = length/2;
        int newx = (x1+x3)/2;
        int newy = (y1+y3)/2;

        if (length>10) {
            drawTriangle(newlength, newx, newy, col);
            drawTriangle(newlength, x1, y1, col);
            drawTriangle(newlength, xm, ym, col);
        }
    }

    private Color getColor(Color col) {
        return col.darker();
    }
}
