package cn.scalecraft.chatgpt.data.test.domain.auth;

import cn.scalecraft.chatgpt.data.domain.auth.model.entity.AuthStateEntity;
import cn.scalecraft.chatgpt.data.domain.auth.service.IAuthService;
import com.alibaba.fastjson2.JSON;
import com.google.common.cache.Cache;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthTest {

    @Resource
    private IAuthService authService;

    @Resource
    private Cache<String, String> cache;

    @Test
    public void test_AuthService() {

        cache.put("1000", "xiaofuge");
        cache.put("xiaofuge", "1000");

        AuthStateEntity authStateEntity = authService.doLogin("1000");
        log.info(JSON.toJSONString(authStateEntity));

        authService.checkToken(authStateEntity.getToken());
    }

}
