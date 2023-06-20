package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Question> parseDapAn(File file) {
        try {
            ArrayList<Question> questions = new ArrayList();

            Document doc = Jsoup.parse(file, "UTF-8", "http://example.com/");
            Elements quesList = doc.getElementsByClass("game-code-view-main-view-panel");

            for (Element e : quesList) {
                Element content = e.getElementsByClass("card-game-content").get(0);

                Elements answerCorrect = e.getElementsByClass("ks-checkBox cardAnswer-correct");
                Elements answerContent = answerCorrect.get(0).getElementsByClass("gwt-HTML");

                Question question = new Question(content.text(), answerContent.get(0).text().substring(3));

                questions.add(question);
            }

            return questions;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String findAnswer(ArrayList<Question> questions, Element element) {
        try {
            String quesContent = element.getElementsByClass("card-game-content").get(0).text();
            String correctAns = "";

            for (Question q : questions) {
                if (q.compare(quesContent)) {
                    correctAns = q.answer;
                    break;
                }
            }

            Elements ans = element.getElementsByClass("gwt-HTML");
            for (Element e : ans) {
                String answer = e.text();
                if (answer.substring(3).equals(correctAns)) {
                    return answer.substring(0,1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "A";
    }

    public static void main(String[] args) {
        System.out.println("Hello world!");

        System.out.println("Chuong ma^'y? Nhập từ 2 tới 5");
        int chapter = new Scanner(System.in).nextInt();

        String fileDapAn = "chuong";

        if (2 <= chapter && chapter <= 5) {
            fileDapAn = fileDapAn + chapter + ".html";
        } else {
            System.out.println("Chay lai chuong trinh di");
            return;
        }

        File chuong4 = new File(fileDapAn);
        ArrayList<Question> dapAn = parseDapAn(chuong4);

        for (Question q : dapAn) {
            System.out.println(q.content);
            System.out.println(q.answer);
            System.out.println();
        }

        try {
            File input = new File("input.html");
            System.out.println(input.getAbsoluteFile());

            Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");


            Elements quesList = doc.getElementsByClass("game-code-view-main-view-panel");
            System.out.println("Number of ques: " + quesList.size());
            String[] answer = new String[quesList.size() + 1];
            int i = 1;

            for (Element e : quesList) {
                try {
                    Element ques = e.getElementsByClass("card-game-content").get(0);
                    System.out.println("Question " + i);
                    System.out.println(ques.text());
                    String correct = findAnswer(dapAn,e);
                    System.out.println(correct);
                    answer[i] = correct;
                    i++;

                } catch (Exception e3) {
                    e3.printStackTrace();
                    continue;
                }

            }

            for (int z = 1; z <= quesList.size(); z++) {
                System.out.println("Quest: " + z + " : " + answer[z]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static class Question {
        String content;
        String answer;

        public Question(String content, String answer) {
            this.content = content;
            this.answer = answer;
        }

        public boolean compare(String content) {
            return this.content.equals(content);
        }
    }
}