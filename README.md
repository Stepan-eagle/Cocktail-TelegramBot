# Cocktail-TelegramBot

##Idea
The main idea is to create real application, which can be used by someone else. 

# How it works 
- User can find information about cocktails
- User can find cocktails by ingredients
- User can subscribe

## Find new posts workflow
The workflow of finding cocktails by schedule or by name and ingredients :
![workflow](bpmn.png)

## Deployment
Deployment process as easy as possible:
Required software:
- terminal for running bash scripts
- docker
- docker-compose

to deploy application, switch to needed branch and run bash script:
```shell
bash start.sh
```
That's all.

# Local development
For local development and testing, use `docker-compose-test.yml`.
Run command:
```shell
docker-compose -f docker-compose-test.yml up
```

And add VM Options:

`-Dspring.profiles.active=test `

With these configurations - run SpringBoot main method.

# Technological stack
- SpringBoot as a skeleton framework
- Spring Scheduler as a task manager
- MySQL database as a database for saving user and subscription info
- Flyway database migration tool
- Telegram-bot SpringBoot starter
- Spring Data starter