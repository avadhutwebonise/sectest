package com.sectest.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
public class IndexController {

    private static final Logger LOG = LoggerFactory.getLogger(IndexController.class);
    
    @RequestMapping(value="/")
    public String welcome () {
        LOG.debug("Index page requested");
        return "index";
    }

}
