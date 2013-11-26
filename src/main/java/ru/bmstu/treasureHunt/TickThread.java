package ru.bmstu.treasureHunt;

import java.util.ArrayList;
import java.util.List;

/**
 * User: voznyuk
 * Date: 24.11.13
 * Time: 21:08
 */
public class TickThread extends Thread {
    private GraphicMap gm;
    private List<Agent> agents;
    private int[] dy = {-1, 0, 1, 0};
    private int[] dx = {0, 1, 0, -1};
    public TickThread (GraphicMap gm) {
        this.gm = gm;
        agents = gm.getAgents();
    }
    @Override
    public void run()
    {
       for (;;) {
           tickStep();
       }
    }

    public void tickStep () {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        doGlobalMovement();
    }
    public void doGlobalMovement () {
        for (Agent agent : agents) {
            if (agent.isIfBack()) {
//                System.out.println(agent.isIfBack());
                gm.moveAgent(agent, agent.nextStepToBase());
                agent.processTracks();
            } else {
//                System.out.println(agent.isIfBack());
                ArrayList<Integer> availableWays = new ArrayList<Integer>();
                int nextX = agent.getPrevX();
                int nextY = agent.getPrevY();
                int nextWay = 0;
                for (int i = 0; i < 4; i++) {
                    if (gm.getMap().isAvailableWay(agent.getX()+dx[i], agent.getY()+dy[i])) {
                        availableWays.add(i);
                    }
                }
                if (availableWays.size() == 1) {
                    nextWay = availableWays.get(0);
                } else if (availableWays.size() == 0) {

                } else {
                    while ((nextX == agent.getPrevX()) && (nextY == agent.getPrevY())) {
                        nextWay = availableWays.get((int)(Math.random() * (availableWays.size())));
                        nextX = agent.getX() + dx[nextWay];
                        nextY = agent.getY() + dy[nextWay];
                    }
                }
                if (gm.getMap().isMine(nextX, nextY)) {
//                    System.out.println("Found mine! Going to base");
                    Mine mine = gm.getMap().getMineByXY(nextX, nextY);
                    Path path = mine.getPathToBase();
                    mine.reduceResource();
                    agent.setIfBack(true, path);
                    agent.setWithTrack(true);
                } else if (gm.getMap().isBase(nextX, nextY)) {

                } else {
                    if (availableWays.size() != 0) {
                        gm.moveAgent(agent, nextWay);
                        agent.processTracks();
                    }
                }
            }

        }
    }

}
