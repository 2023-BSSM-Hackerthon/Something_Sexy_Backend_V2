package kr.hs.bssm.weet.presentation.form;

import kr.hs.bssm.weet.application.form.FormService;
import kr.hs.bssm.weet.global.annotation.LoginRequired;
import kr.hs.bssm.weet.presentation.form.dto.request.FormRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/form")
@RequiredArgsConstructor
public class FormController {

    private final FormService formService;

    @LoginRequired
    @PostMapping
    public Long request(@RequestBody FormRequestDto dto) {
        return formService.create(dto);
    }
}
