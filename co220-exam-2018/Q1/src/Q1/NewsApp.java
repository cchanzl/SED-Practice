package Q1;

import com.dailymail.DailyMail;
import com.dailymail.DailyMailArticle;

import java.util.*;

import static java.util.Comparator.comparing;

public class NewsApp {

    public List<DailyMailArticle> latestStories() {

        List<DailyMailArticle> articles = DailyMail.getArticles();

        articles.sort(comparing(DailyMailArticle::timestamp).reversed());

        return articles;
    }

    public static void main(String[] args) {
        new NewsApp().latestStories().forEach(a -> System.out.println(a.hline()));
    }
}


