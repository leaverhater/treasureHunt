package ru.bmstu.treasureHunt;


import javax.swing.*;

/**
 * User: voznyuk
 * Date: 22.11.13
 * Time: 20:54
 */
public class Main {
    private static int[][] intMap = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0},
            {0, 1, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0},
            {0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0},
            {0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0},
            {0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0},
            {0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0},
            {0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };
    private static Map map = new Map(intMap);
    public static void main( String[] args ) {
        System.out.println("Starting the app...");
        System.out.println("Initializing map...");
        map.setBase(1, 8);
        System.out.println("Initializing mines...");
        Mine mine0 = new Mine(1, 3);
        Mine mine1 = new Mine(7, 4);
        Mine mine2 = new Mine(8, 1);
        Mine mine3 = new Mine(12, 3);
        Mine mine4 = new Mine(12, 8);
        Mine mine5 = new Mine(1, 11);
        System.out.println("Initializing agents...");
        Agent agent0 = new Agent(1, 1);
        Agent agent1 = new Agent(7, 8);
        Agent agent2 = new Agent(10, 10);
        System.out.println("Adding mines...");
        map.addMine(mine0);
        map.addMine(mine1);
        map.addMine(mine2);
        map.addMine(mine3);
        map.addMine(mine4);
        map.addMine(mine5);
        System.out.println("Adding agents...");
        map.addAgent(agent0);
        map.addAgent(agent1);
        map.addAgent(agent2);
//        map.printDistanceMap();
        System.out.println("Starting UI...");
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                GraphicMap gm = new GraphicMap(map);
                gm.setVisible(true);
                TickThread tt = new TickThread(gm);
                tt.start();
            }
        });

    }
}
