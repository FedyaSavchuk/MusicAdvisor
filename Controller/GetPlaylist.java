package advisor.Controller;

import advisor.Config;
import advisor.Model.SpotifyData;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class GetPlaylist extends GetRequest {

    public static List<SpotifyData> getPlaylist(String category_id) {
        String path = Config.RESOURCE + "/v1/browse/categories/" + category_id + "/playlists";
        HttpRequest request = getRequest(path);
        List<SpotifyData> data = null;

        try {
            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JsonObject jo = JsonParser.parseString(response.body()).getAsJsonObject();
            if (jo.toString().contains("Specified id doesn't exist")) {
                System.out.println("Specified id doesn't exist");
            } else { data = addPlaylists(response); }

        } catch (InterruptedException | IOException e) { System.out.println("Error response"); }

        return data;
    }

}
