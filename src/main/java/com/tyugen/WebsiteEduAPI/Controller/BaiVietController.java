package com.tyugen.WebsiteEduAPI.Controller;

import com.tyugen.WebsiteEduAPI.service.BaiVietService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * BaiVietController is a controller class that handles requests related to BaiViet objects.
 */
@RestController
@RequestMapping("api/v1/baiviet")
public class BaiVietController {
    private final BaiVietService baiVietService;

    /**
     * Constructs a new BaiVietController object with the specified BaiVietService.
     *
     * @param baiVietService the BaiVietService to be used by this controller
     */
    public BaiVietController(BaiVietService baiVietService) {
        this.baiVietService = baiVietService;
    }

    /**
     * Adds a new BaiViet object to the database.
     *
     * @param baiViet a JSON representation of the new BaiViet object
     * @return a ResponseEntity indicating the result of the add operation
     */
    @PostMapping("/add")
    public ResponseEntity<?> addBaiViet(@RequestBody String baiViet) {
        return baiVietService.addBaiViet(baiViet);
    }


}
