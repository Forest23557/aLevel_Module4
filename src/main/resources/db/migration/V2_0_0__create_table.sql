CREATE TABLE IF NOT EXISTS "details" (
"detail_id" VARCHAR(36) NOT NULL PRIMARY KEY,
"beginning_time" TIMESTAMP WITHOUT TIME ZONE,
"ending_time" TIMESTAMP WITHOUT TIME ZONE,
"spent_time" INTEGER,
"spent_fuel" INTEGER,
"mined_fuel" INTEGER,
"broken_chips" INTEGER
);