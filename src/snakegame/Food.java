package snakegame;

import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

public class Food implements PaintObject {
    
    private Integer score;
    private Point location;
        
    public Food() {
        Random random = new Random();
        this.score = random.nextInt(Game.OBJECT_DIMENSION) + 1;
        this.location = new Point(
            getRandomNumber(Board.BOARD_WIDTH), 
            getRandomNumber(Board.BOARD_HEIGHT)
        );
    }
    
    @Override
    public void paint(Graphics graphics) {
        graphics.fillRect(
            (int)location.getX(), 
            (int)location.getY(), 
            Game.OBJECT_DIMENSION, 
            Game.OBJECT_DIMENSION
        );
    }
    
    private Integer getRandomNumber(int dimension) {
        Random random = new Random();
        return random.nextInt(dimension / Game.OBJECT_DIMENSION) * Game.OBJECT_DIMENSION;
    }
    
    public Point getLocation() {
        return this.location;
    }
    
    public Integer getScore() {
        return score;
    }
}
