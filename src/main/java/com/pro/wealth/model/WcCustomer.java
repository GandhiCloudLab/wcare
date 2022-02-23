package com.pro.wealth.model;

import java.util.Date;

public interface WcCustomer {

    public int getId() ;

    public void setId(int id) ;

    public int getWcUserId() ;

    public void setWcUserId(int wcUserId);

    public int getWcWealthManagerId();

    public void setWcWealthManagerId(int wcWealthManagerId);

    public String getFirstName();

    public void setFirstName(String firstName);

    public String getLastName();

    public void setLastName(String lastName);

    public String getGender();

    public void setGender(String gender);

    public int getAge();

    public void setAge(int age);

    public double getAvgIncome();
    public void setAvgIncome(double avgIncome);

    public boolean isMarried();

    public void setMarried(boolean married);

    public String getSpouseFirstName();

    public void setSpouseFirstName(String spouseFirstName);

    public String getSpouseLastName();

    public void setSpouseLastName(String spouseLastName);
    public String getSpouseGender();

    public void setSpouseGender(String spouseGender);

    public int getSpouseAge();

    public void setSpouseAge(int spouseAge);

    public double getSpouseAvgIncome();

    public void setSpouseAvgIncome(double spouseAvgIncome);

    public int getNoOfChildren();

    public void setNoOfChildren(int noOfChildren);

    public String getChild1FirstName();

    public void setChild1FirstName(String child1FirstName);

    public String getChild1LastName();
    public void setChild1LastName(String child1LastName);

    public String getChild1Gender();

    public void setChild1Gender(String child1Gender);

    public int getChild1Age();

    public void setChild1Age(int child1Age);

    public String getChild2FirstName();

    public void setChild2FirstName(String child2FirstName);

    public String getChild2LastName();
    public void setChild2LastName(String child2LastName);

    public String getChild2Gender();

    public void setChild2Gender(String child2Gender);

    public int getChild2Age();

    public void setChild2Age(int child2Age);

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

    public String getWealthManagerName();

    public void setWealthManagerName(String wealthManagerName);
}
