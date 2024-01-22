package kr.hs.bssm.weet.domain.user;

import jakarta.persistence.*;
import leehj050211.bsmOauth.dto.resource.BsmUserResource;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class User {

    @Id
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Column(nullable = false)
    private Integer grade;

    @Column(nullable = false)
    private Integer classNo;

    @Column(nullable = false)
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
}
