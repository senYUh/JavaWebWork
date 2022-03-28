package com.senyang.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senyang.boot.entity.UserMibao;
import com.senyang.boot.mapper.UserMibaoMapper;
import com.senyang.boot.service.UserMibaoService;
import org.springframework.stereotype.Service;

@Service
public class UserMibaoServiceImpl extends ServiceImpl<UserMibaoMapper, UserMibao> implements UserMibaoService {
}
