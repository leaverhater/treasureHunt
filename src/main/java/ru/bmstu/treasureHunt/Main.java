package ru.bmstu.treasureHunt;


import javax.swing.*;

/**
 * User: voznyuk
 * Date: 22.11.13
 * Time: 20:54
 */
public class Main {
    private static int[][] intMap = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 1, 1, 1, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 1, 1, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 1, 0, 1, 0},
            {0, 1, 1, 1, 0, 1, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 0, 1, 1, 1, 0},
            {0, 1, 0, 1, 1, 1, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };
    private static Map map = new Map(intMap);
    public static void main( String[] args ) {
        map.setBase(1, 8);
        Mine mine0 = new Mine(1, 3);
        Mine mine1 = new Mine(7, 4);
        Agent agent0 = new Agent(1, 1);
        map.addMine(mine0);
        map.addMine(mine1);
        map.addAgent(agent0);
//        map.printDistanceMap();
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
