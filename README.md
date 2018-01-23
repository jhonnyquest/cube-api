# Java technology solution for Cube Summation challenge

## Application architecture:
[![Cube-_Summation-_Architecture.png](https://s17.postimg.org/ce68oghhb/Cube-_Summation-_Architecture.png)](https://postimg.org/image/az4nzqge3/)

**FrontEnd**
- View layer: Users interface developed with HTML5 and Bootstrap
- View Controller layer: Manage data renderization and proxy to API/backend layer using Javascript and Jquery

**BackEnd**
- API Endpoints: RESFUL interphase that works like a proxy between frontEnd and cube manager that generates process to resolve clients request.
- Cube Manager: Its responsability is to implements whole operations and resolve clients request, it uses the persistence helper to store and retrieve data.
- Persistence Helper: Responsible to manage all persistence operations.
- App Connfig: Abstract and centralize all configuration parameters.
- Exception Handler: Custom exception manager that catch format el return controlled exceptions.
- Model: Contains whole Business Object models and Support models.
- Home Controller: Dispatch frontend from java servlet controller.

**Unit Test**
- JUnit

**Deployment**
- Create ppersistence path in "/tmp/cube/" (you also can change in App Config module).
- Maven Clean.
- Maven Installl.
- Copy .war file to tomcat webapps folder.
