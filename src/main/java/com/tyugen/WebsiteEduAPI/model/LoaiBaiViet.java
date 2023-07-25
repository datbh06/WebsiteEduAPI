package com.tyugen.WebsiteEduAPI.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class LoaiBaiViet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer loaiBaiVietID;

    @Column
    private String tenLoai;

    @OneToMany(mappedBy = "loaiBaiViet", cascade = CascadeType.ALL)
    private Set<ChuDe> chuDes;

    public Integer getLoaiBaiVietID() {
        return loaiBaiVietID;
    }

    public void setLoaiBaiVietID(Integer loaiBaiVietID) {
        this.loaiBaiVietID = loaiBaiVietID;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public Set<ChuDe> getChuDes() {
        return chuDes;
    }

    public void setChuDes(Set<ChuDe> chuDes) {
        this.chuDes = chuDes;
    }
}
