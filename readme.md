# WealthPlan  

## Starting App

1. Download this repo content as zip

2. Unzip the file

3. Goto`war `folder

```
cd war
```

4. Run the war using the below commnad

```
java -jar wealthplan-service-0.0.1-SNAPSHOT.war
```

Now the application should be starting and running at the port 9020.

## Accessing App

To access the applicaiton access the below url in the browser.

```
http://localhost:9020/
```

The user and password details are

```
sam/sam
eva/eva
harry/harry
```

## Screen Details

The detailed screenshots are given in [Screen details....](./readme-screens.md)

## Making Changes to the app

1. Make changes to the files anywhere.

2. Run the below command to compile it using maven
```
      mvn clean package
```
3. You will get the war file `target/wealthplan-service-0.0.1-SNAPSHOT.war` created.
    
4. Run the app
```
     java -jar target/wealthplan-service-0.0.1-SNAPSHOT.war
```
