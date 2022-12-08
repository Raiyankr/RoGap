package Entities;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.EventListener;

public class MeleeEnemy extends Enemy {
    public  BufferedImage[][] animations;
    private int spawnX, spawnY;
    private int xEnemy, yEnemy;
    private Rectangle hitBox;
    private String name;
    public Ellipse2D.Float attackHitRadius;
    public Ellipse2D.Float attackRadius;

    public MeleeEnemy(String name, int x, int y, int spawnX, int spawnY) {
        /**
         * Spawn the enemy at a x,y location and saves it.
         * xEnemy and yEnemy: is used for the visual aspect of enemy
         * spawnX and spawnY: is used for finding enemy relative to walls and players
         */
        this.name = name;
        this.xEnemy = x + spawnX;
        this.yEnemy = y + spawnY;
        this.spawnX = spawnX;
        this.spawnY = spawnY;

        initAttackHitRadius();
        initAttackRadius();
    }
    @Override
    public int getVisualX() {
        return xEnemy;
    }
    @Override
    public int getVisualY() {
        return yEnemy;
    }
    @Override
    public int getHelperX() {
        return spawnX;
    }
    @Override
    public int getHelperY() {
        return spawnY;
    }
    public void setVisualX(int xDelta) {
        this.xEnemy = xDelta + getHelperX();
    }
    public void setVisualY(int yDelta) {
        this.yEnemy = yDelta + getHelperY();
    }
    @Override
    public void changeHelperX(int x) {
        this.spawnX -= x;
    }
    @Override
    public void changeHelperY(int y) {
        this.spawnY -= y;
    }
    @Override
    public Rectangle getHitBox() {
        /**
         * Generates a rectangle around the enemy that can be used for collision and attacks
         */
        hitBox = new Rectangle(spawnX - 1280 + 4, spawnY - 720 + 4, 24, 24);
        return hitBox;
    }
    @Override
    public void setAnimations(BufferedImage[][] animations) {
        /**
         * Sets the current animations depending on which way the enemy is moving
         */
        this.animations = animations;
    }
    @Override
    public BufferedImage[][] getAnimations() {
        return this.animations;
    }

    public void initAttackHitRadius() {
        attackHitRadius = new Ellipse2D.Float(getHelperX(), getHelperY(), 28, 28);
    }

    public void updateAttackHitBox() {
        attackHitRadius.x = getHelperX();
        attackHitRadius.y = getHelperY();
    }

    @Override
    public Ellipse2D.Float getAttackHitRadius() {
        return attackHitRadius;
    }

    public void initAttackRadius() {
        attackRadius = new Ellipse2D.Float(attackHitRadius.x, attackHitRadius.y, attackHitRadius.width, attackHitRadius.height);
    }

    @Override
    public Ellipse2D.Float getAttackRadius() {
        return attackHitRadius;
    }

    @Override
    public void drawMonsterHitRadius(Graphics g) {
        g.setColor(Color.BLUE);
        g.drawOval((int) attackHitRadius.x, (int) attackHitRadius.y, (int) attackHitRadius.width, (int) attackHitRadius.height);
    }
    @Override
    public void drawMonsterAttackRadius(Graphics g) {
        g.setColor(Color.BLUE);
        g.drawOval((int) attackRadius.x, (int) attackRadius.y, (int) attackRadius.width, (int) attackRadius.height);
    }

}