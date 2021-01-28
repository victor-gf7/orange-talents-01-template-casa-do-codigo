package br.com.zup.codehouse.controller.dto;

import br.com.zup.codehouse.config.ExistsId;
import br.com.zup.codehouse.model.Country;
import br.com.zup.codehouse.model.State;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
import org.springframework.util.Assert;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NewPurchaseRequest {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String document;

    @NotBlank
    private String address;

    @NotBlank
    private String complement;

    @NotBlank
    private String city;

    @NotNull
    @ExistsId(domainClass = Country.class, fieldName = "id")
    private Long idCountry;

    @NotNull
    @ExistsId(domainClass = State.class, fieldName = "id")
    private Long idState;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String cep;

    public NewPurchaseRequest(@Email @NotBlank String email, @NotBlank String firstName,
                              @NotBlank String lastName, @NotBlank String document,
                              @NotBlank String address, @NotBlank String complement, @NotBlank String city,
                              @NotNull Long idCountry, @NotNull Long idState,
                              @NotBlank String phoneNumber, @NotBlank String cep) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.document = document;
        this.address = address;
        this.complement = complement;
        this.city = city;
        this.idCountry = idCountry;
        this.idState = idState;
        this.phoneNumber = phoneNumber;
        this.cep = cep;
    }

    public String getDocument() {
        return document;
    }

    public Long getIdCountry() {
        return idCountry;
    }

    public Long getIdState() {
        return idState;
    }

    public boolean isValidDocument() {
        Assert.hasLength(document, "Documento não deve estar em branco");

        CPFValidator cpfValidator = new CPFValidator();
        cpfValidator.initialize(null);

        CNPJValidator cnpjValidator = new CNPJValidator();
        cnpjValidator.initialize(null);

        return cpfValidator.isValid(document, null) || cnpjValidator.isValid(document, null);

    }

    @Override
    public String toString() {
        return "NewPurchaseRequest{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", document='" + document + '\'' +
                ", address='" + address + '\'' +
                ", complement='" + complement + '\'' +
                ", city='" + city + '\'' +
                ", idCountry=" + idCountry +
                ", idState=" + idState +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", cep='" + cep + '\'' +
                '}';
    }
}
