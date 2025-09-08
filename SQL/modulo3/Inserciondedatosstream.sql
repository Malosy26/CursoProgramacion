-- Insertar datos en Artists
INSERT INTO "Spotfi".artists (name) VALUES
('Bad Bunny'),
('Taylor Swift'),
('The Weeknd'),
('Dua Lipa'),
('Ed Sheeran'),
('Billie Eilish'),
('Adele'),
('Bruno Mars'),
('Coldplay'),
('Shawn Mendes'),
('Ariana Grande'),
('Justin Bieber'),
('Beyoncé'),
('Rihanna'),
('Drake');

-- Insertar datos en Songs
INSERT INTO "Spotfi".songs (name) VALUES
('Tití Me Preguntó'),
('Anti-Hero'),
('Blinding Lights'),
('Levitating'),
('Shape of You'),
('Happier Than Ever'),
('Hello'),
('24K Magic'),
('Viva la Vida'),
('Señorita'),
('Thank U, Next'),
('Love Yourself'),
('Halo'),
('Umbrella'),
('God''s Plan'),
('Dákiti'),
('Cruel Summer'),
('Save Your Tears'),
('Don''t Start Now'),
('Perfect');

-- Insertar datos en Users
INSERT INTO "Spotfi".users (name, email) VALUES
('Ana López', 'ana.lopez@example.com'),
('Carlos García', 'carlos.garcia@example.com'),
('María Martínez', 'maria.martinez@example.com'),
('Luis Rodríguez', 'luis.rodriguez@example.com'),
('Laura Sánchez', 'laura.sanchez@example.com'),
('Javier Fernández', 'javier.fernandez@example.com'),
('Sofía Gómez', 'sofia.gomez@example.com'),
('Pablo Díaz', 'pablo.diaz@example.com'),
('Elena Ruiz', 'elena.ruiz@example.com'),
('Diego Álvarez', 'diego.alvarez@example.com'),
('Clara Moreno', 'clara.moreno@example.com'),
('Hugo Jiménez', 'hugo.jimenez@example.com'),
('Lucía Navarro', 'lucia.navarro@example.com'),
('Adrián Torres', 'adrian.torres@example.com'),
('Paula Ramírez', 'paula.ramirez@example.com');

-- Insertar datos en Playlists
INSERT INTO "Spotfi".playlists (name, public) VALUES
('Top 2023', TRUE),
('Pop Favoritos', FALSE),
('Para entrenar', TRUE),
('Relajación', FALSE),
('Fiesta', TRUE),
('Clásicos', FALSE),
('Indie Mix', TRUE),
('Verano 2023', TRUE),
('Baladas', FALSE),
('Rock', TRUE),
('Electrónica', FALSE),
('Reggaeton', TRUE),
('Para estudiar', FALSE),
('Road Trip', TRUE),
('Throwback', FALSE);

-- Insertar datos en Sings (relación entre Artists y Songs)
INSERT INTO "Spotfi".sings (id_artist, id_song) VALUES
(1, 1),  -- Bad Bunny - Tití Me Preguntó
(1, 16), -- Bad Bunny - Dákiti
(2, 2),  -- Taylor Swift - Anti-Hero
(2, 17), -- Taylor Swift - Cruel Summer
(3, 3),  -- The Weeknd - Blinding Lights
(3, 18), -- The Weeknd - Save Your Tears
(4, 4),  -- Dua Lipa - Levitating
(4, 19), -- Dua Lipa - Don't Start Now
(5, 5),  -- Ed Sheeran - Shape of You
(5, 20), -- Ed Sheeran - Perfect
(6, 6),  -- Billie Eilish - Happier Than Ever
(7, 7),  -- Adele - Hello
(8, 8),  -- Bruno Mars - 24K Magic
(9, 9),  -- Coldplay - Viva la Vida
(10, 10),-- Shawn Mendes - Señorita
(11, 11),-- Ariana Grande - Thank U, Next
(12, 12),-- Justin Bieber - Love Yourself
(13, 13),-- Beyoncé - Halo
(14, 14),-- Rihanna - Umbrella
(15, 15);-- Drake - God's Plan

