package com.example.demo.service.student.impl;

import com.example.demo.dao.student.ShowCaseDao;
import com.example.demo.entity.student.Showcase;
import com.example.demo.service.student.IShowcaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowcaseService implements IShowcaseService {
    @Autowired
    ShowCaseDao showcaseDao;

    @Override
    public void insertShowcase(Showcase showcase, String tableName) {
        showcaseDao.insertShowcase( showcase, tableName );
    }

    @Override
    public void updateShowcase(Showcase showcase, String tableName) {
        showcaseDao.updateShowcase( showcase, tableName );
    }

    @Override
    public void deleteShowcase(String moduleType, String imgId, String tableName) {
        showcaseDao.deleteShowcase( moduleType, imgId, tableName );
    }

    @Override
    public List<Showcase> queryShowcase(String moduleType, String imgId, String tableName) {
        return showcaseDao.queryShowcase( moduleType, imgId, tableName );
    }

    @Override
    public List<Showcase> queryShowcases(String tableName) {
        return showcaseDao.queryShowcases( tableName );
    }
}
