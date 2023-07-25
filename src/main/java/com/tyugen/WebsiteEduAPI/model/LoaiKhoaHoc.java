package com.tyugen.WebsiteEduAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table
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

    @Override
    public String toString() {
        return " LoaiKhoaHoc{" + "\n" +
                "    loaiKhoaHocID= " + loaiKhoaHocID + "\n" +
                "    tenLoaiKhoaHoc= " + tenLoaiKhoaHoc + "\n" +
                " }";
    }
}
