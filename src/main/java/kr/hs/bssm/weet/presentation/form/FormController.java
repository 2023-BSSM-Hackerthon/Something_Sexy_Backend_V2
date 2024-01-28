package kr.hs.bssm.weet.presentation.form;

import kr.hs.bssm.weet.application.form.FormService;
import kr.hs.bssm.weet.domain.form.Form;
import kr.hs.bssm.weet.global.annotation.LoginRequired;
import kr.hs.bssm.weet.global.annotation.TeacherOnly;
import kr.hs.bssm.weet.presentation.form.dto.request.FormAcceptRequestDto;
import kr.hs.bssm.weet.presentation.form.dto.request.FormRejectRequestDto;
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
    @GetMapping("/user/{userId}")
    public List<Form> findMyForm(@PathVariable Long userId) {
        return formService.findMyForm(userId);
    }

    @TeacherOnly
    @GetMapping
    public List<Form> findAll() {
        return formService.findAll();
    }

    @TeacherOnly
    @GetMapping("/accepted")
    public List<Form> findByAccepted() {
        return formService.findByAccepted(true);
    }

    @TeacherOnly
    @GetMapping("/not-accepted")
    public List<Form> findByNotAccepted() {
        return formService.findByAccepted(false);
    }

    @LoginRequired
    @PostMapping
    public Long request(@RequestBody FormRequestDto dto) {
        return formService.create(dto);
    }

    @TeacherOnly
    @PatchMapping("/accept/{id}")
    public Long acceptForm(@PathVariable Long id,
                           @RequestBody FormAcceptRequestDto dto) {
        return formService.accept(id, dto.decidedDate());
    }

    @TeacherOnly
    @PatchMapping("/reject/{id}")
    public Long rejectForm(@PathVariable Long id,
                           @RequestBody FormRejectRequestDto dto) {
        return formService.reject(id, dto);
    }

    @LoginRequired
    @PutMapping("/{id}")
    public Long update(@PathVariable Long id,
                       @RequestBody FormRequestDto dto) {
        return formService.update(id, dto);
    }

    @LoginRequired
    @DeleteMapping("/{id}")
    public Long deleteForm(@PathVariable Long id) {
        return formService.delete(id);
    }
}
