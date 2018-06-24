import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;

public class RecursiveTriangles implements ComponentListener {


    public static void main (String[] args){
        new RecursiveTriangles("Triangles", 1280, 720).setVisible();
    }

    private JFrame frame;
    private CanvasPane canvas;
    private Graphics2D graphic;
    private Color backgroundColor;
    private int limit;
    private boolean rnd;

    private Image canvasImage;

    public RecursiveTriangles(String title, int width, int height){
        limit = 10;
        rnd = false;
        frame = new JFrame();
        canvas = new CanvasPane();
        frame.setContentPane(canvas);
        frame.setTitle(title);
        canvas.setPreferredSize(new Dimension(width, height));
        backgroundColor = Color.white;
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        //frame.addWindowStateListener(e -> setVisible());
        frame.addComponentListener(this);
        frame.pack();
    }

    void setVisible() {
        if(graphic == null && !frame.isVisible()) {
            Dimension size = canvas.getSize();
            sizeHandler(size);
        }
        frame.setVisible(true);
    }


    void drawTriangle(int length, int xpos, int ypos, int stageID, boolean random){

        Color col;
        if (random) col = randomColor();
        else col = getColor(stageID);

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

        graphic.setColor(col);
        graphic.draw(p);
        graphic.fillPolygon(p);

        graphic.setColor(Color.WHITE);
        graphic.drawLine(x1, y1, x2, y2);
        graphic.drawLine(x1, y1, x3, y3);
        graphic.drawLine(x2, y2, x3, y3);

        int newlength = length/2;
        int newx = (x1+x3)/2;
        int newy = (y1+y3)/2;

        if (length>limit) {
            stageID++;
            drawTriangle(newlength, newx, newy, stageID, random);
            drawTriangle(newlength, x1, y1, stageID, random);
            drawTriangle(newlength, xm, ym, stageID, random);
        }
    }



    private ArrayList<Color> colors = new ArrayList<>();
    private Color getColor(int stageID){
        if (colors.size()>stageID){
            return colors.get(stageID);
        } else {
            colors.add(randomColor());
            return colors.get(stageID);
        }
    }

    private Color randomColor() {
        int r = (int) (Math.random()*255);
        int g = (int) (Math.random()*255);
        int b = (int) (Math.random()*255);

        return new Color(r,g,b);
    }

    private void sizeHandler(Dimension size) {
        canvasImage = canvas.createImage(size.width, size.height);
        graphic = (Graphics2D) canvasImage.getGraphics();
        graphic.setColor(backgroundColor);
        graphic.fillRect(0, 0, size.width, size.height);
        graphic.setColor(Color.black);
    }

    @Override
    public void componentResized(ComponentEvent e) {
        Dimension size = frame.getSize();
        sizeHandler(size);
        canvas.setPreferredSize(size);
        canvas.repaint();

    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }

    private class CanvasPane extends JPanel
    {
        public void paint(Graphics g)
        {
            Dimension size = frame.getSize();
            g.fillRect(0, 0, size.width, size.height);
            if (size.height<= size.width){
                drawTriangle(size.height-(int)(size.height*0.1), (int) (size.width*0.05), size.height-(int) (size.height*0.1), 0, rnd);
            }
            else {
                drawTriangle(size.width-(int)(size.width*0.1), (int) (size.width*0.05), size.height - (int) (size.height*0.1), 0, rnd);
            }
            g.drawImage(canvasImage, 0, 0, null);

        }
    }
}
