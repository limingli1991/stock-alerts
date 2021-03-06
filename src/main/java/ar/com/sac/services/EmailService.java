package ar.com.sac.services;


import ar.com.sac.model.EmailRecipient;
import ar.com.sac.services.dao.EmailRecipientDAO;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@PropertySource("classpath:application.properties")
public class EmailService {

    //587
    @Value("${mail.smtp.port}")
    private String port;

    //true
    @Value("${mail.smtp.auth}")
    private String auth;

    //true
    @Value("${mail.smtp.starttls.enable}")
    private String ttls;

    //xxx@gmail.com
    private List to;

    @Value("${mail.sender.from}")
    private String from;

    //smtp
    @Value("${mail.session.transport}")
    private String protocol;

    //smtp.gmail.com
    @Value("${mail.session.host}")
    private String host;

    @Value("${mail.session.user.id}")
    private String userId;

    @Value("${mail.session.user.password}")
    private String password;

    @Value("${mail.smtp.ssl.trust}")
    private String trust;

    @Value("${mail.sns.topicArn}")
    private String topicArn;

    @Autowired
    private EmailRecipientDAO emailRecipientDAO;

    private AmazonSimpleEmailService sesClient;

    public EmailService() {
        sesClient = AmazonSimpleEmailServiceClientBuilder.defaultClient();
    }

    public void generateAndSendEmail(String subject, String body) throws MessagingException {
        to = emailRecipientDAO.getAllEmailRecipients().stream().map(EmailRecipient::getEmail).collect(Collectors.toList());
        SendEmailRequest request = new SendEmailRequest()
                .withDestination(new Destination().withToAddresses(to))
                .withMessage(
                        new Message()
                                .withBody(new Body().withHtml(new Content().withCharset("UTF-8").withData(body)))
                                .withSubject(new Content().withCharset("UTF-8").withData(subject))
                )
                .withSource(from);
        sesClient.sendEmail(request);
    }

    public void generateAndSendTestEmail() throws MessagingException {
        generateAndSendEmail("Stock Alert", "Test email by Stock Alerts.");
    }
}