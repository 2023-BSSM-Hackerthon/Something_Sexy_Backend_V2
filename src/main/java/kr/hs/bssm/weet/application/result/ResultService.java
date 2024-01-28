package kr.hs.bssm.weet.application.result;

import kr.hs.bssm.weet.domain.result.repository.ResultRepository;
import kr.hs.bssm.weet.presentation.result.dto.ResultCreateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResultService {

    private final ResultRepository resultRepository;

    public Long create(ResultCreateRequestDto dto) {
        return resultRepository.save(dto.toEntity()).getId();
    }
}