package AIS;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class MainFrame extends JFrame {
    private JTable table;
    private JScrollPane scrollPane;
    private JTextField searchField;
    private String currentTable = "Games";

    public MainFrame() {
        this.setTitle("Catalog of Computer Games");
        this.setSize(600, 400);
        this.setDefaultCloseOperation(3);
        this.setLayout(new BorderLayout());
        this.table = new JTable();
        this.scrollPane = new JScrollPane(this.table);
        this.add(this.scrollPane, "Center");
        JPanel loadButtonPanel = new JPanel();
        loadButtonPanel.setLayout(new FlowLayout());
        JButton loadGamesButton = this.createLoadButton("Игры", "Games");
        JButton loadDevelopersButton = this.createLoadButton("Разработчики", "Developers");
        JButton loadPublishersButton = this.createLoadButton("Издатели", "Publishers");
        JButton loadPlatformsButton = this.createLoadButton("Платформы", "Platforms");
        JButton loadGenresButton = this.createLoadButton("Жанры", "Genres");
        JButton loadReviewsButton = this.createLoadButton("Отзывы", "Reviews");
        loadButtonPanel.add(loadGamesButton);
        loadButtonPanel.add(loadDevelopersButton);
        loadButtonPanel.add(loadPublishersButton);
        loadButtonPanel.add(loadPlatformsButton);
        loadButtonPanel.add(loadGenresButton);
        loadButtonPanel.add(loadReviewsButton);
        this.add(loadButtonPanel, "North");
        JPanel actionButtonPanel = new JPanel();
        actionButtonPanel.setLayout(new FlowLayout());
        JButton addButton = new JButton("Добавить");
        addButton.addActionListener((e) -> {
            this.addEntry();
        });
        actionButtonPanel.add(addButton);
        JButton deleteButton = new JButton("Удалить");
        deleteButton.addActionListener((e) -> {
            this.deleteEntry();
        });
        actionButtonPanel.add(deleteButton);
        this.searchField = new JTextField(10);
        actionButtonPanel.add(this.searchField);
        JButton searchButton = new JButton("Поиск");
        searchButton.addActionListener((e) -> {
            this.searchEntries();
        });
        actionButtonPanel.add(searchButton);
        this.add(actionButtonPanel, "South");
    }

    private JButton createLoadButton(String text, String tableName) {
        JButton button = new JButton(text);
        button.addActionListener((e) -> {
            this.currentTable = tableName;
            this.loadTableData();
        });
        return button;
    }

    private void loadTableData() {
        String query;
        String[] columnNames;
        switch (this.currentTable) {
            case "Games":
                query = "SELECT g.id, g.title, gen.name AS genre, g.release_date, g.rating FROM Games g JOIN Genres gen ON g.genre_id = gen.id";
                columnNames = new String[]{"ID", "Title", "Genre", "Release Date", "Rating"};
                break;
            case "Developers":
                query = "SELECT * FROM Developers";
                columnNames = new String[]{"ID", "Name", "Founded Year", "Website"};
                break;
            case "Publishers":
                query = "SELECT * FROM Publishers";
                columnNames = new String[]{"ID", "Name", "Founded Year", "Website"};
                break;
            case "Platforms":
                query = "SELECT * FROM Platforms";
                columnNames = new String[]{"ID", "Name", "Manufacturer", "Release Year"};
                break;
            case "Genres":
                query = "SELECT * FROM Genres";
                columnNames = new String[]{"ID", "Name", "Description"};
                break;
            case "Reviews":
                query = "SELECT * FROM Reviews";
                columnNames = new String[]{"ID", "Game ID", "Review", "Rating", "Reviewer Name", "Review Date"};
                break;
            default:
                return;
        }

        this.loadTableData(query, columnNames, (String)null);
    }

    private void loadTableData(String query, String[] columnNames, String search) {
        String url = "jdbc:sqlite:games_catalog.db";
        Vector<Vector<Object>> data = new Vector();

        try {
            Connection conn = DriverManager.getConnection(url);

            try {
                PreparedStatement pstmt = conn.prepareStatement(query);

                try {
                    if (search != null) {
                        pstmt.setString(1, search);
                    }

                    ResultSet rs = pstmt.executeQuery();

                    while(rs.next()) {
                        Vector<Object> row = new Vector();
                        String[] var10 = columnNames;
                        int var11 = columnNames.length;

                        for(int var12 = 0; var12 < var11; ++var12) {
                            String columnName = var10[var12];
                            row.add(rs.getObject(columnName.toLowerCase().replace(" ", "_")));
                        }

                        data.add(row);
                    }
                } catch (Throwable var16) {
                    if (pstmt != null) {
                        try {
                            pstmt.close();
                        } catch (Throwable var15) {
                            var16.addSuppressed(var15);
                        }
                    }

                    throw var16;
                }

                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (Throwable var17) {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (Throwable var14) {
                        var17.addSuppressed(var14);
                    }
                }

                throw var17;
            }

            if (conn != null) {
                conn.close();
            }
        } catch (SQLException var18) {
            SQLException e = var18;
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }

        this.table.setModel(new DefaultTableModel(data, new Vector(Arrays.asList(columnNames))));
    }

    private void addEntry() {
        String title = JOptionPane.showInputDialog(this, "Введите название:");
        String gameIdStr;
        String review;
        String ratingStr;
        if (this.currentTable.equals("Games")) {
            gameIdStr = JOptionPane.showInputDialog(this, "Введите айди игры:");
            review = JOptionPane.showInputDialog(this, "Введите дату релиза (ГГГГ-ММ-ДД):");
            ratingStr = JOptionPane.showInputDialog(this, "Введите рейтинг:");
            if (title != null && gameIdStr != null && review != null && ratingStr != null) {
                int genreId = Integer.parseInt(gameIdStr);
                double rating = Double.parseDouble(ratingStr);
                String sql = "INSERT INTO Games (title, genre_id, release_date, rating) VALUES (?, ?, ?, ?)";
                this.executeUpdate(sql, title, genreId, review, rating);
            }
        } else {
            int releaseYear;
            String reviewerName;
            if (this.currentTable.equals("Developers")) {
                gameIdStr = JOptionPane.showInputDialog(this, "Введите год основания:");
                review = JOptionPane.showInputDialog(this, "Введите сайт:");
                if (title != null && gameIdStr != null && review != null) {
                    releaseYear = Integer.parseInt(gameIdStr);
                    reviewerName = "INSERT INTO Developers (name, founded_year, website) VALUES (?, ?, ?)";
                    this.executeUpdate(reviewerName, title, releaseYear, review);
                }
            } else if (this.currentTable.equals("Publishers")) {
                gameIdStr = JOptionPane.showInputDialog(this, "Введите год основания:");
                review = JOptionPane.showInputDialog(this, "Введите сайт:");
                if (title != null && gameIdStr != null && review != null) {
                    releaseYear = Integer.parseInt(gameIdStr);
                    reviewerName = "INSERT INTO Publishers (name, founded_year, website) VALUES (?, ?, ?)";
                    this.executeUpdate(reviewerName, title, releaseYear, review);
                }
            } else if (this.currentTable.equals("Platforms")) {
                gameIdStr = JOptionPane.showInputDialog(this, "Введите производителя:");
                review = JOptionPane.showInputDialog(this, "Введите год основания:");
                if (title != null && gameIdStr != null && review != null) {
                    releaseYear = Integer.parseInt(review);
                    reviewerName = "INSERT INTO Platforms (name, manufacturer, release_year) VALUES (?, ?, ?)";
                    this.executeUpdate(reviewerName, title, gameIdStr, releaseYear);
                }
            } else if (this.currentTable.equals("Genres")) {
                gameIdStr = JOptionPane.showInputDialog(this, "Введите описание");
                if (title != null && gameIdStr != null) {
                    review = "INSERT INTO Genres (name, description) VALUES (?, ?)";
                    this.executeUpdate(review, title, gameIdStr);
                }
            } else if (this.currentTable.equals("Reviews")) {
                gameIdStr = JOptionPane.showInputDialog(this, "Введите айди игры:");
                review = JOptionPane.showInputDialog(this, "Введите отзыв:");
                ratingStr = JOptionPane.showInputDialog(this, "Введите рейтинг:");
                reviewerName = JOptionPane.showInputDialog(this, "Введите имя отзыва:");
                String reviewDate = JOptionPane.showInputDialog(this, "Введите дату отзыва (ГГГГ-ММ-ДД):");
                if (gameIdStr != null && review != null && ratingStr != null && reviewerName != null && reviewDate != null) {
                    int gameId = Integer.parseInt(gameIdStr);
                    double rating = Double.parseDouble(ratingStr);
                    String sql = "INSERT INTO Reviews (game_id, review, rating, reviewer_name, review_date) VALUES (?, ?, ?, ?, ?)";
                    this.executeUpdate(sql, gameId, review, rating, reviewerName, reviewDate);
                }
            }
        }

    }

    private void deleteEntry() {
        int selectedRow = this.table.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (Integer)this.table.getValueAt(selectedRow, 0);
            String sql = "DELETE FROM " + this.currentTable + " WHERE id = ?";

            try {
                Connection conn = DriverManager.getConnection("jdbc:sqlite:games_catalog.db");

                try {
                    PreparedStatement pstmt = conn.prepareStatement(sql);

                    try {
                        pstmt.setInt(1, id);
                        pstmt.executeUpdate();
                        this.loadTableData();
                    } catch (Throwable var10) {
                        if (pstmt != null) {
                            try {
                                pstmt.close();
                            } catch (Throwable var9) {
                                var10.addSuppressed(var9);
                            }
                        }

                        throw var10;
                    }

                    if (pstmt != null) {
                        pstmt.close();
                    }
                } catch (Throwable var11) {
                    if (conn != null) {
                        try {
                            conn.close();
                        } catch (Throwable var8) {
                            var11.addSuppressed(var8);
                        }
                    }

                    throw var11;
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException var12) {
                SQLException e = var12;
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select an entry to delete.");
        }

    }

    private void searchEntries() {
        String searchTerm = this.searchField.getText();
        String query = "";
        if (this.currentTable.equals("Games")) {
            query = "SELECT g.id, g.title, gen.name AS genre, g.release_date, g.rating FROM Games g JOIN Genres gen ON g.genre_id = gen.id WHERE g.title LIKE ?";
        } else if (this.currentTable.equals("Developers")) {
            query = "SELECT * FROM Developers WHERE name LIKE ?";
        } else if (this.currentTable.equals("Publishers")) {
            query = "SELECT * FROM Publishers WHERE name LIKE ?";
        } else if (this.currentTable.equals("Platforms")) {
            query = "SELECT * FROM Platforms WHERE name LIKE ?";
        } else if (this.currentTable.equals("Genres")) {
            query = "SELECT * FROM Genres WHERE name LIKE ?";
        } else if (this.currentTable.equals("Reviews")) {
            query = "SELECT * FROM Reviews WHERE review LIKE ?";
        }

        this.loadTableData(query, new String[]{"ID", "Title", "Genre", "Release Date", "Rating"}, "%" + searchTerm + "%");
    }

    private void executeUpdate(String sql, Object... params) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:games_catalog.db");

            try {
                PreparedStatement pstmt = conn.prepareStatement(sql);

                try {
                    for(int i = 0; i < params.length; ++i) {
                        pstmt.setObject(i + 1, params[i]);
                    }

                    pstmt.executeUpdate();
                    this.loadTableData();
                } catch (Throwable var9) {
                    if (pstmt != null) {
                        try {
                            pstmt.close();
                        } catch (Throwable var8) {
                            var9.addSuppressed(var8);
                        }
                    }

                    throw var9;
                }

                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (Throwable var10) {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (Throwable var7) {
                        var10.addSuppressed(var7);
                    }
                }

                throw var10;
            }

            if (conn != null) {
                conn.close();
            }
        } catch (SQLException var11) {
            SQLException e = var11;
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AIS.Database();
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
