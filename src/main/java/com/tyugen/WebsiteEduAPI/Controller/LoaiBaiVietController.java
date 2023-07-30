package com.tyugen.WebsiteEduAPI.Controller;

import com.tyugen.WebsiteEduAPI.service.LoaiBaiVietService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * LoaiBaiVietController is a controller class that handles requests related to LoaiBaiViet objects.
 */
@RestController
@RequestMapping("api/v1/loaibaiviet")
public class LoaiBaiVietController {
    private final LoaiBaiVietService loaiBaiVietService;

    /**
     * Constructs a new LoaiBaiVietController object with the specified LoaiBaiVietService.
     *
     * @param loaiBaiVietService the LoaiBaiVietService to be used by this controller
     */
    public LoaiBaiVietController(LoaiBaiVietService loaiBaiVietService) {
        this.loaiBaiVietService = loaiBaiVietService;
    }

    /**
     * Adds a new LoaiBaiViet object to the database.
     *
     * @param loaiBaiViet a JSON representation of the new LoaiBaiViet object
     * @return a ResponseEntity indicating the result of the add operation
     */
    @PostMapping("/add")
    public ResponseEntity<?> addLoaiBaiViet(@RequestBody String loaiBaiViet) {
        return loaiBaiVietService.addLoaiBaiViet(loaiBaiViet);
    }

}
