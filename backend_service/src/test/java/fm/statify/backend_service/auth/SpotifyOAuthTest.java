package fm.statify.backend_service.auth;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;

public class SpotifyOAuthTest {

    @Mock
    private HttpClient mockHttpClient;

    @Mock
    private HttpResponse<String> mockResponse;

    @InjectMocks
    private SpotifyOAuth spotifyOAuth;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAuthUrl() {
        String authUrl = spotifyOAuth.getAuthUrl();
        assertNotNull(authUrl);
        assertTrue(authUrl.startsWith("https://"));
        assertTrue(authUrl.contains("client_id="));
    }

    @Test
    public void testRequestAccessToken() throws IOException, InterruptedException {
        String testCode = "testCode";
        String expectedResponse = "{\"error\":\"invalid_client\",\"error_description\":\"Invalid client\"}";
        HttpResponse<String> mockResponse = mock(HttpResponse.class);
        when(mockResponse.body()).thenReturn(expectedResponse);
        when(mockHttpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockResponse);
        String result = spotifyOAuth.requestAccessToken(testCode);
        assertEquals(expectedResponse, result);
    }

    @Test
    public void testParseResponse() {
        String jsonResponse = "{\"access_token\":\"test_token\", \"token_type\":\"Bearer\", \"expires_in\":3600, \"refresh_token\":\"test_refresh_token\"}";
        Map<String, String> result = spotifyOAuth.parseResponse(jsonResponse);
        assertEquals("test_token", result.get("access_token"));
        assertEquals("Bearer", result.get("token_type"));
        assertEquals("3600", result.get("expires_in"));
        assertEquals("test_refresh_token", result.get("refresh_token"));
    }
}
