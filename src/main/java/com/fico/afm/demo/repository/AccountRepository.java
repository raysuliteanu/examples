package com.fico.afm.demo.repository;

import com.fico.afm.demo.data.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("hasRole('ROLE_USER')")
public interface AccountRepository extends CrudRepository<Account, Long> {
}
