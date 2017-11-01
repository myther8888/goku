package com.yongle.goku.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yongle.goku.base.service.impl.BaseServiceImpl;
import com.yongle.goku.constant.Constants;
import com.yongle.goku.constant.ErrorEnum;
import com.yongle.goku.model.system.SysUser;
import com.yongle.goku.model.system.SysUserExample;
import com.yongle.goku.model.vo.Page;
import com.yongle.goku.model.vo.ResultVO;
import com.yongle.goku.model.vo.system.UserVO;
import com.yongle.goku.system.mapper.SysUserMapper;
import com.yongle.goku.system.service.UserService;
import com.yongle.goku.utils.EntityUtils;
import com.yongle.goku.utils.redis.RedisUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.cache.annotation.Cacheable;
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
    public ResultVO findAll(UserVO userVO, UserVO currentUser) {
        Page page = userVO.getPage();
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<SysUser> users = userMapper.selectByExample(new SysUserExample());
        PageInfo<SysUser> pageInfo = new PageInfo<>(users);
        Page<SysUser> a = new Page<>(pageInfo);
        ResultVO<Page<SysUser>> resultVO = new ResultVO<>(ErrorEnum.SUCCESS);
        resultVO.setData(a);
        return resultVO;
    }

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

    private String getPassword(UserVO userVO) {
        Md5Hash md5Hash = new Md5Hash(userVO.getUsername() + userVO.getPassword(), Constants.PASSWORD_SALT);
        return md5Hash.toString();
    }

    @Override
    public ResultVO findOne(Long id, UserVO currentUser) {
        SysUser user = userMapper.selectByPrimaryKey(id);
        ResultVO<UserVO> resultVO = new ResultVO<>(ErrorEnum.SUCCESS);
        UserVO userVO = new UserVO();
        userVO.convert2VO(user);
        resultVO.setData(userVO);
        return resultVO;
    }

    @Override
    public ResultVO update(Long id, UserVO userVO, UserVO currentUser) {
        userVO.setId(id);
        userVO.setUsername(null);
        userMapper.updateByPrimaryKeySelective(userVO);
        return new ResultVO(ErrorEnum.SUCCESS);
    }


    @Cacheable(value = "default")
    @Override
    public String getAbc() {
        return "def";
    }
}
