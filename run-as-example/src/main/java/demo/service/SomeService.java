package demo.service;

import demo.data.Account;
import demo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SomeService {
    @Autowired
    private AccountRepository accountRepository;

    @PreAuthorize("hasAnyRole('RUN_AS_ADMIN', 'ADMIN')")
    public Account doSomething(final Account account) {
        if (SecurityContextHolder.getContext() != null) {
            final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            assert authentication != null : "missing authentication";
            assert authentication.getAuthorities().stream()
                .anyMatch(
                    (GrantedAuthority grantedAuthority) ->
                        grantedAuthority.equals(new SimpleGrantedAuthority("ROLE_ADMIN")) ||
                            grantedAuthority.equals(new SimpleGrantedAuthority("ROLE_RUN_AS_ADMIN"))
                ) : "missing admin authority: " + authentication.getAuthorities();

        }

        return accountRepository.save(account);
    }
}
