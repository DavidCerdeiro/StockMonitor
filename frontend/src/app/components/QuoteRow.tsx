import Badge from "./Badge";
import type { DisplayQuote } from "../schemas/DisplayQoute";

const QuoteRow = ({ quote }: { quote: DisplayQuote }) => {
  const priceColorClass = quote.percentChange > 0 ? 'price-up' : quote.percentChange < 0 ? 'price-down' : 'price-same';
  const badgeVariant = quote.percentChange > 0 ? 'default' : quote.percentChange < 0 ? 'destructive' : 'secondary';
  const updateTime = quote.receivedAt ? quote.receivedAt.toLocaleTimeString('es-ES') : 'N/A';

  return (
    <tr className="table-row">
      <td className="td td-symbol">{quote.symbol}</td>
      <td className="td td-company">{quote.companyName}</td>
      <td className={`td td-price td-right ${priceColorClass}`}>
        ${quote.currentPrice.toFixed(2)}
      </td>
      <td className="td td-right">
        <Badge variant={badgeVariant}>
          {quote.percentChange > 0 ? '+' : ''}{quote.percentChange.toFixed(2)}%
        </Badge>
      </td>
      <td className="td td-last-price td-right">
        ${quote.previousClosePrice.toFixed(2)}
      </td>
      <td className="td td-time td-right">{updateTime}</td>
    </tr>
  );
};

export default QuoteRow;