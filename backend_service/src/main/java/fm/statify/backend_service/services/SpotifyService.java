package fm.statify.backend_service.services;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class SpotifyService {

    private static final String TOP_TRACKS_ENDPOINT = "https://api.spotify.com/v1/me/top/tracks?limit=1";

    public String getTopTrack(String accessToken) throws Exception {
        URL url = new URL(TOP_TRACKS_ENDPOINT);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Authorization", "Bearer " + accessToken);

        Scanner scanner = new Scanner(con.getInputStream(), "UTF-8");
        scanner.useDelimiter("\\A");
        return scanner.hasNext() ? scanner.next() : "";

        //THIS WILL NOT WORK
    }
}
