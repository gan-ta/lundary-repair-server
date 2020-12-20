package com.hs.lunpair.domain.user.entity;

import com.hs.lunpair.common.model.BaseEntity;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;

//주소 정보
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "tbl_user_address")
@Entity
@AttributeOverride(name = "id", column = @Column(name = "user_address_id"))
@Where(clause = "deleted=0")
@AllArgsConstructor
public class UserAddress extends BaseEntity {

    @Column(name = "baseAddr")
    private String baseAddr;

    @Column(name = "detailAddr")
    private String detailAddr;

    @Column(name = "zipCode")
    private String zipCode;

    @Column(name = "represent_addr")
    private boolean represent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder
    public UserAddress(Long id,
                       String baseAddr,
                       String detailAddr,
                       String zipCode,
                       boolean represent,
                       User user){
        this.id = id;
        this.baseAddr = baseAddr;
        this.detailAddr = detailAddr;
        this.zipCode = zipCode;
        this.represent = represent;
        this.user = user;
    }

    public void updateRepresent(boolean represent){ this.represent = represent; }
}
