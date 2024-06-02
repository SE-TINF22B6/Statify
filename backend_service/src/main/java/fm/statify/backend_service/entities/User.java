package fm.statify.backend_service.entities;

import java.util.Date;

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
}
