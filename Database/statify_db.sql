SELECT GROUP_CONCAT(CONCAT('KILL ',id,';') SEPARATOR ' ') 
FROM information_schema.processlist 
WHERE user <> 'system user'
AND db = 'statify_db';

DROP DATABASE IF EXISTS statify_db;

CREATE DATABASE statify_db;
 
USE statify_db;

CREATE TABLE `user` (
 guid CHAR(36) NOT NULL,
 access_token TEXT NOT NULL,
 refresh_token TEXT NOT NULL,
 user_id TEXT,
 PRIMARY KEY (`guid`)
 );

CREATE TABLE playlist (
 guid CHAR(36) NOT NULL,
 user_guid CHAR(36),
 playlist_id TEXT NOT NULL,
 `name` TEXT NOT NULL,
 tracks_number INT NOT NULL,
 duration INT NOT NULL,
 top_genre TEXT NOT NULL,
 top_genre_tracks_number INT,
 top_artist TEXT NOT NULL,
 top_artist_tracks_number INT,
 PRIMARY KEY (`guid`),
 FOREIGN KEY (user_guid) REFERENCES `user`(`guid`)
 );

CREATE TABLE top_artists (
 guid CHAR(36) NOT NULL,
 user_guid CHAR(36),
 first_artist_id TEXT NOT NULL,
 second_artist_id TEXT NOT NULL,
 third_artist_id TEXT NOT NULL,
 fourth_artist_id TEXT NOT NULL,
 fifth_artist_id TEXT NOT NULL,
 generate_date DATE NOT NULL,
 PRIMARY KEY (`guid`),
 FOREIGN KEY (user_guid) REFERENCES `user`(`guid`)
 );

CREATE TABLE top_tracks (
 guid CHAR(36) NOT NULL,
 user_guid CHAR(36),
 first_track_id TEXT NOT NULL,
 second_track_id TEXT NOT NULL,
 third_track_id TEXT NOT NULL,
 fourth_track_id TEXT NOT NULL,
 fifth_track_id TEXT NOT NULL,
 generate_date DATE NOT NULL,
 PRIMARY KEY (`guid`),
 FOREIGN KEY (user_guid) REFERENCES `user`(`guid`)
);
