package com.lolly.controller;

import com.lolly.dao.JdbcTransactionDao;
import com.lolly.model.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/transactions")

public class TransactionController {

    private JdbcTransactionDao transactionDao;

    public TransactionController(JdbcTransactionDao transactionDao) { this.transactionDao = transactionDao; }

    @RequestMapping(path = "", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Transaction> getTransactions(@RequestParam(defaultValue = "0") String userId) {
        if (!userId.equals("")) {
            return transactionDao.getTransactionByUser(Integer.parseInt(userId));
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

}
