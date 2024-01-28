package kr.hs.bssm.weet.domain.result;

import jakarta.persistence.*;
import kr.hs.bssm.weet.global.entity.BaseTimeEntity;
import kr.hs.bssm.weet.presentation.result.dto.ResultUpdateRequestDto;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Result extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long formId;

    @Column(nullable = false, length = 3000)
    private String content;

    public Result update(ResultUpdateRequestDto dto) {
        this.content = dto.content();
        return this;
    }
}
