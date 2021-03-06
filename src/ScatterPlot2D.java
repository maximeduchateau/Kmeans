import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class ScatterPlot2D extends JPanel {

    // Draw axis
    // Draw data collections (each Point as an elipse) in given colors
    // Draw centroids

    private void drawAxis(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        g2d.setPaint(new Color(150, 150, 150));

        g2d.fillRect(30, 20, 50, 50);
        g2d.fillRect(120, 20, 90, 60);
        g2d.fillRoundRect(250, 20, 70, 60, 25, 25);

        g2d.fill(new Ellipse2D.Double(10, 100, 80, 100));
        g2d.fillArc(120, 130, 110, 100, 5, 150);
        g2d.fillOval(270, 130, 50, 50);
    }

    private void drawCollections(Graphics g) {
        // TODO Draw collection
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        drawAxis(g);
        drawCollections(g);
    }
}
