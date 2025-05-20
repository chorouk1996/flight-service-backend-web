package com.service.backend.web.scheduled;


import com.service.backend.web.services.implementation.EmailTokenService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class DeleteExpiredUnusedToken {

    private final EmailTokenService emailTokenService;

    @Scheduled(cron = "0 0 * * * ?")
    public void deleteExpiredUnusedToken() {
        System.out.println("Deleting expired unused tokens.... ");
        emailTokenService.deleteExpiredUnusedTokens();
    }
}
