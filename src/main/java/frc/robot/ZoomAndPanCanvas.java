package frc.robot;

import java.awt.*;
import java.awt.geom.AffineTransform;
import javax.swing.*;

/**
 * Canvas displaying a simple drawing: the coordinate-system axes + some points and their coordinates.
 * It is used to demonstrate the Zoom and Pan functionality.
 *
 * @author Sorin Postelnicu
 * @since July 13, 2009
 */

public class ZoomAndPanCanvas extends Canvas {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Zoom and Pan Canvas");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        ZoomAndPanCanvas chart = new ZoomAndPanCanvas();

        frame.add(chart, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
        chart.createBufferStrategy(2);
    }

    private boolean init = true;

    private Point[] points = {
            new Point(-100, -100),
            new Point(-100, 100),
            new Point(100, -100),
            new Point(100, 100)
    };

    private ZoomAndPanListener zoomAndPanListener;

    public ZoomAndPanCanvas() {
        this.zoomAndPanListener = new ZoomAndPanListener(this);
        this.addMouseListener(zoomAndPanListener);
        this.addMouseMotionListener(zoomAndPanListener);
        this.addMouseWheelListener(zoomAndPanListener);
    }

    public ZoomAndPanCanvas(int minZoomLevel, int maxZoomLevel, double zoomMultiplicationFactor) {
        this.zoomAndPanListener = new ZoomAndPanListener(this, minZoomLevel, maxZoomLevel, zoomMultiplicationFactor);
        this.addMouseListener(zoomAndPanListener);
        this.addMouseMotionListener(zoomAndPanListener);
        this.addMouseWheelListener(zoomAndPanListener);
    }

    public Dimension getPreferredSize() {
        return new Dimension(600, 500);
    }

    public void paint(Graphics g1) {
        Graphics2D g = (Graphics2D) g1;
        if (init) {
            init = false;
            zoomAndPanListener.setCoordTransform(g.getTransform());
        } else {
            // Restore the viewport after it was updated by the ZoomAndPanListener
            g.setTransform(zoomAndPanListener.getCoordTransform());
        }

        
        // Create an "upside-down" font to correct for the inverted y-axis
        Font font = g.getFont();
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.scale(1, -1);
        g.setFont(font.deriveFont(affineTransform));
        // Draw the points and their coordinates
        

    }



}