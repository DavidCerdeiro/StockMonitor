CREATE TABLE IF NOT EXISTS "company" (
    "Id" serial PRIMARY KEY,
    "Name" varchar(100) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS "symbol" (
    "Id" serial PRIMARY KEY,
    "Id_Company" int NOT NULL,
    "Name" varchar(8) NOT NULL UNIQUE,
    "LastUpdated" timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT "RS_Symbol__Id_Company" FOREIGN KEY ("Id_Company") REFERENCES "company"("Id") ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS "stock_price" (
  "Id" serial PRIMARY KEY,
  "Id_Symbol" int NOT NULL,
  "Price" decimal(10, 2) NOT NULL,
  "LastPrice" decimal(10, 2) NOT NULL,
  "PercentChange" decimal(5, 2) NOT NULL,
  "Date" timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT "RS_StockPrice__Id_Symbol" FOREIGN KEY ("Id_Symbol") REFERENCES "symbol"("Id") ON DELETE CASCADE
);

CREATE INDEX IX_StockPrice__Id_Symbol ON "stock_price"("Id_Symbol");

INSERT INTO "company" ("Name") VALUES
('Apple Inc.'),
('Alphabet Inc.'),
('Microsoft Corporation'),
('Amazon.com, Inc.'),
('NVIDIA Corporation'),
('Tesla, Inc.'),
('Meta Platforms, Inc.'),
('JPMorgan Chase & Co.'),
('Visa Inc.'),
('Johnson & Johnson');

INSERT INTO "symbol" ("Id_Company", "Name") VALUES
(1, 'AAPL'),
(2, 'GOOGL'),
(3, 'MSFT'),
(4, 'AMZN'),
(5, 'NVDA'),
(6, 'TSLA'),
(7, 'META'),
(8, 'JPM'),
(9, 'V'),
(10, 'JNJ');
