# Task-Management


```
    Регистрация проходит через JWT токен
```
## Токен
```
{
    "email" : "email@example.com",
    "password" : "123123"
}
```
```
    Пару токенов для облегчения проверки))

1. eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6InVnYXJpbmtvM0BpY2xvdWQuY29tIiwicGFzc3dvcmQiOjE1MTYyMzkwMjJ9.M5Q4XoGdHha1DDtWhUZ7zGX6SCQnDwXjUrEMxMOVaxo
2. eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6ImVsaWVlbmVAZ21haWwuY29tIiwicGFzc3dvcmQiOjE1MTYyMzkwMjJ9.uB7Yb9hN3lf1zXzyX9JF0PwmxfcvOx3ilRJ115pwWP0
3. eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6IndoaXRlcnVuQGdtYWlsLmNvbSIsInBhc3N3b3JkIjoiUXdlcnR5MTIzNDU2In0.sBGsBCWdS616eldnGdlg7PQkB9mElJs-pfsYBQJvA68
```
## Запуск проекта


### Версии
```
 OpenJDK v.17  
 Maven v.3.9.9
```


```bash 
mvn clean package
```
```bash
docker-compose up --build
```

/v3/api-docs  - в поиске swagger<br>
[Открыть Swagger UI](http://localhost:8081/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config)