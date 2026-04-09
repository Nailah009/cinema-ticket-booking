INSERT INTO movies (id, title, genre, duration_minutes)
VALUES ('MOV-001', 'Avengers Reborn', 'Action', 125)
ON DUPLICATE KEY UPDATE title=VALUES(title), genre=VALUES(genre), duration_minutes=VALUES(duration_minutes);

INSERT INTO movies (id, title, genre, duration_minutes)
VALUES ('MOV-002', 'The Silent Code', 'Thriller', 110)
ON DUPLICATE KEY UPDATE title=VALUES(title), genre=VALUES(genre), duration_minutes=VALUES(duration_minutes);

INSERT INTO show_schedules (id, movie_id, studio_name, show_time, price)
VALUES ('SCH-001', 'MOV-001', 'Studio 1', '2026-04-10 19:00:00', 50000.00)
ON DUPLICATE KEY UPDATE movie_id=VALUES(movie_id), studio_name=VALUES(studio_name), show_time=VALUES(show_time), price=VALUES(price);

INSERT INTO show_schedules (id, movie_id, studio_name, show_time, price)
VALUES ('SCH-002', 'MOV-002', 'Studio 2', '2026-04-10 20:00:00', 45000.00)
ON DUPLICATE KEY UPDATE movie_id=VALUES(movie_id), studio_name=VALUES(studio_name), show_time=VALUES(show_time), price=VALUES(price);
