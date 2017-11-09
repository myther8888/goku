package com.yongle.goku.system.service.impl;

import com.yongle.goku.base.service.impl.BaseServiceImpl;
import com.yongle.goku.constant.Constants;
import com.yongle.goku.constant.ErrorEnum;
import com.yongle.goku.model.system.SysUser;
import com.yongle.goku.model.system.SysUserExample;
import com.yongle.goku.model.vo.ResultVO;
import com.yongle.goku.model.vo.system.UserVO;
import com.yongle.goku.system.mapper.SysUserMapper;
import com.yongle.goku.system.service.UserService;
import com.yongle.goku.utils.EntityUtils;
import com.yongle.goku.utils.redis.RedisUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
        ResultVO<UserVO> resultVO = new ResultVO<>(ErrorEnum.SUCCESS);
        userVO.convert2VO(users.get(0));
        userVO.setToken(tokenStr);
        redisUtils.hMSet(RedisUtils.RedisKey.getTokenKey(tokenStr), EntityUtils.objectToHash(users.get(0)));
        redisUtils.expire(RedisUtils.RedisKey.getTokenKey(tokenStr), 60 * 60, TimeUnit.SECONDS);
        resultVO.setData(userVO);
        return resultVO;
    }

    @Override
    public ResultVO logout(UserVO userVO) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return null;
    }

    private String getPassword(UserVO userVO) {
        Md5Hash md5Hash = new Md5Hash(userVO.getUsername() + userVO.getPassword(), Constants.PASSWORD_SALT);
        return md5Hash.toString();
    }

}
