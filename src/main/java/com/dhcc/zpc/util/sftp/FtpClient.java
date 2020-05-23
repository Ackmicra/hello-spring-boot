package com.dhcc.zpc.util.sftp;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.*;
import java.net.SocketException;

/**
 * FTP 工具类
 * @author Fang
 *
 */
@Slf4j
//@Configuration
public class FtpClient {

	@Value("${ftp.host}")
	private String host;

	@Value("${ftp.username}")
	private String username;

	@Value("${ftp.password}")
	private String password;

	@Value("${ftp.port}")
	private int port;// FTP默认端口 21

	private FTPClient ftp = null;
	private boolean isOpen = false;

	/**
	 * 建立FTP连接
	 */
	public void connect() {
		log.info("FTP 连接" + host+ " " + port + " " + username);
		try {
			ftp = new FTPClient();
			ftp.connect(host, port);
			ftp.login(username, password);
			int reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				log.error("FTP 登录拒绝");
			} else {
				isOpen = true;
				//ftp.changeWorkingDirectory(bean.getReomtePath());
				ftp.setControlEncoding("GBK");
				ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
				log.info("FTP 登录成功");
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
			log.error("FTP 连接失败" + ioe.getMessage());
		}
	}

	/**
	 * 切换FTP目录
	 * @param ftpDir
	 * @return
	 */
	public boolean changeFTPDir(String ftpDir) {
		boolean flag = false;
		if (isOpen) {
			try {
				flag = ftp.changeWorkingDirectory(ftpDir);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	/**
	 * 传进FTP远程目录
	 * @param ftpDir
	 * @return
	 */
	public boolean createFTPDir(String ftpDir) {
		boolean flag = false;
		if (isOpen) {
			try {
				flag = ftp.makeDirectory(ftpDir);
			} catch (IOException e) {
				log.error("FTP 切换目录异常 cd" + ftpDir);
				e.printStackTrace();
			}
		}
		return flag;
	}

	/**
	 * 删除FTP文件
	 * @param fileName
	 * @return
	 */
	public boolean deleteFTPFile(String fileName){
		boolean flag = false;
		if (isOpen) {
			try {
				flag = ftp.deleteFile(fileName);
			} catch (IOException e) {
				log.error("FTP 删除文件异常 rm -rf" + fileName);
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	/**
	 * 关闭FTP
	 */
	public void close() {
		try {
			if (ftp != null && ftp.isConnected()) {
				ftp.logout();
				ftp.disconnect();
				isOpen = false;
				log.info("FTP 成功关闭");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 上传文件
	 */
	public boolean put(String localPath, String fileName) {
		FileInputStream input = null;
		if (!isOpen) {
			return false;
		}
		boolean flag = false;
		try {
			File file = new File(localPath, fileName);
			if (file.exists()) {
				input = new FileInputStream(file);
				flag = ftp.storeFile(fileName, input);
				log.info("FILE PUT://" + localPath + " " + fileName + " TO  "
						+ file.length() + "b");
			} else {
				log.info("FILE PUT://" + localPath + " " + fileName + " NOT EXISTS");
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
			log.error("FILE PUT://" + localPath + " " + fileName + " FAIL " + ioe.getMessage());
		} finally {
			if (input != null){
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			close();
		}
		return flag;
	}

	/**
	 * 下载FTP文件
	 */
	public boolean get(String localPath, String fileName) {
		if (!isOpen) {
			return false;
		}
		boolean flag = false;
		try {
			OutputStream output = new FileOutputStream(localPath + "/" + fileName);
			flag = ftp.retrieveFile(fileName, output);
			output.flush();
			output.close();
			log.info("FILE GET:// " + fileName + " TO  FILE://" + localPath
					+ " " + fileName);
		} catch (IOException ioe) {
			ioe.printStackTrace();
			log.error("FILE GET:// " + fileName + " FAIL " + ioe.getMessage());
		}
		return flag;
	}

	/**
	 * @Author 赵朋超
	 * @Description 从localPath目录将fileName移动到reomtePath
	 * @Date 17:25 2020/2/27
	 * @Param [reomtePath, localPath, fileName]
	 * @return boolean
	 **/
	public boolean put(String reomtePath, String localPath, String fileName) {
		boolean result = false;
		try {
			FileInputStream input=new FileInputStream(new File(localPath));
			int reply = ftp.getReplyCode();
	        if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return result;
	        }
	        ftp.setControlEncoding("GBK"); // 中文支持
	        ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
	        ftp.enterLocalPassiveMode();
	        ftp.changeWorkingDirectory(reomtePath);
	
	        result = ftp.storeFile(fileName, input);
	        input.close();
	        ftp.logout();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
            if (ftp.isConnected()) {
                try {
                	ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
		return result;
	}
	 /**
     * 从FTP服务器下载文件
     *
     * @param ftpPath FTP服务器中文件所在路径 格式： ftptest/aa
     * @param localPath 下载到本地的位置 格式：H:/download
     * @param fileName 文件名称
     */
    public boolean downloadFtpFile(String ftpPath, String localPath,
                                   String fileName) {
    	boolean result = false;

        try {
            ftp.setControlEncoding("GBK"); // 中文支持
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftp.enterLocalPassiveMode();
            ftp.changeWorkingDirectory(ftpPath);

            File localFile = new File(localPath + File.separatorChar + fileName);
            OutputStream os = new FileOutputStream(localFile);
            result = ftp.retrieveFile(fileName, os);
            os.close();
            ftp.logout();

        } catch (FileNotFoundException e) {
            log.error("没有找到" + ftpPath + "文件", e);
            e.printStackTrace();
        } catch (SocketException e) {
			log.error("连接FTP失败.", e);
            e.printStackTrace();
        } catch (IOException e) {
			log.error("文件读取错误。", e);
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                	ftp.disconnect();
                } catch (IOException ioe) {
                	ioe.printStackTrace();
                }
            }
        }
        return result;
    }

	/**
	 * @Author 赵朋超
	 * @Description 创建目录
	 * @Date 17:28 2020/2/27
	 * @Param [dir]
	 * @return boolean
	 **/
	public boolean makeDirectory(String dir) {
		boolean flag = true;
		try {
			flag = ftp.makeDirectory(dir);
			if (flag) {
				System.out.println("创建文件夹" + dir + " 成功！");

			} else {
				System.out.println("创建文件夹" + dir + " 失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	//判断ftp服务器文件是否存在
	public boolean existFile(String path) throws IOException {
		boolean flag = false;
		FTPFile[] ftpFileArr = ftp.listFiles(path);
		//FTPFile[] ftpFileArr = getDefaultFtpClient().listFiles(path);
		if (ftpFileArr.length > 0) {
			flag = true;
		}
		return flag;
	}

}
