package com.mx.web;

import com.mx.domain.ReturnMessage;
import com.mx.util.AesDecryptUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    Logger logger  = LoggerFactory.getLogger(TestController.class);

    @GetMapping("/get")
    public String testGet(){
        return "get success";
    }

    @PostMapping("/post")
    public String testPost(){
        return "post success";
    }

    @DeleteMapping("/delete")
    public String testDelete(){
        return "delete success";
    }

    @PostMapping("/decrypt")
    public ReturnMessage decrypt(@RequestParam("content") String content,@RequestParam("key") String key){
        logger.info("AES解密测试,内容" + content);
        String result =  AesDecryptUtil.getDecodeStr(content,key);
        logger.info("AES解密后内容,内容" + result);
        return new ReturnMessage("0000","解密成功",result);
    }

    @PostMapping("/encrypt")
    public ReturnMessage encrypt(@RequestParam("content") String content,@RequestParam("key") String key){
        logger.info("AES加密测试,内容" + content);
        String result =  AesDecryptUtil.getEncodeStr(content,key);
        logger.info("AES加密测试,内容" + result);
        return new ReturnMessage("0000","加密成功",result);
    }
}
