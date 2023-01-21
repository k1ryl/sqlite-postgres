## Application for migrating data from SQLite to PostgreSQL

To start app:
```
docker-compose up
```

HTTP Migration API:
```
curl --location --request POST 'http://localhost:8080/migrate' \
--form 'files=@"./test.db"' \
--form 'files=@"<path to SQLite file>"'
```
It's possible to add multiple files to `files` section of `multipart/form-data`. In that case files will be processed sequentially.

You can use `test.db` as SQLite DB example. This DB contains 10 records per table.

Temporary SQLite files are stored in `./sqlite` folder. Files are deleted after processing.
