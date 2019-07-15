package com.xiajr.masterblog.modules.service;

import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author xiajr
 *
 */
@Service
public interface MailService {
    void config();
    void sendTemplateEmail(String to, String title, String template, Map<String, Object> content);
}
