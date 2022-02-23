--  This sql is executed only when H2 db is enabled in application.properties. This will not be executed for DB2.
--  This sql file should be available in both wealthusers and wealthfinancialplan services

--    delete from Wcusers;
--    ALTER TABLE Wcusers ALTER COLUMN id RESTART WITH 10001;
    insert into Wcusers (UserName, Password, EmailId, Role) values ('harry','harry','harry@wcare.com', 'BM');
    insert into Wcusers (UserName, Password, EmailId, Role) values ('charlie','charlie','charlie@wcare.com', 'BM');
    insert into Wcusers (UserName, Password, EmailId, Role) values ('oliver','oliver','oliver@wcare.com', 'BM');
    insert into Wcusers (UserName, Password, EmailId, Role) values ('ananth','ananth','ananth@wcare.com', 'WM');
    insert into Wcusers (UserName, Password, EmailId, Role) values ('aish','aish','aish@wcare.com', 'WM');
    insert into Wcusers (UserName, Password, EmailId, Role) values ('anu','anu','anu@wcare.com', 'WM');
    insert into Wcusers (UserName, Password, EmailId, Role) values ('siva','siva','siva@wcare.com', 'WM');
    insert into Wcusers (UserName, Password, EmailId, Role) values ('sangeetha','sangeetha','sangeetha@wcare.com', 'WM');
    insert into Wcusers (UserName, Password, EmailId, Role) values ('sam','sam','sam@wcare.com', 'WM');
    insert into Wcusers (UserName, Password, EmailId, Role) values ('nathan','nathan','nathan@wcare.com', 'CU');
    insert into Wcusers (UserName, Password, EmailId, Role) values ('sandy','sandy','sandy@wcare.com', 'CU');
    insert into Wcusers (UserName, Password, EmailId, Role) values ('sharma','sharma','sharma@wcare.com', 'CU');
    insert into Wcusers (UserName, Password, EmailId, Role) values ('ram','ram','ram@wcare.com', 'CU');
    insert into Wcusers (UserName, Password, EmailId, Role) values ('deepah2','deepa','deepa@wcare.com', 'CU');
    insert into Wcusers (UserName, Password, EmailId, Role) values ('tom','tom','tom@wcare.com', 'CU');

--    delete from WcBusinessManager;
--    ALTER TABLE WcBusinessManager ALTER COLUMN id RESTART WITH 20001;
    insert into WcBusinessManager (WcUserId, FirstName, LastName, StartDate) values (10001, 'Harry', 'Jack', '2012-02-01');
    insert into WcBusinessManager (WcUserId, FirstName, LastName, StartDate) values (10002, 'Charlie', 'Jacob', '2013-02-01');
    insert into WcBusinessManager (WcUserId, FirstName, LastName, StartDate) values (10003, 'Oliver', 'Noah', '2014-02-01');


--    delete from WcWealthManager;
--    ALTER TABLE WcWealthManager ALTER COLUMN id RESTART WITH 30001;
    insert into WcWealthManager (WcUserId, FirstName, LastName, Gender, City, Phone, EmailId, StartDate, Country, ZipCode)
            values (10004, 'Ananth Kumar', 'Naga', 'Male', 'Bangalore', '423234323', 'ananth@wcare.com', '2014-02-01', 'IN', '530068');
    insert into WcWealthManager (WcUserId, FirstName, LastName, Gender, City, Phone, EmailId, StartDate, Country, ZipCode)
            values (10005, 'Aishwaryaa', 'Roi', 'Female', 'Chennai', '564232323', 'aish@wcare.com', '2014-07-01', 'IN', '600001');
    insert into WcWealthManager (WcUserId, FirstName, LastName, Gender, City, Phone, EmailId, StartDate, Country, ZipCode)
            values (10006, 'Anuradha', 'Gupta', 'Female', 'Bangalore', '754234323', 'anu@wcare.com', '2015-09-01', 'IN', '530068');
    insert into WcWealthManager (WcUserId, FirstName, LastName, Gender, City, Phone, EmailId, StartDate, Country, ZipCode)
            values (10007, 'Siva Rama ', 'Krishnan', 'Male', 'Mysore', '344234323', 'siva@wcare.com', '2017-02-01', 'IN', '570001');
    insert into WcWealthManager (WcUserId, FirstName, LastName, Gender, City, Phone, EmailId, StartDate, Country, ZipCode)
            values (10008, 'Sangeetha', 'Vega', 'Female', 'Pune', '4523234323', 'sangeetha@wcare.com', '2018-02-01', 'IN', '111045');
    insert into WcWealthManager (WcUserId, FirstName, LastName, Gender, City, Phone, EmailId, StartDate, Country, ZipCode)
            values (10009, 'Sam', 'Kim', 'Female', 'Raleigh', '3432332', 'sam@wcare.com', '2018-02-01', 'US', '27513');


