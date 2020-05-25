package advisor;

import advisor.Controller.*;
import advisor.Model.SpotifyData;
import advisor.View.PrintData;

import java.util.List;
import java.util.Scanner;


public class MusicAdvisor {
    boolean adviceOn = false;
    List<SpotifyData> data;

    void advise() {
        adviceOn = true;
        chooseCommand();
    }

    void chooseCommand() {
        Scanner scanner = new Scanner(System.in);

        while (!scanner.hasNext("auth")) {
            if (scanner.nextLine().equals("exit")) { exit(); break; }
            System.out.println("Please, provide access for application.");
        }

        while (adviceOn) {
            String command = scanner.next();

            switch(command) {
                case "new":
                    newReleases();
                    break;
                case "featured":
                    featured();
                    break;
                case "categories":
                    categories();
                    break;
                case "playlists":
                    playlists();
                    break;
                case "exit":
                    exit();
                    break;
                case "auth":
                    auth();
                    break;
                case "next":
                    PrintData.printNextPage();
                    break;
                case "prev":
                    PrintData.printPrevPage();
                    break;
                default:
                    error();
            }
        }
    }

    void auth() {
        Authorization auth = new Authorization();
        auth.createHttpServer();
        auth.authRequest();
    }

    void newReleases() {
        data = GetNew.getNew();
        PrintData.print(data);
    }

    void featured() {
        data = GetFeatures.getFeatures();
        PrintData.print(data);
    }

    void playlists() {
        Scanner scanner = new Scanner(System.in);
        data = GetPlaylist.getPlaylist(scanner.next());
        PrintData.print(data);
    }

    void categories() {
        data = GetCategories.getCategories();
        PrintData.print(data);
    }

    void error() {
        System.out.println("Incorrect command. Try again.");
    }

    void exit() {
        System.out.println("---GOODBYE!---");
        adviceOn = false;
    }
}
