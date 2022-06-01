# current-exchange-rate
spring boot rest feign giphy openexchangerates mockito

To run the application locally:
```
git clone https://github.com/MironovAlexanderJR/current-exchange-rate.git
cd current-exchange-rate
.\gradlew bootRun
```

To create a docker image and run it:
```
git clone https://github.com/MironovAlexanderJR/current-exchange-rate.git
cd current-exchange-rate
.\gradlew build
docker build -t current-exchange-rate .
docker run -p 8080:8080 current-exchange-rate
```