--    delete from WcCustomer;
--    ALTER TABLE WcCustomer ALTER COLUMN id RESTART WITH 40001;
    insert into WcCustomer (WcUserId, WcWealthManagerId, FirstName, LastName, Gender, Age, AvgIncome, Married, SpouseFirstName, SpouseLastName, SpouseGender, SpouseAge, SpouseAvgIncome, NoOfChildren, Child1FirstName, Child1LastName, Child1Gender, Child1Age, Child2FirstName, Child2LastName, Child2Gender, Child2Age, City, Phone, EmailId, StartDate, Country, ZipCode)
           values (10010, 30001, 'Nathan', 'Kumar', 'Male', 32, 1000000, true, 'Vimala', 'Kumar', 'Female', 28, 0,
                    1, 'Viswa', 'Ram', 'Male', 2, '', '', '', 0 ,'Bangalore', '2309439084', 'nathan@wcare.com', '2015-02-01', 'IN', '530068');
    insert into WcCustomer (WcUserId, WcWealthManagerId, FirstName, LastName, Gender, Age, AvgIncome, Married, SpouseFirstName, SpouseLastName, SpouseGender, SpouseAge, SpouseAvgIncome, NoOfChildren, Child1FirstName, Child1LastName, Child1Gender, Child1Age, Child2FirstName, Child2LastName, Child2Gender, Child2Age, City, Phone, EmailId, StartDate, Country, ZipCode)
           values (10011, 30001, 'Sandy', 'Prem', 'Male', 38, 1500000, true, 'Roshini', 'Prem', 'Female', 33, 0,
                    1, 'Kesav', 'Venu', 'Male', 2, '', '', '', 0 , 'Bangalore', '3509439084', 'sandy@wcare.com', '2015-07-01', 'IN', '530068');
    insert into WcCustomer (WcUserId, WcWealthManagerId, FirstName, LastName, Gender, Age, AvgIncome, Married, SpouseFirstName, SpouseLastName, SpouseGender, SpouseAge, SpouseAvgIncome, NoOfChildren, Child1FirstName, Child1LastName, Child1Gender, Child1Age, Child2FirstName, Child2LastName, Child2Gender, Child2Age, City, Phone, EmailId, StartDate, Country, ZipCode)
           values (10012, 30002, 'Sharma', 'Rohit', 'Male', 39, 1700000, true, 'Sheela', 'Rohit', 'Female', 34, 1000000,
                    2, 'Kalai', 'Malai', 'Male', 3, '', '', '', 0 , 'Bangalore', '8909439084', 'sharma@wcare.com', '2016-02-01', 'IN', '530068');
    insert into WcCustomer (WcUserId, WcWealthManagerId, FirstName, LastName, Gender, Age, AvgIncome, Married, SpouseFirstName, SpouseLastName, SpouseGender, SpouseAge, SpouseAvgIncome, NoOfChildren, Child1FirstName, Child1LastName, Child1Gender, Child1Age, Child2FirstName, Child2LastName, Child2Gender, Child2Age, City, Phone, EmailId, StartDate, Country, ZipCode)
           values (10013, 30003, 'Ram', 'Saran', 'Male', 35, 1400000, true, 'Lakshmi', 'Saran', 'Female', 31, 800000,
                    2, 'Alex', 'Kum', 'Male', 3, 'Eva', 'Jam', 'Female', 2, 'Chennai', '7509439084', 'ram@wcare.com', '2017-02-01', 'IN', '600001');
    insert into WcCustomer (WcUserId, WcWealthManagerId, FirstName, LastName, Gender, Age, AvgIncome, Married, SpouseFirstName, SpouseLastName, SpouseGender, SpouseAge, SpouseAvgIncome, NoOfChildren, Child1FirstName, Child1LastName, Child1Gender, Child1Age, Child2FirstName, Child2LastName, Child2Gender, Child2Age, City, Phone, EmailId, StartDate, Country, ZipCode)
           values (10014, 30004, 'Deepa', 'Kumar', 'Female', 32, 1000000, true, 'Veera', 'Kumar', 'Male', 38, 1600000,
                    1, 'Vishnu', 'Venk', 'Male', 3, 'Palani', 'Kum', 'Male', 2, 'Pune', '659439084', 'deepa@wcare.com', '2017-05-01', 'IN', '111045');
    insert into WcCustomer (WcUserId, WcWealthManagerId, FirstName, LastName, Gender, Age, AvgIncome, Married, SpouseFirstName, SpouseLastName, SpouseGender, SpouseAge, SpouseAvgIncome, NoOfChildren, Child1FirstName, Child1LastName, Child1Gender, Child1Age, Child2FirstName, Child2LastName, Child2Gender, Child2Age, City, Phone, EmailId, StartDate, Country, ZipCode)
           values (10015, 30006, 'Tom', 'Alex', 'Male', 32, 1000000, true, 'Claire', 'Alex', 'Female', 38, 1600000,
                    1, 'Tom', 'Peter', 'Male', 3,  '', '', '', 0 , 'New York', '659439084', 'tom@wcare.com', '2017-05-01', 'US', '27513');


