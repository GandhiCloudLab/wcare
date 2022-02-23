package com.pro.wealth.model;

import java.util.Date;

public interface WcGoal {

    public int getId() ;

    public void setId(int id);

    public int getWcCustomerId();

    public void setWcCustomerId(int wcCustomerId);

    public int getWcWealthManagerId();

    public void setWcWealthManagerId(int wcWealthManagerId);
    public String getGoalReference();

    public void setGoalReference(String goalReference);

    public String getGoalDesc();

    public void setGoalDesc(String goalDesc);

    public Date getStartDate();

    public void setStartDate(Date startDate);

    public Date getTargetDate();

    public void setTargetDate(Date targetDate);

    public double getTargetAmount();

    public void setTargetAmount(double targetAmount);
}

