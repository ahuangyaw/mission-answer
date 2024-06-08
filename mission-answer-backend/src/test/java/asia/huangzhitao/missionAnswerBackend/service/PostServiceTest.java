package asia.huangzhitao.missionAnswerBackend.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import asia.huangzhitao.missionAnswerBackend.model.dto.post.PostQueryRequest;
import asia.huangzhitao.missionAnswerBackend.model.entity.Post;
import javax.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 帖子服务测试
 *
 *
 */
@SpringBootTest
class PostServiceTest {

    @Resource
    private PostService postService;

}