import { CheckCircle } from 'lucide-react';
import { useTranslation } from "react-i18next";
const HeroSection = () => {
    const { t } = useTranslation();

    return (
        <>
            <section className="hero-section">
                <div className="container hero-content">
                    <h1 className="hero-title">{t("hero.title")}</h1>
                    <p className="hero-subtitle">
                    {t("hero.subtitle")}
                    </p>
                    <p className="hero-text">
                    {t("hero.explanation")} <br />
                    </p>
                    <a href="#dashboard-section" className="cta-button">
                    <CheckCircle style={{ width: '1.25rem', height: '1.25rem' }} />
                    {t("hero.view")}
                    </a>
                </div>
        </section>
        </>
    );
};

export default HeroSection;