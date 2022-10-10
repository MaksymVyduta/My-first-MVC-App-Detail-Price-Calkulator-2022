package Wycena.ZWWG.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailSender {

    private JavaMailSender javaMailSender;

    @Autowired
    public EmailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void send (String to, String title, String contents) throws Exception {

        MimeMessage mail = javaMailSender.createMimeMessage();
        MimeMessageHelper mailsend = new MimeMessageHelper(mail, true);
        mailsend.setTo(to);
        mailsend.setReplyTo(to);
        mailsend.setFrom("nowawycena@mail.ru");
        mailsend.setSubject(title);
        mailsend.setText(contents,true);

        javaMailSender.send(mail);
    }
}