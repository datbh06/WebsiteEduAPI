package com.tyugen.WebsiteEduAPI.Controller;

import com.tyugen.WebsiteEduAPI.service.TaiKhoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TaiKhoanController is a RestController that handles HTTP requests for the TaiKhoan API.
 */
@RestController
@RequestMapping("api/v1/taikhoan")
public class TaiKhoanController {
    private final TaiKhoanService taiKhoanService;

    /**
     * Constructs a new TaiKhoanController object with the specified TaiKhoanRepository.
     *
     * @param taiKhoanService the TaiKhoanService to be used by this controller
     */
    public TaiKhoanController(TaiKhoanService taiKhoanService) {
        this.taiKhoanService = taiKhoanService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addTaiKhoan(@RequestBody String taiKhoan) {
        return taiKhoanService.addTaiKhoan(taiKhoan);
    }

}
