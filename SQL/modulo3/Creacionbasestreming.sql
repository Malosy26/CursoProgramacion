-- Crear el esquema Spotfi (si no existe)
CREATE SCHEMA IF NOT EXISTS "Spotfi";

-- Tabla Artists
CREATE TABLE IF NOT EXISTS "Spotfi".artists (
    id_artist SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

-- Tabla Songs
CREATE TABLE IF NOT EXISTS "Spotfi".songs (
    id_song SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

-- Tabla Users
CREATE TABLE IF NOT EXISTS "Spotfi".users (
    id_user SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL
);

-- Tabla Playlists
CREATE TABLE IF NOT EXISTS "Spotfi".playlists (
    id_playlist SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    public BOOLEAN DEFAULT FALSE
);

-- Tabla intermedia Sings (relación muchos a muchos entre Artists y Songs)
CREATE TABLE IF NOT EXISTS "Spotfi".sings (
    id_artist INT NOT NULL,
    id_song INT NOT NULL,
    CONSTRAINT pk_sings PRIMARY KEY (id_artist, id_song),
    CONSTRAINT fk_sings_artist
        FOREIGN KEY (id_artist)
        REFERENCES "Spotfi".artists(id_artist)
        ON DELETE CASCADE
		ON UPDATE CASCADE,
    CONSTRAINT fk_sings_song
        FOREIGN KEY (id_song)
        REFERENCES "Spotfi".songs(id_song)
        ON DELETE CASCADE
		ON UPDATE CASCADE
);

-- Tabla Play (relación muchos a muchos entre Users y Songs)
CREATE TABLE IF NOT EXISTS "Spotfi".play (
    id_plays SERIAL PRIMARY KEY,
    id_user INT NOT NULL,
    id_song INT NOT NULL,
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_play_user
        FOREIGN KEY (id_user)
        REFERENCES "Spotfi".users(id_user)
        ON DELETE CASCADE
		ON UPDATE CASCADE,
    CONSTRAINT fk_play_song
        FOREIGN KEY (id_song)
        REFERENCES "Spotfi".songs(id_song)
        ON DELETE CASCADE
		ON UPDATE CASCADE
);

-- Tabla intermedia Playlist_user (relación muchos a muchos entre Users y Playlists)
CREATE TABLE IF NOT EXISTS "Spotfi".playlist_user (
    id_playlist INT NOT NULL,
    id_user INT NOT NULL,
    CONSTRAINT pk_playlist_user PRIMARY KEY (id_playlist, id_user),
    CONSTRAINT fk_playlist_user_playlist
        FOREIGN KEY (id_playlist)
        REFERENCES "Spotfi".playlists(id_playlist)
        ON DELETE CASCADE
		ON UPDATE CASCADE,
    CONSTRAINT fk_playlist_user_user
        FOREIGN KEY (id_user)
        REFERENCES "Spotfi".users(id_user)
        ON DELETE CASCADE
		ON UPDATE CASCADE
);

-- Tabla intermedia Playlist_song (relación muchos a muchos entre Playlists y Songs)
CREATE TABLE IF NOT EXISTS "Spotfi".playlist_song (
    id_playlist INT NOT NULL,
    id_song INT NOT NULL,
    CONSTRAINT pk_playlist_song PRIMARY KEY (id_playlist, id_song),
    CONSTRAINT fk_playlist_song_playlist
        FOREIGN KEY (id_playlist)
        REFERENCES "Spotfi".playlists(id_playlist)
        ON DELETE CASCADE
		ON UPDATE CASCADE,
    CONSTRAINT fk_playlist_song_song
        FOREIGN KEY (id_song)
        REFERENCES "Spotfi".songs(id_song)
        ON DELETE CASCADE
		ON UPDATE CASCADE
);

-- Eliminar tablas en orden inverso para evitar errores de dependencia
/*
DROP TABLE IF EXISTS "Spotfi".playlist_song;
DROP TABLE IF EXISTS "Spotfi".playlist_user;
DROP TABLE IF EXISTS "Spotfi".play;
DROP TABLE IF EXISTS "Spotfi".sings;
DROP TABLE IF EXISTS "Spotfi".playlists;
DROP TABLE IF EXISTS "Spotfi".users;
DROP TABLE IF EXISTS "Spotfi".songs;
DROP TABLE IF EXISTS "Spotfi".artists;
*/
