package com.tyugen.WebsiteEduAPI.repository;

import com.tyugen.WebsiteEduAPI.model.DangKyHoc;
import com.tyugen.WebsiteEduAPI.model.KhoaHoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * The DangKyHocRepository interface is used to define custom database queries for the DangKyHoc class.
 */
public interface DangKyHocRepository extends JpaRepository<DangKyHoc, Integer> {

    /**
     * Counts the number of DangKyHoc objects in the database that have the specified KhoaHoc.
     *
     * @param khoaHoc the KhoaHoc to be used in the query
     * @return the number of DangKyHoc objects in the database that have the specified KhoaHoc
     */
    int countByKhoaHoc(KhoaHoc khoaHoc);

    /**
     * Finds a Page of DangKyHoc objects that have the specified hocVienID.
     *
     * @param hocVienID the hocVienID to be used in the query
     * @param pageable  the pageable to be used in the query
     * @return a Page object that contains the DangKyHoc objects that have the specified hocVienID
     */
    @Query("SELECT d FROM DangKyHoc d JOIN d.hocVien h WHERE h.hocVienID = :hocVienID")
    Page<DangKyHoc> findByHocVienID(int hocVienID, Pageable pageable);

}
