package ru.bmstu.treasureHunt;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
    private List <BufferedImage> agentImgs = new ArrayList<BufferedImage>();
    private BufferedImage agentImg;
    private BufferedImage trackImg;
    private java.util.List<Mine> mines;
    private java.util.List<Agent> agents;

    private Graphics2D g2d;
    private Surface sf;

    private int TRACK_NUM = 3;



    public GraphicMap(Map map) {
        this.map = map;
        init();
        initUI();
    }

    private void init() {
        try {
            mineImg = ImageIO.read(new File("/mnt/ext/mine.png"));
            baseImg = ImageIO.read(new File("/mnt/ext/base.png"));
            agentImgs.add(ImageIO.read(new File("/mnt/ext/agent.jpg")));
            agentImgs.add(ImageIO.read(new File("/mnt/ext/agent1.gif")));
            agentImgs.add(ImageIO.read(new File("/mnt/ext/agent2.jpg")));
            agentImg = ImageIO.read(new File("/mnt/ext/agent.jpg"));
            trackImg = ImageIO.read(new File("/mnt/ext/coin.gif"));
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
//        System.out.println("Initializing graphics");
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
            g2d.drawImage(agentImgs.get(agent.getId() % agents.size()), null, agent.getX() * cellSize, agent.getY() * cellSize);
            for (Track track : agent.getTracks()) {
                g2d.drawImage(trackImg, null, track.getX() * cellSize, track.getY() * cellSize);
            }

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
        agent.setPrevX(agent.getX());
        agent.setPrevY(agent.getY());
        agent.setX(destX);
        agent.setY(destY);
        sf.repaint();

    }

    public List<Agent> getAgents() {
        return agents;
    }

    private void moveAgents () {

    }
    public Map getMap() {
        return map;
    }

    public int getTRACK_NUM() {
        return TRACK_NUM;
    }
}