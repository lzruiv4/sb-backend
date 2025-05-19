## WIP
    Make the authentication by spring security.

##### Easy to run with docker compose
Make sure you are in the root directory of the project. 
You can use the `ls` command to check whether there is `docker-compose.yml` currently.

P.S. Please make sure you have docker. And use the following command. 
👇👇👇👇👇
```
docker compse up -d
```
Once the container is created successfully, 
you can use the following link to see if you can access swaager doc.
##### Swagger UI
```
http://localhost:9090/swagger-ui/index.html
```

### Springboot backend for frontend projects
    1. Angular frontend 👉 https://github.com/lzruiv4/angular_study
    2. VUE frontend 👉 https://github.com/lzruiv4/vue3_study

##### Database
Now have 3 options.
1. H2 
   - The current data is stored in h2 and saved in ```resources/db/database.mv.db```
2. Postgresql from docker
   - You can give this in console.
    ~~~
    docker run -d \
    --name dev-postgres \
    -e POSTGRES_USER=sa \
    -e POSTGRES_DB=sb_db \
    -p 9999:5432 \
    postgres:15
    ~~~
3. Postgresql in local
