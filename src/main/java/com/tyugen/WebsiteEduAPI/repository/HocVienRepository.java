package com.tyugen.WebsiteEduAPI.repository;

import com.tyugen.WebsiteEduAPI.model.HocVien;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The HocVienRepository interface is used to define custom database methods for the HocVien class.
 */
public interface HocVienRepository extends JpaRepository<HocVien, Integer> {

    /**
     * Finds a HocVien object from the database by its hoTen.
     *
     * @param name the hoTen of the HocVien object to be found
     * @return an Optional containing the HocVien object with the specified hoTen if it exists
     */
    HocVien findByHoTen(String name);

    /**
     * Finds a HocVien object from the database by its email.
     *
     * @param email the email of the HocVien object to be found
     * @return an Optional containing the HocVien object with the specified email if it exists
     */
    HocVien findByEmail(String email);
}
