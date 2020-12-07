package com.hs.lunpair.domain.user.entity;

import com.hs.lunpair.common.model.BaseEntity;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;

//주소 정보
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "tbl_address")
@Entity
@AttributeOverride(name = "id", column = @Column(name = "address_id"))
@Where(clause = "deleted=0")
@AllArgsConstructor
@Builder
public class Address extends BaseEntity {

    @Column(name = "baseAddr")
    private String baseAddr;

    @Column(name = "detailAddr")
    private String detailAddr;

    @Column(name = "zipCode")
    private String zipCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
