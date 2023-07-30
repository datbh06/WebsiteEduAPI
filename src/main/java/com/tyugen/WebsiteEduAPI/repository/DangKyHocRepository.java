package com.tyugen.WebsiteEduAPI.repository;

import com.tyugen.WebsiteEduAPI.model.DangKyHoc;
import com.tyugen.WebsiteEduAPI.model.KhoaHoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DangKyHocRepository extends JpaRepository<DangKyHoc, Integer> {

    /**
     * Counts the number of DangKyHoc objects in the database that have the specified KhoaHoc.
     *
     * @param khoaHoc the KhoaHoc to be used in the query
     * @return the number of DangKyHoc objects in the database that have the specified KhoaHoc
     */
    int countByKhoaHoc(KhoaHoc khoaHoc);

}
