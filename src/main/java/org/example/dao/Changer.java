package org.example.dao;

import org.example.repo.Article;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.*;

public class Changer {
    static List getOneCountryList(String country, String reg){
        Document doc = HtmlReader.docMaker();

        LinkedList<String> list = new LinkedList<>();
        LinkedHashSet<String> set = new LinkedHashSet<>();
        String s = doc.getElementsByTag("td").text();
        String sub = s.substring(s.indexOf(country), s.length());
        String sub1 = sub.substring(sub.indexOf(country), sub.indexOf(reg));
        String[] sub2 = sub1.split(" ");
        for (int i = 0; i < sub2.length; i++){
            set.add(sub2[i]);
        }
        set.forEach(x -> list.add(x));
        return list;
    }
}