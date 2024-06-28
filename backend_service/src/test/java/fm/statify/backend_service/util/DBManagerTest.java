package fm.statify.backend_service.util;

import fm.statify.backend_service.auth.SpotifyOAuth;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DBManagerTest {

    @InjectMocks
    private DBManager dbManager;

    @Mock
    private Connection con;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        // Use reflection to set the private field 'con' in DBManager
        Field connectionField = DBManager.class.getDeclaredField("con");
        connectionField.setAccessible(true);
        connectionField.set(dbManager, con);
    }

    @Test
    void testInsertUser() throws SQLException {
        // Mock input parameters
        String accessToken = "access_token";
        String refreshToken = "refresh_token";
        String userID = "user_id";
        java.util.Date expireDate = new java.util.Date();

        // Mock SQL query
        String sql = "INSERT INTO user (guid, access_token, refresh_token, user_id, expires) VALUES (?, ?, ?, ?, ?)";

        // Mock UUID
        UUID mockUUID = UUID.randomUUID();

        // Mock PreparedStatement
        when(con.prepareStatement(sql)).thenReturn(preparedStatement);

        // Mock UUID random generation
        try (MockedStatic<UUID> mockedUUID = mockStatic(UUID.class)) {
            mockedUUID.when(UUID::randomUUID).thenReturn(mockUUID);

            // Perform the test
            dbManager.insertUser(accessToken, refreshToken, userID, expireDate);

            // Verify interactions
            verify(con, times(1)).prepareStatement(sql);
            verify(preparedStatement, times(1)).setString(1, mockUUID.toString());
            verify(preparedStatement, times(1)).setString(2, accessToken);
            verify(preparedStatement, times(1)).setString(3, refreshToken);
            verify(preparedStatement, times(1)).setString(4, userID);
            verify(preparedStatement, times(1)).setTimestamp(5, new Timestamp(expireDate.getTime()));
            verify(preparedStatement, times(1)).executeUpdate();
        }
    }

    @Test
    void testGetUserGuid() throws SQLException {
        // Mock input parameter
        String userID = "user_id";
        String expectedGuid = "expected_guid";

        // Mock SQL query
        String sql = "SELECT guid FROM user WHERE user_id = ?";

        // Mock PreparedStatement and ResultSet
        when(con.prepareStatement(sql)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString("guid")).thenReturn(expectedGuid);

        // Perform the test
        String actualGuid = dbManager.getUserGuid(userID);

        // Verify interactions and assert result
        verify(con, times(1)).prepareStatement(sql);
        verify(preparedStatement, times(1)).setString(1, userID);
        verify(preparedStatement, times(1)).executeQuery();
        verify(resultSet, times(1)).next();
        verify(resultSet, times(1)).getString("guid");

        assertEquals(expectedGuid, actualGuid);
    }

    @Test
    void testUserExists_UserExists() throws SQLException {
        // Mock input parameter
        String userID = "user_id";
        String expectedGuid = "expected_guid";

        // Mock DBManager method getUserGuid
        DBManager dbManagerSpy = spy(dbManager);
        doReturn(expectedGuid).when(dbManagerSpy).getUserGuid(userID);

        // Perform the test
        Boolean userExists = dbManagerSpy.userExists(userID);

        // Verify interactions and assert result
        verify(dbManagerSpy, times(1)).getUserGuid(userID);
        assertTrue(userExists);
    }

    @Test
    void testUserExists_UserDoesNotExist() throws SQLException {
        // Mock input parameter
        String userID = "user_id";
        String expectedGuid = "";

        // Mock DBManager method getUserGuid
        DBManager dbManagerSpy = spy(dbManager);
        doReturn(expectedGuid).when(dbManagerSpy).getUserGuid(userID);

        // Perform the test
        Boolean userExists = dbManagerSpy.userExists(userID);

        // Verify interactions and assert result
        verify(dbManagerSpy, times(1)).getUserGuid(userID);
        assertFalse(userExists);
    }
}