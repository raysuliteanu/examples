package com.fico.afm.demo.data;

import java.util.Calendar;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Access(AccessType.FIELD)
public class Account {
    @Id
    private Long id;
    private long createDate;
    private String accountNumber;
    private String accountName;
    private String accountType;

    // protected because don't want it used other than because JPA requires it
    protected Account() {
    }

    @JsonCreator
    public Account(@JsonProperty("id") final Long id, @JsonProperty("accountName") final String accountName, @JsonProperty("accountNumber") final String accountNumber, @JsonProperty("accountType") final String accountType) {
        this(id, Calendar.getInstance().getTimeInMillis(), accountName, accountNumber, accountType);
    }

    protected Account(final Long id, final long createDate, final String accountName, final String accountNumber, final String accountType) {
        this.accountName = accountName;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.createDate = createDate;
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public long getCreateDate() {
        return createDate;
    }

    public Long getId() {
        return id;
    }
}
