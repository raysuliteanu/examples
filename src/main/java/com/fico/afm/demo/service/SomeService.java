package com.fico.afm.demo.service;

import com.fico.afm.demo.data.Account;
import com.fico.afm.demo.repository.AccountRepository;
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

    @PreAuthorize("hasRole('RUN_AS_ADMIN')")
    public Account doSomething(final Account account) {
        LOG.info("should have ROLE_RUN_AS_ADMIN: {}", SecurityContextHolder.getContext().getAuthentication());

        return account;
    }
}
