package com.adimessi30.elevatorsystem.components;

import com.adimessi30.elevatorsystem.components.abstractions.IdAutoGenerated;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class Button extends IdAutoGenerated {

    protected void postConstruct() {
        log.debug("New externalButton with instance id: {} was created.......", getId());
    }
}
