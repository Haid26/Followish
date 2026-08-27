# Проект по автоматизации тестирования для сайта   [Followish](https://followish.io/)
<p align="center">
<img title="Followish" src="images/logo/followish.jpg"  alt="followish">  
</p>
## **Содержание**
____
* <a href="#tools">Технологии и инструменты</a>

* <a href="#cases">Тестовое покрытие</a>

* <a href="#console">Запуск из терминала</a>

* <a href="#jenkins">Сборка в Jenkins</a>

* <a href="#allure">Allure отчет</a>

* <a href="#testops">Интеграция с Allure TestOps

* <a href="#jira"> Интеграция с  Jira

* <a href="#telegram">Уведомление в Telegram при помощи бота</a>

* <a href="#video">Примеры видео выполнения тестов на Selenoid</a>


<a id="tools"></a>
## Технологии и инструменты

<p align="center">  
<a href="https://www.jetbrains.com/idea/"><img src="images/logo/Intelij_IDEA.svg" width="50" height="50"  alt="IDEA"/></a>  
<a href="https://www.java.com/"><img src="images/logo/Java.svg" width="50" height="50"  alt="Java"/></a>  
<a href="https://github.com/"><img src="images/logo/Github.svg" width="50" height="50"  alt="Github"/></a>  
<a href="https://junit.org/junit5/"><img src="images/logo/JUnit5.svg" width="50" height="50"  alt="JUnit 5"/></a>  
<a href="https://gradle.org/"><img src="images/logo/Gradle.svg" width="50" height="50"  alt="Gradle"/></a>  
<a href="https://selenide.org/"><img src="images/logo/Selenide.svg" width="50" height="50"  alt="Selenide"/></a>  
<a href="https://aerokube.com/selenoid/"><img src="images/logo/Selenoid.svg" width="50" height="50"  alt="Selenoid"/></a>  
<a href="https://allurereport.org/"><img src="images/logo/Allure.svg" width="50" height="50"  alt="Allure"/></a> 
<a href="https://qameta.io/"><img src="images/logo/Qameta.svg" width="50" height="50"  alt="Allure TestOps"/></a>   
<a href="https://www.jenkins.io/"><img src="images/logo/Jenkins.svg" width="50" height="50"  alt="Jenkins"/></a>
<a href="https://rest-assured.io/"><img src="images/logo/rest-assured.png" width="50" height="50"  alt="Jenkins"/></a>
<a href="https://www.atlassian.com/software/jira"><img src="images/logo/jira.svg" width="50" height="50"  alt="Jenkins"/></a>

</p>

<a id="cases"></a>
## Тестовое покрытие

> Разработаны автотесты на <code>UI</code> и <code>API</code>

### UI

- [x] Тестирование входа на сайт
- [x] Создание нового вишлиста
- [x] Проверка на обязательность имени вишлиста при создании
- [x] Редактирование вишлиста со страницы вишлиста
- [x] Редактирование вишлиста с домашней страницы пользователя
- [x] Удаление вишлиста со страницы вишлиста
- [x] Удаление вишлиста с домашней страницы пользователя

### API

- [x] Тестирование авторизации
- [x] Создание нового вишлиста
- [x] Получение списка всех вишлистов пользователя
- [x] Редактирование вишлиста
- [x] Удаление вишлиста
- [x] Редактирование несуществующего вишлиста
- [x] Удаление уже удаленного вишлиста

<a id="console"></a>
##  Запуск тестов из терминала

### Локальный запуск:
```
gradle clean test
```

<a id="jenkins"></a>
## <img alt="Jenkins" height="25" src="images/logo/Jenkins.svg" width="25"/></a><a name="Сборка"></a> Сборка в [Jenkins](https://jenkins.qa.guru/view/java-students/job/41-Karpov-diploma/)</a>

### **Параметры сборки в Jenkins:**

- *browser (браузер, по умолчанию chrome)*
- *browserVersion (версия браузера, по умолчанию 100.0)*
- *browserSize (размер окна браузера, по умолчанию 1920x1080)*
- *baseUrl (адрес тестируемого веб-сайта)*
- *remoteUrl (логин, пароль и адрес удаленного сервера Selenoid)*
- *apiUrl (эндпоит апи сервера)*

<a id="allure"></a>
## <img alt="Report" height="25" src="images/logo/Allure.svg" width="25"/></a><a name="Сборка"></a> [Allure отчет](https://jenkins.qa.guru/view/java-students/job/41-Karpov-diploma/13/allure)</a>

### *Основная страница отчёта*

<p align="center">  
<img title="Allure Overview Dashboard" src="images/screens/report_main.jpg" width="850">  
</p>

### *Тест-кейсы*

<p align="center">  
<img title="Allure Tests" src="images/screens/report_tests.jpg" width="850">  
</p>

### *Графики*

  <p align="center">  
<img title="Allure Graphics" src="images/screens/report_graphs.jpg" width="850">

<a id="testops"></a>
## <img alt="Testops" height="25" src="images/logo/Qameta.svg" width="25"/></a><a name="Сборка"></a> Интеграция с [Allure TestOps](https://allure.qa.guru/project/5369/dashboards)</a>

### *Дашборд*

<p align="center">  
<img title="Allure TestOps Dashboard" src="images/screens/testops_dashboard.jpg" width="850">  
</p>

### *Тест-кейсы*

<p align="center">  
<img title="Allure TestOps Tests" src="images/screens/testops_testcases.jpg" width="850">  
</p>

<a id="jira"></a>
## <img alt="Jira" height="25" src="images/logo/jira.svg" width="25"/></a><a name="Сборка"></a> Интеграция с [Attlassian Jira](https://jira.qa.guru/browse/MUL-43)</a>
____
<p align="center">  
<img title="Allure TestOps Tests" src="images/screens/jira.jpg" width="850">  
</p>

<a id="telegram"></a>
## <img height="25" src="images/logo/Telegram.svg" width="25"/></a> Уведомление в Telegram при помощи бота

> По результатам каждого прогона тестов в Jenkins отправляется сообщение в Telegram. Сообщение содержит информацию о прогоне, а также диаграмму со статистикой прохождения тестов.

<p align="center">  
<img title="Allure Overview Dashboard" src="images/screens/telegram.jpg" width="550">  
</p>

<a id="video"></a>
## <img alt="Selenoid" height="25" src="images/logo/Selenoid.svg" width="25"/></a> Примеры видео выполнения тестов на Selenoid
____
<p align="center">
<img title="Selenoid Video" src="images/video/edit_wishlist.gif" width="550" height="350"  alt="video">  
</p>