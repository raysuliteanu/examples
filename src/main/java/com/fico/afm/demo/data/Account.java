package com.fico.afm.demo.data;

import java.util.Calendar;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Access(AccessType.FIELD)
public class Account {
    @Id
    @GeneratedValue
    private Long id;
    private long createDate;
    private String accountNumber;
    private String accountName;
    private String accountType;

    // protected because don't want it used other than because JPA requires it
    protected Account() {
    }

    @JsonCreator
    public Account(@JsonProperty("accountName") final String accountName, @JsonProperty("accountNumber") final String accountNumber, @JsonProperty("accountType") final String accountType) {
        this.accountName = accountName;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
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

    @PrePersist
    void setCreateDate() {
        createDate = Calendar.getInstance().getTimeInMillis();
    }
}
