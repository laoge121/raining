package com.laoge.raining.client.test;

import org.springframework.aop.support.DefaultIntroductionAdvisor;

public class IsModifiedAdvisor extends DefaultIntroductionAdvisor {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public IsModifiedAdvisor() {
        super(new IsModifiedMixin());
    }

}
