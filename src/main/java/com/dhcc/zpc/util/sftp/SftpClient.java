package com.dhcc.zpc.util.sftp;
 
import com.jcraft.jsch.*;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.Properties;
import java.util.Vector;

/**
 * @Author 赵朋超
 * @Description SFTP客户端
 * @Date 21:39 2020/2/26
 **/
@Slf4j
//@Configuration
public class SftpClient {

    @Value("${sftp.host}")
    private String host;

    @Value("${sftp.username}")
    private String username;

    @Value("${sftp.password}")
    private String password;

    @Value("${sftp.port}")
    private int port;// SFTP默认端口 22

    @Value("${sftp.privateKey}")
    protected String privateKey;// 密钥文件路径

    @Value("${sftp.privateKey}")
    protected String passphrase;// 密钥口令

    private ChannelSftp sftp = null;

    private Session sshSession = null;

    /**
     * 连接sftp服务器
     * @return
     */
    public void connect() {
        JSch jsch = new JSch();
        Channel channel = null;
        try {
            if (!StringUtils.isEmpty(privateKey)) {
                // 使用密钥验证方式，密钥可以使有口令的密钥，也可以是没有口令的密钥
                if (!StringUtils.isEmpty(passphrase)) {
                    jsch.addIdentity(privateKey, passphrase);
                } else {
                    jsch.addIdentity(privateKey);
                }
            }
            sshSession = jsch.getSession(username, host, port);
            if (!StringUtils.isEmpty(password)) {
                sshSession.setPassword(password);
            }
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");// do not verify host
            // key
            sshSession.setConfig(sshConfig);
            // session.setTimeout(timeout);
            // session.setServerAliveInterval(92000);
            sshSession.connect();
            // 参数sftp指明要打开的连接是sftp连接
            channel = sshSession.openChannel("sftp");
            channel.connect();
            sftp = (ChannelSftp) channel;
        } catch (JSchException e) {
            log.error("连接【" + host + ":" + port + "】异常", e);
        }
    }
 
