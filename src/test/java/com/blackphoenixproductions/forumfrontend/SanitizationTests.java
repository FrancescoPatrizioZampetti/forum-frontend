package com.blackphoenixproductions.forumfrontend;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
public class SanitizationTests {

    @Test
    public void test_input_sanitization(){
        String input = Jsoup.clean("<script>console.log('errore');</script><a>link</a><p>xiao</p>", Whitelist.basic());
        System.out.println(input);
        input = Jsoup.clean("<script>console.log('errore');</script><br><a>link</a><p>wow</p>", Whitelist.none());
        System.out.println(input);
//        input = Jsoup.clean("<script>console.log('errore');</script><br><a>link</a><p style = 'color:black;'></p>", Whitelist.relaxed().addTags("p").addAttributes("style"));
        input = Jsoup.clean("<p style = ''></p><a script=''><script>ddd</script></a>", Whitelist.relaxed().addTags("p").addAttributes(":all", "style"));
        System.out.println(input);
    }

    @Test
    public void code_sanitization(){
        String code = "4%2F0AY0e-g4QUBkYFOGyrTxo6bOmVIUW3CTm-0IHewXuPbLmqPPwqHp5SzIXQE1188BwwjET2A&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";
        String[] split = code.split("&");
        System.out.println(">>>>> code: " + split[0].replace("%2F","/"));
    }

}
