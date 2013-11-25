package ru.bmstu.treasureHunt;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * User: voznyuk
 * Date: 24.11.13
 * Time: 17:52
 */
class Surface extends JPanel {
    private GraphicMap gm;

    public Surface(GraphicMap gm) {
        this.gm = gm;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        gm.drawMap(g);
    }
}

public class GraphicMap extends JFrame {
    private Map map;
    private Color BLACK = new Color(0, 0, 0);
    private Color WHITE = new Color(255, 255, 255);
    private int cellSize = 32;
    private BufferedImage mineImg;
    private BufferedImage baseImg;
    private BufferedImage agentImg;
    private BufferedImage trackImg;
    private java.util.List<Mine> mines;
    private java.util.List<Agent> agents;
    private Graphics2D g2d;
    private Surface sf;

    public GraphicMap(Map map) {
        this.map = map;
        init();
        initUI();
    }

    private void init() {
        try {
            mineImg = ImageIO.read(new File("/mnt/ext/mine.png"));
            baseImg = ImageIO.read(new File("/mnt/ext/base.png"));
            agentImg = ImageIO.read(new File("/mnt/ext/agent.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mines = map.getMines();
        agents = map.getAgents();
    }

    private void initUI() {
        setTitle("Graphic Map");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sf = new Surface(this);
        add(sf);
        setSize(map.w * cellSize, map.h * cellSize);
        setLocationRelativeTo(null);

    }

    public void drawMap(Graphics g) {
        System.out.println("Initializing graphics");
        g2d = initGraphics(g);
        for (int i = 0; i < map.h; i++) {
            for (int j = 0; j < map.w; j++) {
                //Если проход
                if (map.mapValue(i, j)) {
                    g2d.setColor(WHITE);
                } else {
                    g2d.setColor(BLACK);
                }
                g2d.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
            }
        }
        g2d.setColor(WHITE);
        for (Mine mine : mines) {
            g2d.drawImage(mineImg, null, mine.getX() * cellSize, mine.getY() * cellSize);
        }
        for (Agent agent : agents) {
            g2d.drawImage(agentImg, null, agent.getX() * cellSize, agent.getY() * cellSize);
        }
        g2d.drawImage(baseImg, null, map.getBaseX() * cellSize, map.getBaseY() * cellSize);

    }

    private Graphics2D initGraphics(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHints(rh);
        return g2d;
    }

    public void moveAgent (Agent agent, int way) {
        int[] dy = {-1, 0, 1, 0};
        int[] dx = {0, 1, 0, -1};
        int destX = agent.getX() + dx[way];
        int destY = agent.getY() + dy[way];
        System.out.println("Moving an agent");
        g2d.fillRect(agent.getX() * cellSize, agent.getY() * cellSize, cellSize, cellSize);
        agent.setX(destX);
        agent.setY(destY);
        g2d.drawImage(agentImg, null, destX * cellSize, destY * cellSize);
//        sf.revalidate();
        sf.repaint();

    }

    public List<Agent> getAgents() {
        return agents;
    }

    private void moveAgents () {

    }
}