package com.hs.lunpair.common.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
//공통적으로 엔티티에 적용이 되는 필드들을 담은 클래스
//엔티티가 아님
//직접 생성해서 만드는 것이 아니기 때문에 추상 클래스 권장
//주로 등록일, 수정일, 등록자, 수정자 같은 전체 엔티티에서 공통으로 적용하는 정보
@EntityListeners(AuditingEntityListener.class)
//자동으로 값을 넣어주는 기능
@EqualsAndHashCode(of="id", callSuper = false)
//equals: 두 객체의 내용이 같은 지 확인
//hashcode: 두 객체가 같은 객체인지 확인
//연관 관계가 복잡해 질 때,
//@EqualsAndHashCode에서 서로 다른 연관 관계를 순환 참조하느라 무한 루프가 발생
//stack overflow가 발생할 수 있기 때문에 id 값만 주로 사용
@Getter
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "deleted", nullable = false, columnDefinition = "BIT default 0")
    protected Boolean deleted = false;

    @CreatedBy
    @Column(name = "created_by", updatable = false, length = 64)
    protected String createdBy;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    protected LocalDateTime createdAt = null;

    @LastModifiedBy
    @Column(name = "last_modified_by", length = 64)
    protected String lastModifiedBy = "";

    @LastModifiedDate
    @Column(name = "last_modified_at", nullable = false)
    protected LocalDateTime lastModifiedAt = null;

    //삭제를 했지만 남겨 두워야 할 데이터가 있을 수 있기 때문에 delete 필드로 관리
    public void delete(){
        deleted = true;
    }
}
