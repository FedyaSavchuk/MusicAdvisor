package advisor;

public class Main {
    public static void main(String[] args) {
        if (args.length > 1 && args[0].equals("-access")) { Config.SERVER_PATH = args[1]; }
        if (args.length > 2 && args[2].equals("-resource")) { Config.RESOURCE = args[3]; }
        if (args.length > 4 && args[4].equals("-page")) { Config.RESULTS_PER_PAGE = Integer.parseInt(args[5]); }

        MusicAdvisor advisor = new MusicAdvisor();
        advisor.advise();
    }
}
