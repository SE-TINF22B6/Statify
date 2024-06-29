package fm.statify.backend_service.util;

import fm.statify.backend_service.auth.SpotifyOAuth;
import fm.statify.backend_service.entities.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UserManagerTest {
    @Mock
    SpotifyOAuth spotifyOAuth;
    @Mock
    DBManager db;

    @Spy
    Map<String, User> userData;

    @InjectMocks
    @Spy
    UserManager userManager;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        userManager = new UserManager(spotifyOAuth);
        userManager.setDBManager(db);
        userManager = spy(userManager);
        try {
            Field field = userManager.getClass().getDeclaredField("userData");
            field.setAccessible(true);
            userData = (HashMap) field.get(userManager);
        }catch (Exception e){
        }
    }

    @Test
    public void testAddUser(){
        User user = new User("id", "accessToken", new Date(), "refreshToken");

        userManager.addUser(user);

        assertEquals(1, userData.size());
        assertEquals(user, userData.get("id"));
    }

    @Test
    @DisplayName("Test Get Access Token By UserID → expired token")
    public void testGetAccessTokenByUserIdExpired(){
        try {
            doReturn("newTokenData").when(spotifyOAuth).refreshAccessToken(any());
            Map<String, String> tokenData = new HashMap<>();
            tokenData.put("access_token", "newAccessToken");
            tokenData.put("expires_in", "100");
            doReturn(tokenData).when(spotifyOAuth).parseResponse(any());
            doNothing().when(db).updateTokens(any(), any(), any());
        } catch (IOException | InterruptedException ignored) {  }

        Date date = new Date(System.currentTimeMillis() + 1000 * 100);
        User user1 = new User("id1", "accessToken1", new Date(), "refreshToken1");
        User user2 = new User("id2", "accessToken2", new Date(), "refreshToken2");

        userData.put("id1", user1);
        userData.put("id2", user2);

        String token = userManager.getAccessTokenByUserID("id2");

        assertNotNull(token);
        assertEquals("newAccessToken", token);
        assertEquals(user2.getAccessToken(), "newAccessToken");
        assertTrue(user2.getExpires().after(date));
        try {
            verify(spotifyOAuth, times(1)).refreshAccessToken("refreshToken2");
            verify(spotifyOAuth, times(1)).parseResponse("newTokenData");
            verify(db, times(1)).updateTokens("id2", "newAccessToken", user2.getExpires());
        } catch (IOException | InterruptedException ignored) { }
    }

    @Test
    public void testGetAccessTokenByUserId(){

        User user1 = new User("id1", "accessToken1", new Date(System.currentTimeMillis() + 1000), "refreshToken1");
        User user2 = new User("id2", "accessToken2", new Date(System.currentTimeMillis() + 1000), "refreshToken2");

        userData.put("id1", user1);
        userData.put("id2", user2);

        String token = userManager.getAccessTokenByUserID("id2");

        assertNotNull(token);
        assertEquals("accessToken2", token);

    }


}
