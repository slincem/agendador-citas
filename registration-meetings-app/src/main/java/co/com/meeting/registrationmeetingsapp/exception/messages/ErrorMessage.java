package co.com.meeting.registrationmeetingsapp.exception.messages;

public class ErrorMessage {

    public static final String USER_NOT_FOUND = "exception.userNotFound";
    public static final String USER_IS_ALREADY_REGISTERED = "exception.userAlreadyRegistered";
    public static final String NO_REGISTERED_USERS_FOUND = "exception.zeroUsersInBD";
    public static final String RESOURCE_NOT_FOUND = "exception.resourceNotFound";

    private ErrorMessage() {
    }
}
