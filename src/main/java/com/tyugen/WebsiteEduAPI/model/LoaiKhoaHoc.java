package com.tyugen.WebsiteEduAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoaiKhoaHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer loaiKhoaHocID;

    @Column
    @Size(max = 30, message = "Tên loại khóa học không được vượt quá 30 ký tự")
    private String tenLoaiKhoaHoc;

    @OneToMany(mappedBy = "loaiKhoaHoc", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("loaiKhoaHoc")
    Set<KhoaHoc> khoaHocs;
}
