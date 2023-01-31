package snakegame;

public enum Direction {
    UP {
        public Direction opposite() {
            return DOWN;
        }
    },
    DOWN {
        public Direction opposite() {
            return UP;
        }
    },
    LEFT {
        public Direction opposite() {
            return RIGHT;
        }
    },
    RIGHT {
        public Direction opposite() {
            return LEFT;
        }
    }
    ;
    public abstract Direction opposite();
}
