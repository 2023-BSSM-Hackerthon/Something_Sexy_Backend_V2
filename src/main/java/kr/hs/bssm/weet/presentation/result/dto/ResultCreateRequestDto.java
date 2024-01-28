package kr.hs.bssm.weet.presentation.result.dto;

import kr.hs.bssm.weet.domain.result.Result;

public record ResultCreateRequestDto(
        Long formId,
        String content
) {
    public Result toEntity() {
        return Result.builder()
                .formId(formId)
                .content(content)
                .build();
    }
}
