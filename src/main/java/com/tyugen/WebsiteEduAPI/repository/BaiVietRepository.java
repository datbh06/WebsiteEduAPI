package com.tyugen.WebsiteEduAPI.repository;

import com.tyugen.WebsiteEduAPI.model.BaiViet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaiVietRepository extends JpaRepository<BaiViet, Integer> {

    /**
     * Finds a BaiViet object by its tenBaiViet.
     *
     * @param tenBaiViet the tenBaiViet of the BaiViet to be found
     * @return a BaiViet object
     */
    BaiViet findByTenBaiViet(String tenBaiViet);
}
