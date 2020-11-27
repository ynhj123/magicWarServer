package com.ynhj.magic_war.conf;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;

/**
 * @Auther: ynhj
 * @Date: 2020-01-12 17:24
 * @Description:
 */
@Configuration
@Import(FdfsClientConfig.class)
@EnableMBeanExport(registration= RegistrationPolicy.IGNORE_EXISTING)// 解决jmx重复注册bean的问题
public class FastDFSConfiguration {

}

