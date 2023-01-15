package ru.job4.grabber;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4.utils.HabrCareerDateTimeParser;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HabrCareerParse {

private static final String SOURCE_LINK = "https://career.habr.com";
private static final String PAGE_LINK = String.format("%s/vacancies/java_developer", SOURCE_LINK);
private static final int NUMBER_PAGES = 1;

    private String retrieveDescription(String link) throws IOException {
        StringBuilder rsl = new StringBuilder();
        Connection connection = Jsoup.connect(String.format("%s%s", SOURCE_LINK, link));
        Document document = connection.get();
        Elements rows01 = document.select(".basic-section--appearance-vacancy-description");
        rows01.stream().forEach(row -> {
            Element descriptionTittleElement = row.select(".section-title--divider").first();
            Element descriptionElement = row.select(".style-ugc").first();
            rsl.append(descriptionTittleElement.text()).append("\n").append(descriptionElement.wholeText());
        });
        return rsl.toString();
    }

    public static void parse(int n) throws IOException {
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
                description = new HabrCareerParse().retrieveDescription(descriptionLinkElement.child(0).attr("href"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            String vacancyName = titleElement.text();
            String lInk = String.format("%s%s", SOURCE_LINK, linkElement.attr("href"));
            LocalDateTime date = new HabrCareerDateTimeParser().parse(dateElement.attr("datetime"));
            System.out.printf("%s, %s, %s\n%s\n\n", vacancyName, lInk, date.format(formatter), description);
        });
    }

    public static void main(String[] args) throws IOException {
        for (int i = 1; i <= NUMBER_PAGES; i++) {
            System.out.println(i + " Страница.");
                parse(i);
        }
    }

}
