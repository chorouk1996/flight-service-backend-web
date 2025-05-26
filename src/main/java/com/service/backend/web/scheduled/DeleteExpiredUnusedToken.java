package com.service.backend.web.scheduled;


import com.service.backend.web.services.implementation.EmailTokenService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class DeleteExpiredUnusedToken {

    private final EmailTokenService emailTokenService;

    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteExpiredUnusedToken.class);

    @Scheduled(cron = "0 0 * * * ?")
    public void deleteExpiredUnusedToken() {
        LOGGER.info("Deleting expired unused tokens.... ");
        emailTokenService.deleteExpiredUnusedTokens();
    }
}
