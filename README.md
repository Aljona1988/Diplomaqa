# Процедура запуска автотестов:

## Запуск контейнеров через Docker (скачать при необходимости) для  MySQl, PostgerSQL и Node.js через терминал в IDE по команде:
 **docker-compose up** 
 
## Запуск SUT по команде через терминал IDE:
 для БД MySQL: **start_mysql.cmd**
 для БД Postgres: **start_postgres.cmd**
 
 - Приложение запускается на порту 8080:
 http://localhost:8080/
 
## Запуск тестов ко манде через терминал IDE:
 для БД MySQL: **start_test_mysql.cmd**
 для БД Postgres: **start_test_postgres.cmd**
 
## Запуск репортинга (Allure) по команде в терминале IDE:
**gradlew allureServe**

## Завершение работы после окончания тестов по команде в термнале IDE:
**Ctrl+C**

## Остановка контейнеров по команде в термнале IDE:
**docker-compose down**

## Демонстрация:



https://user-images.githubusercontent.com/110630229/219972390-f07997ff-3bd9-4395-b67b-58bff64a49ae.mp4

