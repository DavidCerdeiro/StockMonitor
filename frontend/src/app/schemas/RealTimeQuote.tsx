import { z } from 'zod';

export const RealTimeQuoteSchema = z.object({
  symbol: z.string(),
  companyName: z.string(),
  c: z.number(),
  dp: z.number(),
  pc: z.number(),
  receivedAt: z.date().optional(),
});

export type RealTimeQuote = z.infer<typeof RealTimeQuoteSchema>;