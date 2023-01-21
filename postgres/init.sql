CREATE TABLE a
(
    uuid   UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    title  VARCHAR NOT NULL,
    number BIGINT  NOT NULL
);

CREATE TABLE b
(
    id      SERIAL PRIMARY KEY,
    subject VARCHAR NOT NULL
);