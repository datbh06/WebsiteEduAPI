package com.tyugen.WebsiteEduAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class LoaiKhoaHoc {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer loaiKhoaHocID;

    @Column
    @Size(max = 30, message = "Tên loại khóa học không được vượt quá 30 ký tự")
    private String tenLoaiKhoaHoc;

    @OneToMany(mappedBy = "loaiKhoaHoc", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("loaiKhoaHoc")
    Set<KhoaHoc> khoaHocs;

    public Integer getLoaiKhoaHocID() {
        return loaiKhoaHocID;
    }

    public void setLoaiKhoaHocID(Integer loaiKhoaHocID) {
        this.loaiKhoaHocID = loaiKhoaHocID;
    }

    public String getTenLoaiKhoaHoc() {
        return tenLoaiKhoaHoc;
    }

    public void setTenLoaiKhoaHoc(String tenLoaiKhoaHoc) {
        this.tenLoaiKhoaHoc = tenLoaiKhoaHoc;
    }

    public Set<KhoaHoc> getKhoaHocs() {
        return khoaHocs;
    }

    public void setKhoaHocs(Set<KhoaHoc> khoaHocs) {
        this.khoaHocs = khoaHocs;
    }
}
