package snakegame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Game implements PaintObject {
    
    private final static Integer FOOD_COUNT = 1;
    private final static Integer SNAKE_COUNT = 1;
    public static final Integer OBJECT_DIMENSION = 10;
    private static final Color[] COLORS = {Color.GREEN, Color.PINK, Color.ORANGE};
    private static final Point[] LOCATIONS = {new Point(50,50), new Point(50, 150), new Point(50, 250)};
    
    private List<Snake> snakeList = new ArrayList();
    private List<Food> foodList = new LinkedList();
    private ScoreListener scoreListener;
    
    public Game(ScoreListener scoreListener) {
        this.scoreListener = scoreListener;
        for(int i = 0; i < SNAKE_COUNT; i++) {
            snakeList.add(new Snake(COLORS[i], LOCATIONS[i]));
        }
        
        for(int i = 0; i < FOOD_COUNT; i++) {
            foodList.add(new Food());
        }
        
        this.scoreListener.setScores(0, foodList.get(0).getScore());
    }
    
    @Override
    public void paint(Graphics graphics) {
        for(Snake snake: snakeList) {
            snake.paint(graphics);
        }
        
        for(Food food: foodList) {
            food.paint(graphics);
        }
    }

    public void next() {
        for(Snake snake: snakeList) {
            snake.move();
            Point snakeFace = snake.getFace();
            for(Food food: foodList) {
                Point foodLocation = food.getLocation();
                if(snakeFace.getX() == foodLocation.getX() && 
                   snakeFace.getY() == foodLocation.getY()) {
                    snake.addScore(food.getScore());
                    Food newFood = new Food();
                    this.scoreListener.setScores(snake.getScore(), newFood.getScore());
                    foodList.remove(food);
                    foodList.add(newFood);
                    snake.addNode();
                }
            }
        }
    }
    
    
    public void changeDirection(Direction direction, int playerNumber) {
        snakeList.get(playerNumber - 1).setDirection(direction);
    }
}
