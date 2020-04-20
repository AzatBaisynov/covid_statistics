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
            String a = doc.getElementsByTag("td").get(count + 1).text();
            String b = checkForNull(doc.getElementsByTag("td").get(count + 2).text());
            String c = doc.getElementsByTag("td").get(count + 3).text();
            String d = checkForNull(doc.getElementsByTag("td").get(count + 4).text());

            articles.add(new Article(
                    doc.getElementsByTag("td").get(count).text(),
                    doc.getElementsByTag("td").get(count + 1).text(),
                    checkForNull(doc.getElementsByTag("td").get(count + 2).text()) + pAdd(a, b),
                    doc.getElementsByTag("td").get(count + 3).text(),
                    checkForNull(doc.getElementsByTag("td").get(count + 4).text()) + "  " + pAdd(c, d),
                    doc.getElementsByTag("td").get(count + 5).text(),
                    doc.getElementsByTag("td").get(count + 6).text()));
            count += 13;
        }
        return articles;
    }

    public static Article getCountryOfficialTable(String country, String region) {
        List<String> list = Changer.getOneCountryList(country, region);
        String a = list.get(1);
        String b = checkForNull(list.get(2));
        String c = list.get(3);
        String d = checkForNull(list.get(4));
        Article article = new Article(
                worldTranslator(list.get(0)),
                list.get(1),
                checkForNull(list.get(2)) + pAdd(a, b),
                list.get(3),
                checkForNull(list.get(4)) + "  " + pAdd(c, d),
                list.get(5),
                list.get(6));
        return article;
    }

    public static String checkForNull(String str) {
        if (str.equals("")) {
            return "0";
        }
        return str;
    }

    public static Document docMaker() {
        Document doc = null;
        try {
            doc = Jsoup.connect("https://www.worldometers.info/coronavirus/").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }

    public static String worldTranslator(String str) {
        if (str.equals("World")) {
            return "Мир";
        }
        return str;
    }

    public static String pAdd(String t, String p) {
        if (p != "0") {
            int l1 = p.length();
            String j = "";
            for (int i = 0; i < 12 - l1; i++) {
                j = j + " ";
            }
            int a = Integer.parseInt(t.replace(",", ""));
            int b = Integer.parseInt(p.replace(",", "").replace("+", ""));
            double c = (double) a / 100;
            double d = (double) b / c;
            double check = d - ((int) d);
            if (check < 0.25) {
                return j + "+" + ((int) d) + "%";
            } else if (check > 0.25 && check < 0.5 ||
                    check > 0.5 && check < 0.75) {
                return j + "+" + ((int) d + 0.5) + "%";
            } else {
                return j + "+" + ((int) d + 1.0) + "%";
            }
        } return "";
    }
}