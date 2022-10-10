package Wycena.ZWWG.Controller;
import Wycena.ZWWG.Service.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Send {

    private EmailSender emailSender;

    @Autowired

    public Send(EmailSender emailSender) {

        this.emailSender = emailSender;
    }

    @RequestMapping("/1")
    public String sendForm() {
        return "send";
    }

    @RequestMapping("/send")
    public String kasuj(@RequestParam("to") String to, @RequestParam("title") String title, @RequestParam("contents") String contents) {
        try {
            emailSender.send(to, title, contents);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return "send";
    }
}