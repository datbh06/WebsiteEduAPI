package com.tyugen.WebsiteEduAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
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
public class QuyenHan {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer quyenHanID;

    @Column
    @Size(max = 50, message = "Tên quyền hạn không được vượt quá 50 ký tự")
    private String tenQuyenHan;

    @OneToMany(mappedBy = "quyenHan", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("quyenHan")
    private Set<TaiKhoan> taiKhoans;

    public Integer getQuyenHanID() {
        return quyenHanID;
    }

    public void setQuyenHanID(Integer quyenHanID) {
        this.quyenHanID = quyenHanID;
    }

    public String getTenQuyenHan() {
        return tenQuyenHan;
    }

    public void setTenQuyenHan(String tenQuyenHan) {
        this.tenQuyenHan = tenQuyenHan;
    }

    public Set<TaiKhoan> getTaiKhoans() {
        return taiKhoans;
    }

    public void setTaiKhoans(Set<TaiKhoan> taiKhoans) {
        this.taiKhoans = taiKhoans;
    }
}
