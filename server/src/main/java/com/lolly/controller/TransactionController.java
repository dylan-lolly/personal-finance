package com.lolly.controller;

import com.lolly.dao.JdbcTransactionDao;
import com.lolly.exception.DaoException;
import com.lolly.service.TransactionDto;
import com.lolly.model.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/transactions")

public class TransactionController {

    private JdbcTransactionDao transactionDao;

    public TransactionController(JdbcTransactionDao transactionDao) { this.transactionDao = transactionDao; }

    @GetMapping(path = "/{transactionId}")
    public TransactionDto getTransactionById(@PathVariable int transactionId) {
        Transaction transaction;
        try {
            transaction = transactionDao.getTransactionById(transactionId);
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return convertEntityToDto(transaction);
    }

    @RequestMapping(path = "/user", method = RequestMethod.GET)
    public List<TransactionDto> getTransactionsByUser(@RequestParam(defaultValue = "") String userId) {
        if (!userId.equals("")) {
            List<TransactionDto> transactionDtos = new ArrayList<>();
            List<Transaction> transactions = transactionDao.getTransactionsByUser(Integer.parseInt(userId));
            for (Transaction transaction : transactions) {
                transactionDtos.add(convertEntityToDto(transaction));
            }
            return transactionDtos;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/account", method = RequestMethod.GET)
    public List<TransactionDto> getTransactionsByAccount(@RequestParam(defaultValue = "") String accountId) {
        if (!accountId.equals("")) {
            List<TransactionDto> transactionDtos = new ArrayList<>();
            List<Transaction> transactions = transactionDao.getTransactionsByAccount(Integer.parseInt(accountId));
            for (Transaction transaction : transactions) {
                transactionDtos.add(convertEntityToDto(transaction));
            }
            return transactionDtos;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/category")
    public List<TransactionDto> getTransactionsByCategory(@RequestParam(defaultValue = "") String categoryId) {
        if (!categoryId.equals("")) {
            List<TransactionDto> transactionDtos = new ArrayList<>();
            List<Transaction> transactions = transactionDao.getTransactionsByCategory(Integer.parseInt(categoryId));
            for (Transaction transaction : transactions) {
                transactionDtos.add(convertEntityToDto(transaction));
            }
            return transactionDtos;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/date")
    public List<TransactionDto> getTransactionsByDate(@RequestParam(defaultValue = "") String date) {
        if (!date.equals("")) {
            List<TransactionDto> transactionDtos = new ArrayList<>();
            List<Transaction> transactions = transactionDao.getTransactionsByDate(Date.valueOf(date));
            for (Transaction transaction : transactions) {
                transactionDtos.add(convertEntityToDto(transaction));
            }
            return transactionDtos;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/vendor")
    public List<TransactionDto> getTransactionsByVendor(@RequestParam(defaultValue = "") String vendor) {
        if (!vendor.equals("")) {
            List<TransactionDto> transactionDtos = new ArrayList<>();
            List<Transaction> transactions = transactionDao.getTransactionsByVendor(vendor);
            for (Transaction transaction : transactions) {
                transactionDtos.add(convertEntityToDto(transaction));
            }
            return transactionDtos;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/description")
    public List<TransactionDto> getTransactionsByDescription(@RequestParam(defaultValue = "") String description) {
        if (!description.equals("")) {
            List<TransactionDto> transactionDtos = new ArrayList<>();
            List<Transaction> transactions = transactionDao.getTransactionsByDescription(description);
            for (Transaction transaction : transactions) {
                transactionDtos.add(convertEntityToDto(transaction));
            }
            return transactionDtos;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public TransactionDto updateTransaction(@RequestBody TransactionDto newTransactionDto) {
        // Convert DTO to Entity to make compatible with DAO
        Transaction newTransaction = convertDtoToEntity(newTransactionDto);
        // Create new transaction in database
        Transaction updatedTransaction = transactionDao.updateTransaction(newTransaction);
        // Convert new Transaction entity to DTO to return to client
        return convertEntityToDto(updatedTransaction);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionDto createTransaction(@RequestBody TransactionDto newTransactionDto) {
        // Convert DTO to Entity to make compatible with DAO
        Transaction newTransaction = convertDtoToEntity(newTransactionDto);
        // Create new transaction in database
        Transaction createdTransaction = transactionDao.createTransaction(newTransaction);
        // Convert new Transaction entity to DTO to return to client
        return convertEntityToDto(createdTransaction);
    }

    @RequestMapping(path = "/{transactionId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTransaction(@PathVariable int transactionId) {
        try {
            transactionDao.deleteTransaction(transactionId);
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    // HELPER METHODS
    public TransactionDto convertEntityToDto(Transaction transaction){
        TransactionDto dto = new TransactionDto();
        dto.setId(transaction.getId());
        dto.setDate(transaction.getDate());
        dto.setAmount(transaction.getAmount());
        dto.setVendor(transaction.getVendor());
        dto.setDescription(transaction.getDescription());
        dto.setReceiptUrl(transaction.getReceiptUrl());
        dto.setAccountId(transaction.getAccountId());
        dto.setCategoryId(transaction.getCategoryId());
        dto.setUserId(transaction.getUserId());
        return dto;
    }

    public Transaction convertDtoToEntity(TransactionDto dto) {
        Transaction entity = new Transaction();
        entity.setId(dto.getId());
        entity.setDate(dto.getDate());
        entity.setAmount(dto.getAmount());
        entity.setVendor(dto.getVendor());
        entity.setDescription(dto.getDescription());
        entity.setReceiptUrl(dto.getReceiptUrl());
        entity.setAccountId(dto.getAccountId());
        entity.setCategoryId(dto.getCategoryId());
        entity.setUserId(dto.getUserId());
        return entity;
    }
}
