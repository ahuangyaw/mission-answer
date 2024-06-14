package asia.huangzhitao.missionAnswerBackend.service;

import asia.huangzhitao.missionAnswerBackend.model.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 用户服务测试
 *
 *
 */
@SpringBootTest
public class UserServiceTest {

    @Resource
    private UserService userService;

    //线程设置
    private final ExecutorService executorService = new ThreadPoolExecutor(16, 1000, 10000, TimeUnit.MINUTES, new ArrayBlockingQueue<>(10000));


    @Test
    void userRegister() {
        String userAccount = "";
        String userPassword = "12345678";
        String checkPassword = "12345678";
        for (int i = 0; i < 10; i++){

        }
        long result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertNotEquals(-1, result);

        String mail = "12345678@qq.com";
        String userName = "12345678@qq.com";
        String userAvatar = "12345678@qq.com";
    }
    /**
     * 并发批量插入用户   100000  耗时： 26830ms
     */
    @Test
    public void doConcurrencyInsertUser() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        final int INSERT_NUM = 100;
        // 分十组
        int j = 0;
        //批量插入数据的大小
        int batchSize = 50;
        List<CompletableFuture<Void>> futureList = new ArrayList<>();
        // i 要根据数据量和插入批量来计算需要循环的次数。
        for (int i = 0; i < INSERT_NUM/batchSize; i++) {
            List<User> userList = new ArrayList<>();
            do {
                j++;
                User user = new User();
                user.setUserAccount("xiaohuang" + j);
                user.setUserPassword("12345678");
                user.setUserName("xiaohuang" + j);
                user.setUserAvatar("https://k.sinaimg.cn/n/sinakd20110/560/w1080h1080/20230930/915d-f3d7b580c33632b191e19afa0a858d31.jpg/w700d1q75cms.jpg");
                userList.add(user);
            } while (j % batchSize != 0);
            //异步执行
            CompletableFuture<Void> future = CompletableFuture.runAsync(() ->{
                System.out.println("ThreadName：" + Thread.currentThread().getName());
                userService.saveBatch(userList,batchSize);
            },executorService);
            futureList.add(future);
        }
        CompletableFuture.allOf(futureList.toArray(new CompletableFuture[]{})).join();

        stopWatch.stop();
        System.out.println( stopWatch.getLastTaskTimeMillis());

    }
}
