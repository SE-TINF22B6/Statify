package fm.statify.backend_service.util;

import fm.statify.backend_service.auth.SpotifyOAuth;
import fm.statify.backend_service.entities.User;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class UserManager {
    private Map<String, User> userData = new HashMap<>();

    private final SpotifyOAuth spotifyOAuth;
    private DBManager db;

    @Autowired
    public UserManager(SpotifyOAuth spotifyOAuth){
        this.spotifyOAuth = spotifyOAuth;
    }

    @Autowired
    public void setDBManager(@Lazy DBManager db) {
        this.db = db;
    }

    private User getUser(String userId) throws Exception {
        if (userData.containsKey(userId)) {
            return userData.get(userId);
        } else {
            return db.getUser(userId);
        }
    }

    public String getAccessTokenByUserID(String userID) {
        try {
            User user = getUser(userID);
            if(user.isAccessTokenExpired()){
                boolean refreshed = refreshAccessToken(user);
                if(refreshed){
                    db.updateTokens(userID, user.getAccessToken(), user.getExpires());
                }
            }
            return user.getAccessToken();
        }
        catch (Exception e){
            return null;
        }
    }

    private boolean refreshAccessToken(User user){
        try {
            String response = spotifyOAuth.refreshAccessToken(user.getRefreshToken());
            Map<String, String> tokenData = spotifyOAuth.parseResponse(response);
            JSONObject tokenDataJson = new JSONObject(tokenData);
            user.setAccessToken(tokenDataJson.getString("access_token"));
            int expires_in = Integer.parseInt(tokenDataJson.getString("expires_in"));

            user.setExpires(new Date(System.currentTimeMillis() + 1000 * expires_in));

            return true;
        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.out.println("Token not refreshed");
            return false;
        }

    }

    public void addUser(User user){
        userData.put(user.getUserID(), user);
    }
}
