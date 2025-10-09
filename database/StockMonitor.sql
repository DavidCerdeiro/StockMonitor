CREATE TABLE IF NOT EXISTS "stock" (
    "Id" serial PRIMARY KEY,
    "Symbol" varchar(8) NOT NULL UNIQUE,
    "CompanyName" varchar(100) NOT NULL,
    "LastUpdated" timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS "stock_price" (
  "Id" serial PRIMARY KEY,
  "Id_Stock" int NOT NULL,
  "Price" decimal(10, 2) NOT NULL,
  "LastPrice" decimal(10, 2) NOT NULL,
  "PercentChange" decimal(5, 2) NOT NULL,
  "Timestamp" timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT "RS_StockPrice__Id_Stock" FOREIGN KEY ("Id_Stock") REFERENCES "stock"("Id") ON DELETE CASCADE
);
