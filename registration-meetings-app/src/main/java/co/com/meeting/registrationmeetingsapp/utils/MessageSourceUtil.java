package co.com.meeting.registrationmeetingsapp.utils;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessageSourceUtil {

	@Autowired
	private MessageSource messageSource;

	public String buildMessage(String contentMessage) {
		Locale locale = LocaleContextHolder.getLocale();
		
		return messageSource.getMessage(contentMessage, null, locale);
	}

}
