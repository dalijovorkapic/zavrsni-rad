package com.fer.hr.zavrsni.dao;

import com.fer.hr.zavrsni.model.ScheduleAction;

import java.util.List;

public interface ScheduleDao {

    int insertSch(ScheduleAction sch, int user_id);

    List<ScheduleAction> getSch(int user_id);

    int deleteSch(int id);

    int updateSch(int id, ScheduleAction newEvent);
}
