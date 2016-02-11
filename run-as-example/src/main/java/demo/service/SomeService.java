package demo.service;

import java.util.Arrays;

import demo.data.Account;
import demo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class SomeService {
    @Autowired
    private AccountRepository accountRepository;

    @PreAuthorize("hasAnyRole('RUN_AS_ADMIN', 'ADMIN')")
    public Account doSomething(final Account account) {
        if (SecurityContextHolder.getContext() != null) {
            final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            assert authentication != null &&
                    CollectionUtils.containsAny(
                            authentication.getAuthorities(),
                            Arrays.asList(new SimpleGrantedAuthority("RUN_AS_ADMIN"), new SimpleGrantedAuthority("ADMIN"))
                    );
        }

        return accountRepository.save(account);
    }
}
