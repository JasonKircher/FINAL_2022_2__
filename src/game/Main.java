package game;

/**
 * main class to start the game
 * @author upvlx
 * @version 0.1
 */
public final class Main {
    private static final String ERROR_MSG = "Error, this game is supposed to run without parameters";

    /**
     * private constructor for execution class
     */
    private Main() { }

    /**
     * main function
     * @param args commandline arguments
     */
    public static void main(String[] args) {
        if (args.length != 0)
            System.out.println(ERROR_MSG);
        else {
            RunasStrive runasStrive = new RunasStrive();
            runasStrive.start();
        }
    }
}
