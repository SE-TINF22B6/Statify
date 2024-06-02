package fm.statify.backend_service;

import fm.statify.backend_service.auth.SpotifyOAuth;
import java.util.Map;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {

    //TESTING Code

    public static void main(String[] args) {
        try {
            SpotifyOAuth spotifyOAuth = new SpotifyOAuth();
            System.out.println("Authorizing...");
            String initialResponse = spotifyOAuth.getAccessToken();
            Map<String, String> tokenData = spotifyOAuth.parseResponse(initialResponse);
            String accessToken = tokenData.get("access_token");
            String refreshToken = tokenData.get("refresh_token");
            System.out.println("Initial Access Token: " + accessToken);
            System.out.println("Refresh Token: " + refreshToken);

            // Refresh the access token
            String refreshedTokenResponse = spotifyOAuth.refreshAccessToken(refreshToken);
            Map<String, String> refreshedTokenData = spotifyOAuth.parseResponse(refreshedTokenResponse);
            String refreshedAccessToken = refreshedTokenData.get("access_token");
            System.out.println("Refreshed Access Token: " + refreshedAccessToken);
            testRefreshedToken(refreshedAccessToken);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void testRefreshedToken(String token) {
        try {
            URL url = new URL("https://api.spotify.com/v1/me");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", "Bearer " + token);

            int status = con.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            System.out.println("Response Status: " + status);
            System.out.println("Response Content: " + content.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
