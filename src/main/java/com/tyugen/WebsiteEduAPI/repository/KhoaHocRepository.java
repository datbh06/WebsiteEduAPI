package com.tyugen.WebsiteEduAPI.repository;

import com.tyugen.WebsiteEduAPI.model.KhoaHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface KhoaHocRepository extends JpaRepository<KhoaHoc, Integer>, PagingAndSortingRepository<KhoaHoc, Integer> {
    KhoaHoc findByTenKhoaHoc(String tenKhoaHoc);
}
