package com.nbclass.service;

import com.nbclass.enums.TemplateType;

import java.util.Map;

public interface MailService {

    /**
     * 发送文本邮件
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     */
    void sendSimpleMail(String to, String subject, String content);

    /**
     * 发送HTML邮件
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     */
    public void sendHtmlMail(String to, String subject, String content);

    /**
     * 发送带附件的邮件
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     * @param filePath 附件
     */
    public void sendAttachmentsMail(String to, String subject, String content, String filePath);

    /**
     * 发送模板邮件
     * @param templateType 模板
     * @param to 收件人
     * @param subject 主题
     * @param map 模板参数
     */
    public void sendTemplateMail(TemplateType templateType, String to, String subject, Map<String,Object> map);
}