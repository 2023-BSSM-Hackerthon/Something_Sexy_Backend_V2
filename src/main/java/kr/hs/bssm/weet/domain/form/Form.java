package kr.hs.bssm.weet.domain.form;

import jakarta.persistence.*;
import kr.hs.bssm.weet.presentation.form.dto.request.FormRequestDto;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Form {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 3000)
    private String content;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "form_date",
            joinColumns = @JoinColumn(name = "form_id")
    )
    @Cascade(CascadeType.ALL)
    private List<Date> date;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Boolean isAccepted;

    public Form update(FormRequestDto dto) {
        this.title = dto.title();
        this.content = dto.content();
        this.date = dto.dates();

        return this;
    }

    public Form accept() {
        this.isAccepted = true;

        return this;
    }
}
