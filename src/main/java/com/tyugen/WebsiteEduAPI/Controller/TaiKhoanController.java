package com.tyugen.WebsiteEduAPI.Controller;

import com.tyugen.WebsiteEduAPI.service.TaiKhoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    /**
     * Updates an existing TaiKhoan object in the database.
     *
     * @param id       the ID of the TaiKhoan object to be updated
     * @param taiKhoan a JSON representation of the updated TaiKhoan object
     * @return a ResponseEntity indicating the result of the update operation
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTaiKhoan(@PathVariable("id") int id, @RequestBody String taiKhoan) {
        return taiKhoanService.updateTaiKhoan(id, taiKhoan);
    }

}