-- Insertar datos en Play (reproducciones de canciones por usuarios)
INSERT INTO "Spotfi".play (id_user, id_song, date) VALUES
(1, 1, '2023-09-01 10:00:00'),
(1, 2, '2023-09-01 11:30:00'),
(2, 3, '2023-09-01 12:45:00'),
(3, 4, '2023-09-01 13:10:00'),
(4, 5, '2023-09-01 14:20:00'),
(5, 6, '2023-09-01 15:05:00'),
(6, 7, '2023-09-01 16:40:00'),
(7, 8, '2023-09-01 17:15:00'),
(8, 9, '2023-09-01 18:30:00'),
(9, 10, '2023-09-01 19:50:00'),
(10, 11, '2023-09-02 09:00:00'),
(11, 12, '2023-09-02 10:20:00'),
(12, 13, '2023-09-02 11:40:00'),
(13, 14, '2023-09-02 12:55:00'),
(14, 15, '2023-09-02 14:10:00'),
(15, 16, '2023-09-02 15:30:00');

-- Insertar datos en Playlist_user (relación entre Users y Playlists)
INSERT INTO "Spotfi".playlist_user (id_playlist, id_user) VALUES
(1, 1),  -- Top 2023 - Ana López
(2, 1),  -- Pop Favoritos - Ana López
(3, 2),  -- Para entrenar - Carlos García
(4, 3),  -- Relajación - María Martínez
(5, 4),  -- Fiesta - Luis Rodríguez
(6, 5),  -- Clásicos - Laura Sánchez
(7, 6),  -- Indie Mix - Javier Fernández
(8, 7),  -- Verano 2023 - Sofía Gómez
(9, 8),  -- Baladas - Pablo Díaz
(10, 9), -- Rock - Elena Ruiz
(11, 10),-- Electrónica - Diego Álvarez
(12, 11),-- Reggaeton - Clara Moreno
(13, 12),-- Para estudiar - Hugo Jiménez
(14, 13),-- Road Trip - Lucía Navarro
(15, 14);-- Throwback - Adrián Torres

-- Insertar datos en Playlist_song (relación entre Playlists y Songs)
INSERT INTO "Spotfi".playlist_song (id_playlist, id_song) VALUES
(1, 1),  -- Top 2023 - Tití Me Preguntó
(1, 2),  -- Top 2023 - Anti-Hero
(1, 3),  -- Top 2023 - Blinding Lights
(2, 4),  -- Pop Favoritos - Levitating
(2, 5),  -- Pop Favoritos - Shape of You
(2, 6),  -- Pop Favoritos - Happier Than Ever
(3, 3),  -- Para entrenar - Blinding Lights
(3, 8),  -- Para entrenar - 24K Magic
(3, 9),  -- Para entrenar - Viva la Vida
(4, 7),  -- Relajación - Hello
(4, 9),  -- Relajación - Viva la Vida
(4, 10), -- Relajación - Señorita
(5, 1),  -- Fiesta - Tití Me Preguntó
(5, 16), -- Fiesta - Dákiti
(5, 18), -- Fiesta - Save Your Tears
(6, 9),  -- Clásicos - Viva la Vida
(6, 10), -- Clásicos - Señorita
(6, 13), -- Clásicos - Halo
(7, 18), -- Indie Mix - Save Your Tears
(7, 19), -- Indie Mix - Don't Start Now
(7, 11), -- Indie Mix - Thank U, Next
(8, 1),  -- Verano 2023 - Tití Me Preguntó
(8, 16), -- Verano 2023 - Dákiti
(8, 17), -- Verano 2023 - Cruel Summer
(9, 7),  -- Baladas - Hello
(9, 10), -- Baladas - Señorita
(9, 13), -- Baladas - Halo
(10, 9), -- Rock - Viva la Vida
(10, 14),-- Rock - Umbrella
(10, 15);-- Rock - God's Plan


/*
ESTE INSERT LO HICE EL DIA DE HACER LAS CONSULTAS PARA LA
CONSULTA DE VER QUE HA ESCUCHADO EN UN INTERVALO DE TIEMPO

*/

INSERT INTO "Spotfi".play (id_user, id_song, date) VALUES
(2, 1, CURRENT_TIMESTAMP - INTERVAL '1 day'),
(2, 2, CURRENT_TIMESTAMP - INTERVAL '2 days');
