package net.lz1998.pbbot.method;


import lombok.extern.slf4j.Slf4j;
import net.lz1998.pbbot.bean.Charcater;
import net.lz1998.pbbot.constant.Constant;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

@Slf4j
public class JsoupParseHtml {

    /**
     * 获取人物图片链接 image
     *
     * @param name
     * @return
     */
    public static Charcater parseImageUrl(String name) {
        name = "紫色的传令 艾丽丝";
        Document document = null;
        try {
            document = Jsoup.connect("https://wiki.biligame.com/cq/" + name).get();
        } catch (IOException e) {
            log.info("获取不到人物链接");
        }
        //图片地址
        Elements cqhero_img = document.getElementsByClass("cqhero_img");
//        String img = cqhero_img.attr("img");
        String src = cqhero_img.select("img").attr("src");

        //获取名字
        String title = document.title();

        //输出内容
        Elements cqframe_box = document.getElementsByClass("cqframe_box");
        Elements content = document.select("div.cqframe_box:contains(b)");

        String text = content.text();
        if (text.length() > Constant.Thirty) {
            text = text.substring(0, Constant.Thirty)+Constant.etc;
        }
//        select(“div.masthead”).first();
        Charcater character = new Charcater();
        character.setImageUrl(src);
        character.setName(name);
        character.setTitle(title);
        character.setContent(text);
        return character;
    }

    public static void main(String[] args) {
        parseImageUrl(null);
    }

}
