package com.hololeenko.task_4.model.mapper;

import com.hololeenko.task_4.model.entity.AbstractEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface EntityMapper <T extends AbstractEntity> {
    T map(ResultSet rs) throws SQLException;
}
