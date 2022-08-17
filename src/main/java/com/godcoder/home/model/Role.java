package com.godcoder.home.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // 설정에 맞게 양방향 매핑
    @ManyToMany(mappedBy = "roles") // User 테이블에 있는 컬럼 이름.
    private List<User> users;
}
