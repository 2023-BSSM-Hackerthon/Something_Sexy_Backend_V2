package kr.hs.bssm.weet.domain.form.repository;

import kr.hs.bssm.weet.domain.form.Form;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormRepository extends JpaRepository<Form, Long> {
}
