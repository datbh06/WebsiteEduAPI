package com.tyugen.WebsiteEduAPI.Controller;

import com.tyugen.WebsiteEduAPI.service.HocVienService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/hocvien")
public class HocVienController {
    private final HocVienService hocVienService;

    /**
     * Constructs a new HocVienController object with the specified HocVienService.
     *
     * @param hocVienService the HocVienService to be used by this controller
     */
    public HocVienController(HocVienService hocVienService) {
        this.hocVienService = hocVienService;
    }

    /**
     * Adds a new HocVien object to the database.
     *
     * @param hocVien a JSON representation of the new HocVien object
     * @return a ResponseEntity indicating the result of the add operation
     */
    @PostMapping("/add")
    public ResponseEntity<?> addHocVien(@RequestBody String hocVien) {
        return hocVienService.addHocVien(hocVien);
    }
}
