package advisor.Controller;

import advisor.Config;
import advisor.Model.SpotifyData;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class GetNew extends GetRequest {

    public static List<SpotifyData> getNew() {
        List<SpotifyData> data = new ArrayList<>();
        String path = Config.RESOURCE + "/v1/browse/new-releases";
        HttpRequest request = getRequest(path);

        try {
            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JsonObject jo = JsonParser.parseString(response.body()).getAsJsonObject();
            JsonObject categories = jo.getAsJsonObject("albums");


            for (JsonElement item : categories.getAsJsonArray("items")) {
                SpotifyData elem = new SpotifyData();
                elem.setAlbum(item.getAsJsonObject().get("name").toString());

                StringBuilder artists = new StringBuilder("[");
                for (JsonElement name : item.getAsJsonObject().getAsJsonArray("artists")) {
                    if (!artists.toString().endsWith("[")) { artists.append(", "); }
                    artists.append(name.getAsJsonObject().get("name"));
                }
                elem.setArtists(artists.append("]").toString());

                elem.setLink(item.getAsJsonObject().get("external_urls").getAsJsonObject().get("spotify").toString());
                data.add(elem);
            }

        } catch (InterruptedException | IOException e) { System.out.println("Error response"); }

        return data;
    }
}
