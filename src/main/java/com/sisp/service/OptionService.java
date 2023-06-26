package com.sisp.service;

import com.sisp.dao.OptionEntityMapper;
import com.sisp.entity.OptionEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.UUID;

@Service
public class OptionService {
    private final static Logger logger = LoggerFactory.getLogger(OptionService.class);
    @Resource
    OptionEntityMapper optionEntityMapper;
    @Transactional(propagation = Propagation.REQUIRED)
    public OptionEntity addOptionInfo(OptionEntity option, String currentUserId,
                                      long currentTime) throws Exception {
        option.setId("o" + UUID.randomUUID());
        option.setCreatedBy(currentUserId);
        option.setCreationDate(new java.sql.Date(currentTime));
        option.setLastUpdatedBy(currentUserId);
        option.setLastUpdateDate(
                new java.sql.Date(currentTime));
        try {
            int effectRow = optionEntityMapper.insertOption(
                    option);
            if (effectRow != 1)
                throw new SQLException("插入失败");
        } catch (Exception e) {
            logger.error(
                    "Error : optionEntityMapper.insertOption(option)\n" + option);
            logger.error(e.getMessage());
            throw e;
        }
        return option;
    }
}
