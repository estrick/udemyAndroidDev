package javaDeepDive;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        // STRING SPLITTING
//        String s = "I wanna be bad with you girl like we're robbing a bank.";
//        String[] splitString = s.split(" ");
//        System.out.println(Arrays.toString(splitString));

        // SUBSTRINGS
//        s = "I wanna be mad at the world like they took you away";
//        String subString = s.substring(11, 27);
//        System.out.println(subString);

        // REGEX
//        Pattern p = Pattern.compile("be (.*?) like");
//        Matcher m = p.matcher(s);
//        while (m.find()) {
//            System.out.println(m.group(1));
//        }

        String s = "<div class=\"card player-card\">\n" +
                "    \n" +
                "        <div class=\"card__content\">\n" +
                "                <a href=\"/arsenal/players/hector-bellerin\" class=\"player-card__wrapper player-card__wrapper--link\">\n" +
                "          <div class=\"player-card__portrait\">\n" +
                "        \n" +
                "    \n" +
                "        \n" +
                "      <div class=\"bg-img  b-lazy\"\n" +
                "         data-src=\"/sites/default/files/styles/large/public/images/Hector%20Bellerin_400x252.png?itok=abypV1db\"\n" +
                "         data-src-mobile=\"/sites/default/files/styles/mobile_16x9/public/images/Hector%20Bellerin_400x252.png?itok=xdd_WLJV\"\n" +
                "         data-src-tablet=\"/sites/default/files/styles/tablet_16x9/public/images/Hector%20Bellerin_400x252.png?itok=2IWnnnm3\"\n" +
                "         data-src-laptop=\"\"\n" +
                "         data-src-desktop=\"\"\n" +
                "         aria-role=\"img\"\n" +
                "    >\n" +
                "      <span class=\"visually-hidden\"></span>\n" +
                "    </div>\n" +
                "        </div>\n" +
                "      <div class=\"player-card__info\">\n" +
                "        <div class=\"player-card__info__number\"> 2 </div>\n" +
                "        <div class=\"player-card__info__position-or-first-name\">Hector</div>\n" +
                "                  <div class=\"player-card__info__name\">Bellerin</div>\n" +
                "                <div class=\"player-card__info__nationality\">\n" +
                "                      <div class=\"player-card__flag\">\n" +
                "              <img src=\"/sites/default/files/styles/flag/public/2017-06/Spain.png?itok=ZZWDsry0\" alt=\"\">\n" +
                "            </div>\n" +
                "                    <div class=\"player-card__info__nationality__label\">Spain</div>\n" +
                "        </div>\n" +
                "      </div>\n" +
                "          </a>\n" +
                "      \n" +
                "    </div>\n" +
                "  \n" +
                "    \n" +
                "  \n" +
                "</div>";
        Pattern p = Pattern.compile("<div class=\"player-card__info__name\">(.*?)</div>");
        Matcher m = p.matcher(s);
        ArrayList<String> values = new ArrayList<>();
        while (m.find()) {
            values.add(m.group(1));
        }
        p = Pattern.compile("data-src=\"(.*?)\\?");
        m = p.matcher(s);
        while (m.find()) {
            values.add(m.group(1));
        }
        System.out.println(values);
        System.out.println(values.get(0));
        System.out.println("http://www.arsenal.com" + values.get(1));

    }
}
