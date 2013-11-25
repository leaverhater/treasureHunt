package ru.bmstu.treasureHunt;

import java.util.List;

/**
 * User: voznyuk
 * Date: 24.11.13
 * Time: 21:08
 */
public class TickThread extends Thread {
    private GraphicMap gm;
    private List<Agent> agents;
    public TickThread (GraphicMap gm) {
        this.gm = gm;
        agents = gm.getAgents();
    }
    @Override
    public void run()
    {
        tickStep();
        tickStep();
        tickStep();
        tickStep();
    }

    public void tickStep () {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        doGlobalMovement();
    }
    public void doGlobalMovement () {
        for (Agent agent : agents) {
            gm.moveAgent(agent, 2);
        }
    }
}
