package kr.hs.bssm.weet.presentation.form;

import kr.hs.bssm.weet.application.form.FormService;
import kr.hs.bssm.weet.domain.form.Form;
import kr.hs.bssm.weet.global.annotation.LoginRequired;
import kr.hs.bssm.weet.presentation.form.dto.request.FormRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/form")
@RequiredArgsConstructor
public class FormController {

    private final FormService formService;

    @LoginRequired
    @GetMapping
    public List<Form> findMyForm() {
        return formService.findMyForm();
    }

    @LoginRequired
    @PostMapping
    public Long request(@RequestBody FormRequestDto dto) {
        return formService.create(dto);
    }
}