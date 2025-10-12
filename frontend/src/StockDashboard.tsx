import { useState, useMemo } from 'react';
import { Search, Wifi, WifiOff } from 'lucide-react';

import { useWebSocket } from './app/hooks/useWebSocket';
import Footer from './app/components/Footer';
import Header from './app/components/Header';
import HeroSection from './app/components/HeroSection';
import Badge from './app/components/Badge';
import QuoteRow from './app/components/QuoteRow';
import type { RealTimeQuote } from './app/schemas/RealTimeQuote';
import type { DisplayQuote } from './app/schemas/DisplayQoute';
import { useTranslation } from 'react-i18next';

const StockDashboard = () => {
  const { t } = useTranslation();
  const { quotes, isConnected } = useWebSocket('/topic/quotes');
  const [searchTerm, setSearchTerm] = useState('');

  // Filtrar las cotizaciones según el término de búsqueda
  // Si no hay término de búsqueda, mostrar todas
  const filteredQuotes = useMemo(() => {
    const enhancedQuotes: DisplayQuote[] = (quotes as unknown as RealTimeQuote[]).map(q => ({
      ...q,
      currentPrice: q.c,
      percentChange: q.dp,
      previousClosePrice: q.pc,
      companyName: q.companyName,
    }));

    if (!searchTerm) return enhancedQuotes;
    
    const search = searchTerm.toLowerCase();
    return enhancedQuotes.filter(
      quote =>
        quote.symbol.toLowerCase().includes(search) ||
        quote.companyName.toLowerCase().includes(search)
    );
  }, [quotes, searchTerm]);

  return (
    <div>
      <Header />

      <main>
        <HeroSection />

        {/* Dashboard Section */}
        <section id="dashboard-section" className="container">
          <div className="dashboard-header">
            <div>
              <h2 className="dashboard-title">{t('dashboard.title')}</h2>
              <p className="dashboard-subtitle">
                {t('dashboard.subtitle')}
              </p>
            </div>
            <Badge variant={isConnected ? "default" : "destructive"}>
              {isConnected ? <Wifi style={{ width: '1rem', height: '1rem', marginRight: '0.5rem' }} /> : <WifiOff style={{ width: '1rem', height: '1rem', marginRight: '0.5rem' }} />}
              {isConnected ? t('dashboard.connected') : t('dashboard.disconnected')}
            </Badge>
          </div>

          <div className="search-wrapper">
            <Search className="search-icon" />
            <input
              type="text"
              placeholder={t('dashboard.searchPlaceholder')}
              className="search-input"
              value={searchTerm}
              onChange={(e) => setSearchTerm(e.target.value)}
            />
          </div>

          <div className="table-container">
            <div style={{ overflowX: 'auto' }}>
              <table className="table">
                <thead className="table-head">
                  <tr>
                    <th className="th">{t('dashboard.table.columns.symbol')}</th>
                    <th className="th">{t('dashboard.table.columns.company')}</th>
                    <th className="th th-right">{t('dashboard.table.columns.price')}</th>
                    <th className="th th-right">{t('dashboard.table.columns.change')}</th>
                    <th className="th th-right">{t('dashboard.table.columns.percentChange')}</th>
                    <th className="th th-right">{t('dashboard.table.columns.lastUpdate')}</th>
                  </tr>
                </thead>
                <tbody className="table-body">
                  {filteredQuotes.length > 0 ? (
                    filteredQuotes.map(quote => <QuoteRow key={quote.symbol} quote={quote} />)
                  ) : (
                    <tr>
                      <td colSpan={6} style={{ padding: '4rem 1.5rem', textAlign: 'center' }}>
                        <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'center', gap: '0.75rem' }}>
                          <Search style={{ width: '3rem', height: '3rem', color: '#cbd5e1' }} />
                          <p style={{ color: '#64748b', fontSize: '1.125rem', fontWeight: '500' }}>
                            {searchTerm 
                              ? t('dashboard.noResults', { term: searchTerm })
                              : t('dashboard.table.waiting')}
                          </p>
                        </div>
                      </td>
                    </tr>
                  )}
                </tbody>
              </table>
            </div>
            <div className="table-caption">
              <p>{t('dashboard.table.caption')}</p>
            </div>
          </div>
        </section>
      </main>

      <Footer />
    </div>
  );
};

export default StockDashboard;