    /**
     * 上传文件
     *
     * @param directory  上传的目录  /home/app/
     * @param uploadFile 要上传的文件  C:\\Users\\hp\\Desktop\\123456.png
     */
    public boolean upload(String directory, String uploadFile) {
        FileInputStream fileInputStream = null;
        try {
            if (sftp == null) {
                connect();
            }
            sftp.cd(directory);
            File file = new File(uploadFile);
            fileInputStream = new FileInputStream(file);
            sftp.put(fileInputStream, file.getName());
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        } finally {
            if (fileInputStream != null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    log.error("关闭连接异常", e);
                }
            }
            disconnect();
        }
    }

    /**
     * 下载单个文件
     * @param remotePath
     *              SFTP目录
     * @param remoteFileName
     *            下载文件名
     * @param localPath
     *            本地保存目录(以路径符号结束)
     * @param localFileName
     *            保存文件名
     * @return
     */
    public synchronized boolean downloadFile(String remotePath, String remoteFileName, String localPath, String localFileName) {
        log.info("SFTP文件：" + remotePath + "/" + remoteFileName + "||本地存放文件： " + localPath + "/" + localFileName);
        try {
            if (sftp == null || !isConnected()) {
                connect();
            }
            sftp.cd(remotePath);
            File file = new File(localPath + localFileName);
            mkdirs(localPath + localFileName);
            sftp.get(remoteFileName, new FileOutputStream(file));
            return true;
        } catch (FileNotFoundException e) {
            log.error("不存在文件,Path:" + remotePath + ",file:" + remoteFileName, e);
        } catch (SftpException e) {
            log.error("下载文件处理异常,Path:" + remotePath + ",file:" + remoteFileName, e);
        } finally {
            disconnect();
        }
        return false;
    }
 
    /**
     * 下载文件
     *
     * @param directory    下载目录 /home/app
     * @param downloadFile 下载的文件 123.png
     * @param saveFile     存在本地的路径 C:\\Users\\hp\\Desktop\\123456.png
     */
    public File download(String directory, String downloadFile, String saveFile) {
        try {
            if (sftp == null || !isConnected()) {
                connect();
            }
            sftp.cd(directory);
            File file = new File(saveFile);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            sftp.get(downloadFile, fileOutputStream);
            fileOutputStream.close();
            return file;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        } finally {
            disconnect();
        }
    }
 
    /**
     * 下载文件
     *
     * @param downloadFilePath 下载的文件完整目录  /home/app/123.png
     * @param saveFile     存在本地的路径  C:\\Users\\hp\\Desktop\\123456.png
     */
    public File download(String downloadFilePath, String saveFile) {
        try {
            int i = downloadFilePath.lastIndexOf('/');
            if (i == -1){
                return null;
            }
            sftp.cd(downloadFilePath.substring(0, i));
            File file = new File(saveFile);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            sftp.get(downloadFilePath.substring(i + 1), fileOutputStream);
            fileOutputStream.close();
            return file;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        } finally {
            disconnect();
        }
    }
 
    /**
     * 删除文件
     *
     * @param directory  要删除文件所在目录 /home/app
     * @param deleteFile 要删除的文件 123.png
     */
    public void deleteFile(String directory, String deleteFile) {
        try {
            sftp.cd(directory);
            sftp.rm(deleteFile);
        } catch (Exception e) {
            log.error("删除文件【" + directory + "/" + deleteFile + "】发生异常！", e);
        }
    }

    /**
     * @Author 赵朋超
     * @Description 关闭连接
     * @Date 21:57 2020/2/26
     * @Param []
     * @return void
     **/
    public void disconnect() {
        if (sshSession != null) {
            if (sshSession.isConnected()) {
                sshSession.disconnect();
            }
        }
        if (sftp != null) {
            if (sftp.isConnected()) {
                sftp.quit();
                sftp.disconnect();
            }
        }
    }

    /**
     * @Author 赵朋超
     * @Description sftp is connected
     * @Date 22:58 2020/2/26
     * @Param []
     * @return boolean
     **/
    public boolean isConnected() {
        return sftp != null && sftp.isConnected();
    }
 
    /**
     * 列出目录下的文件
     *
     * @param directory 要列出的目录  /home/app
     * @throws SftpException
     */
    public Vector<LsEntry> listFiles(String directory) throws SftpException {
        return sftp.ls(directory);
    }

    /**
     * 创建目录
     *
     * @param createpath
     * @return
     */
    private boolean createDir(String createpath) {
        try {
            if (isDirExist(createpath)) {
                this.sftp.cd(createpath);
                return true;
            }
            String[] pathArry = createpath.split("/");
            StringBuffer filePath = new StringBuffer("/");
            for (String path : pathArry) {
                if (path.equals("")) {
                    continue;
                }
                filePath.append(path + "/");
                if (isDirExist(filePath.toString())) {
                    sftp.cd(filePath.toString());
                } else {
                    // 建立目录
                    sftp.mkdir(filePath.toString());
                    // 进入并设置为当前目录
                    sftp.cd(filePath.toString());
                }
            }
            return true;
        } catch (SftpException e) {
            log.error("sftp创建目录异常", e);
        }
        return false;
    }

    /**
     * 判断目录是否存在
     *
     * @param directory
     * @return
     */
    public boolean isDirExist(String directory) {
        try {
            SftpATTRS sftpATTRS = sftp.lstat(directory);
            return sftpATTRS.isDir();
        } catch (Exception e) {
             log.error("sftp目录isDirExist异常", e);
        }
        return false;
    }

    /**
     * 如果目录不存在就创建目录
     *
     * @param path
     */
    private void mkdirs(String path) {
        File f = new File(path);
        String fs = f.getParent();
        f = new File(fs);
        if (!f.exists()) {
            f.mkdirs();
        }
    }
}