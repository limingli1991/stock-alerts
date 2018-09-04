package ar.com.sac.services.dao;

import ar.com.sac.model.EmailRecipient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmailRecipientDAO extends AbstractDAO<EmailRecipient,String> {

    public EmailRecipientDAO() {
        super(EmailRecipient.class);
    }

    public List<EmailRecipient> getAllEmailRecipients(){
        String query = "Select e FROM EmailRecipient e WHERE active = 1";
        return getEntityManager().createQuery(query, EmailRecipient.class).getResultList();
    }
}
