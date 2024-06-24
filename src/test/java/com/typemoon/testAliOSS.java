package com.typemoon;

import com.typemoon.strategy.impl.OssUploadStrategyImpl;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class testAliOSS {

    @Autowired
    private OssUploadStrategyImpl ossUploadStrategy;

    @Test
    public void test() {
        val ossClient = ossUploadStrategy.getOssClient();
        boolean b = ossClient.doesBucketExist("missblue-blog");
    }
}