--    delete from WcGoal;
--    ALTER TABLE WcGoal ALTER COLUMN id RESTART WITH 50001;
    insert into WcGoal (WcCustomerId, WcWealthManagerId, GoalReference, GoalDesc, StartDate, TargetDate, TargetAmount)
           values (40001, 30001, '1', 'Buy a House in a Villa Gated Community', '2015-05-01', '2025-05-01', 27000000);
    insert into WcGoal (WcCustomerId, WcWealthManagerId, GoalReference, GoalDesc, StartDate, TargetDate, TargetAmount)
           values (40001, 30001, '2', 'Travel to Europe', '2016-05-01', '2026-05-01', 9000000);
    insert into WcGoal (WcCustomerId, WcWealthManagerId, GoalReference, GoalDesc, StartDate, TargetDate, TargetAmount)
           values (40001, 30001, '3', 'Save for Child’s Higher Education H2', '2017-05-01', '2027-05-01', 8000000);
    insert into WcGoal (WcCustomerId, WcWealthManagerId, GoalReference, GoalDesc, StartDate, TargetDate, TargetAmount)
           values (40002, 30001, '1', 'Buy a Flat H2', '2015-05-01', '2025-05-01', 6000000);
    insert into WcGoal (WcCustomerId, WcWealthManagerId, GoalReference, GoalDesc, StartDate, TargetDate, TargetAmount)
           values (40002, 30001, '2', 'Travel to USA', '2017-05-01', '2027-05-01', 5000000);
    insert into WcGoal (WcCustomerId, WcWealthManagerId, GoalReference, GoalDesc, StartDate, TargetDate, TargetAmount)
           values (40003, 30002, '1', 'Higher Education', '2017-05-01', '2027-05-01', 4000000);
    insert into WcGoal (WcCustomerId, WcWealthManagerId, GoalReference, GoalDesc, StartDate, TargetDate, TargetAmount)
           values (40004, 30003, '1', 'Travel Abroad', '2017-05-01', '2027-05-01', 6000000);
    insert into WcGoal (WcCustomerId, WcWealthManagerId, GoalReference, GoalDesc, StartDate, TargetDate, TargetAmount)
           values (40006, 30006, '1', 'Travel to Asia', '2017-05-01', '2027-05-01', 7000000);
    insert into WcGoal (WcCustomerId, WcWealthManagerId, GoalReference, GoalDesc, StartDate, TargetDate, TargetAmount)
           values (40001, 30001, '4', 'Travel to USA', '2019-05-01', '2022-05-01', 800000);
    insert into WcGoal (WcCustomerId, WcWealthManagerId, GoalReference, GoalDesc, StartDate, TargetDate, TargetAmount)
           values (40001, 30001, '5', 'Buy a Flat H2', '2022-05-01', '2025-05-01', 800000);
    insert into WcGoal (WcCustomerId, WcWealthManagerId, GoalReference, GoalDesc, StartDate, TargetDate, TargetAmount)
           values (40001, 30001, '6', 'Buy a BMW', '2019-06-01', '2026-05-01', 3800000);
    insert into WcGoal (WcCustomerId, WcWealthManagerId, GoalReference, GoalDesc, StartDate, TargetDate, TargetAmount)
           values (40001, 30001, '7', 'Travel to Asia', '2019-07-01', '2027-05-01', 7000000);

