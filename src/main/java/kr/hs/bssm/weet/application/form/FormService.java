package kr.hs.bssm.weet.application.form;

import kr.hs.bssm.weet.domain.form.repository.FormRepository;
import kr.hs.bssm.weet.presentation.form.dto.request.FormRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FormService {

    private final FormRepository formRepository;

    public Long create(FormRequestDto dto) {
        return formRepository.save(dto.toEntity()).getId();
    }
}
