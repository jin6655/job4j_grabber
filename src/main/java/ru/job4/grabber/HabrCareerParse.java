package ru.job4.grabber;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4.utils.DateTimeParser;
import ru.job4.utils.HabrCareerDateTimeParser;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class HabrCareerParse implements Parse {

private static final String SOURCE_LINK = "https://career.habr.com";
private static final String PAGE_LINK = String.format("%s/vacancies/java_developer", SOURCE_LINK);
private static final int NUMBER_PAGES = 1;
private final DateTimeParser dateTimeParser;
private static final Logger LOG = LoggerFactory.getLogger(HabrCareerParse.class.getName());

    public HabrCareerParse(DateTimeParser dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    private String retrieveDescription(String link) throws IOException {
        StringBuilder rsl = new StringBuilder();
        Connection connection = Jsoup.connect(String.format("%s%s", SOURCE_LINK, link));
        Document document = connection.get();
        Elements rows01 = document.select(".basic-section--appearance-vacancy-description");
        rows01.stream().forEach(row -> {
            Element descriptionTittleElement = row.select(".section-title--divider").first();
            Element descriptionElement = row.select(".style-ugc").first();
            rsl.append(descriptionTittleElement.text()).append("\n").append(descriptionElement.text());
        });
        return rsl.toString().replace("'", "|");
    }

    @Override
    public List<Post> list() throws IOException {
        LOG.info("Старт парсинга");
        List<Post> list = new ArrayList<>();
        for (int n = 1; n <= NUMBER_PAGES; n++) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            Connection connection = Jsoup.connect(String.format("%s%s" + n, PAGE_LINK, "?page="));
                Document document = connection.get();
                Elements rows = document.select(".vacancy-card__inner");
                rows.stream().forEach(row -> {
                    Element titleElement = row.select(".vacancy-card__title").first();
                    Element linkElement = titleElement.child(0);
                    Element dateElement = row.select(".vacancy-card__date").first().child(0);
                    Element descriptionLinkElement = row.select(".vacancy-card__title").first();
                    String description = "";
                    try {
                        description = new HabrCareerParse(new HabrCareerDateTimeParser())
                                .retrieveDescription(descriptionLinkElement.child(0).attr("href"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    String vacancyName = titleElement.text();
                    String linkPost = String.format("%s%s", SOURCE_LINK, linkElement.attr("href"));
                    LocalDateTime date = new HabrCareerDateTimeParser().parse(dateElement.attr("datetime"));
                    list.add(new Post(vacancyName, linkPost, description, date));
                });
        }
        LOG.info("Выполнен парсинг {} вакансий.", list.size());
        return list;
    }

    public static void main(String[] args) throws IOException {
        Parse parse = new HabrCareerParse(new HabrCareerDateTimeParser());
        List<Post> list = parse.list();
        System.out.println(list.size());
    }

}
