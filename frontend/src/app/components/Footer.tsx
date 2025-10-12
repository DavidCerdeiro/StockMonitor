import { Github, Linkedin, Mail } from "lucide-react";
import { useTranslation } from "react-i18next";

const Footer = () => {
    const { t } = useTranslation();
    const socialLinks = [
        { href: "mailto:davidcergall22@gmail.com", icon: <Mail />, label: "Email" },
        { href: "https://www.linkedin.com/in/david-jes%C3%BAs-cerdeiro-gallardo-0b1123284/", icon: <Linkedin />, label: "LinkedIn" },
        { href: "https://github.com/DavidCerdeiro", icon: <Github />, label: "GitHub" },
    ];
    return (
        <>
            <footer id="footer"className="footer">
                <div className="container footer-content">
                <p className="footer-copyright">
                    © {new Date().getFullYear()} {t("footer.author")} {t("footer.rights")}
                </p>
                <div className="social-links">
                    {socialLinks.map(link => (
                        <a 
                        key={link.label}
                        href={link.href} 
                        target="_blank" 
                        rel="noopener noreferrer" 
                        aria-label={link.label}
                        className="social-button"
                        >
                        {link.icon}
                        </a>
                    ))}
                </div>
                </div>
            </footer>
        </>
    );
}

export default Footer;