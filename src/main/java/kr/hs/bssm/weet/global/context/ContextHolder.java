package kr.hs.bssm.weet.global.context;

import lombok.Getter;
import lombok.Setter;

public class ContextHolder {

    @Getter
    @Setter
    private static Authentication authentication;
}
