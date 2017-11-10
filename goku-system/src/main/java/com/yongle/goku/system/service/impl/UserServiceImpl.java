package com.yongle.goku.system.service.impl;

import com.yongle.goku.base.service.impl.BaseServiceImpl;
import com.yongle.goku.constant.ErrorEnum;
import com.yongle.goku.model.system.SysUser;
import com.yongle.goku.model.system.SysUserExample;
import com.yongle.goku.model.vo.ResultVO;
import com.yongle.goku.model.vo.system.UserVO;
import com.yongle.goku.system.mapper.SysUserMapper;
import com.yongle.goku.system.service.UserService;
import com.yongle.goku.utils.EntityUtils;
import com.yongle.goku.utils.redis.RedisUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


/**
 * @author weinh
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserVO> implements UserService {
    @Resource
    SysUserMapper userMapper;

    @Override
    public ResultVO login(UserVO userVO) {
        SysUserExample example = new SysUserExample();
        example.createCriteria().andUsernameEqualTo(userVO.getUsername()).andPasswordEqualTo(getPassword(userVO));
        List<SysUser> users = userMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(users) || 1 != users.size()) {
            return new ResultVO(ErrorEnum.LOGIN_ERROR);
        }
        String tokenStr = UUID.randomUUID().toString();
        userVO = new UserVO();
        userVO.convert2VO(users.get(0));
        userVO.setToken(tokenStr);
        redisUtils.hMSet(RedisUtils.RedisKey.getUserTokenKey(tokenStr), EntityUtils.objectToHash(userVO));
        redisUtils.expire(RedisUtils.RedisKey.getUserTokenKey(tokenStr), 1L, TimeUnit.DAYS);

        ResultVO<UserVO> resultVO = new ResultVO<>(ErrorEnum.SUCCESS);
        resultVO.setData(userVO);
        return resultVO;
    }

    @Override
    public ResultVO logout(UserVO userVO) {
        Subject subject = SecurityUtils.getSubject();
        redisUtils.del(RedisUtils.RedisKey.getUserTokenKey(userVO.getToken()));
        subject.logout();
        return new ResultVO(ErrorEnum.SUCCESS);
    }

    private String getPassword(UserVO userVO) {
        Md5Hash md5Hash = new Md5Hash(userVO.getPassword(), new Md5Hash(userVO.getUsername()));
        return md5Hash.toString();
    }

}
