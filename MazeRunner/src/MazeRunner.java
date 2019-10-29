import java.util.Scanner;

public class MazeRunner {

    static Maze myMap = new Maze();
    static Scanner myScanner = new Scanner(System.in);
    static void intro() {
        System.out.println("WELCOME TO THE MAZE!");
        System.out.println("Here is your current position:");
        myMap.printMap();
    }

    public static String userMove() {
        System.out.println("Where would you like to move? (R, L, U, D)");
        return myScanner.nextLine();
    }

    static void checkDir(String direction) {
        switch (direction) {
            default:
                System.out.println("You have made an input error");
                break;
            case "R":
                if (myMap.canIMoveRight())
                    myMap.moveRight();
                else
                    System.out.println("Sorry, you've hit a wall");
                break;
            case "L":
                if (myMap.canIMoveLeft())
                    myMap.moveLeft();
                else
                    System.out.println("Sorry, you've hit a wall");
                break;
            case "U":
                if (myMap.canIMoveUp())
                    myMap.moveUp();
                else
                    System.out.println("Sorry, you've hit a wall");
                break;
            case "D":
                if (myMap.canIMoveDown())
                    myMap.moveDown();
                else
                    System.out.println("Sorry, you've hit a wall");
                break;
        }
    }

    static void navigatePit(String direction) {
        System.out.println("Watch out! There's a pit ahead, jump it?");
        String answer = myScanner.nextLine();
        if(answer.charAt(0) == 'y' || answer.charAt(0) == 'Y')
            myMap.jumpOverPit(direction);
        else {
            System.out.println("Please enter a valid input.");
            navigatePit(direction);
        }
    }
    static void currMove(int moves) {
        switch(moves) {
            case 50:
                System.out.println("Warning: You have made 50 moves, you have 50 remaining before the maze closes.");
            case 75:
                System.out.println("Alert! You have made 75 moves, you only have 25 moves left to escape.");
            case 90:
                System.out.println("DANGER! You have made 90 moves, you only have 10 moves left to escape");
            case 100:
                System.out.println("Oh no! You took too long to escape, and now the maze is closed FOREVER >:[");
                if(!myMap.didIWin()) {
                    System.out.println("Sorry, but you didn't escape in time - YOU LOSE!");
                    System.exit(0);
                }
                break;
            default:
                break;
        }
    }

    public static void main(String[] args) {
        intro();
        int moves = 0;
        do {
            moves++;
            currMove(moves);
            String direction = userMove();
            if (direction.equals("R") || direction.equals("L") || direction.equals("U") || direction.equals("D")) {
                if(myMap.isThereAPit(direction)) {
                    navigatePit(direction);
                }
                checkDir(direction);
                myMap.printMap();
                System.out.println("Moves:  " +moves);
            } else {
                System.out.println("Please enter a valid input.");
            }
        } while (!myMap.didIWin() && moves < 100);
    }
}

