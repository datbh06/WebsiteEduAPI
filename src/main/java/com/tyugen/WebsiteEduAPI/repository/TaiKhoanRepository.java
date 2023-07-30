package com.tyugen.WebsiteEduAPI.repository;

import com.tyugen.WebsiteEduAPI.model.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * TaiKhoanRepository is an interface that extends JpaRepository for class TaiKhoan.
 */
public interface TaiKhoanRepository extends JpaRepository<TaiKhoan, Integer> {
    /**
     * Checks if a TaiKhoan object with the specified account name exists in the database.
     *
     * @param taiKhoan the account name to be checked
     * @return true if the account name exists, false otherwise
     */
    boolean existsByTaiKhoan(String taiKhoan);
}
