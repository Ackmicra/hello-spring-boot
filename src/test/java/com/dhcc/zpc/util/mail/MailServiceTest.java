package com.dhcc.zpc.util.mail;


import cn.hutool.core.io.resource.ResourceUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;

import javax.mail.MessagingException;
import java.net.URL;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {

    @Autowired
    private MailService mailService;
    @Autowired
    private TemplateEngine templateEngine;
    @Autowired
    private ApplicationContext context;

    /**
     * 测试简单邮件
     */
    @Test
    public void sendSimpleMailTest(){
        mailService.sendSimpleMail("zhaopengchao@dhcc.com.cn", "测试邮件", "This is a good idea", null);
    }

    /**
     * 测试HTML邮件
     *
     * @throws MessagingException 邮件异常
     */
    @Test
    public void sendHtmlMailTest() throws MessagingException {
        Context context = new Context();
        context.setVariable("project", "Spring Boot Demo");
        context.setVariable("author", "Yangkai.Shen");
        context.setVariable("url", "https://github.com/xkcoding/spring-boot-demo");

        String emailTemplate = templateEngine.process("welcome", context);
        mailService.sendHtmlMail("zhaopengchao@dhcc.com.cn", "这是一封模板HTML邮件", emailTemplate);
    }

    /**
     * 测试HTML邮件，自定义模板目录
     *
     * @throws MessagingException 邮件异常
     */
    @Test
    public void sendHtmlMail2Test() throws MessagingException {

        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(context);
        templateResolver.setCacheable(false);
        templateResolver.setPrefix("classpath:/email/");
        templateResolver.setSuffix(".html");

        templateEngine.setTemplateResolver(templateResolver);

        Context context = new Context();
        context.setVariable("project", "Spring Boot Demo");
        context.setVariable("author", "Yangkai.Shen");
        context.setVariable("url", "https://github.com/xkcoding/spring-boot-demo");

        String emailTemplate = templateEngine.process("test", context);
        mailService.sendHtmlMail("zhaopengchao@dhcc.com.cn", "这是一封模板HTML邮件", emailTemplate);
    }

    /**
     * 测试附件邮件
     *
     * @throws MessagingException 邮件异常
     */
    @Test
    public void sendAttachmentsMailTest() throws MessagingException {
        URL resource = ResourceUtil.getResource("static/suolong.jpg");
        mailService.sendAttachmentsMail("zhaopengchao@dhcc.com.cn", "这是一封带附件的邮件", "邮件中有附件，请注意查收！", resource.getPath(), "suolong.jpg");
//        mailService.sendAttachmentsMail("zhaopengchao@dhcc.com.cn", "这是一封带附件的邮件", "邮件中有附件，请注意查收！", "C:\\Users\\Ackmicra\\Pictures\\Saved Pictures\\锁屏壁纸\\suolong.jpg");
    }

    /**
     * 测试静态资源邮件
     *
     * @throws MessagingException 邮件异常
     */
    @Test
    public void sendResourceMailTest() throws MessagingException {
        String rscId = "xkcoding";
        String content = "<html><body>这是带静态资源的邮件<br/><img src=\'cid:" + rscId + "\' ></body></html>";
        URL resource = ResourceUtil.getResource("static/suolong.jpg");
        mailService.sendResourceMail("zhaopengchao@dhcc.com.cn", "这是一封带静态资源的邮件", content, resource.getPath(), rscId);
//        mailService.sendResourceMail("zhaopengchao@dhcc.com.cn", "这是一封带静态资源的邮件", content, "C:\\Users\\Ackmicra\\Pictures\\Saved Pictures\\锁屏壁纸\\suolong.jpg", rscId);
    }
}