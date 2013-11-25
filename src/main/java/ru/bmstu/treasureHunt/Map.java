package ru.bmstu.treasureHunt;

import java.util.ArrayList;
import java.util.List;

/**
 * User: voznyuk
 * Date: 22.11.13
 * Time: 21:04
 */
public class Map {
    //Высота и ширина карты
    public int h;
    public int w;

    public int getBaseX() {
        return baseX;
    }

    public int getBaseY() {

        return baseY;
    }

    //Координаты базы
    private int baseY;
    private int baseX;

    //Массив лабиринта. True - проход, false - стена
    private boolean [][] map;

    private int[][] distanceMap;


    //Список рудников
    private List<Mine> mines = new ArrayList<Mine>();

    //Список агентов на карте
    private List<Agent> agents = new ArrayList<Agent>();

    private static boolean[][] intMapToBool(int[][] map) {
        boolean[][] boolMap = new boolean[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == 0) {
                    boolMap[i][j] = false;
                } else
                    boolMap[i][j] = true;
            }
        }
        return boolMap;
    }

    public Map(boolean [][] map) {
        this.map = map;
        this.h = map.length;
        this.w = map[0].length;
    }

    public Map (int [][] map) {
        this(intMapToBool(map));
    }

    //True если выходит за рамки матрицы лабиринта
    private boolean ifOutside (int h, int w) {
        if (h > this.h - 1 || w > this.w - 1 || h < 0 || w < 0)
            return true; else
            return false;
    }

    public boolean mapValue (int h, int w) {
        return map[h][w];
    }

    public void addMine (Mine mine) {
        mine.setMap(this);
        createPathToBase(mine);
        this.mines.add(mine);
//        System.out.print("Added mine to map. Path to base: ");
//        mine.getPathToBase().printPath();
//        System.out.println();
    }

    public List<Mine> getMines() {
        return mines;
    }

    public void setBase (int y, int x) {
        baseY = y;
        baseX = x;
        createDistanceMap();
    }

    private void createDistanceMap () {

        int d = 1;
        distanceMap = new int[h][w];
        distanceMap [baseY][baseX] = d;
        int[] dy = {-1, 0, 1, 0};
        int[] dx = {0, 1, 0, -1};
        boolean stop = false;
        while (!stop) {
            stop = true;
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (distanceMap[i][j] == d) {
                        for (int z = 0; z < 4; z++) {
                            if (map[i + dy[z]][j + dx[z]] && (distanceMap[i + dy[z]][j + dx[z]] == 0)) {
                                distanceMap [i + dy[z]][j + dx[z]] = d + 1;
                                stop = false;
                            }
                        }
                    }
                }
            }
            d++;
        }
    }

    public void printDistanceMap () {
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                System.out.print(distanceMap[i][j] + " ");
            }
            System.out.println();
        }
    }



    private void printMap (boolean[][] map) {
        for (int i = 0; i < this.h; i++) {
            for (int j = 0; j < this.w; j++) {
                if (map[i][j])
                    System.out.print("1 ");
                else
                    System.out.print("0 ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private void createPathToBase (Mine mine) {
        Path path = new Path();
        int y = mine.getY();
        int x = mine.getX();
        int d = distanceMap[y][x];
//        System.out.println("Distance to mine: " + (d - 1));
        int[] dy = {-1, 0, 1, 0};
        int[] dx = {0, 1, 0, -1};
        while (d != 1) {
            for (int i = 0; i < 4; i++) {
                if (distanceMap[y + dy[i]][x + dx[i]] == d - 1) {
                    path.addWay(i);
                    y = y + dy[i];
                    x = x + dx[i];
//                    System.out.println("Added way " + i);
                    d--;
                    break;
                }
            }
        }
        mine.setPathToBase(path);
    }

    public List<Agent> getAgents() {
        return agents;
    }

    public void addAgent (Agent agent) {
        agent.setMap(this);
        this.agents.add(agent);
    }
}
