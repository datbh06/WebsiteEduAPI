package com.tyugen.WebsiteEduAPI.repository;

import com.tyugen.WebsiteEduAPI.model.TaiKhoan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    /**
     * Finds a TaiKhoan object with the specified account name in the database.
     *
     * @param taiKhoan the account name to be found
     * @return the TaiKhoan object with the specified account name
     */
    TaiKhoan findByTaiKhoan(String taiKhoan);

    /**
     * Finds a page of TaiKhoan object with the specified account name in the database.
     *
     * @param tenTaiKhoan the account name to be found
     * @param pageable    the page information
     * @return a page of TaiKhoan object with the specified account name
     */
    Page<TaiKhoan> findByTaiKhoanContaining(String tenTaiKhoan, Pageable pageable);
}
