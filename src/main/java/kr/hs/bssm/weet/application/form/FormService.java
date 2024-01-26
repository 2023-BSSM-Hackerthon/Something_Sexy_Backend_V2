package kr.hs.bssm.weet.application.form;

import kr.hs.bssm.weet.application.user.UserService;
import kr.hs.bssm.weet.domain.form.Form;
import kr.hs.bssm.weet.domain.form.repository.FormRepository;
import kr.hs.bssm.weet.presentation.form.dto.request.FormRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FormService {

    private final FormRepository formRepository;
    private final UserService userService;

    public List<Form> findMyForm() {
        Long userId = userService.findCurrentUser().getId();
        return formRepository.findByUserId(userId);
    }

    public Long create(FormRequestDto dto) {
        Long userId = userService.findCurrentUser().getId();
        return formRepository.save(dto.toEntity(userId)).getId();
    }
}