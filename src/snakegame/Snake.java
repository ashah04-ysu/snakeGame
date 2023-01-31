package snakegame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

public class Snake implements PaintObject {
    
    private static final Integer INITIAL_LENGTH = 5;
    
    private List<Point> nodes = new LinkedList();
    private Color color;
    private Direction direction;
    private Integer score = 0;
    
    public Snake(Color color, Point location) {
        this.color = color;
        
        this.direction = Direction.DOWN;
        nodes.add(location);
        for(int i = 1; i < INITIAL_LENGTH; i++) {
            int x = (int)location.getX() - (i * Game.OBJECT_DIMENSION);
            int y = (int)location.getY();
            Point point = new Point(x, y);
            nodes.add(point);
        }
    }
    
    @Override
    public void paint(Graphics graphics) {
        Color color = graphics.getColor();
        graphics.setColor(this.color);
        for(Point node: nodes) {
            graphics.fillRect(
                (int)node.getX(), 
                (int)node.getY(), 
                Game.OBJECT_DIMENSION, 
                Game.OBJECT_DIMENSION
            );
        }
        graphics.setColor(color);
    }
    
    public void move() {
        nodes.remove(nodes.size() - 1);
        Point frontNode = nodes.get(0);
        int x = (int)frontNode.getX();
        int y = (int)frontNode.getY();
        
        switch(this.direction) {
            case RIGHT:
                x += Game.OBJECT_DIMENSION;
                break;
            case LEFT: 
                x -= Game.OBJECT_DIMENSION;
                break;
            case UP:
                y -= Game.OBJECT_DIMENSION;
                break;
            case DOWN:
                y += Game.OBJECT_DIMENSION;
                break;
        }
        
        if(getFace().getX() < Game.OBJECT_DIMENSION) {
            x = Board.BOARD_WIDTH - Game.OBJECT_DIMENSION;
        }
        else if(getFace().getX() > Board.BOARD_WIDTH - Game.OBJECT_DIMENSION) {
            x = Game.OBJECT_DIMENSION;
        }
        else if(getFace().getY() == 0) {
            y = Board.BOARD_HEIGHT - Game.OBJECT_DIMENSION;
        }
        else if(getFace().getY() == Board.BOARD_HEIGHT) {
            y = Game.OBJECT_DIMENSION;
        }
        
        nodes.add(0, new Point(x, y));
    }
    
    public Point getFace() {
        return this.nodes.get(0);
    }
    
    public void setDirection(Direction direction) {
        if(!direction.equals(this.direction.opposite())) {
            this.direction = direction;
        }
    }
    
    public Integer getScore() {
        return score;
    }
    
    public void addNode() {
        Point lastNode = this.nodes.get(this.nodes.size() - 1);
        int x = (int)lastNode.getX();
        int y = (int)lastNode.getY();
        Point point = new Point(x, y);
        this.nodes.add(point);
    }
    
    public void addScore(int score) {
        this.score += score;
    }
}
