import javax.swing.*;
import java.awt.*;

public class CustomCanvas {

    private JFrame frame;
    private CanvasPane canvas;
    private Graphics2D graphic;
    private Color backgroundColor;

    private Image canvasImage;
    public CustomCanvas(String title)
    {
        this(title, 300, 300, Color.white);
    }

    public CustomCanvas(String title, int width, int height)
    {
        this(title, width, height, Color.white);
    }

    public CustomCanvas(String title, int width, int height, Color bgColor)
    {
        frame = new JFrame();
        canvas = new CanvasPane();
        frame.setContentPane(canvas);
        frame.setTitle(title);
        canvas.setPreferredSize(new Dimension(width, height));
        backgroundColor = bgColor;
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.pack();
    }

    void setSize(int width, int height){
        Dimension dim = new Dimension(width, height);
        frame.setResizable(true);
        frame.setSize(dim);
        canvas.setPreferredSize(dim);
        canvas.setSize(dim);

        setVisible();
    }

    void setVisible()
    {
        if(graphic == null) {
            Dimension size = canvas.getSize();
            canvasImage = canvas.createImage(size.width, size.height);
            graphic = (Graphics2D)canvasImage.getGraphics();
            graphic.setColor(backgroundColor);
            graphic.fillRect(0, 0, size.width, size.height);
            graphic.setColor(Color.black);
        }
        frame.setVisible(true);
    }

    public boolean isVisible()
    {
        return frame.isVisible();
    }

    public void drawLine(int x1, int y1, int x2, int y2)
    {
        graphic.drawLine(x1, y1, x2, y2);
        canvas.repaint();
    }

    public void draw(Polygon shape, Color c)
    {
        graphic.setColor(c);
        graphic.draw(shape);
        graphic.fillPolygon(shape);
        canvas.repaint();
    }



    private class CanvasPane extends JPanel
    {
        public void paint(Graphics g)
        {
            g.drawImage(canvasImage, 0, 0, null);
        }
    }
}
