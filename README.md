
# Krypt <img width="60" align="center" src="https://github.com/princeku07/Krypto_Android_App/blob/UI/app/src/main/res/drawable/logo.png" />
Crypto Currency maket news and Currency information app.



## Tech Stack

- Jetpack Compose
- Retrofit
- Dependency Injection (Hilt)
- Lottie animation
- Glide for image loading
 #### Architecture ####
- Clean Architecture
- MVVM


## Screenshots

<p float="left">
  <img height="300" width="150" src="https://github.com/princeku07/Krypto_Android_App/blob/UI/app_part0.gif" />
<img height="300" width="150" src="https://github.com/princeku07/Krypto_Android_App/blob/UI/app_part1.gif" />
<img height="300" width="150" src="https://github.com/princeku07/Krypto_Android_App/blob/UI/app_part2.gif" />
<img height="300" width="150" src="https://github.com/princeku07/Krypto_Android_App/blob/UI/app_part3.gif" />

</p>


## api.coinpaprika.com/v1/ (Base Url)

#### Get all items

```http
  GET /tickers/
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `api_key` | `string` | **Not Required** |

- Returns total available coins.

#### Get item

```http
  GET /tickers/{id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of item to fetch |

- Returns details of id.



#### Get news
```http
  GET /tickers/news/latest?limit
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `limit`      | `int` | No. of news fetched |


