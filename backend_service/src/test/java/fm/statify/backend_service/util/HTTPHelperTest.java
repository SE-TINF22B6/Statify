package fm.statify.backend_service.util;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class HTTPHelperTest {

    @Test
    public void testPerformRequest() {
        HTTPHelper httpHelperMock = Mockito.mock(HTTPHelper.class);

        String mockResponse = "Mocked response";
        String endpoint = "https://api.example.com/data";
        String accessToken = "your_access_token_here";

        try {
            Mockito.when(httpHelperMock.performRequest(endpoint, accessToken)).thenReturn(mockResponse);

            String response = httpHelperMock.performRequest(endpoint, accessToken);

            assertNotNull(response, "Response should not be null");
            assertFalse(response.isEmpty(), "Response should not be empty");
            assertEquals(mockResponse, response, "Response should match mocked value");

        } catch (IOException e) {
            fail("IOException occurred during performRequest: " + e.getMessage());
        }
    }
}