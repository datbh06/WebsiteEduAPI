package com.tyugen.WebsiteEduAPI.Controller;

import com.tyugen.WebsiteEduAPI.repository.HocVienRepository;
import com.tyugen.WebsiteEduAPI.repository.KhoaHocRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/khoahoc")
public class KhoaHocController {

    private final KhoaHocRepository khoaHocRepository;

    private final HocVienRepository hocVienRepository;

    /**
     * Constructs a new KhoaHocController object with the specified KhoaHocRepository.
     *
     * @param khoaHocRepository the khoaHocRepository to be used by this controller
     * @param hocVienRepository the hocVienRepository to be used by this controller
     */
    public KhoaHocController(KhoaHocRepository khoaHocRepository, HocVienRepository hocVienRepository) {
        this.khoaHocRepository = khoaHocRepository;
        this.hocVienRepository = hocVienRepository;
    }

    // create a new khoahoc

}
