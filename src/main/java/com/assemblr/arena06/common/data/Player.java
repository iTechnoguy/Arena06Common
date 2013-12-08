package com.assemblr.arena06.common.data;

import com.assemblr.arena06.common.data.map.generators.MapGenerator;
import com.assemblr.arena06.common.data.weapon.Weapon;
import com.assemblr.arena06.common.utils.Fonts;
import com.assemblr.arena06.common.utils.Serialize;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Player extends Sprite {
    
    private final boolean self;
    private boolean clientIsCurrent = true;
    private Weapon weapon = Weapon.BERETTA_93R;
    
    @Serialize private int loadedBullets;
    private double cooldownRemaining;
    private double reloadRemaining;
    @Serialize private boolean isReloading;
    @Serialize private double life = 1;
    @Serialize private boolean alive = false;
    @Serialize private String name;
    
    public Player() {
        this(false, "Player");
    }
    
    public Player(boolean self, String name) {
        this.self = self;
        this.name = name;
        width = height = MapGenerator.TILE_SIZE - 10;
        loadedBullets = weapon.getMagSize();
    }
    
    public Color getColor() {
        return new Color(name.hashCode());
    }
    
    public void render(Graphics2D g) {
        if (!alive) {
            return;
        }
        g.setColor(getColor());
        g.fill(new Rectangle2D.Double(0, 0, width, height));
        
        if (!self) {
            Font f = Fonts.FONT_PRIMARY.deriveFont(8f);
            FontMetrics metrics = g.getFontMetrics(f);
            Rectangle2D bounds = metrics.getStringBounds(name, g);
            
            g.setColor(new Color(0x88000000, true));
            g.fillRect((int) (-bounds.getWidth()/2 - 5 + getWidth()/2), (int) (-bounds.getHeight() - 13), (int) bounds.getWidth() + 10, (int) bounds.getHeight() + 8);
            
            g.setColor(Color.WHITE);
            g.setFont(f);
            g.drawString(name, (int) (-bounds.getWidth()/2 + getWidth()/2), -9);
        }
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public boolean isAlive() {
        return alive;
    }

    public void kill() {
        this.alive = false;
    }
    
    public void setAlive(boolean alive) {
        if (this.alive == false && alive) {
            this.setLife(1);
        }
        this.alive = alive;
    }

    /**
     * @return the clientIsCurrent
     */
    public boolean isClientIsCurrent() {
        return clientIsCurrent;
    }

    /**
     * @param clientIsCurrent the clientIsCurrent to set
     */
    public void setClientIsCurrent(boolean clientIsCurrent) {
        this.clientIsCurrent = clientIsCurrent;
    }

    /**
     * @return the life
     */
    public double getLife() {
        return life;
    }

    /**
     * @param life the life to set
     */
    public void setLife(double life) {
        this.life = life;
    }

    /**
     * @return the weapon
     */
    public Weapon getWeapon() {
        return weapon;
    }

    /**
     * @param weapon the weapon to set
     */
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    /**
     * @return the loadedBullets
     */
    public int getLoadedBullets() {
        return loadedBullets;
    }

    /**
     * @param loadedBullets the loadedBullets to set
     */
    public void setLoadedBullets(int loadedBullets) {
        this.loadedBullets = loadedBullets;
    }

    /**
     * @return the timeCoolingDown
     */
    public double cooldownRemaining() {
        return cooldownRemaining;
    }

    /**
     * @param timeCoolingDown the timeCoolingDown to set
     */
    public void setCooldownRemaining(double timeCoolingDown) {
        this.cooldownRemaining = timeCoolingDown;
    }

    /**
     * @return the timeRreloading
     */
    public double getReloadRemaining() {
        return reloadRemaining;
    }

    /**
     * @param timeRreloading the timeRreloading to set
     */
    public void setReloadRemaining(double timeRreloading) {
        this.reloadRemaining = timeRreloading;
    }

    /**
     * @return the isReloading
     */
    public boolean isReloading() {
        return isReloading;
    }

    /**
     * @param isReloading the isReloading to set
     */
    public void setIsReloading(boolean isReloading) {
        this.isReloading = isReloading;
    }
    
    public void fillMagazine() {
        this.loadedBullets = weapon.getMagSize();
    }
    
}
