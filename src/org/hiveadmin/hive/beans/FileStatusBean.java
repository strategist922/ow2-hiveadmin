/**  
* @Project: javaHiveAdimin
* @Title: FileStatusBean.java
* @Package org.hiveadmin.hive.beans
* @Description: TODO
* @author wangjie wangjie370124@163.com
* @date Aug 15, 2013 7:20:13 PM
* @version V1.0  
*/
package org.hiveadmin.hive.beans;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.log4j.Logger;
import org.aspectj.apache.bcel.generic.NEW;
import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.hiveadmin.hdfs.utils.HDFSUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.mysql.jdbc.log.Log;

/**
 * FileStatusBean
 * to record file infomations
 * @author wangjie wangjie370124@163.com
 * @date Aug 15, 2013 7:20:13 PM
 */
@Component
@Scope("prototype")
public class FileStatusBean implements Serializable{
	/**
	 * owner of the hdfs file
	 */
	private String owner;
	/**
	 * path of the hdfs file
	 */
	private Path path;
	/**
	 * basePath of the hdfs file
	 */
	private String basePath;
	/**
	 * blockSize of the hdfs file
	 */
	private long blockSize;
	/**
	 * group  of the hdfs file
	 */
	private String group;
	/**
	 * len of the hdfs file
	 */
	private long len;
	/**
	 * accessTime of the hdfs file
	 */
	private Date accessTime;
	/**
	 * modificationTime of the hdfs file
	 */
	private String modificationTime;
	/**
	 * mark the file if it is a derector
	 */
	private boolean dir;
	/**
	 * hdfsUtils to do some hdfs operations
	 */
	private static HDFSUtils hdfsUtils;
	/**
	 * log
	 */
	Logger log = Logger.getLogger(FileStatusBean.class);
	
	public String getBasePath() {
		return basePath;
	}
	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}
	public HDFSUtils getHdfsUtils() {
		return hdfsUtils;
	}
	@Resource(name="hdfsUtils")
	public void setHdfsUtils(HDFSUtils hdfsUtils) {
		this.hdfsUtils = hdfsUtils;
	}

	public boolean getDir() {
		return dir;
	}
	public void setDir(boolean dir) {
		this.dir = dir;
	}
	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Path getPath() {
		return path;
	}

	public void setPath(Path path) {
		this.path = path;
	}

	public long getBlockSize() {
		return blockSize;
	}

	public void setBlockSize(long blockSize) {
		this.blockSize = blockSize;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public long getLen() {
		return len;
	}

	public void setLen(long len) {
		this.len = len;
	}

	public Date getAccessTime() {
		return accessTime;
	}

	public void setAccessTime(Date accessTime) {
		this.accessTime = accessTime;
	}

	public String getModificationTime() {
		return modificationTime;
	}

	public void setModificationTime(String modificationTime) {
		this.modificationTime = modificationTime;
	}
	/**
	 * constructor
	 */
	public FileStatusBean(){}
	
	/**FileStatusBean
	 * <p>constructor to create a FileStatusBean<br>
	 * @param status
	 * @throws URISyntaxException
	 */
	public FileStatusBean(FileStatus status) throws URISyntaxException{
		System.out.println("==========status");
		this.path = status.getPath();
		URI uri = null;
		try {
			uri = new URI(this.path.toString());
		} catch (URISyntaxException e) {
			log.error("not a valid path.");
			throw e;
		}
		this.basePath = uri.getPath();
		this.blockSize = status.getBlockSize();
		this.dir = status.isDir();
		
		this.len = status.getLen();
		this.owner = status.getOwner();
		this.group = status.getGroup();
		this.accessTime = new Date(status.getAccessTime());
		this.modificationTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date(status.getModificationTime()));
		
	}
	@Override
	public String toString(){
		
		return "path:"+this.path.toString()+";basepath:"+this.basePath+";blocksize:"+this.blockSize+";this.dir:"+this.dir+";owner:"+this.owner;
	}
	
}
