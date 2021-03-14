package com.transaction.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping("/required-required")
    public void required_required() {
        transactionService.required_required();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping("/jpa-required-required")
    public void jpa_required_required() {
        transactionService.jpa_required_required();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping("/mybatis-jpa-required-required")
    public void mybatis_jpa_required_required() {
        transactionService.mybatis_jpa_required_required();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping("/inner-mybatis-jpa-required-required")
    public void inner_mybatis_jpa_required_required() {
        transactionService.inner_mybatis_jpa_required_required();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping("/required-requires-new")
    public void required_requiresNew() {
        transactionService.required_requiresNew();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping("/required-nested")
    public void required_nested() {
        transactionService.required_nested();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping("/requires-new-required")
    public void requiresNew_required() {
        transactionService.requiresNew_required();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping("/requires-new-requires-new")
    public void requiresNew_requiresNew() {
        transactionService.requiresNew_requiresNew();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping("/requires-new-nested")
    public void requiresNew_nested() {
        transactionService.requiresNew_nested();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping("/nested-required")
    public void nested_required() {
        transactionService.nested_required();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping("/nested-requires-new")
    public void nested_requiresNew() {
        transactionService.nested_requiresNew();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping("/nested-nested")
    public void nested_nested() {
        transactionService.nested_nested();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping("/not-supported-required")
    public void notSupported_required() {
        transactionService.notSupported_required();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping("/not-supported-requires-new")
    public void notSupported_requiresNew() {
        transactionService.notSupported_requiresNew();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping("/not-supported-nested")
    public void notSupported_nested() {
        transactionService.notSupported_nested();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping("/not-supported-not-supported")
    public void notSupported_notSupported() {
        transactionService.notSupported_notSupported();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping("/jpa-not-supported-not-supported")
    public void jpaNotSupported_notSupported() {
        transactionService.jpa_notSupported_notSupported();
    }

}
