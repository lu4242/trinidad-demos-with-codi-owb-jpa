package jsf2jpa.beans;

import java.util.List;
import javax.enterprise.context.RequestScoped;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.Query;
import javax.persistence.EntityManager;

import jsf2jpa.entity.User;

@Named("authenticator")
@RequestScoped
public class AuthenticatorAction extends SimpleAction
{
    
    private User user;
    
    public User getUser()
    {
        if (user == null)
        {
            user = new User();
        }
        return user;
    }

    public String authenticate() {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select u from User u"
                + " where u.username = :username and u.password = :password");
        query.setParameter("username", user.getUsername());
        query.setParameter("password", user.getPassword());
        List<User> users = query.getResultList();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (users.size() == 0) {
            //if (BookingApplication.LOG_ENABLED)
            //{
            //    logger.error("Login failed");
            //}
            facesContext.addMessage(null, new FacesMessage("Login failed"));
            return null;
        }
        User user = users.get(0);
        BookingSession session = (BookingSession) facesContext.getApplication().
                evaluateExpressionGet(facesContext, "#{bookingSession}", 
                    BookingSession.class);
        session.setUser(user);
        //if (BookingApplication.LOG_ENABLED)
        //{
        //    logger.info("Login succeeded");
        //}
        facesContext.addMessage(null, new FacesMessage("Login succeeded"));
        session.info("Welcome, " + user.getUsername());
        return "main";
    }
}
