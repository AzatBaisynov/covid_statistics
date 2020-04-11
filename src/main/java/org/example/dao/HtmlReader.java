package org.example.dao;

import org.example.repo.Article;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HtmlReader {

    public static List<Article> getOfficialTable() {
        List<Article> articles = new ArrayList<>();
        Document doc = docMaker();

        int count = 104;

        while (count <= 224) {
            articles.add(new Article(
                    doc.getElementsByTag("td").get(count).text(),
                    checkForNull(doc.getElementsByTag("td").get(count+1).text()),
                    checkForNull(doc.getElementsByTag("td").get(count+2).text()),
                    checkForNull(doc.getElementsByTag("td").get(count+3).text()),
                    checkForNull(doc.getElementsByTag("td").get(count+4).text()),
                    checkForNull(doc.getElementsByTag("td").get(count+5).text()),
                    checkForNull(doc.getElementsByTag("td").get(count+6).text())));
            count += 13;
        }
        return articles;
    }

    public static Article getCountryOfficialTable(String country, String region){
        List<String> list = Changer.getOneCountryList(country, region);
        Article article = new Article(
                list.get(0),
                list.get(1),
                checkForNull(list.get(2)),
                list.get(3),
                checkForNull(list.get(4)),
                list.get(5),
                list.get(6));
        return article;
    }

    public static String checkForNull(String str){
        if (str.equals("")){
            return "0";
        }
        return str;
    }

    public static Document docMaker(){
        Document doc = null;
        try { doc = Jsoup.connect("https://www.worldometers.info/coronavirus/").get(); }
        catch (IOException e) { e.printStackTrace(); }
        return doc;
    }
}