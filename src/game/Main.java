package game;

/**
 * main class to start the game
 * @author upvlx
 * @version 0.1
 */
public final class Main {

    /**
     * private constructor for execution class
     */
    private Main() { }

    /**
     * main function
     * @param args commandline arguments
     */
    public static void main(String[] args) {

        RunasStrive runasStrive = new RunasStrive();
        runasStrive.start();

    }
}
