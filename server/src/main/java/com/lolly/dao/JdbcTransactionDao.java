package com.lolly.dao;

import com.lolly.exception.DaoException;
import com.lolly.model.Category;
import com.lolly.model.Transaction;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransactionDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTransactionDao(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    public List<Transaction> getTransactionByUser(int id){
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE user_id = ?; ORDER BY transaction_date";
        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
            if (result.next()) {
                Transaction transaction = mapRowToTransaction(result);
                transactions.add(transaction);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return transactions;
    }

    public List<Transaction> getTransactionByAccount(int userId, int accountId) {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE user_id = ? AND account_id = ? ORDER BY transaction_date;";
        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userId, accountId);
            if (result.next()) {
                Transaction transaction = mapRowToTransaction(result);
                transactions.add(transaction);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return transactions;
    }

    private List<Transaction> template() {
        List<Transaction> transactions = new ArrayList<>();
        String sql = ";";
        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet();
            if (result.next()) {
                Transaction transaction = mapRowToTransaction(result);
                transactions.add(transaction);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return transactions;

    }

    public Transaction mapRowToTransaction(SqlRowSet sr) {
        Transaction transaction = new Transaction();
        transaction.setId(sr.getInt("transaction_id"));
        transaction.setDate(sr.getDate("transaction_date"));
        transaction.setAmount(sr.getBigDecimal("amount"));;
        transaction.setVendor(sr.getString("vendor"));
        transaction.setDescription(sr.getString("transaction_desc"));
        transaction.setAccountId(sr.getInt("account_id"));
        transaction.setCategoryId(sr.getInt("category_id"));
        transaction.setUserId(sr.getInt("user_id"));
        return transaction;
    }
}
