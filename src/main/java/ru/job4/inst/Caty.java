package ru.job4.inst;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Caty {

    private static final String SOURCE_LINK  = "https://ria.ru";
    private static final String PAGE_LINK = String.format("%s/lenta", SOURCE_LINK);

    public static void main(String[] args) throws IOException {
        Connection connect = Jsoup.connect(PAGE_LINK);
        Document document = connect.get();
        Elements rows = document.select(".list-Item");
        rows.stream().forEach(row -> {
            Element titleElement = row.select(".list-item__content").first();
            Element dateElement = row.select(".list-item__info").first();
            Element linkElement = row.child(1).child(0);
            String title = titleElement.child(1).text();
            String time = dateElement.child(0).text();
            String link = linkElement.attr("href");
            //System.out.printf("%s, %s, %s", title, date, link);
            System.out.printf("%s, %s, %s\n", title, time, link);
        });
    }
}