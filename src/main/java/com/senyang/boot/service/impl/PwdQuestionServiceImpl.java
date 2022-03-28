package com.senyang.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senyang.boot.entity.PwdQuestion;
import com.senyang.boot.mapper.PwdQuestionMapper;
import com.senyang.boot.service.PwdQuestionService;
import org.springframework.stereotype.Service;

@Service
public class PwdQuestionServiceImpl extends ServiceImpl<PwdQuestionMapper, PwdQuestion> implements PwdQuestionService {
}
