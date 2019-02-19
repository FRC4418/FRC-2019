package frc.robot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.List;

public class DrawGraph extends JPanel{
    public static int MAX_SCORE = 200;
    private static final int PREF_W = 40000;
    private static final int PREF_H = 650;
    private static final int BORDER_GAP = 30;
    private static final Color GRAPH_COLOR = Color.green;
    private static final Color GRAPH_POINT_COLOR = new Color(150, 50, 50, 180);
    private static final Stroke GRAPH_STROKE = new BasicStroke(3f);
    private static final int GRAPH_POINT_WIDTH = 5;
    private static final int Y_HATCH_CNT = 10;
    private java.util.List<Double> scores;
    private static JFrame frame = new JFrame("DrawGraph");
    private static double scale = 1;

    public DrawGraph(java.util.List<Double> scores) {
        this.scores = scores;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.translate(getWidth()/2,getHeight()/2);
        g2.scale(scale, scale);
        g2.translate(-getWidth()/2,-getHeight()/2);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double xScale = ((double) getWidth() - 2 * BORDER_GAP) / (scores.size() - 1);
        double yScale = ((double) getHeight() - 2 * BORDER_GAP) / (MAX_SCORE - 1);

        List<Point> graphPoints = new ArrayList<Point>();
        for (int i = 0; i < scores.size(); i++) {
            int x1 = (int) (i * xScale + BORDER_GAP);
            int y1 = (int) ((MAX_SCORE - scores.get(i)) * yScale + BORDER_GAP);
            graphPoints.add(new Point(x1, y1));
        }

        // create x and y axes
        g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, BORDER_GAP, BORDER_GAP);
        g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, getWidth() - BORDER_GAP, getHeight() - BORDER_GAP);

        int x10 = BORDER_GAP;
        int x11 = getWidth() - BORDER_GAP;
        int y10 = getHeight() - (((4 + 1) * (getHeight() - BORDER_GAP * 2)) / Y_HATCH_CNT + BORDER_GAP);
        int y11 = y10;
        g2.drawLine(x10, y10, x11, y11);

        // create hatch marks for y axis.
        for (int i = 0; i < Y_HATCH_CNT; i++) {
            int x0 = BORDER_GAP;
            int x1 = GRAPH_POINT_WIDTH + BORDER_GAP;
            int y0 = getHeight() - (((i + 1) * (getHeight() - BORDER_GAP * 2)) / Y_HATCH_CNT + BORDER_GAP);
            int y1 = y0;
            g2.drawLine(x0, y0, x1, y1);
        }

        // and for x axis
        for (int i = 0; i < scores.size() - 1; i++) {
            int x0 = (i + 1) * (getWidth() - BORDER_GAP * 2) / (scores.size() - 1) + BORDER_GAP;
            int x1 = x0;
            int y0 = getHeight() - BORDER_GAP;
            int y1 = y0 - GRAPH_POINT_WIDTH;
            g2.drawLine(x0, y0, x1, y1);
        }

        Stroke oldStroke = g2.getStroke();
        g2.setColor(GRAPH_COLOR);
        g2.setStroke(GRAPH_STROKE);
        for (int i = 0; i < graphPoints.size() - 1; i++) {
            int x1 = graphPoints.get(i).x;
            int y1 = graphPoints.get(i).y;
            int x2 = graphPoints.get(i + 1).x;
            int y2 = graphPoints.get(i + 1).y;
            g2.drawLine(x1, y1, x2, y2);
        }

        g2.setStroke(oldStroke);
        g2.setColor(GRAPH_POINT_COLOR);
        for (int i = 0; i < graphPoints.size(); i++) {
            int x = graphPoints.get(i).x - GRAPH_POINT_WIDTH / 2;
            int y = graphPoints.get(i).y - GRAPH_POINT_WIDTH / 2;
            ;
            int ovalW = GRAPH_POINT_WIDTH;
            int ovalH = GRAPH_POINT_WIDTH;
            g2.fillOval(x, y, ovalW, ovalH);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }

    @Override
    protected void processKeyEvent(KeyEvent k) {
        switch( k.getKeyCode() ) { 
            case KeyEvent.VK_UP:
                frame.setLocation(frame.getLocation().x, frame.getLocation().y-1);
                break;
            case KeyEvent.VK_DOWN:
                frame.setLocation(frame.getLocation().x, frame.getLocation().y-1);
                break;
            case KeyEvent.VK_LEFT:
                frame.setLocation(frame.getLocation().x, frame.getLocation().y-1);
                break;
            case KeyEvent.VK_RIGHT:
                frame.setLocation(frame.getLocation().x, frame.getLocation().y-1);
                break;
         }
    }

    public static void createAndShowGui(ArrayList<Double> scores) {

        DrawGraph mainPanel = new DrawGraph(scores);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        frame.addMouseWheelListener(new MouseWheelListener(){
            @Override
            public void mouseWheelMoved(MouseWheelEvent m) {
                if(m.getWheelRotation() > 0){
                    scale+=0.05;
                    frame.repaint();
                }
                if(m.getWheelRotation() < 0){
                    scale-=0.05;
                    frame.repaint();
                }
            }
        });
    }

    public static void updateGui(ArrayList<Double> scores){
        frame.getContentPane().remove(0);
        DrawGraph mainPanel = new DrawGraph(scores);
        frame.getContentPane().setVisible(false);
        frame.getContentPane().add(mainPanel);
        frame.getContentPane().setVisible(true);
        frame.revalidate();
        frame.repaint();
    }

    public static void clearContent(){
        DrawGraph mainPanel = new DrawGraph(new ArrayList<Double>());
    }
}

class graphFrame extends javax.swing.JFrame{
    public graphFrame(){

    }
}