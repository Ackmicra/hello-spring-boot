package com.dhcc.zpc.util.mail;

import javax.mail.MessagingException;

/**
 * @Author 赵朋超
 * @Description 邮件接口
 * @Date 14:08 2020/3/9
 * @Param
 * @return
 **/
public interface MailService {
    /**
     * 发送文本邮件
     *
     * @param to      收件人地址
     * @param subject 邮件主题
     * @param content 邮件内容
     * @param cc      抄送地址
     */
    void sendSimpleMail(String to, String subject, String content, String... cc);

    /**
     * 发送HTML邮件
     *
     * @param to      收件人地址
     * @param subject 邮件主题
     * @param content 邮件内容
     * @param cc      抄送地址
     * @throws MessagingException 邮件发送异常
     */
    void sendHtmlMail(String to, String subject, String content, String... cc) throws MessagingException;

    /**
     * 发送带附件的邮件
     *
     * @param to       收件人地址
     * @param subject  邮件主题
     * @param content  邮件内容
     * @param filePath 附件地址
     * @param fileName 附件名字
     * @param cc       抄送地址
     * @throws MessagingException 邮件发送异常
     */
    void sendAttachmentsMail(String to, String subject, String content, String filePath, String fileName, String... cc) throws MessagingException;

    /**
     * 发送正文中有静态资源的邮件
     *
     * @param to      收件人地址
     * @param subject 邮件主题
     * @param content 邮件内容
     * @param rscPath 静态资源地址
     * @param rscId   静态资源id
     * @param cc      抄送地址
     * @throws MessagingException 邮件发送异常
     */
    void sendResourceMail(String to, String subject, String content, String rscPath, String rscId, String... cc) throws MessagingException;

} 