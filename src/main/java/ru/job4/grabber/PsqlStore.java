package ru.job4.grabber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PsqlStore implements Store, AutoCloseable {

    private Connection cnn;
    private static final Logger LOG = LoggerFactory.getLogger(Post.class.getName());

    public PsqlStore(Properties  cfg) throws SQLException {
        try {
            Class.forName(cfg.getProperty("driver"));
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
        cnn = DriverManager.getConnection(cfg.getProperty("url"), cfg.getProperty("login"), cfg.getProperty("password"));
    }

    public static Properties addProperties(String url) throws IOException {
        Path file = Paths.get(url);
        Properties properties = new Properties();
        try (FileReader read = new FileReader(file.toFile())) {
            properties.load(read);
        }
        return properties;
    }

    @Override
    public void save(Post post) {
        try (Statement statement = cnn.createStatement()) {
            String sql = String.format("insert into post(name, text, link, created) values('%s', '%s', '%s', '%s')",
                    post.getTitle(),
                    post.getDescription(),
                    post.getLink(),
                    Timestamp.valueOf(post.getCreated())
            );
            statement.execute(sql);
        } catch (SQLException e) {
            LOG.error("Ошибка сохранения объекта в БД - {}", e);
        }
    }

    @Override
    public List<Post> getAll() {
        List<Post> list = new ArrayList<>();
        try (Statement statement = cnn.createStatement()) {
            String sql = "select * from post";
            ResultSet rsl = statement.executeQuery(sql);
            while (rsl.next()) {
                Post post = new Post(
                        rsl.getString("name"),
                        rsl.getString("text"),
                        rsl.getString("link"),
                        rsl.getTimestamp("created").toLocalDateTime()
                );
                post.setId(rsl.getInt("id"));
                list.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Post findById(int id) {
        Post post = null;
        try (Statement statement = cnn.createStatement()) {
            String sql = String.format("select * from post where id = %s", id);
            ResultSet rsl = statement.executeQuery(sql);
            while (rsl.next()) {
                post.setId(rsl.getInt("id"));
                post.setTitle(rsl.getString("name"));
                post.setDescription(rsl.getString("text"));
                post.setLink(rsl.getString("link"));
                post.setCreated(rsl.getTimestamp("created").toLocalDateTime());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return post;
    }

    @Override
    public void close() throws Exception {
        if (cnn != null) {
            cnn.close();
        }
    }

    public static void main(String[] args) throws IOException, SQLException {
        Post post = new Post(
                "Java Developer Best B",
                "https://www.yandex.ru/search/?text=java+properties+load&lr=1196004",
                "best world job",
                LocalDateTime.now());
        Properties cfg = addProperties("C:\\projects\\job4j_grabber\\src\\main\\resources\\rabbit.properties");
        PsqlStore store = new PsqlStore(cfg);
        store.getAll().stream().forEach(s -> System.out.println(s));
    }

}
