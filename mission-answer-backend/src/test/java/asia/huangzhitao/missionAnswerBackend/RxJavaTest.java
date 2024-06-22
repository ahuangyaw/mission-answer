package asia.huangzhitao.missionAnswerBackend;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

/**
 * @author tao
 * @description RxJavaTest
 */
@SpringBootTest
public class RxJavaTest {

    @Test
    public void test() throws InterruptedException {
        // 创建数据流
        Flowable<Long> flowable = Flowable.interval(1, TimeUnit.SECONDS)
                .map(i -> i + 1)
                .subscribeOn(Schedulers.io());
        //  订阅Flowable流
        flowable
                .observeOn(Schedulers.io())
                .doOnNext(item -> System.out.println(item.toString()))
                .subscribe(System.out::println);
        // 主线程睡眠
        Thread.sleep(10000L);

    }
}
