package group.msg.at.cloud.cloudtrain.core.boundary;

import group.msg.at.cloud.cloudtrain.core.entity.Message;
import org.eclipse.microprofile.jwt.JsonWebToken;

import jakarta.annotation.Resource;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.SessionContext;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import java.security.Principal;
import java.util.UUID;

/**
 * Simple {@code Boundary} that returns welcome messages.
 */
@Stateless
@RolesAllowed("CLOUDTRAIN_USER")
public class HelloWorld {

    private static final String WELCOME_MESSAGE_TEMPLATE = "Dear \"%s\", welcome to a cloud native Java application based on MicroProfile protected by OpenID Connect!";

    @Inject
    private JsonWebToken token;
    
    @Resource
    private SessionContext sessionContext;

    public Message getHelloMessage() {
        return new Message(UUID.randomUUID(), "hello", String.format(WELCOME_MESSAGE_TEMPLATE, getAuthenticatedUserName()));
    }

    @RolesAllowed("CLOUDTRAIN_ADMIN")
    public Message getHelloAdminMessage() {
        return null;
    }

    private String getAuthenticatedUserName() {
        String result = "anonymous";
        Principal principal = this.sessionContext.getCallerPrincipal();
        if (principal != null) {
            result = principal.getName();
        }
        return result;
    }
}
