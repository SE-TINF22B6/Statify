SELECT GROUP_CONCAT(CONCAT('KILL ',id,';') SEPARATOR ' ') 
FROM information_schema.processlist 
WHERE user <> 'system user'
AND db = 'statify_db';

DROP DATABASE IF EXISTS statify_db;

CREATE DATABASE statify_db;
 
USE statify_db;

CREATE TABLE `user` (
 guid CHAR(36) NOT NULL,
 access_token TEXT NOT NULL UNIQUE,
 refresh_token TEXT NOT NULL UNIQUE,
 user_id TEXT UNIQUE,
 PRIMARY KEY (`guid`)
 );

CREATE TABLE playlists (
 guid CHAR(36) NOT NULL,
 user_guid CHAR(36),
 playlist_id TEXT NOT NULL UNIQUE,
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

CREATE TABLE top_artist (
 guid CHAR(36) NOT NULL,
 user_guid CHAR(36),
 artist_id TEXT NOT NULL UNIQUE,
 generate_date DATE NOT NULL,
 PRIMARY KEY (`guid`),
 FOREIGN KEY (user_guid) REFERENCES `user`(`guid`)
 );

CREATE TABLE top_track (
 guid CHAR(36) NOT NULL,
 user_guid CHAR(36),
 track_id TEXT NOT NULL UNIQUE,
 generate_date DATE NOT NULL,
 PRIMARY KEY (`guid`),
 FOREIGN KEY (user_guid) REFERENCES `user`(`guid`)
);
