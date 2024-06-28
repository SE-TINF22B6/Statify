package fm.statify.backend_service.util;

import fm.statify.backend_service.entities.Playlist;
import fm.statify.backend_service.entities.UserProfile;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ParserTest {

    @Test
    public void testParseUserProfile() {
        // Готовим тестовые данные
        String userProfileJsonString = "{\"id\":\"user123\",\"display_name\":\"John Doe\",\"email\":\"john.doe@example.com\",\"external_urls\":{\"spotify\":\"https://open.spotify.com/user/user123\"},\"images\":[{\"url\":\"https://example.com/profile.jpg\"}, {\"url\":\"https://example.com/profile.jpg\"}],\"product\":\"premium\"}";

        // Создаем экземпляр Parser
        Parser parser = new Parser();

        // Вызываем метод для парсинга
        UserProfile userProfile = parser.parseUserProfile(userProfileJsonString);

        // Проверяем результаты парсинга
        assertNotNull(userProfile);
        assertEquals("user123", userProfile.getId());
        assertEquals("John Doe", userProfile.getUserName());
        assertEquals("john.doe@example.com", userProfile.getEmail());
        assertEquals("https://open.spotify.com/user/user123", userProfile.getUserURL());
        assertEquals("https://example.com/profile.jpg", userProfile.getProfilePictureURL());
        assertEquals("premium", userProfile.getProduct());
    }

    @Test
    public void testParsePlaylists() {
        // Готовим тестовые данные
        String playlistsJsonString = "{\"items\":[{\"id\":\"playlist1\",\"name\":\"My Playlist\",\"images\":[{\"url\":\"https://example.com/playlist.jpg\"}]}]}";

        // Создаем экземпляр Parser
        Parser parser = new Parser();

        // Вызываем метод для парсинга
        List<Playlist> playlists = parser.parsePlaylists(playlistsJsonString);

        // Проверяем результаты парсинга
        assertNotNull(playlists);
        assertEquals(1, playlists.size());

        Playlist playlist = playlists.get(0);
        assertEquals("playlist1", playlist.getId());
        assertEquals("My Playlist", playlist.getName());
        assertEquals("https://example.com/playlist.jpg", playlist.getImageURL());
    }
}