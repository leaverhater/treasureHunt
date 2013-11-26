package ru.bmstu.treasureHunt;

/**
 * User: voznyuk
 * Date: 26.11.13
 * Time: 18:52
 */
public class Track {
    private int x;
    private int y;
    private boolean alive = true;
    private int lifeTime = 3;

    public Track(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void decLifeTime () {
        if (lifeTime > 0) {
            lifeTime--;
        } else {
            alive = false;
        }
    }

    public boolean isAlive() {
        return alive;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
