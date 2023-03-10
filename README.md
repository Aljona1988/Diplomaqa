# Процедура запуска автотестов:

## Запуск контейнеров

Через Docker (скачать при необходимости) для  MySQl, PostgerSQL и Node.js через терминал в IDE по команде:
 **docker-compose up** 
 
## Запуск SUT:

по команде через терминал IDE:
- для БД MySQL: **java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar**
- для БД Postgres: **java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar artifacts/aqa-shop.jar**
 
## Приложение запускается на порту 8080:
 http://localhost:8080/
 
## Запуск тестов:

По команде через терминал IDE:
-  для БД MySQL: **./gradlew clean test "-Durl=jdbc:mysql://localhost:3306/app"**
-  для БД Postgres: **./gradlew clean test "-Durl=jdbc:postgresql://localhost:5432/app"**
 
## Запуск репортинга (Allure):

По команде в терминале IDE:
**gradlew allureServe**

## Завершение работы:

После окончания тестов по команде в термнале IDE:
**Ctrl+C**

## Остановка контейнеров:

По команде в термнале IDE:
**docker-compose down**

## Документация:

 - [План автоматизации](https://github.com/Aljona1988/Diplomaqa/blob/master/docs/Plan.md)
 - [Отчет по итогам тестирования](https://github.com/Aljona1988/Diplomaqa/blob/master/docs/Report.md)
 - [Отчет по итогам автоматизации](https://github.com/Aljona1988/Diplomaqa/blob/master/docs/Summary.md)

## Демонстрация:

https://user-images.githubusercontent.com/110630229/219972390-f07997ff-3bd9-4395-b67b-58bff64a49ae.mp4

