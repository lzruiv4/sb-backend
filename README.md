## Next version

##### Increase modularization of the project

## Quickly start

#### 1. Easy to run with docker compose

Make sure you are in the root directory of the project.
You can use the `ls` command to check whether there is `docker-compose.yml` currently.

###### P.S. Please make sure you have docker. And use the following command.

👇🏻👇🏻👇🏻

```bash
docker compose up -d
```

Once the container is created successfully,

#### 2. Setup database

Now have 3 options.

1. H2

   - The current data is stored in h2 and saved in `resources/db/database.mv.db`

2. Postgresql from docker (RECOMMEND)

   - You can give this in console. 👇🏻👇🏻👇🏻

   ```bash
   docker run -d \
   --name dev-postgres \
   -e POSTGRES_USER=sa \
   -e POSTGRES_PASSWORD=123 \
   -e POSTGRES_DB=sb \
   -p 9999:5432 \
   postgres:15
   ```

3. Postgresql in local

## Swagger UI

You can use the following link to see if you can access swaager doc. 👇🏻👇🏻👇🏻

```link
http://localhost:9090/swagger-ui/index.html
```

<img src="markdown/swaager.png" width="100%" height="auto"/>

## Springboot backend for frontend projects

1. Angular frontend 👇🏻👇🏻👇🏻 client, users can play lottery games

```link
https://github.com/lzruiv4/sb-client-lotto
```

2. VUE frontend 👇🏻👇🏻👇🏻 for the manager, one can modify user information, e.g. update user information.

```link
https://github.com/lzruiv4/sb-management
```
