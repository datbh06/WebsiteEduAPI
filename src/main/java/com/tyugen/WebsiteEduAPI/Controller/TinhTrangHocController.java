package com.tyugen.WebsiteEduAPI.Controller;

import com.tyugen.WebsiteEduAPI.service.TinhTrangHocService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tinhtranghoc")
public class TinhTrangHocController {
    private final TinhTrangHocService tinhTrangHocService;

    /**
     * Constructs a new TinhTrangHocController object with the specified TinhTrangHocService.
     *
     * @param tinhTrangHocService the TinhTrangHocService to be used by this controller
     */
    public TinhTrangHocController(TinhTrangHocService tinhTrangHocService) {
        this.tinhTrangHocService = tinhTrangHocService;
    }

    /**
     * Adds a new TinhTrangHoc object to the database.
     *
     * @param tinhTrangHoc a JSON representation of the new TinhTrangHoc object
     * @return a ResponseEntity indicating the result of the add operation
     */
    @PostMapping("/add")
    public ResponseEntity<?> addTinhTrangHoc(@RequestBody String tinhTrangHoc) {
        return tinhTrangHocService.addTinhTrangHoc(tinhTrangHoc);
    }

}
