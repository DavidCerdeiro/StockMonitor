const Badge = ({ children, variant = 'default' }: { children: React.ReactNode; variant?: 'default' | 'destructive' | 'secondary'; }) => {
  const variantClasses = {
    default: 'badge-default',
    destructive: 'badge-destructive',
    secondary: 'badge-secondary'
  };
  
  return (
    <span className={`badge ${variantClasses[variant]}`}>
      {children}
    </span>
  );
};

export default Badge;