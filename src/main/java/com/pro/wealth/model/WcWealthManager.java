package com.pro.wealth.model;

import java.util.Date;

public interface WcWealthManager {

    public int getId();

    public void setId(int id);

    public int getWcUserId();

    public void setWcUserId(int wcUserId);

    public String getFirstName();

    public void setFirstName(String firstName);

    public String getLastName();

    public void setLastName(String lastName);

    public String getGender();

    public void setGender(String gender);

    public String getCity();

    public void setCity(String city);

    public String getPhone();

    public void setPhone(String phone);

    public String getEmailId();

    public void setEmailId(String emailId);

    public Date getStartDate();

    public void setStartDate(Date startDate);

    public String getCountry();

    public void setCountry(String country);

    public String getZipCode();
    public void setZipCode(String zipCode);
    public String getStartDateString();
    public void setStartDateString(String startDateString);

    public String getImage();

    public void setImage(String image);
}
