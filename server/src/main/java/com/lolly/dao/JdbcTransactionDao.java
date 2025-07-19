package com.lolly.dao;

import com.lolly.exception.DaoException;
import com.lolly.model.Category;
import com.lolly.model.Transaction;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class JdbcTransactionDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTransactionDao(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }


    public Transaction getTransactionById(int transactionId) {
        Transaction transaction = null;
        String sql = "SELECT * FROM transactions WHERE transaction_id = ?;";
        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, transactionId);
            if (result.next()) {
                transaction = mapRowToTransaction(result);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return transaction;
    }

    public List<Transaction> getTransactionsByUser(int userId){
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE user_id = ? ORDER BY transaction_date;";
        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userId);
            while (result.next()) {
                Transaction transaction = mapRowToTransaction(result);
                transactions.add(transaction);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return transactions;
    }

    public List<Transaction> getTransactionsByAccount(int accountId) {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE account_id = ? ORDER BY transaction_date;";
        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, accountId);
            while (result.next()) {
                Transaction transaction = mapRowToTransaction(result);
                transactions.add(transaction);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return transactions;
    }

    public List<Transaction> getTransactionsByCategory(int categoryId) {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE category_id = ? ORDER BY transaction_date;";
        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, categoryId);
            while (result.next()) {
                Transaction transaction = mapRowToTransaction(result);
                transactions.add(transaction);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return transactions;
    }

    public List<Transaction> getTransactionsByDate(Date date) {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE transaction_date = ? ORDER BY amount;";
        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, date);
            while (result.next()) {
                Transaction transaction = mapRowToTransaction(result);
                transactions.add(transaction);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return transactions;
    }

    public List<Transaction> getTransactionsByVendor(String vendor) {
        vendor = "%" + vendor + "%";
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE vendor ILIKE ? ORDER BY transaction_date;";
        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, vendor);
            while (result.next()) {
                Transaction transaction = mapRowToTransaction(result);
                transactions.add(transaction);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return transactions;
    }

    public List<Transaction> getTransactionsByDescription(String description) {
        description = "%" + description + "%";
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE transaction_desc ILIKE ? ORDER BY transaction_date;";
        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, description);
            while (result.next()) {
                Transaction transaction = mapRowToTransaction(result);
                transactions.add(transaction);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return transactions;
    }

    public Transaction updateTransaction(Transaction newTransaction) {
        Transaction updatedTransaction = null;
        String sql = "UPDATE transactions SET transaction_date = ?, amount = ?, vendor = ?, " +
                "transaction_desc = ?, receipt_url = ?, account_id = ?, category_id = ?, user_id = ? " +
                "WHERE transaction_id = ?;";
        try {
            int rowsAffected = jdbcTemplate.update(sql, newTransaction.getDate(), newTransaction.getAmount(),
                    newTransaction.getVendor(), newTransaction.getDescription(), newTransaction.getReceiptUrl(),
                    newTransaction.getAccountId(), newTransaction.getCategoryId(), newTransaction.getUserId(),
                    newTransaction.getId());
            if (rowsAffected == 0) {
                throw new DaoException("Zero rows affected, expected at least one");
            }
            updatedTransaction = getTransactionById(newTransaction.getId());
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Cannot connect to server or database", e);
        }
        catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return updatedTransaction;
    }

    public Transaction createTransaction(Transaction newTransaction) {
        Transaction createdTransaction = null;
        String sql = "INSERT INTO transactions (transaction_date, amount, vendor, " +
                "transaction_desc, receipt_url, account_id, category_id, user_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING transaction_id;";

        try {
            int transactionId = jdbcTemplate.queryForObject(sql, int.class,
                    newTransaction.getDate(), newTransaction.getAmount(), newTransaction.getVendor(),
                    newTransaction.getDescription(), newTransaction.getReceiptUrl(), newTransaction.getAccountId(),
                    newTransaction.getCategoryId(), newTransaction.getUserId());
            createdTransaction = getTransactionById(transactionId);
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Cannot connect to the server or database", e);
        }
        catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return createdTransaction;
    }

    public int deleteTransaction(int transactionId) {
        int rowsAffected;
        String sql = "DELETE FROM transactions WHERE transaction_id = ?;";
        try {
            rowsAffected = jdbcTemplate.update(sql, transactionId);
            if (rowsAffected == 0) {
                throw new DaoException("Zero rows affected, expected at least one");
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Cannot connect to server or database", e);
        }
        catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return rowsAffected;
    }

//    private List<Transaction> template() {
//        List<Transaction> transactions = new ArrayList<>();
//        String sql = ";";
//        try {
//            SqlRowSet result = jdbcTemplate.queryForRowSet();
//            if (result.next()) {
//                Transaction transaction = mapRowToTransaction(result);
//                transactions.add(transaction);
//            }
//        }
//        catch (CannotGetJdbcConnectionException e) {
//            throw new DaoException("Unable to connect to server or database", e);
//        }
//        return transactions;
//
//    }

    public Transaction mapRowToTransaction(SqlRowSet sr) {
        Transaction transaction = new Transaction();
        transaction.setId(sr.getInt("transaction_id"));
        transaction.setDate(sr.getDate("transaction_date"));
        transaction.setAmount(sr.getBigDecimal("amount"));;
        transaction.setVendor(sr.getString("vendor"));
        transaction.setDescription(sr.getString("transaction_desc"));
        transaction.setReceiptUrl(sr.getString("receipt_url"));
        transaction.setAccountId(sr.getInt("account_id"));
        transaction.setCategoryId(sr.getInt("category_id"));
        transaction.setUserId(sr.getInt("user_id"));
        return transaction;
    }
}
