package kr.hs.bssm.weet.application.form;

import kr.hs.bssm.weet.application.user.UserService;
import kr.hs.bssm.weet.domain.form.Form;
import kr.hs.bssm.weet.domain.form.repository.FormRepository;
import kr.hs.bssm.weet.global.error.exception.ErrorCode;
import kr.hs.bssm.weet.global.error.exception.WeetException;
import kr.hs.bssm.weet.presentation.form.dto.request.FormRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FormService {

    private final FormRepository formRepository;
    private final UserService userService;

    @Transactional(readOnly = true)
    public List<Form> findMyForm() {
        Long userId = userService.findCurrentUser().getId();
        return formRepository.findByUserId(userId);
    }

    @Transactional
    public Long create(FormRequestDto dto) {
        Long userId = userService.findCurrentUser().getId();
        return formRepository.save(dto.toEntity(userId)).getId();
    }

    @Transactional
    public Long update(Long id, FormRequestDto dto) {
        Form form = formRepository.findById(id)
                .orElseThrow(() -> new WeetException(ErrorCode.NOT_FOUND_FORM));

        if (form.getIsAccepted()) {
            throw new WeetException(ErrorCode.ALREADY_ACCEPTED_FORM);
        }

        return formRepository.save(form.update(dto)).getId();
    }

    @Transactional
    public Long delete(Long id) {
        formRepository.deleteById(id);
        return id;
    }
}
