package com.tyugen.WebsiteEduAPI.repository;

import com.tyugen.WebsiteEduAPI.model.HocVien;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

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
    List<HocVien> findByHoTen(String name);

    /**
     * Finds a HocVien object from the database by its email.
     *
     * @param email the email of the HocVien object to be found
     * @return an Optional containing the HocVien object with the specified email if it exists
     */
    Optional<HocVien> findByEmail(String email);

    /**
     * Finds a HocVien object from the database by its soDienThoai.
     *
     * @param soDienThoai the soDienThoai of the HocVien object to be found
     * @return an Optional containing the HocVien object with the specified soDienThoai if it exists
     */
    Optional<HocVien> findBySoDienThoai(String soDienThoai);

    /**
     * Finds a Page of HocVien objects from the database by their hoTen.
     *
     * @param hoTen    the hoTen of the HocVien object to be found
     * @param pageable the Pageable object used to construct the query
     * @return a Page containing the HocVien objects with the specified hoTen
     */
    Page<HocVien> findByHoTenContaining(String hoTen, Pageable pageable);
}
