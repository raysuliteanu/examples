package demo.service;

import demo.data.Account;
import demo.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SomeService {
    private static final Logger LOG = LoggerFactory.getLogger(SomeService.class);

    @Autowired
    private AccountRepository accountRepository;

    @PreAuthorize("hasAnyRole('RUN_AS_ADMIN', 'ADMIN')")
    public Account doSomething(final Account account) {
        LOG.info("should have ROLE_RUN_AS_ADMIN and/or ROLE_ADMIN: {}", SecurityContextHolder.getContext().getAuthentication());

        return account;
    }
}
