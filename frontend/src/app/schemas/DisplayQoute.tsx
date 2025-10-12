import { z } from 'zod';

export const DisplayQuoteSchema = z.object({
  symbol: z.string(),
  companyName: z.string(),
  currentPrice: z.number(),
  percentChange: z.number(),
  previousClosePrice: z.number(),
  receivedAt: z.date().optional(),
});

export type DisplayQuote = z.infer<typeof DisplayQuoteSchema>;