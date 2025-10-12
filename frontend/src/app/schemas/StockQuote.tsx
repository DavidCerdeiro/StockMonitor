import { z } from 'zod';

export const StockQuoteSchema = z.object({
  symbol: z.string(),
  currentPrice: z.number(),
  percentChange: z.number(),
  previousClosePrice: z.number(),
  receivedAt: z.date().optional(),
});

export type StockQuote = z.infer<typeof StockQuoteSchema>;