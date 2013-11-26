package ru.bmstu.treasureHunt;

/**
 * User: voznyuk
 * Date: 22.11.13
 * Time: 21:00
 */
public class Mine {
    private int x;
    private int y;
    private Map map;
    private Path pathToBase;
    private int resource = 100500;
    private static int num = 0;
    private int id;
    public Mine (int y, int x) {
        this.y = y;
        this.x = x;
        id = num;
        num++;
    }
    public void setMap (Map map) {
        this.map = map;
    }

    public void setPathToBase(Path path) {
        this.pathToBase = path;
    }

    public int getX () {
        return x;
    }

    public Path getPathToBase() {
        return pathToBase;
    }

    public int getY() {
        return y;
    }

    public void reduceResource () {
        resource -= 10;
        System.out.println("Mine: " + id + " Resource: " + resource);
    }

    public int getId() {
        return id;
    }
}
