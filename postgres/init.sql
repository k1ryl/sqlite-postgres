CREATE TABLE a
(
    uuid        UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    sqlite_uuid VARCHAR NOT NULL,
    title       VARCHAR NOT NULL,
    number      BIGINT  NOT NULL
);

CREATE TABLE b
(
    id          SERIAL PRIMARY KEY,
    sqlite_uuid VARCHAR NOT NULL,
    subject     VARCHAR NOT NULL
);