package kr.hs.bssm.weet.presentation.form.dto.request;

import kr.hs.bssm.weet.domain.form.Form;

import java.util.Date;
import java.util.List;

public record FormRequestDto(
        String title,
        String content,
        List<Date> dates
) {
    public Form toEntity() {
        return Form.builder()
                .title(title)
                .content(content)
                .date(dates)
                .build();
    }
}
