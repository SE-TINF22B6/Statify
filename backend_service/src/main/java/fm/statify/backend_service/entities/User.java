package fm.statify.backend_service.entities;

import fm.statify.backend_service.auth.SpotifyOAuth;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

public class User {
    private String userID;
    private String accessToken;
    private Date expires;
    private String refreshToken;

    public User(String userID, String accessToken, Date expires, String refreshToken) {
        this.userID = userID;
        this.accessToken = accessToken;
        this.expires = expires;
        this.refreshToken = refreshToken;
    }

    public String getUserID() {
        return userID;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public Date getExpires() {
        return expires;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setExpires(Date expires) {
        this.expires = expires;
    }

    public boolean isAccessTokenExpired(){
        return expires.before(new Date());
    }

}
