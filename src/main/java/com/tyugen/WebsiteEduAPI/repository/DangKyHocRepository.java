package com.tyugen.WebsiteEduAPI.repository;

import com.tyugen.WebsiteEduAPI.model.DangKyHoc;
import com.tyugen.WebsiteEduAPI.model.KhoaHoc;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DangKyHocRepository extends JpaRepository<DangKyHoc, Integer> {
    int countByKhoaHoc(KhoaHoc khoaHoc);
}
