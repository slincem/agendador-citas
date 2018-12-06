package co.com.meeting.registrationmeetingsapp;

import co.com.meeting.registrationmeetingsapp.model.entity.Account;
import co.com.meeting.registrationmeetingsapp.model.entity.Customer;

import java.util.Date;

public class CustomerTestDataBuilder {

    private Long id;
    private String identification;
    private String name;
    private String lastName;
    private String age;
    private Date birthDate;
    private String type;
    private Account account;

    public CustomerTestDataBuilder() {
        this.id = 1L;
        this.identification = "101010";
        this.name = "Paul";
        this.lastName = "McCartney";
        this.age = "70";
        this.birthDate = new Date();
        this.type = "Customer";

        Account currentAccount = new Account();
        currentAccount.setUsername("s");
        currentAccount.setPassword("pass");
        this.account = currentAccount;
    }

    public CustomerTestDataBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public CustomerTestDataBuilder withIdentification(String identification) {
        this.identification = identification;
        return this;
    }

    public CustomerTestDataBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public CustomerTestDataBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public CustomerTestDataBuilder withAge(String age) {
        this.age = age;
        return this;
    }

    public CustomerTestDataBuilder withBirthDate(Date birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public CustomerTestDataBuilder withType(String type) {
        this.type = type;
        return this;
    }

    public CustomerTestDataBuilder withAccount(Account account) {
        this.account = account;
        return this;
    }

    public Customer build () {
        Customer customer = new Customer(id, identification, name, lastName, age,
                birthDate, type, account);
        return customer;
    }
}
