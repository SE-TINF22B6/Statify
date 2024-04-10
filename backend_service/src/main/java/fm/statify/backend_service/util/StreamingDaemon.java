package fm.statify.backend_service.util;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/*TODO: Implement a daemon that streams the user's top tracks and saves them to the database.
        Multithreading is recommended to avoid blocking the main thread and to allow for multiple users to stream their top tracks simultaneously.
        The initial idea is that the daemon checks the Spotify API every 20 seconds if a user is really listening to a track if there is no way to get it via the offical Spotify API.
        Otherwise it would also be a good idea to use the Spotify API to get the user's top tracks every few minutes and add the delta to the database (actually the better idea).
        @see https://developer.spotify.com/documentation/web-api
        The management of tokens is crucial here, as the daemon needs to be able to access the Spotify API on behalf of the user. The daemon should use tokens securely.
        If firebase is used, the tokens can be stored there next to internal user logins.
        For the demo of this project, this should be enough because there won't be many users.
        For performance checking there will be a dashboard and better logging.
        In the following, there is an idea/structure of how the daemon could be implemented.
*/

public class StreamingDaemon {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public StreamingDaemon() {
        // Initialize daemon, potentially loading tokens from Firebase or another secure store
    }

    public void startStreamingForUser(String userId) {
        // Each call to this method effectively starts a new thread dedicated to a user's streaming data
        scheduler.scheduleAtFixedRate(new UserTopTracksTask(userId), 0, 20, TimeUnit.MINUTES);
    }

    private static class UserTopTracksTask implements Runnable {
        private final String userId;

        UserTopTracksTask(String userId) {
            this.userId = userId;
        }

        @Override
        public void run() {
            try {
                // Placeholder for token management
                String token = getTokenForUser(userId);

                // Placeholder for Spotify API call to fetch top tracks
                String topTracksJson = fetchTopTracksFromSpotify(token);

                // Placeholder for database operation to save fetched tracks
                saveTopTracksToDatabase(userId, topTracksJson);
            } catch (Exception e) {
                e.printStackTrace();
                // Proper error handling and logging should be implemented here
            }
        }

        private String getTokenForUser(String userId) {
            // Placeholder for actual implementation
            return "user-specific-token";
        }

        private String fetchTopTracksFromSpotify(String token) {
            // Placeholder for actual Spotify API call
            return "{\"tracks\": []}";
        }

        private void saveTopTracksToDatabase(String userId, String topTracksJson) {
            // Placeholder for actual database operation
        }
    }

    public static void main(String[] args) {
        StreamingDaemon daemon = new StreamingDaemon();
        daemon.startStreamingForUser("exampleUserId");
        // For the idea here, we are starting the stream for a single user. In a real scenario, multiple users would be handled.
    }
}
