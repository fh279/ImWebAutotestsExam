package org.mike.triestowriteautotests;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

public class ImPagesData {
    protected static FileInputStream fileInputStream;// = new FileInputStream("C:\\Users\\mpogorelov\\IdeaProjects\\Yandex tests from GH\\YaTestsAgain\\src\\test\\java\\org\\mike\\triestowriteautotestsaaaaaa");
    protected static Properties PROPERTIES;

    static {
        try {
            fileInputStream = new FileInputStream("C:\\Users\\mpogorelov\\IdeaProjects\\Yandex tests from GH\\YaTestsAgain\\src\\test\\resources\\imPagesData.properties");
            PROPERTIES = new Properties();
            PROPERTIES.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("С файликом настроек что-то не так...");
        } finally {
            if (fileInputStream != null) try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }
}