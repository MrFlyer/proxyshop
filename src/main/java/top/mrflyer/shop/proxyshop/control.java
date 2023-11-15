package top.mrflyer.shop.proxyshop;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class control {

    public int countNum = 0;
    @RequestMapping("/getinfo")
    public String getinfo() {
        getSent getSent = new getSent();
        countNum += 1;
        return getSent.getinfo(countNum);
    }
}
