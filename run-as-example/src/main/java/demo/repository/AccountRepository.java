package demo.repository;

import java.util.List;

import demo.data.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("hasRole('USER')")
public interface AccountRepository extends CrudRepository<Account, Long> {
    @RestResource(path = "accountNumber")
    List<Account> findByAccountNumber(final String accountNumber);

    @RestResource(path = "accountType")
    List<Account> findByAccountType(final String accountType);

    @RestResource(path = "accountName")
    List<Account> findByAccountName(final String accountName);
}
