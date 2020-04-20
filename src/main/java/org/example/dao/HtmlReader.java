package org.example.dao;

import com.vdurmont.emoji.EmojiParser;
import org.example.repo.Article;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class HtmlReader {
    public static String parsSm(String str){
        switch (str){
            case "USA":
                String parsedSmile1 = EmojiParser.parseToUnicode(":us:");
                return parsedSmile1;
            case "Spain":
                String parsedSmile2 = EmojiParser.parseToUnicode(":es:");
                return parsedSmile2;
            case "Italy":
                String parsedSmile3 = EmojiParser.parseToUnicode(":it:");
                return parsedSmile3;
            case "France":
                String parsedSmile4 = EmojiParser.parseToUnicode(":fr:");
                return parsedSmile4;
            case "Germany":
                String parsedSmile5 = EmojiParser.parseToUnicode(":de:");
                return parsedSmile5;
            case "UK" :
                String parsedSmile6 = EmojiParser.parseToUnicode(":gb:");
                return parsedSmile6;
            case "Turkey" :
                String parsedSmile7 = EmojiParser.parseToUnicode(":tr:");
                return parsedSmile7;
            case "Iran" :
                String parsedSmile8 = EmojiParser.parseToUnicode(":ir:");
                return parsedSmile8;
            case "Russia" :
                String parsedSmile9 = EmojiParser.parseToUnicode(":ru:");
                return parsedSmile9;
            case "Brazil" :
                String parsedSmile10 = EmojiParser.parseToUnicode(":br:");
                return parsedSmile10;
            case "Kyrgyzstan" :
                String parsedSmile11 = EmojiParser.parseToUnicode(":kg:");
                return parsedSmile11;
            case "Kazakhstan" :
                String parsedSmile12 = EmojiParser.parseToUnicode(":kz:");
                return parsedSmile12;
            case "Uzbekistan" :
                String parsedSmile13 = EmojiParser.parseToUnicode(":uz:");
                return parsedSmile13;
        }
        return "";
    }

    public static List<Article> getOfficialTable() {
        List<Article> articles = new ArrayList<>();
        Document doc = docMaker();

        int count = 104;


        while (count <= 224) {
            String name = doc.getElementsByTag("td").get(count).text();
            String a = doc.getElementsByTag("td").get(count + 6).text();
            String b = checkForNull(doc.getElementsByTag("td").get(count + 2).text());
            String c = doc.getElementsByTag("td").get(count + 3).text();
            String d = checkForNull(doc.getElementsByTag("td").get(count + 4).text());

            articles.add(new Article(
                    name + " " + parsSm(name),
                    doc.getElementsByTag("td").get(count + 1).text(),
                    checkForNull(doc.getElementsByTag("td").get(count + 2).text()),
                    doc.getElementsByTag("td").get(count + 3).text() + "  " + pAdd(c, d),
                    checkForNull(doc.getElementsByTag("td").get(count + 4).text()),
                    doc.getElementsByTag("td").get(count + 5).text(),
                    doc.getElementsByTag("td").get(count + 6).text()+ "  " + pAdd(a, b)));
            count += 13;
        }
        return articles;
    }

    public static Article getCountryOfficialTable(String country, String region) {
        List<String> list = Changer.getOneCountryList(country, region);
        String name = list.get(0);
        String a = list.get(6);
        String b = checkForNull(list.get(2));
        String c = list.get(3);
        String d = checkForNull(list.get(4));
        Article article = new Article(
                worldTranslator(list.get(0)) + " " + parsSm(name),
                list.get(1),
                checkForNull(list.get(2)),
                list.get(3) + "   " + pAdd(c, d),
                checkForNull(list.get(4)),
                list.get(5),
                list.get(6) + pAdd(a, b));
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