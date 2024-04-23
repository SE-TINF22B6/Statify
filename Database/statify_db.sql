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
 PRIMARY KEY (`guid`)
 );

CREATE TABLE playlist_stats (
 guid CHAR(36) NOT NULL,
 user_guid CHAR(36),
 `name` TEXT NOT NULL,
 tracks_number INT NOT NULL,
 duration_ms INT NOT NULL,
 top_genre TEXT NOT NULL,
 top_genre_tracks_number INT,
 top_artist TEXT NOT NULL,
 top_artist_tracks_number INT,
 PRIMARY KEY (`guid`),
 FOREIGN KEY (user_guid) REFERENCES `user`(`guid`)
 );

CREATE TABLE artist (
 guid CHAR(36) NOT NULL,
 `name` TEXT NOT NULL,
 image_url TEXT,
 PRIMARY KEY (`guid`)
 );

CREATE TABLE top_artists_stats (
 guid CHAR(36) NOT NULL,
 user_guid CHAR(36),
 first_artist CHAR(36),
 second_artist CHAR(36),
 third_artist CHAR(36),
 fourth_artist CHAR(36),
 fifth_artist CHAR(36),
 start_date DATE NOT NULL,
 end_date DATE NOT NULL,
 PRIMARY KEY (`guid`),
 FOREIGN KEY (user_guid) REFERENCES `user`(`guid`),
 FOREIGN KEY (first_artist) REFERENCES artist(`guid`),
 FOREIGN KEY (second_artist) REFERENCES artist(`guid`),
 FOREIGN KEY (third_artist) REFERENCES artist(`guid`),
 FOREIGN KEY (fourth_artist) REFERENCES artist(`guid`),
 FOREIGN KEY (fifth_artist) REFERENCES artist(`guid`)
 );

CREATE TABLE audio_feautures(
 guid CHAR(36) NOT NULL,
 duration_ms INT,
 `key` INT,
 tempo FLOAT,
 `mode` INT,
 danceability FLOAT,
 energy FLOAT,
 loudness FLOAT,
 speechiness FLOAT, 
 acousticness FLOAT, 
 instrumentalness FLOAT,
 liveness FLOAT,
 valence FLOAT,
 PRIMARY KEY (`guid`)
);

CREATE TABLE track (
 guid CHAR(36) NOT NULL,
 `name` TEXT NOT NULL,
 artist_name TEXT NOT NULL,
 image_url TEXT, 
 popularity INT,
 audio_features CHAR(36), 
 PRIMARY KEY (`guid`),
 FOREIGN KEY (audio_features) REFERENCES audio_feautures(`guid`)
 );

CREATE TABLE top_tracks_stats(
 guid CHAR(36) NOT NULL,
 user_guid CHAR(36),
 first_track CHAR(36),
 second_track CHAR(36),
 third_track CHAR(36),
 fourth_track CHAR(36),
 fifth_track CHAR(36),
 start_date DATE NOT NULL,
 end_date DATE NOT NULL,
 PRIMARY KEY (`guid`),
 FOREIGN KEY (user_guid) REFERENCES `user`(`guid`),
 FOREIGN KEY (first_track) REFERENCES track(`guid`),
 FOREIGN KEY (second_track) REFERENCES track(`guid`),
 FOREIGN KEY (third_track) REFERENCES track(`guid`),
 FOREIGN KEY (fourth_track) REFERENCES track(`guid`),
 FOREIGN KEY (fifth_track) REFERENCES track(`guid`)
);