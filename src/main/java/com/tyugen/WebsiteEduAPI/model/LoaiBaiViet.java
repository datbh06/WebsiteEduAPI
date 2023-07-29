package com.tyugen.WebsiteEduAPI.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table
@Data
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

}
