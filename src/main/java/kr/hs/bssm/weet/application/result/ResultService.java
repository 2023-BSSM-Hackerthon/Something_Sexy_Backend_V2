package kr.hs.bssm.weet.application.result;

import kr.hs.bssm.weet.domain.result.Result;
import kr.hs.bssm.weet.domain.result.repository.ResultRepository;
import kr.hs.bssm.weet.global.error.exception.ErrorCode;
import kr.hs.bssm.weet.global.error.exception.WeetException;
import kr.hs.bssm.weet.presentation.result.dto.ResultCreateRequestDto;
import kr.hs.bssm.weet.presentation.result.dto.ResultUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResultService {

    private final ResultRepository resultRepository;

    @Transactional(readOnly = true)
    public Result findByForm(Long formId) {
        return resultRepository.findByFormId(formId);
    }

    @Transactional(readOnly = true)
    public List<Result> findAll() {
        return resultRepository.findAll();
    }

    @Transactional
    public Long create(ResultCreateRequestDto dto) {
        return resultRepository.save(dto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, ResultUpdateRequestDto dto) {
        Result result = resultRepository.findById(id)
                .orElseThrow(() -> new WeetException(ErrorCode.NOT_FOUND_RESULT));
        return resultRepository.save(result.update(dto)).getId();
    }
}
