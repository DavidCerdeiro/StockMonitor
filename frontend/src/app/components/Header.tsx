import { useTranslation } from "react-i18next";

const Header = () => {
  // 1. Obtenemos no solo `t`, sino también el objeto `i18n` completo.
  // `i18n.language` nos dirá el idioma actual ('es' o 'en').
  // `i18n.changeLanguage()` nos permitirá cambiarlo.
  const { t, i18n } = useTranslation();

  // 2. Creamos una función para cambiar el idioma.
  const handleChangeLanguage = (lang: 'es' | 'en') => {
    i18n.changeLanguage(lang);
  };

  return (
    <header className="header">
      <div className="container header-content">
        <h1 className="header-title">{t("header.title")}</h1>
        <nav className="nav">
          <a href="#dashboard-section" className="nav-link">{t("header.dashboard")}</a>
          <a href="#footer" className="nav-link">{t("header.contact")}</a>
        
          {/* Botón para cambiar el idioma */}
          <div className="lang-switcher">
            <button
              className={`lang-button ${i18n.language === 'es' ? 'lang-button-active' : ''}`}
              onClick={() => handleChangeLanguage('es')}
            >
              ES
            </button>
            <span className="lang-separator">|</span>
            <button
              className={`lang-button ${i18n.language === 'en' ? 'lang-button-active' : ''}`}
              onClick={() => handleChangeLanguage('en')}
            >
              EN
            </button>
          </div>
        </nav>
      </div>
    </header>
  );
}

export default Header;
