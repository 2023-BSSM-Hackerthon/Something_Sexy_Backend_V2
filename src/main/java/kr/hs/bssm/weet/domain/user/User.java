package kr.hs.bssm.weet.domain.user;

import jakarta.persistence.*;
import kr.hs.bssm.weet.global.entity.BaseTimeEntity;
import leehj050211.bsmOauth.dto.resource.BsmUserResource;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class User extends BaseTimeEntity {

    @Id
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Column
    private Integer grade;

    @Column
    private Integer classNo;

    @Column
    private Integer number;

    public static User createStudent(BsmUserResource resource) {
        return User.builder()
                .id(resource.getUserCode())
                .name(resource.getStudent().getName())
                .email(resource.getEmail())
                .authority(Authority.STUDENT)
                .grade(resource.getStudent().getGrade())
                .classNo(resource.getStudent().getClassNo())
                .number(resource.getStudent().getStudentNo())
                .build();
    }

    public static User createTeacher(BsmUserResource resource) {
        return User.builder()
                .id(resource.getUserCode())
                .name(resource.getTeacher().getName())
                .email(resource.getEmail())
                .authority(Authority.TEACHER)
                .grade(null)
                .classNo(null)
                .number(null)
                .build();
    }
}
