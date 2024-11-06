
package AIS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static final String URL = "jdbc:sqlite:games_catalog.db";

    public Database() {
        this.createTables();
        this.insertInitialData();
    }

    private void createTables() {
        String createGenresTable = "CREATE TABLE IF NOT EXISTS Genres (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, description TEXT);";
        String createDevelopersTable = "CREATE TABLE IF NOT EXISTS Developers (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, founded_year INTEGER, website TEXT);";
        String createPublishersTable = "CREATE TABLE IF NOT EXISTS Publishers (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, founded_year INTEGER, website TEXT);";
        String createPlatformsTable = "CREATE TABLE IF NOT EXISTS Platforms (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, manufacturer TEXT, release_year INTEGER);";
        String createGamesTable = "CREATE TABLE IF NOT EXISTS Games (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT NOT NULL, genre_id INTEGER, developer_id INTEGER, publisher_id INTEGER, platform_id INTEGER, release_date TEXT, rating REAL, description TEXT, price REAL, is_multiplayer BOOLEAN, FOREIGN KEY (genre_id) REFERENCES Genres(id), FOREIGN KEY (developer_id) REFERENCES Developers(id), FOREIGN KEY (publisher_id) REFERENCES Publishers(id), FOREIGN KEY (platform_id) REFERENCES Platforms(id));";
        String createReviewsTable = "CREATE TABLE IF NOT EXISTS Reviews (id INTEGER PRIMARY KEY AUTOINCREMENT, game_id INTEGER, review TEXT NOT NULL, rating INTEGER, reviewer_name TEXT, review_date TEXT, FOREIGN KEY (game_id) REFERENCES Games(id));";

        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:games_catalog.db");

            try {
                Statement stmt = conn.createStatement();

                try {
                    stmt.execute(createGenresTable);
                    stmt.execute(createDevelopersTable);
                    stmt.execute(createPublishersTable);
                    stmt.execute(createPlatformsTable);
                    stmt.execute(createGamesTable);
                    stmt.execute(createReviewsTable);
                } catch (Throwable var13) {
                    if (stmt != null) {
                        try {
                            stmt.close();
                        } catch (Throwable var12) {
                            var13.addSuppressed(var12);
                        }
                    }

                    throw var13;
                }

                if (stmt != null) {
                    stmt.close();
                }
            } catch (Throwable var14) {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (Throwable var11) {
                        var14.addSuppressed(var11);
                    }
                }

                throw var14;
            }

            if (conn != null) {
                conn.close();
            }
        } catch (SQLException var15) {
            SQLException e = var15;
            System.out.println("Error creating tables: " + e.getMessage());
        }

    }

    private void insertInitialData() {
        String insertGenres = "INSERT INTO Genres (name, description) VALUES ('Action', 'Fast-paced games with physical challenges.'), ('Adventure', 'Story-driven games with exploration.'), ('RPG', 'Role-playing games with character development.'), ('Simulation', 'Games that simulate real-world activities.'), ('Puzzle', 'Games that require problem-solving.'), ('Strategy', 'Games that require planning and tactics.'), ('Horror', 'Games designed to scare the player.'), ('Sports', 'Games based on real-world sports.'), ('Racing', 'Fast-paced competition on vehicles.'), ('Music', 'Games focused on rhythm and music.'), ('Educational', 'Games designed for learning.'), ('Card', 'Games played with cards.'), ('Party', 'Multiplayer games for social gatherings.'), ('Massively Multiplayer', 'Online games with large player counts.'), ('Fighting', 'Games focused on combat between characters.'), ('Stealth', 'Games focused on avoiding detection.'), ('Platformer', 'Games that involve jumping between platforms.'), ('Idle', 'Games that progress without active player input.'), ('Visual Novel', 'Story-driven games with narrative choices.'), ('Survival', 'Games focused on survival in challenging environments.');";
        String insertDevelopers = "INSERT INTO Developers (name, founded_year, website) VALUES ('Developer A', 1995, 'http://developera.com'), ('Developer B', 2000, 'http://developerb.com'), ('Developer C', 2010, 'http://developerc.com'), ('Developer D', 1998, 'http://developerd.com'), ('Developer E', 2005, 'http://developere.com'), ('Developer F', 2015, 'http://developerf.com'), ('Developer G', 2020, 'http://developerg.com'), ('Developer H', 2012, 'http://developerh.com'), ('Developer I', 2018, 'http://developeri.com'), ('Developer J', 1990, 'http://developerj.com'), ('Developer K', 2003, 'http://developerk.com'), ('Developer L', 2011, 'http://developerl.com'), ('Developer M', 2008, 'http://developerm.com'), ('Developer N', 1997, 'http://developern.com'), ('Developer O', 2001, 'http://developero.com'), ('Developer P', 2009, 'http://developerp.com'), ('Developer Q', 2017, 'http://developerq.com'), ('Developer R', 2006, 'http://developerr.com'), ('Developer S', 1993, 'http://developers.com'), ('Developer T', 2019, 'http://developert.com');";
        String insertPublishers = "INSERT INTO Publishers (name, founded_year, website) VALUES ('Publisher A', 1995, 'http://publishera.com'), ('Publisher B', 2000, 'http://publisherb.com'), ('Publisher C', 2010, 'http://publisherc.com'), ('Publisher D', 1998, 'http://publisherd.com'), ('Publisher E', 2005, 'http://publishere.com'), ('Publisher F', 2015, 'http://publisherf.com'), ('Publisher G', 2020, 'http://publisherg.com'), ('Publisher H', 2012, 'http://publisherh.com'), ('Publisher I', 2018, 'http://publisheri.com'), ('Publisher J', 1990, 'http://publisherj.com'), ('Publisher K', 2003, 'http://publisherk.com'), ('Publisher L', 2011, 'http://publisherl.com'), ('Publisher M', 2008, 'http://publisherm.com'), ('Publisher N', 1997, 'http://publishern.com'), ('Publisher O', 2001, 'http://publishero.com'), ('Publisher P', 2009, 'http://publisherp.com'), ('Publisher Q', 2017, 'http://publisherq.com'), ('Publisher R', 2006, 'http://publisherr.com'), ('Publisher S', 1993, 'http://publishers.com'), ('Publisher T', 2019, 'http://publishert.com');";
        String insertPlatforms = "INSERT INTO Platforms (name, manufacturer, release_year) VALUES ('PC', 'Various', 1980), ('PlayStation 4', 'Sony', 2013), ('PlayStation 5', 'Sony', 2020), ('Xbox One', 'Microsoft', 2013), ('Xbox Series X', 'Microsoft', 2020), ('Nintendo Switch', 'Nintendo', 2017), ('Mobile', 'Various', 2000), ('Mac', 'Apple', 1984), ('Linux', 'Various', 1991), ('Web', 'Various', 1990), ('VR', 'Various', 2016), ('Augmented Reality', 'Various', 2016), ('Handheld', 'Various', 1990), ('Retro', 'Various', 1980), ('Cloud', 'Various', 2010), ('Emulator', 'Various', 1995), ('Game Streaming', 'Various', 2015), ('Smart TV', 'Various', 2012), ('Wearable', 'Various', 2015), ('Interactive TV', 'Various', 2018);";
        String insertGames = "INSERT INTO Games (title, genre_id, developer_id, publisher_id, platform_id, release_date, rating, description, price, is_multiplayer) VALUES ('Game 1', 1, 1, 1, 1, '2023-01-01', 9.0, 'Action-packed adventure game.', 59.99, 1), ('Game 2', 2, 2, 2, 2, '2022-02-01', 8.5, 'Explore a vast world.', 49.99, 0), ('Game 3', 3, 3, 3, 3, '2021-03-01', 9.2, 'Role-playing epic.', 39.99, 1), ('Game 4', 4, 4, 4, 4, '2020-04-01', 7.8, 'Simulation of city life.', 29.99, 0), ('Game 5', 5, 5, 5, 5, '2019-05-01', 8.0, 'Challenging puzzles await.', 19.99, 0), ('Game 6', 6, 6, 6, 6, '2018-06-01', 9.5, 'Strategize your way to victory.', 34.99, 1), ('Game 7', 7, 7, 7, 7, '2017-07-01', 8.7, 'Horror game that chills.', 44.99, 0), ('Game 8', 8, 8, 8, 8, '2016-08-01', 7.4, 'Fast-paced racing experience.', 54.99, 1), ('Game 9', 9, 9, 9, 9, '2015-09-01', 9.1, 'Musical rhythm game.', 29.99, 0), ('Game 10', 10, 10, 10, 10, '2014-10-01', 8.6, 'Educational adventure.', 24.99, 1), ('Game 11', 1, 11, 11, 11, '2023-01-02', 9.1, 'Action sequel with more content.', 64.99, 1), ('Game 12', 2, 12, 12, 12, '2022-02-02', 8.4, 'Adventure with new characters.', 59.99, 0), ('Game 13', 3, 13, 13, 13, '2021-03-02', 9.3, 'RPG with rich lore.', 39.99, 1), ('Game 14', 4, 14, 14, 14, '2020-04-02', 7.9, 'City simulation with new features.', 29.99, 0), ('Game 15', 5, 15, 15, 15, '2019-05-02', 8.1, 'Puzzle game with unique mechanics.', 19.99, 0), ('Game 16', 6, 16, 16, 16, '2018-06-02', 9.6, 'Strategy game with intense battles.', 34.99, 1), ('Game 17', 7, 17, 17, 17, '2017-07-02', 8.8, 'Horror game with gripping story.', 44.99, 0), ('Game 18', 8, 18, 18, 18, '2016-08-02', 7.5, 'Racing with multiplayer mode.', 54.99, 1), ('Game 19', 9, 19, 19, 19, '2015-09-02', 9.2, 'Music game that challenges rhythm.', 29.99, 0), ('Game 20', 10, 20, 20, 20, '2014-10-02', 8.7, 'Educational game for all ages.', 24.99, 1);";
        String insertReviews = "INSERT INTO Reviews (game_id, review, rating, reviewer_name, review_date) VALUES (1, 'Amazing gameplay!', 10, 'Alice', '2023-01-05'), (2, 'Very enjoyable!', 9, 'Bob', '2022-02-06'), (3, 'Could be better.', 7, 'Charlie', '2021-03-07'), (4, 'Not my type.', 5, 'Dave', '2020-04-08'), (5, 'Loved the graphics!', 8, 'Eve', '2019-05-09'), (6, 'Great mechanics!', 9, 'Frank', '2018-06-10'), (7, 'Fun but repetitive.', 6, 'Grace', '2017-07-11'), (8, 'Too hard!', 4, 'Heidi', '2016-08-12'), (9, 'Excellent story!', 10, 'Ivan', '2015-09-13'), (10, 'Would recommend!', 8, 'Judy', '2014-10-14'), (11, 'Fantastic sequel!', 10, 'Alice', '2023-01-06'), (12, 'Pretty good.', 8, 'Bob', '2022-02-07'), (13, 'Disappointing.', 3, 'Charlie', '2021-03-08'), (14, 'Just okay.', 5, 'Dave', '2020-04-09'), (15, 'Beautiful world!', 9, 'Eve', '2019-05-10'), (16, 'Not worth it.', 2, 'Frank', '2018-06-11'), (17, 'Loved it!', 10, 'Grace', '2017-07-12'), (18, 'Challenging!', 7, 'Heidi', '2016-08-13'), (19, 'Pretty boring.', 4, 'Ivan', '2015-09-14'), (20, 'An absolute blast!', 10, 'Judy', '2014-10-15');";

        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:games_catalog.db");

            try {
                Statement stmt = conn.createStatement();

                try {
                    stmt.execute(insertGenres);
                    stmt.execute(insertDevelopers);
                    stmt.execute(insertPublishers);
                    stmt.execute(insertPlatforms);
                    stmt.execute(insertGames);
                    stmt.execute(insertReviews);
                } catch (Throwable var13) {
                    if (stmt != null) {
                        try {
                            stmt.close();
                        } catch (Throwable var12) {
                            var13.addSuppressed(var12);
                        }
                    }

                    throw var13;
                }

                if (stmt != null) {
                    stmt.close();
                }
            } catch (Throwable var14) {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (Throwable var11) {
                        var14.addSuppressed(var11);
                    }
                }

                throw var14;
            }

            if (conn != null) {
                conn.close();
            }
        } catch (SQLException var15) {
            SQLException e = var15;
            System.out.println("Error inserting initial data: " + e.getMessage());
        }

    }
}