--    delete from WcInvestment;
--    ALTER TABLE WcInvestment ALTER COLUMN id RESTART WITH 60001;
    insert into WcInvestment (WcGoalId, InvestmentDate, InvestmentAmount, StockAmount, MutualFundAmount, FixedDepositAmount)
           values (50001, '2015-05-01', 400000, 100000, 200000, 100000);
    insert into WcInvestment (WcGoalId, InvestmentDate, InvestmentAmount, StockAmount, MutualFundAmount, FixedDepositAmount)
           values (50001, '2015-06-01', 600000, 310000, 200000, 100000);
    insert into WcInvestment (WcGoalId, InvestmentDate, InvestmentAmount, StockAmount, MutualFundAmount, FixedDepositAmount)
           values (50001, '2015-07-01', 500000, 100000, 200000, 200000);
    insert into WcInvestment (WcGoalId, InvestmentDate, InvestmentAmount, StockAmount, MutualFundAmount, FixedDepositAmount)
           values (50001, '2016-06-01', 400000, 100000, 200000, 100000);
    insert into WcInvestment (WcGoalId, InvestmentDate, InvestmentAmount, StockAmount, MutualFundAmount, FixedDepositAmount)
           values (50001, '2017-05-01', 500000, 200000, 200000, 100000);
    insert into WcInvestment (WcGoalId, InvestmentDate, InvestmentAmount, StockAmount, MutualFundAmount, FixedDepositAmount)
           values (50001, '2017-06-01', 300000, 100000, 100000, 100000);
    insert into WcInvestment (WcGoalId, InvestmentDate, InvestmentAmount, StockAmount, MutualFundAmount, FixedDepositAmount)
           values (50002, '2016-05-01', 300000, 100000, 100000, 100000);
    insert into WcInvestment (WcGoalId, InvestmentDate, InvestmentAmount, StockAmount, MutualFundAmount, FixedDepositAmount)
           values (50002, '2016-05-01', 700000, 300000, 200000, 300000);
    insert into WcInvestment (WcGoalId, InvestmentDate, InvestmentAmount, StockAmount, MutualFundAmount, FixedDepositAmount)
           values (50002, '2016-06-01', 400000, 100000, 200000, 100000);
    insert into WcInvestment (WcGoalId, InvestmentDate, InvestmentAmount, StockAmount, MutualFundAmount, FixedDepositAmount)
           values (50003, '2017-05-01', 500000, 200000, 200000, 100000);
    insert into WcInvestment (WcGoalId, InvestmentDate, InvestmentAmount, StockAmount, MutualFundAmount, FixedDepositAmount)
           values (50004, '2015-05-01', 400000, 200000, 100000, 100000);
    insert into WcInvestment (WcGoalId, InvestmentDate, InvestmentAmount, StockAmount, MutualFundAmount, FixedDepositAmount)
           values (50005, '2017-05-01', 300000, 100000, 100000, 100000);
    insert into WcInvestment (WcGoalId, InvestmentDate, InvestmentAmount, StockAmount, MutualFundAmount, FixedDepositAmount)
           values (50006, '2017-05-01', 400000, 100000, 100000, 200000);
    insert into WcInvestment (WcGoalId, InvestmentDate, InvestmentAmount, StockAmount, MutualFundAmount, FixedDepositAmount)
           values (50007, '2017-05-01', 700000, 200000, 300000, 200000);
    insert into WcInvestment (WcGoalId, InvestmentDate, InvestmentAmount, StockAmount, MutualFundAmount, FixedDepositAmount)
           values (50007, '2017-06-01', 300000, 100000, 100000, 100000);
    insert into WcInvestment (WcGoalId, InvestmentDate, InvestmentAmount, StockAmount, MutualFundAmount, FixedDepositAmount)
           values (50008, '2017-05-01', 400000, 100000, 200000, 100000);
    insert into WcInvestment (WcGoalId, InvestmentDate, InvestmentAmount, StockAmount, MutualFundAmount, FixedDepositAmount)
           values (50008, '2017-06-01', 300000, 100000, 100000, 100000);